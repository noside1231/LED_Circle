import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;

/**
 * Created by edisongrauman on 11/15/19.
 */
public class MainToolBar extends ToolBar{

    private MenuButton fileButton;
    private MenuItem newItem;
    private MenuItem openItem;
    private MenuItem saveItem;

    public MainToolBar(){

        fileButton = new MenuButton("File");
        newItem = new MenuItem("New");
        openItem = new MenuItem("Open");
        saveItem = new MenuItem("Save");

        fileButton.getItems().addAll(newItem, openItem, saveItem);

        getItems().addAll(fileButton);


    }
}
