import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by edisongrauman on 3/15/20.
 */
public class PresetWindow extends VBox {
    private CheckBox presetEnabled;

    private VBox presetParameters;

    private String presetName = "";

    private SimpleObjectProperty<JSONArray> value;

    public PresetWindow(JSONArray a) {

        value = new SimpleObjectProperty<>();

        try {
            presetName = a.getString(0);
        } catch (Exception e) {}
        presetEnabled = new CheckBox(presetName);
        presetEnabled.setOnAction(event -> {
            try {
                JSONArray o = new JSONArray();
                o.put(presetName+"Enabled");
                o.put(presetEnabled.isSelected());
                value.set(o);
            } catch (Exception e) {}
        });

        presetParameters = new VBox();
        presetParameters.visibleProperty().bind(presetEnabled.selectedProperty());
        presetParameters.managedProperty().bind(presetEnabled.selectedProperty());
        getChildren().addAll(presetEnabled,presetParameters);

        loadPresetConfig(a);

    }

    public SimpleObjectProperty<JSONArray> getValue() {
        return value;
    }

    private void addSliderTextField(String name, int min, int max) {
        SliderTextField s = new SliderTextField(max, min, name);
        s.valueProperty().addListener(event -> value.set(constructValueArray(name,s.getValue())));
        presetParameters.getChildren().addAll(s);
    }

    private void addCheckBox(String name) {
        CheckBox c = new CheckBox(name);
        c.setOnAction(event -> value.set(constructValueArray(name,c.isSelected())));
        presetParameters.getChildren().addAll(c);
    }

    private void addRadioGroup(String name, ArrayList<String> options) {
        RadioGroup r = new RadioGroup(name, options);
        r.valueProperty().addListener(event -> value.set(constructValueArray(name,r.getValue())));
        presetParameters.getChildren().addAll(r);
    }

    private void loadPresetConfig(JSONArray b) {
        for (int j = 1; j < b.length(); j++) { //iterate through preset parameters
            try {
                JSONArray c = b.getJSONArray(j); //get preset parameter type

                if (c.get(0).equals("Radio")) {
                    ArrayList<String> l = new ArrayList<>();
                    for (int k = 2; k < c.length(); k++) {
                        l.add(c.getString(k));
                    }
                    addRadioGroup(c.getString(1),l);
                }
                else if (c.get(0).equals("Slider")) {
                    addSliderTextField(c.getString(1),c.getInt(3),c.getInt(4));
                }
                else if (c.get(0).equals("Check")) {
                    addCheckBox(c.getString(1));
                }


            } catch (Exception f) {
                f.printStackTrace();
            }
        }

    }

    public void refreshPresets(JSONObject o) {
//            value.set(o);
        String k = presetName+"Enabled";
        try {
            ((CheckBox)getChildren().get(0)).setSelected(o.getBoolean(k));
        } catch (Exception e) {}

        System.out.println(((CheckBox)getChildren().get(0)).getText());

        for (int i = 1; i < getChildren().size(); i++) {
            VBox v = ((VBox)getChildren().get(i));
            for (int j = 0; j < v.getChildren().size(); j++) {
//                    System.out.println(v.getChildren().get(j));

                if (v.getChildren().get(j) instanceof SliderTextField) {
                    String key = presetName+((SliderTextField)v.getChildren().get(j)).getName();
//                        System.out.println(key);
                    try {
                        ((SliderTextField) v.getChildren().get(j)).setValue(o.getInt(key));
                    } catch (Exception e) {}
                } else if (v.getChildren().get(j) instanceof RadioGroup) {
                    String key = presetName+((RadioGroup)v.getChildren().get(j)).getName();
//                        System.out.println(key);
                    try {
                        ((RadioGroup)v.getChildren().get(j)).setValue(o.getString(key));
                    } catch (Exception e) {}
                } else if (v.getChildren().get(j) instanceof CheckBox) {
                    String key = presetName+((CheckBox) v.getChildren().get(j)).getText();
//                        System.out.println("key: " +key);
                    try {
                        ((CheckBox) v.getChildren().get(j)).setSelected(o.getBoolean(key));
                    } catch (Exception e) {}
                }

            }
        }
    }

    private JSONArray constructValueArray(String name, String val) {
        try {
            JSONArray a = new JSONArray();
            a.put(presetName+name);
            a.put(val);
            return a;
        } catch (Exception e) {}
        return null;
    }

    private JSONArray constructValueArray(String name, int val) {
        try {
            JSONArray a = new JSONArray();
            a.put(presetName+name);
            a.put(val);
            return a;
        } catch (Exception e) {}
        return null;
    }

    private JSONArray constructValueArray(String name, boolean val) {
        try {
            JSONArray a = new JSONArray();
            a.put(presetName+name);
            a.put(val);
            return a;
        } catch (Exception e) {}
        return null;
    }


}
