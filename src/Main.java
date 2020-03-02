import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Created by edisongrauman on 11/15/19.
 */
public class Main extends Application {

    private MainWindow mainWindow;



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {

        mainWindow = new MainWindow(window);








    }
}
