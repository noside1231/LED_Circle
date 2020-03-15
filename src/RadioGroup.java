import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

/**
 * Created by edisongrauman on 11/19/19.
 */
public class RadioGroup extends HBox {

    private ToggleGroup tg;
    private ArrayList<RadioButton> radios;

    private SimpleStringProperty value;

    private String name = "";

    public RadioGroup(String ...radioNames) {

        tg = new ToggleGroup();

        radios = new ArrayList<>();

        for (int i = 0; i < radioNames.length; i++) {
            radios.add(new RadioButton(radioNames[i]));
            radios.get(i).setToggleGroup(tg);
        }

        value = new SimpleStringProperty(radioNames[0]);

        tg.selectedToggleProperty().addListener(event -> {
            value.setValue(((RadioButton)tg.getSelectedToggle()).getText());
        });
        getChildren().addAll(radios);
    }

    public RadioGroup(String name, ArrayList<String> radioNames) {
        this.name = name;

        tg = new ToggleGroup();

        radios = new ArrayList<>();

        for (int i = 0; i < radioNames.size(); i++) {
            radios.add(new RadioButton(radioNames.get(i)));
            radios.get(i).setToggleGroup(tg);
        }

        value = new SimpleStringProperty(radioNames.get(0));

        tg.selectedToggleProperty().addListener(event -> {
            value.setValue(((RadioButton)tg.getSelectedToggle()).getText());
        });
        getChildren().addAll(radios);
    }

    public SimpleStringProperty valueProperty() {
        return value;
    }

    public String getValue() {
        return value.getValue();
    }

    public void setValue(String v) {

        for (RadioButton i : radios) {
            if (i.getText().equals(v)) {
                value.setValue(v);
                tg.selectToggle(i);
                return;
            }
        }

    }

    public String getName() {
        return name;
    }


}
