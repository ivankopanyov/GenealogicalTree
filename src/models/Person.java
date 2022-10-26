package models;

/**
 * Класс, описывающий человека.
 */
public abstract class Person implements INamed, IGendered {

    /**
     * Имя человека.
     */
    private final String name;

    /**
     * Пол человека.
     */
    private final Gender gender;

    /**
     * Инициализация объекта человека.
     * @param name Имя человека.
     * @param gender Пол человека.
     * @throws IllegalArgumentException Возбуждается, если параметр не инициализирован.
     */
    public Person(String name, Gender gender) throws IllegalArgumentException  {

        if (name == null || gender == null)
            throw new IllegalArgumentException("Передан неинициализированный объект.");

        this.name = name;
        this.gender = gender;
    }

    /**
     * Метод, возвращающий имя человека.
     * @return Имя человека.
     */
    public String getName() {
        return name;
    }

    /**
     * Метод, возвращающий пол человека.
     * @return Пол человека.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Переопределение метода преобразования объекта человеа в строку.
     * @return Строка с именем человека.
     */
    @Override
    public String toString() {
        return name;
    }
}
