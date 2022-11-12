package models;

import java.util.HashSet;
import java.util.Set;

/**
 * Класс, описывающий узел генеалогичского дерева.
 */
public class GenealogicalTreeNode {

    /**
     * Идетификатор узла.
     */
    private final int id;

    /**
     * Объект генеалогического дерева, к которому принадлежит узел.
     */
    private final GenealogicalTree genealogicalTree;

    /**
     * Объект человека, хранящийся в узле.
     */
    private final Person person;

    /**
     * Узел, хранящий объект отца.
     */
    private GenealogicalTreeNode father;

    /**
     * Узел, хранящий объект матери.
     */
    private GenealogicalTreeNode mother;

    /**
     * Сэт, хранящий узлы с объектами детей.
     */
    private final Set<GenealogicalTreeNode> children;

    /**
     * Инициализация узла.
     * @param id Идентификатор узла.
     * @param genealogicalTree Объект генеалогического дерева, к которому относится узел.
     * @param person Объект человека, хранящийся в узле.
     */
    GenealogicalTreeNode(int id, GenealogicalTree genealogicalTree, Person person) {
        this.id = id;
        this.genealogicalTree = genealogicalTree;
        this.person = person;
        children = new HashSet<>();
    }

    /**
     * Инициализация узла.
     * @param id Идентификатор узла.
     * @param genealogicalTree Объект генеалогического дерева, к которому относится узел.
     * @param person Объект человека, хранящийся в узле.
     * @param father Узел, хранящий объект отца.
     * @param mother Узел, хранящий объект матери.
     */
    GenealogicalTreeNode(int id, GenealogicalTree genealogicalTree, Person person,
                         GenealogicalTreeNode father, GenealogicalTreeNode mother) {

        this(id, genealogicalTree, person);
        this.father = father;
        this.mother = mother;
    }

    /**
     * Метод, возвращающий идентификатор узла.
     * @return Идентифкатор узла.
     */
    public int getId() {
        return id;
    }

    /**
     * Метод, возвращающий объект генеалогического дерева, к которому относится узел.
     * @return Объект генеалогического дерева, к которому относится узел.
     */
    public GenealogicalTree getGenealogicalTree() {
        return genealogicalTree;
    }

    /**
     * Метод, возвращающий объект человека, хранящегося в узле.
     * @return Объект человека, хранящегося в узле.
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Метод, возвращающий узел, хранящий объект отца.
     * @return Узел, хранящий объект отца.
     */
    public GenealogicalTreeNode getFather() {
        return father;
    }

    /**
     * Метод, возвращающий узел, хранящий объект матери.
     * @return Узел, хранящий объект матери.
     */
    public GenealogicalTreeNode getMother() {
        return mother;
    }

    /**
     * Метод добавления объекта ребенка.
     * @param child Узел, хранящий объект ребенка.
     */
    void addChild(GenealogicalTreeNode child) {
        children.add(child);
    }

    /**
     * Метод, возвращающий массив узлов, хранящих объекты детей.
     * @return Массив узлов, хранящих объекты детей.
     */
    public GenealogicalTreeNode[] getChildren() {
        GenealogicalTreeNode[] children = new GenealogicalTreeNode[this.children.size()];
        int i = 0;
        for (GenealogicalTreeNode node: this.children)
            children[i++] = node;
        return children;
    }

    /**
     * Переопределения метода перобразования объекта узла в строку.
     * @return Строка, содержащая имя объекта человека, хранящегося в узле, и идентификатор узла.
     */
    @Override
    public String toString() {
        return person.toString() + '(' + id + ')';
    }
}
