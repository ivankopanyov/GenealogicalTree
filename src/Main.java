import controllers.GenealogicalTreeController;
import repositories.GenealogicalTreeRepository;
import views.MainView;

import java.io.IOException;

public class Main {

    /**
     * Точка входа в приложение.
     */
    public static void main(String[] args) throws IOException {
        GenealogicalTreeRepository repository = new GenealogicalTreeRepository();
        GenealogicalTreeController controller = new GenealogicalTreeController(repository);
        MainView view = new MainView(controller);
        view.start();
    }
}