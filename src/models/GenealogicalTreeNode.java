package models;

import models.abstractions.IGendered;
import models.abstractions.ITreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * Класс, описывающий узел генеалогичского дерева.
 * @param <T> Тип объекта, хранящегося в узле.
 */
public class GenealogicalTreeNode<T extends IGendered> implements ITreeNode<T> {

    /**
     * Идетификатор узла.
     */
    private final int id;

    /**
     * Объект генеалогического дерева, к которому принадлежит узел.
     */
    private final GenealogicalTree<T> genealogicalTree;

    /**
     * Объект, хранящийся в узле.
     */
    private final T value;

    /**
     * Узел, хранящий объект отца.
     */
    private GenealogicalTreeNode<T> father;

    /**
     * Узел, хранящий объект матери.
     */
    private GenealogicalTreeNode<T> mother;

    /**
     * Сэт, хранящий узлы с объектами детей.
     */
    private final Set<GenealogicalTreeNode<T>> children;

    /**
     * Инициализация узла.
     * @param id Идентификатор узла.
     * @param genealogicalTree Объект генеалогического дерева, к которому относится узел.
     * @param value Объект, хранящийся в узле.
     */
    GenealogicalTreeNode(int id, GenealogicalTree<T> genealogicalTree, T value) {
        this.id = id;
        this.genealogicalTree = genealogicalTree;
        this.value = value;
        children = new HashSet<>();
    }

    /**
     * Инициализация узла.
     * @param id Идентификатор узла.
     * @param genealogicalTree Объект генеалогического дерева, к которому относится узел.
     * @param value Объект, хранящийся в узле.
     * @param father Узел, хранящий объект отца.
     * @param mother Узел, хранящий объект матери.
     */
    GenealogicalTreeNode(int id, GenealogicalTree<T> genealogicalTree, T value,
                         GenealogicalTreeNode<T> father, GenealogicalTreeNode<T> mother) {

        this(id, genealogicalTree, value);
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
    public GenealogicalTree<T> getTree() {
        return genealogicalTree;
    }

    /**
     * Метод, возвращающий объект, хранящийся в узле.
     * @return Объект, хранящийся в узле.
     */
    public T getValue() {
        return value;
    }

    /**
     * Метод, возвращающий узел, хранящий объект отца.
     * @return Узел, хранящий объект отца.
     */
    public GenealogicalTreeNode<T> getFather() {
        return getLeft();
    }

    /**
     * Метод, возвращающий узел, хранящий объект матери.
     * @return Узел, хранящий объект матери.
     */
    public GenealogicalTreeNode<T> getMother() {
        return getRight();
    }

    /**
     * Метод, возвращающий левый узел.
     * @return Левый узел.
     */
    public GenealogicalTreeNode<T> getLeft() {
        return father;
    }


    /**
     * Метод, возвращающий правый узел.
     * @return Правый узел.
     */
    public GenealogicalTreeNode<T> getRight() {
        return mother;
    }

    /**
     * Метод добавления объекта ребенка.
     * @param child Узел, хранящий объект ребенка.
     */
    void addChild(GenealogicalTreeNode<T> child) {
        children.add(child);
    }

    /**
     * Метод, возвращающий массив узлов, хранящих объекты детей.
     * @return Массив узлов, хранящих объекты детей.
     */
    public GenealogicalTreeNode<T>[] getChildren() {
        GenealogicalTreeNode<T>[] children = new GenealogicalTreeNode[this.children.size()];
        int i = 0;
        for (GenealogicalTreeNode<T> node: this.children)
            children[i++] = node;
        return children;
    }

    /**
     * Переопределения метода перобразования объекта узла в строку.
     * @return Строка, содержащая объект, хранящийся в узле, и идентификатор узла.
     */
    @Override
    public String toString() {
        return value.toString() + '(' + id + ')';
    }
}
