package repositories;

import models.GenealogicalTree;
import models.abstractions.IGendered;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, описывающий репозиторий для хранения объектов генеалогических деревьев.
 * @param <T> Тип объектов, хранящихся в генеалогических деревьях.
 */
public class GenealogicalTreeRepository<T extends IGendered> implements IGenealogicalTreeRepository<T> {

    /**
     * Список для хранения объектов генеалогических деревьев.
     */
    private final List<GenealogicalTree<T>> genealogicalTrees;

    /**
     * Инициализация объекта репозитория.
     */
    public GenealogicalTreeRepository() {
        genealogicalTrees = new ArrayList<>();
    }

    /**
     * Метод добавления генеалогического дерева в репозиторий.
     * @param entity Объект генеалогического дерева.
     */
    @Override
    public void add(GenealogicalTree<T> entity) {
        if (entity == null)
            return;

        genealogicalTrees.add(entity);
    }

    /**
     * Метод получения объекта генеалогического дерева из репозитория.
     * @param id Идентификатор гееалогического дерева.
     * @return Возвращаемый объект.
     */
    @Override
    public GenealogicalTree<T> get(int id) {
        if (id < 0 || id >= genealogicalTrees.size())
            return null;

        return genealogicalTrees.get(id);
    }

    /**
     * Метод получения списка всех генеалогических деревьев из репозитория.
     * @return Список всех генеалогических деревьев.
     */
    @Override
    public List<GenealogicalTree<T>> getAll() {
        return new ArrayList<>(genealogicalTrees);
    }

    /**
     * Метод удаления генеалгического дерева из репозитория.
     * @param id Идентификатор негеалогического дерева.
     */
    @Override
    public void remove(int id) {
        if (id < 0 || id >= genealogicalTrees.size())
            return;

        genealogicalTrees.remove(id);
    }
}
