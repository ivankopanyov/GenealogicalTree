package models;

/**
 * Класс, описывающий мужчину.
 */
public class Male extends Person {

    /**
     * Инициализация объекта мужчины.
     * @param name Имя мужчины.
     */
    public Male(String name) {
        super(name, Gender.male);
    }
}
