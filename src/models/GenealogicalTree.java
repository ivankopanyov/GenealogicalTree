package models;

import models.abstractions.IGendered;
import models.abstractions.INamed;
import models.abstractions.ITree;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Класс, описывающий генеалогическое дерево.
 * @param <T> Тип объектов, хранящихся в узлах генеалогического дерева.
 */
public class GenealogicalTree<T extends IGendered> implements INamed, ITree<T, GenealogicalTreeNode<T>> {

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
    private final GenealogicalTreeNode<T> head;

    /**
     * Инициализация объекта генеалогического дерева.
     * @param name Название генеалогического дерева.
     * @param head Объект для хранения в корневом узле дерева.
     * @throws IllegalArgumentException Возбуждается, если передан неинициализированный параметр.
     */
    public GenealogicalTree(String name, T head) throws IllegalArgumentException  {
        if (name == null || head == null)
            throw new IllegalArgumentException("Передан неинициализированный объект.");

        this.name = name;
        this.head = new GenealogicalTreeNode<>(++idCounter, this, head);
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
    public GenealogicalTreeNode<T> getHead() {
        return head;
    }

    /**
     * Метод добавления человека в генеалогическое дерево.
     * @param value Объект для добавления.
     * @param father Узел дерева, содержащий объект отца.
     * @param mother Узел дерева, содержащий объект матери.
     * @return Узел дерева, содержащий добавленный объект.
     * @throws IllegalArgumentException Возбуждается, если параметр не инициализирован.
     */
    public GenealogicalTreeNode<T> add(T value, GenealogicalTreeNode<T> father, GenealogicalTreeNode<T> mother)
            throws IllegalArgumentException {

        if (value == null || father == null || mother == null)
            throw new IllegalArgumentException("Передан неинициализированный объект.");

        if (father.getTree() != this || mother.getTree() != this)
            throw new IllegalArgumentException("Переданный узел не содержится в этом экземпляре генеалогического дерева.");

        if (father.getValue().getGender() != Gender.male)
            throw new IllegalArgumentException("Невалидный пол отца.");

        if (mother.getValue().getGender() != Gender.female)
            throw new IllegalArgumentException("Невалидный пол матери.");

        GenealogicalTreeNode<T> child = new GenealogicalTreeNode<>(++idCounter, this, value, father, mother);
        father.addChild(child);
        mother.addChild(child);
        return child;
    }

    /**
     * Метод добавления человека в генеалогическое дерево.
     * @param value Объект человека для добавления.
     * @param father Узел дерева, содержащий объект отца.
     * @param mother Объект матери, с которым будет создан новый узел.
     * @return Узел дерева, содержащий добавленный объект.
     * @throws IllegalArgumentException Возбуждается, если параметр не инициализирован.
     */
    public GenealogicalTreeNode<T> add(T value, GenealogicalTreeNode<T> father, T mother)
            throws IllegalArgumentException {

        if (mother == null)
            throw new IllegalArgumentException("Передан неинициализированный объект.");

        if (mother.getGender() != Gender.female)
            throw new IllegalArgumentException("Невалидный пол матери.");

        GenealogicalTreeNode<T> motherNode = new GenealogicalTreeNode<>(++idCounter, this, mother);
        return add(value, father, motherNode);
    }

    /**
     * Метод добавления человека в генеалогическое дерево.
     * @param value Объект для добавления.
     * @param father Объект отца, с которым будет создан новый узел.
     * @param mother Узел дерева, содержащий объект матери.
     * @return Узел дерева, содержащий добавленный объект.
     * @throws IllegalArgumentException Возбуждается, если параметр не инициализирован.
     */
    public GenealogicalTreeNode<T> add(T value, T father, GenealogicalTreeNode<T> mother)
            throws IllegalArgumentException {

        if (father == null)
            throw new IllegalArgumentException("Передан неинициализированный объект.");

        if (father.getGender() != Gender.male)
            throw new IllegalArgumentException("Невалидный пол отца.");

        GenealogicalTreeNode<T> fatherNode = new GenealogicalTreeNode<>(++idCounter, this, father);
        return add(value, fatherNode, mother);
    }

    /**
     * Метод получения узла генеалогического дерева.
     * @param id Идентификатор узда.
     * @return Узел генеалогического дерева.
     */
    public GenealogicalTreeNode<T> findNode(int id) {
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
    private GenealogicalTreeNode<T> findNode(int id, GenealogicalTreeNode<T> node) {
        if (node.getId() == id)
            return node;

        GenealogicalTreeNode<T>[] children = node.getChildren();
        if (children.length == 0)
            return null;

        for (GenealogicalTreeNode<T> child: children) {

            GenealogicalTreeNode<T> secondParent = node.getValue().getGender() == Gender.male
                    ? child.getMother() : child.getFather();

            if (secondParent.getId() == id)
                return secondParent;

            GenealogicalTreeNode<T> result = findNode(id, child);
            if (result != null)
                return result;
        }

        return null;
    }

    /**
     * Метод, возвращающий итератор генеалогического дерева.
     * @return Итератор генеалогического дерева.
     */
    @Override
    public Iterator<T> iterator() {
        Set<T> values = new HashSet<>();
        dfsGenealogicalTree(head, values);
        return values.iterator();
    }

    /**
     * Метод обхода дерева в глубину для записи в значений узлов в сет.
     * @param node Текущий узел.
     * @param values Сет для записи.
     */
    private void dfsGenealogicalTree(GenealogicalTreeNode<T> node, Set<T> values) {
        values.add(node.getValue());

        GenealogicalTreeNode<T>[] children = node.getChildren();
        if (children.length == 0)
            return;

        GenealogicalTreeNode<T> spouse = node.getValue().getGender() == Gender.male
                ? children[0].getMother() : children[0].getFather();
        values.add(spouse.getValue());

        for (GenealogicalTreeNode<T> child: children) {
            values.add(child.getValue());
            dfsGenealogicalTree(child, values);
        }
    }
}
