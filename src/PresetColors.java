import javafx.scene.control.ComboBox;

/**
 * Created by edisongrauman on 11/19/19.
 */
public class PresetColors extends ComboBox<String> {

    String[] colors = {"Black", "White", "Red", "Green"};
    public PresetColors() {

        getItems().addAll(colors);
    }
}
