package models;

/**
 * Класс, описывающий генеалогическое дерево.
 */
public class GenealogicalTree implements INamed {

    /**
     * Название генеалогического дерева.
     */
    private final String name;

    /**
     * Счетчик иднтфикаторов узлов.
     */
    private int idCounter;

    /**
     * Корневой узел дерева.
     */
    private final GenealogicalTreeNode head;

    /**
     * Инициализация объекта генеалогического дерева.
     * @param name Название генеалогического дерева.
     * @param head Объект человека для хранения в корневом узле дерева.
     * @throws IllegalArgumentException Возбуждается, если передан неинициализированный параметр.
     */
    public GenealogicalTree(String name, Person head) throws IllegalArgumentException  {
        if (name == null || head == null)
            throw new IllegalArgumentException("Передан неинициализированный объект.");

        this.name = name;
        this.head = new GenealogicalTreeNode(++idCounter, this, head);
    }

    /**
     * Метод, возвращающий название дерева.
     * @return Название дерева.
     */
    public String getName() {
        return name;
    }

    /**
     * Метод, возвращающий корневой узел дереваю
     * @return Корневой узел дерева.
     */
    public GenealogicalTreeNode getHead() {
        return head;
    }

    /**
     * Метод добавления человека в генеалогическое дерево.
     * @param person Объект человека для добавления.
     * @param father Узел дерева, содержащий объект отца.
     * @param mother Узел дерева, содержащий объект матери.
     * @return Узел дерева, содержащий объект добавленного человека.
     * @throws IllegalArgumentException Возбуждается, если параметр не инициализирован.
     */
    public GenealogicalTreeNode addPerson(Person person, GenealogicalTreeNode father, GenealogicalTreeNode mother)
            throws IllegalArgumentException {

        if (person == null || father == null || mother == null)
            throw new IllegalArgumentException("Передан неинициализированный объект.");

        if (father.getGenealogicalTree() != this || mother.getGenealogicalTree() != this)
            throw new IllegalArgumentException("Переданный узел не содержится в этом экземпляре генеалогического дерева.");

        if (father.getPerson().getGender() != Gender.male)
            throw new IllegalArgumentException("Невалидный пол отца.");

        if (mother.getPerson().getGender() != Gender.female)
            throw new IllegalArgumentException("Невалидный пол матери.");

        GenealogicalTreeNode child = new GenealogicalTreeNode(++idCounter, this, person, father, mother);
        father.addChild(child);
        mother.addChild(child);
        return child;
    }

    /**
     * Метод добавления человека в генеалогическое дерево.
     * @param person Объект человека для добавления.
     * @param father Узел дерева, содержащий объект отца.
     * @param mother Объект матери, с которым будет создан новый узел.
     * @return Узел дерева, содержащий объект добавленного человека.
     * @throws IllegalArgumentException Возбуждается, если параметр не инициализирован.
     */
    public GenealogicalTreeNode addPerson(Person person, GenealogicalTreeNode father, Female mother)
            throws IllegalArgumentException {

        if (mother == null)
            throw new IllegalArgumentException("Передан неинициализированный объект.");

        GenealogicalTreeNode motherNode = new GenealogicalTreeNode(++idCounter, this, mother);
        return addPerson(person, father, motherNode);
    }

    /**
     * Метод добавления человека в генеалогическое дерево.
     * @param person Объект человека для добавления.
     * @param father Объект отца, с которым будет создан новый узел.
     * @param mother Узел дерева, содержащий объект матери.
     * @return Узел дерева, содержащий объект добавленного человека.
     * @throws IllegalArgumentException Возбуждается, если параметр не инициализирован.
     */
    public GenealogicalTreeNode addPerson(Person person, Male father, GenealogicalTreeNode mother)
            throws IllegalArgumentException {

        if (father == null)
            throw new IllegalArgumentException("Передан неинициализированный объект.");

        GenealogicalTreeNode fatherNode = new GenealogicalTreeNode(++idCounter, this, father);
        return addPerson(person, fatherNode, mother);
    }

    /**
     * Метод получения узла генеалогического дерева.
     * @param id Идентификатор узда.
     * @return Узел генеалогического дерева.
     * @throws IllegalArgumentException Возбуждается, если параметр не инициализирован.
     */
    public GenealogicalTreeNode findNode(int id) {
        if (id <= 0 || id > idCounter)
            return null;

        return findNode(id, head);
    }

    /**
     * Рекурсивный метод поиска узла.
     * @param id Идентификатор узла.
     * @param node Текущий узел.
     * @return Результат поиска.
     */
    private GenealogicalTreeNode findNode(int id, GenealogicalTreeNode node) {
        if (node.getId() == id)
            return node;

        GenealogicalTreeNode[] children = node.getChildren();
        if (children.length == 0)
            return null;

        for (GenealogicalTreeNode child: children) {

            GenealogicalTreeNode secondParent = node.getPerson().getGender() == Gender.male
                    ? child.getMother() : child.getFather();

            if (secondParent.getId() == id)
                return secondParent;

            GenealogicalTreeNode result = findNode(id, child);
            if (result != null)
                return result;
        }

        return null;
    }

}
