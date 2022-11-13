package views;

import controllers.GenealogicalTreeController;
import models.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Класс, описывающий главное консольное представление.
 */
public class MainView {

    /**
     * Объект контроллера генеалогического дерева.
     */
    private final GenealogicalTreeController<Person> genealogicalTreeController;

    /**
     * Объект ридера для ввода данных в консоли.
     */
    private final BufferedReader reader;

    /**
     * Инициализация представления.
     * @param genealogicalTreeController Экземпляр контроллера для взаимодействия с экземплярами генеалгических деревьев.
     * @throws IllegalArgumentException Возбуждается, если передан неинициализированный параметр.
     */
    public MainView(GenealogicalTreeController<Person> genealogicalTreeController) throws IllegalArgumentException {
        if (genealogicalTreeController == null)
            throw new IllegalArgumentException("Переданный параметр неинициализтрован.");

        this.genealogicalTreeController = genealogicalTreeController;
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Метод запуска представления.
     */
    public void start() throws IOException {
        genealogicalTreeList();
    }

    /**
     * Метод вывода списка генеалогических деревьев.
     */
    private void genealogicalTreeList() throws IOException {
        while (true) {
            List<GenealogicalTree<Person>> genealogicalTrees = genealogicalTreeController.getAll();
            String[] items;

            if (genealogicalTrees.size() == 0) {
                System.out.println("\n\t ** Список пуст. **\n");
                items = new String[1];
            } else {
                items = new String[genealogicalTrees.size() + 1];
                for (int i = 0; i < genealogicalTrees.size(); i++)
                    items[i] = genealogicalTrees.get(i).getName();
            }
            items[items.length - 1] = "Создать дерево";
            int selectedItem = selectMenuItem(items, "Выход");

            if (selectedItem == -1)
                return;
            if (selectedItem == items.length - 1)
                createGenealogicalTree();
            else
                genealogicalTreeMenu(selectedItem);
        }
    }

    /**
     * Метод создания нового генеалогического дерева.
     */
    private void createGenealogicalTree() throws IOException {
        System.out.print("\nУкажите название дерева: ");
        String name = reader.readLine();
        Person person = createPerson("Укажите имя и фамилию самого дальнего предка: ");
        if (person == null)
            return;
        GenealogicalTree<Person> genealogicalTree = new GenealogicalTree<>(name, person);
        genealogicalTreeController.add(genealogicalTree);
    }

    /**
     * Метод отображения меню генеалогического дерева.
     * @param id Идентификатор генеалогического дерева.
     */
    private void genealogicalTreeMenu(int id) throws IOException {
        while (true) {
            String[] items = new String[] {
                    "Добавить человека",
                    "Показать дерево",
                    "Удалить дерево"
            };
            int selectedItem = selectMenuItem(items, "Назад");
            switch (selectedItem) {
                case -1:
                    return;
                case 0:
                    addPerson(id);
                    break;
                case 1:
                    System.out.println();
                    GenealogicalTree<Person> genealogicalTree = genealogicalTreeController.get(id);
                    System.out.println(genealogicalTreeController.getAllTree(genealogicalTree));
                    break;
                case 2:
                    genealogicalTreeController.remove(id);
                    return;
            }
        }
    }

    /**
     * Метод добавления нового человека в генеалогическое дерево.
     * @param id Идентификатор дерева.
     */
    private void addPerson(int id) throws IOException {
        Person person = createPerson("Укажите имя и фамилию нового человека: ");
        GenealogicalTree<Person> genealogicalTree = genealogicalTreeController.get(id);
        int number = inputNumber("Укажите ID отца или матери: ");
        GenealogicalTreeNode<Person> parent = genealogicalTreeController.get(genealogicalTree, number);
        if (parent == null) {
            System.out.println("Человек с указанным ID не найден.");
            return;
        }
        String parentGender = parent.getValue().getGender() == Gender.male ? "матери" : "отца";
        System.out.print("Укажите ID или имя и фамилию " + parentGender + ": ");
        String input = reader.readLine();
        if (IsNumber(input)) {
            GenealogicalTreeNode<Person> father;
            GenealogicalTreeNode<Person> mother;

            if (parent.getValue().getGender() == Gender.male) {
                father = parent;
                mother = genealogicalTreeController.get(genealogicalTree, Integer.parseInt(input));
            } else {
                father = genealogicalTreeController.get(genealogicalTree, Integer.parseInt(input));
                mother = parent;
            }

            if (mother == null || father == null) {
                System.out.println("Человек с указанным ID не найден.");
                return;
            }

            try {
                genealogicalTreeController.add(genealogicalTree, person, father, mother);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            return;
        }

        if (parent.getValue().getGender() == Gender.male)
            genealogicalTreeController.add(genealogicalTree, person, parent, new Female(input));
        else
            genealogicalTreeController.add(genealogicalTree, person, new Male(input), parent);
    }

    /**
     * Метод создания нового экземпляра человека.
     * @param title Описание.
     * @return Экземпляр человека.
     */
    private Person createPerson(String title) throws IOException {
        System.out.print(title);
        String personName = reader.readLine();
        System.out.println("Укажите пол.");
        String[] items = new String[] { "Мужской", "Женский" };
        int selectedItem = selectMenuItem(items, "Отмена");
        return switch (selectedItem) {
            case -1 -> null;
            case 0 -> new Male(personName);
            default -> new Female(personName);
        };
    }

    /**
     * Метод проверки строки на возможность приведения к числу.
     * @param value Строка для проверки.
     * @return Результат проверки.
     */
    private boolean IsNumber(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Метод ввода числа в консоль.
     * @param title Описание.
     * @return Введенное число.
     */
    private int inputNumber(String title) throws IOException {
        while (true) {
            System.out.print(title);
            try {
                return Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод! Повторите попытку...");
            }
        }
    }

    /**
     * Метод ввода числа в консоль в заданном диапазоне.
     * @param title Описание.
     * @param min Минимальное значение.
     * @param max Максимальное значение.
     * @return Введенное число.
     */
    private int inputNumberInRange(String title, int min, int max) throws IOException {
        while (true) {
            int number = inputNumber(title);
            if (number >= min && number <= max)
                return number;
            System.out.println("Нарушены границы диапазона! Повторите попытку...");
        }
    }

    /**
     * Метод выбора пункта меню.
     * @param menuItems Пункты меню.
     * @param backName Имя пункта выхода из меню.
     * @return Индекс введенного пункта меню.
     */
    private int selectMenuItem(String[] menuItems, String backName) throws IOException {
        System.out.println();
        for (int i = 0; i < menuItems.length; i++) {
            System.out.print(i + 1);
            System.out.print(". ");
            System.out.println(menuItems[i]);
        }
        System.out.print("0. ");
        System.out.println(backName);
        System.out.println();

        return inputNumberInRange("Укажите пункт меню: ", 0, menuItems.length) - 1;
    }
}
