package controllers;

import models.*;
import repositories.IGenealogicalTreeRepository;

import java.util.List;

/**
 * Класс, описывающий контроллер для взаимодействия с генеалогическими деревьями.
 */
public class GenealogicalTreeController {

    /**
     * Объект репозитория, хранящего экземпляры генеалогичских деревьев.
     */
    private final IGenealogicalTreeRepository genealogicalTreeRepository;

    /**
     * Инициализация объекта контроллера.
     * @param genealogicalTreeRepository Объект репозитория.
     */
    public GenealogicalTreeController(IGenealogicalTreeRepository genealogicalTreeRepository) {
        if (genealogicalTreeRepository == null)
            throw new IllegalArgumentException("Переданный параметр неинициализтрован.");

        this.genealogicalTreeRepository = genealogicalTreeRepository;
    }

    /**
     * Метод добавления объекта генеалогического дерева.
     * @param genealogicalTree Объект генеалогического дерева.
     */
    public void add(GenealogicalTree genealogicalTree) {
        if (genealogicalTree == null)
            return;

        genealogicalTreeRepository.add(genealogicalTree);
    }

    /**
     * Метод получения объекта генеалогическоо дерева.
     * @param id Идентификатор дерева.
     * @return Объект геналогического дерева.
     */
    public GenealogicalTree get(int id) {
        return genealogicalTreeRepository.get(id);
    }

    /**
     * Метод получения списка всех генеалогических деревьев.
     * @return Список всех генеалогических деревьев.
     */
    public List<GenealogicalTree> getAll() {
        return genealogicalTreeRepository.getAll();
    }

    /**
     * Метод удаления генеалогического дерева.
     * @param id Идентификатор генеалогического дерева.
     */
    public void remove(int id) {
        genealogicalTreeRepository.remove(id);
    }

    /**
     * Метод добавления человека в генеалогическое дерево.
     * @param genealogicalTree Объект генеалогического дерева.
     * @param person Объект человека для добавления.
     * @param father Узел дерева, содержащий объект отца.
     * @param mother Узел дерева, содержащий объект матери.
     * @return Узел дерева, содержащий объект добавленного человека.
     * @throws IllegalArgumentException Возбуждается, если параметр не инициализирован.
     */
    public GenealogicalTreeNode addPerson(GenealogicalTree genealogicalTree, Person person,
                                          GenealogicalTreeNode father, GenealogicalTreeNode mother)
            throws IllegalArgumentException {

        if (genealogicalTree == null)
            throw new IllegalArgumentException("Переданный параметр неинициализтрован.");

        return genealogicalTree.addPerson(person, father, mother);
    }

    /**
     * Метод добавления человека в генеалогическое дерево.
     * @param genealogicalTree Объект генеалогического дерева.
     * @param person Объект человека для добавления.
     * @param father Узел дерева, содержащий объект отца.
     * @param mother Объект матери, с которым будет создан новый узел.
     * @return Узел дерева, содержащий объект добавленного человека.
     * @throws IllegalArgumentException Возбуждается, если параметр не инициализирован.
     */
    public GenealogicalTreeNode addPerson(GenealogicalTree genealogicalTree, Person person,
                                          GenealogicalTreeNode father, Female mother)
            throws IllegalArgumentException {

        if (genealogicalTree == null)
            throw new IllegalArgumentException("Переданный параметр неинициализтрован.");

        return genealogicalTree.addPerson(person, father, mother);
    }

    /**
     * Метод добавления человека в генеалогическое дерево.
     * @param genealogicalTree Объект генеалогического дерева.
     * @param person Объект человека для добавления.
     * @param father Объект отца, с которым будет создан новый узел.
     * @param mother Узел дерева, содержащий объект матери.
     * @return Узел дерева, содержащий объект добавленного человека.
     * @throws IllegalArgumentException Возбуждается, если параметр не инициализирован.
     */
    public GenealogicalTreeNode addPerson(GenealogicalTree genealogicalTree, Person person,
                                          Male father, GenealogicalTreeNode mother)
            throws IllegalArgumentException {

        if (genealogicalTree == null)
            throw new IllegalArgumentException("Переданный параметр неинициализтрован.");

        return genealogicalTree.addPerson(person, father, mother);
    }

    /**
     * Метод получения узла генеалогического дерева.
     * @param genealogicalTree Объект генеаоргического дерева.
     * @param id Идентификатор узда.
     * @return Узел генеалогического дерева.
     * @throws IllegalArgumentException Возбуждается, если параметр не инициализирован.
     */
    public GenealogicalTreeNode getPerson(GenealogicalTree genealogicalTree, int id)
            throws IllegalArgumentException {

        if (genealogicalTree == null)
            throw new IllegalArgumentException("Переданный параметр неинициализтрован.");

        return genealogicalTree.findNode(id);
    }

    /**
     * Метод, возвращающий строку со всеми узлами.
     * @param genealogicalTree Объект генеалогического дерева.
     * @return Строка со всеми узлами дерева.
     * @throws IllegalArgumentException Возбуждается, если параметр не инициализирован.
     */
    public String getAllTree(GenealogicalTree genealogicalTree) throws IllegalArgumentException {
        if (genealogicalTree == null)
            throw new IllegalArgumentException("Переданный параметр неинициализтрован.");

        StringBuilder builder = new StringBuilder();
        dfsGenealogicalTree(genealogicalTree.getHead(), builder, "", true);
        return builder.toString();
    }

    /**
     * Метод обхода дерева в глубину для записи в строку.
     * @param person Текущий узел.
     * @param builder Объект StringBuilder для записи узлов.
     * @param indent Отступ.
     * @param last Является ли узел последним в текущем списке.
     */
    private void dfsGenealogicalTree(GenealogicalTreeNode person, StringBuilder builder, String indent, boolean last) {
        builder.append(indent)
                .append(last ? '└' : '├')
                .append('─')
                .append(person);

        GenealogicalTreeNode[] children = person.getChildren();
        if (children.length == 0)
            return;

        builder.append(" & ");

        GenealogicalTreeNode spouse = person.getPerson().getGender() == Gender.male
                ? children[0].getMother() : children[0].getFather();
        builder.append(spouse);

        for (GenealogicalTreeNode child: children) {
            builder.append('\n');
            dfsGenealogicalTree(child, builder, indent + (last ? "  " : "│ "), child == children[children.length - 1]);
        }
    }
}
