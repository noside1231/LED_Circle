import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by edisongrauman on 3/14/20.
 */
public class FixtureGeneralWindow extends VBox {
    private NumberTextFieldDecimal fadeIn;
    private NumberTextFieldDecimal hold;
    private NumberTextFieldDecimal fadeOut;
    private CheckBox timingOn;
    private TextField noteName;

    private Label currentNoteLabel;
    private Button incrementButton;
    private Button decrementButton;
    private Button triggerButton;

    private HBox topContainer;
    private HBox timingParameters;

    private SimpleObjectProperty<JSONArray> value;

//    ColorRainbowPresetContainer colorRainbowPresetContainer = new ColorRainbowPresetContainer();
//        colorRainbowPresetContainer.setVisible(false);

    public FixtureGeneralWindow() {

        value = new SimpleObjectProperty<>();

        currentNoteLabel = new Label("");
        incrementButton = new Button(">");
        incrementButton.setOnAction(event -> {
            try {
                JSONArray o = new JSONArray();
                o.put("Increment");
                value.set(o);
            } catch (Exception e) {}
        });
        decrementButton = new Button("<");
        decrementButton.setOnAction(event -> {
            try {
                JSONArray o = new JSONArray();
                o.put("Decrement");
                value.set(o);
            } catch (Exception e) {}
        });
        triggerButton = new Button("Trigger");
        triggerButton.setOnAction(event -> {
            try {
                JSONArray o = new JSONArray();
                o.put("Trigger");
                value.set(o);
            } catch (Exception e) {}
        });

        noteName = new TextField("Note Name");
        noteName.textProperty().addListener(event -> {
            try {
                JSONArray o = new JSONArray();
                o.put("NoteName");
                o.put(noteName.getText());
                value.set(o);
            } catch (Exception e) {}
        });

        fadeIn = new NumberTextFieldDecimal(0,100,"Fade In");
        fadeIn.valueProperty().addListener(event -> {
            try {
                JSONArray o = new JSONArray();
                o.put("TimingIn");
                o.put(fadeIn.getValue());
                value.set(o);
            } catch (Exception e) {}
        });
        hold = new NumberTextFieldDecimal(0,100,"Hold");
        hold.valueProperty().addListener(event -> {
            try {
                JSONArray o = new JSONArray();
                o.put("TimingHold");
                o.put(hold.getValue());
                value.set(o);
            } catch (Exception e) {}
        });
        fadeOut = new NumberTextFieldDecimal(0,100,"Fade Out");
        fadeOut.valueProperty().addListener(event -> {
            try {
                JSONArray o = new JSONArray();
                o.put("TimingOut");
                o.put(fadeOut.getValue());
                value.set(o);
            } catch (Exception e) {}
        });
        timingOn = new CheckBox("Always On");
        timingOn.selectedProperty().addListener(event -> {
            try {
                JSONArray o = new JSONArray();
                o.put("AlwaysOn");
                o.put(timingOn.isSelected());
                value.set(o);
            } catch (Exception e) {}
        });
        timingParameters = new HBox(fadeIn,hold,fadeOut);
        timingParameters.visibleProperty().bind(timingOn.selectedProperty().not());
        timingParameters.managedProperty().bind(timingOn.selectedProperty().not());

        topContainer = new HBox(noteName,currentNoteLabel,timingOn,timingParameters);

        getChildren().addAll(topContainer,incrementButton,decrementButton,triggerButton);


        loadPresets("/Users/edisongrauman/IdeaProjects/LedCircle/Resources/General.json");



        setMinWidth(500);
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

    public void refresh(JSONObject o) {

        try {
            noteName.setText(o.getString("NoteName"));
        } catch (Exception e) {}
        try {
            currentNoteLabel.setText("Note: " + o.getString("NoteNumber"));
        } catch (Exception e) {}
        try {
            fadeIn.setValue(o.getDouble("TimingIn"));
        } catch (Exception e) {}
        try {
            hold.setValue(o.getDouble("TimingHold"));
        } catch (Exception e) {}
        try {
            fadeOut.setValue(o.getDouble("TimingOut"));
        } catch (Exception e) {}
        try {
            timingOn.setSelected(o.getBoolean("AlwaysOn"));
        } catch (Exception e) {}
    }

    public SimpleObjectProperty<JSONArray> getValue() {
        return value;
    }

}

