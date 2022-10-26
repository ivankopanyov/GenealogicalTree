package models;

/**
 * Класс, описывающий женщину.
 */
public class Female extends Person {

    /**
     * Инициализация объекта женщины.
     * @param name Имя женщины.
     */
    public Female(String name) {
        super(name, Gender.female);
    }
}
