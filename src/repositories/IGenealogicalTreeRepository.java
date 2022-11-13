package repositories;

import models.GenealogicalTree;
import models.abstractions.IGendered;

/**
 * Репозиторий для хранения генеалогических деревьев.
 * @param <T> Тип объектов, хранящихся в генеалогических деревьях.
 */
public interface IGenealogicalTreeRepository<T extends IGendered> extends IRepository<GenealogicalTree<T>> { }
