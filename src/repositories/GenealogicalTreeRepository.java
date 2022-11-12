package repositories;

import models.GenealogicalTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, описывающий репозиторий для хранения объектов генеалогических деревьев.
 */
public class GenealogicalTreeRepository implements IGenealogicalTreeRepository {

    /**
     * Список для хранения объектов генеалогических деревьев.
     */
    private List<GenealogicalTree> genealogicalTrees;

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
    public void add(GenealogicalTree entity) {
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
    public GenealogicalTree get(int id) {
        if (id < 0 || id >= genealogicalTrees.size())
            return null;

        return genealogicalTrees.get(id);
    }

    /**
     * Метод получения списка всех генеалогических деревьев из репозитория.
     * @return Список всех генеалогических деревьев.
     */
    @Override
    public List<GenealogicalTree> getAll() {
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
