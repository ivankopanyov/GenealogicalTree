package repositories;

import java.util.List;

/**
 * Репозиторий.
 * @param <T> Тип хранимых объектов.
 */
public interface IRepository<T> {

    /**
     * Метод добавления объекта в репозиторий.
     * @param entity Объект для добавления.
     */
    void add(T entity);

    /**
     * Метод получения объекта из репозитория.
     * @param id Идентификатор репозитория.
     * @return Возвращаемый объект.
     */
    T get(int id);

    /**
     * Метод получения списка всех объектов из репозитория.
     * @return Список всех объектов.
     */
    List<T> getAll();

    /**
     * Метод удаления объекта из репозитория.
     * @param id Идентификатор объекта.
     */
    void remove(int id);
}
