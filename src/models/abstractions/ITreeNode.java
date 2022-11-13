package models.abstractions;

/**
 * Узел дерева.
 * @param <TValue> Тип объекта, хранящегося в узле дерева.
 */
public interface ITreeNode<TValue> {

    /**
     * Метод, возвращающий объект дерева, к которому относится узел.
     * @return Объект дерева, к которому относится узел.
     * @param <T> Тип дерева.
     */
    <T extends ITree<TValue, ITreeNode<TValue>>> T getTree();

    /**
     * Метод, возвращающий объект, хранящийся в узле.
     * @return Объект, хранящийся в узле.
     */
    TValue getValue();

    /**
     * Метод, возвращающий левый узел.
     * @return Левый узел.
     * @param <T> Тип узла.
     */
    <T extends ITreeNode<TValue>> T getLeft();

    /**
     * Метод, возвращающий правый узел.
     * @return Правый узел.
     * @param <T> Тип узла.
     */
    <T extends ITreeNode<TValue>> T getRight();

    /**
     * Метод, возвращающий массив узлов, хранящих объекты детей.
     * @return Массив узлов, хранящих объекты детей.
     * @param <T> Тип узла.
     */
    <T extends ITreeNode<TValue>> T[] getChildren();
}
