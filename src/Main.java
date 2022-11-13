import controllers.GenealogicalTreeController;
import models.Person;
import repositories.GenealogicalTreeRepository;
import views.MainView;

import java.io.IOException;

public class Main {

    /**
     * Точка входа в приложение.
     */
    public static void main(String[] args) throws IOException {
        GenealogicalTreeRepository<Person> repository = new GenealogicalTreeRepository<>();
        GenealogicalTreeController<Person> controller = new GenealogicalTreeController<>(repository);
        MainView view = new MainView(controller);
        view.start();
    }
}