import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by edisongrauman on 3/14/20.
 */



public class FixturePresetWindow extends VBox {

    private SimpleObjectProperty<JSONArray> value;

    public FixturePresetWindow(String fixtureName) {

        Label l = new Label(fixtureName);

        loadPresets("/Users/edisongrauman/IdeaProjects/LedCircle/Resources/BigCircle.json");
        getChildren().add(l);
        setMinWidth(400);

        value = new SimpleObjectProperty<>();

    }

    private void loadPresets(String path) {

        JSONArray a = JSONHelp.loadFromFile(path);

        for (int i = 0; i < a.length(); i++) {
            try {
                PresetWindow p = new PresetWindow(a.getJSONArray(i));
                p.getValue().addListener(event -> value.set(p.getValue().get()));
                getChildren().add(p);
            } catch (Exception e) {}
        }
    }

    public void refreshPresetWindow(JSONObject o) {
        for (int i  = 0; i < getChildren().size()-1; i++) {
            ((PresetWindow)getChildren().get(i)).refreshPresets(o);
        }
    }

    public SimpleObjectProperty<JSONArray> getValue() {
        return value;
    }


}


