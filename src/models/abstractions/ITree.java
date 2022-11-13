package models.abstractions;

/**
 * Дерево.
 * @param <TValue> Тип значения узла дерева.
 * @param <TNode> Тип узла дерева.
 */
public interface ITree<TValue, TNode extends ITreeNode<TValue>> extends Iterable<TValue> {

    /**
     * Метод, возвращающий корневой узел дереваю
     * @return Корневой узел дерева.
     */
    TNode getHead();

    /**
     * Метод добавления объекта в дерево.
     * @param value Объект для добавления.
     * @param left Ссылка на левый узел.
     * @param right Ссылка на правый узел.
     * @return Узел дерева, содержащий добавленный объект.
     */
    TNode add(TValue value, TNode left, TNode right);

    /**
     * Метод добавления объекта в дерево.
     * @param value Объект для добавления.
     * @param left Ссылка на левый узел.
     * @param right Объект для добавления в правый узел.
     * @return Узел дерева, содержащий добавленный объект.
     */
    TNode add(TValue value, TNode left, TValue right);

    /**
     * Метод добавления объекта в дерево.
     * @param value Объект для добавления.
     * @param left Объект для добавления в левый узел.
     * @param right Ссылка на правый узел.
     * @return Узел дерева, содержащий добавленный объект.
     */
    TNode add(TValue value, TValue left, TNode right);
}
