package controllers;

import models.*;
import models.abstractions.IGendered;
import repositories.IGenealogicalTreeRepository;

import java.util.List;

/**
 * Класс, описывающий контроллер для взаимодействия с генеалогическими деревьями.
 * @param <T> Тип объектов, хранящихся в генеалогических деревьях.
 */
public class GenealogicalTreeController<T extends IGendered> {

    /**
     * Объект репозитория, хранящего экземпляры генеалогичских деревьев.
     */
    private final IGenealogicalTreeRepository<T> genealogicalTreeRepository;

    /**
     * Инициализация объекта контроллера.
     * @param genealogicalTreeRepository Объект репозитория.
     */
    public GenealogicalTreeController(IGenealogicalTreeRepository<T> genealogicalTreeRepository) {
        if (genealogicalTreeRepository == null)
            throw new IllegalArgumentException("Переданный параметр неинициализтрован.");

        this.genealogicalTreeRepository = genealogicalTreeRepository;
    }

    /**
     * Метод добавления объекта генеалогического дерева.
     * @param genealogicalTree Объект генеалогического дерева.
     */
    public void add(GenealogicalTree<T> genealogicalTree) {
        if (genealogicalTree == null)
            return;

        genealogicalTreeRepository.add(genealogicalTree);
    }

    /**
     * Метод получения объекта генеалогическоо дерева.
     * @param id Идентификатор дерева.
     * @return Объект геналогического дерева.
     */
    public GenealogicalTree<T> get(int id) {
        return genealogicalTreeRepository.get(id);
    }

    /**
     * Метод получения списка всех генеалогических деревьев.
     * @return Список всех генеалогических деревьев.
     */
    public List<GenealogicalTree<T>> getAll() {
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
     * Метод добавления объекта в генеалогическое дерево.
     * @param genealogicalTree Объект генеалогического дерева.
     * @param value Объект для добавления.
     * @param father Узел дерева, содержащий объект отца.
     * @param mother Узел дерева, содержащий объект матери.
     * @return Узел дерева, содержащий добавленный объект.
     * @throws IllegalArgumentException Возбуждается, если параметр не инициализирован.
     */
    public GenealogicalTreeNode<T> add(GenealogicalTree<T> genealogicalTree, T value,
                                          GenealogicalTreeNode<T> father, GenealogicalTreeNode<T> mother)
            throws IllegalArgumentException {

        if (genealogicalTree == null)
            throw new IllegalArgumentException("Переданный параметр неинициализтрован.");

        return genealogicalTree.add(value, father, mother);
    }

    /**
     * Метод добавления объекта в генеалогическое дерево.
     * @param genealogicalTree Объект генеалогического дерева.
     * @param value Объект для добавления.
     * @param father Узел дерева, содержащий объект отца.
     * @param mother Объект матери, с которым будет создан новый узел.
     * @return Узел дерева, содержащий добавленный объект.
     * @throws IllegalArgumentException Возбуждается, если параметр не инициализирован.
     */
    public GenealogicalTreeNode<T> add(GenealogicalTree<T> genealogicalTree, T value,
                                          GenealogicalTreeNode<T> father, T mother)
            throws IllegalArgumentException {

        if (genealogicalTree == null)
            throw new IllegalArgumentException("Переданный параметр неинициализтрован.");

        return genealogicalTree.add(value, father, mother);
    }

    /**
     * Метод добавления объекта в генеалогическое дерево.
     * @param genealogicalTree Объект генеалогического дерева.
     * @param value Объект для добавления.
     * @param father Объект отца, с которым будет создан новый узел.
     * @param mother Узел дерева, содержащий объект матери.
     * @return Узел дерева, содержащий добавленный объект.
     * @throws IllegalArgumentException Возбуждается, если параметр не инициализирован.
     */
    public GenealogicalTreeNode<T> add(GenealogicalTree<T> genealogicalTree, T value,
                                          T father, GenealogicalTreeNode<T> mother)
            throws IllegalArgumentException {

        if (genealogicalTree == null)
            throw new IllegalArgumentException("Переданный параметр неинициализтрован.");

        return genealogicalTree.add(value, father, mother);
    }

    /**
     * Метод получения узла генеалогического дерева.
     * @param genealogicalTree Объект генеаоргического дерева.
     * @param id Идентификатор узда.
     * @return Узел генеалогического дерева.
     * @throws IllegalArgumentException Возбуждается, если параметр не инициализирован.
     */
    public GenealogicalTreeNode<T> get(GenealogicalTree<T> genealogicalTree, int id)
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
    public String getAllTree(GenealogicalTree<T> genealogicalTree) throws IllegalArgumentException {
        if (genealogicalTree == null)
            throw new IllegalArgumentException("Переданный параметр неинициализтрован.");

        StringBuilder builder = new StringBuilder();
        dfsGenealogicalTree(genealogicalTree.getHead(), builder, "", true);
        return builder.toString();
    }

    /**
     * Метод обхода дерева в глубину для записи в строку.
     * @param node Текущий узел.
     * @param builder Объект StringBuilder для записи узлов.
     * @param indent Отступ.
     * @param last Является ли узел последним в текущем списке.
     */
    private void dfsGenealogicalTree(GenealogicalTreeNode<T> node, StringBuilder builder, String indent, boolean last) {
        builder.append(indent)
                .append(last ? '└' : '├')
                .append('─')
                .append(node);

        GenealogicalTreeNode<T>[] children = node.getChildren();
        if (children.length == 0)
            return;

        builder.append(" & ");

        GenealogicalTreeNode<T> spouse = node.getValue().getGender() == Gender.male
                ? children[0].getMother() : children[0].getFather();
        builder.append(spouse);

        for (GenealogicalTreeNode<T> child: children) {
            builder.append('\n');
            dfsGenealogicalTree(child, builder, indent + (last ? "  " : "│ "), child == children[children.length - 1]);
        }
    }
}
