package models.abstractions;

import models.Gender;

/**
 * Объект, имеющий пол.
 */
public interface IGendered {

    /**
     * Метод, возвращающий пол объекта.
     * @return Пол объекта.
     */
    Gender getGender();
}
