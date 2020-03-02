import com.sun.tools.javac.comp.Check;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

/**
 * Created by edisongrauman on 12/26/19.
 */
public class Preset extends HBox{

    private HBox presetParameters;

    private CheckBox presetEnabled;

    public Preset(String name) {

        presetEnabled = new CheckBox(name);
        presetEnabled.setSelected(false);

        presetEnabled.setOnAction(event -> {
            System.out.println(presetEnabled.isSelected());
        });

        presetParameters = new HBox();
        presetParameters.visibleProperty().bind(presetEnabled.selectedProperty());

        getChildren().addAll(presetEnabled, presetParameters);
    }

    public void addRadioGroup(String... options) {
        RadioGroup r = new RadioGroup(options);
        r.valueProperty().addListener(event -> {
            System.out.println(r.valueProperty().getValue());
        });
        presetParameters.getChildren().addAll(r);
    }

    public void addSliderTextField(String name, int min, int max) {
        SliderTextField s = new SliderTextField(max, min, name);
        s.valueProperty().addListener(event -> {
            System.out.println(name + " " + s.valueProperty().getValue());
        });
        presetParameters.getChildren().addAll(s);
    }

    public void addCheckBox(String name) {
        CheckBox c = new CheckBox(name);
        c.setOnAction(event -> {
            System.out.println(name + " " + c.selectedProperty().get());
        });
        presetParameters.getChildren().addAll(c);
    }

    public void refreshPreset(Note n) {

        for (int i = 0; i < presetParameters.getChildren().size(); i++) {
            System.out.println(i);
            Node preset = presetParameters.getChildren().get(i);

            if (preset instanceof SliderTextField) {
                System.out.println("STF " + " " + ((SliderTextField) preset).getValue());
            } else if (preset instanceof CheckBox) {
                System.out.println("Check " + " " + ((CheckBox) preset).getText() + " "  +((CheckBox) preset).isSelected());
            } else if (preset instanceof RadioGroup) {

            }


        }
    }



}
