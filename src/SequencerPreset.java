import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

/**
 * Created by edisongrauman on 12/30/19.
 */
public class SequencerPreset extends HBox {

    private ArrayList<ChoiceBox<Integer>> choices;
    private Button addButton;

    private SimpleIntegerProperty valueProperty;


    public SequencerPreset() {
        choices = new ArrayList<>();
        addButton = new Button("Add");
        addButton.setOnAction(event -> addChoice(0));
        getChildren().addAll(addButton);

        valueProperty = new SimpleIntegerProperty(0);



    }

    private void addChoice(int v) {
        ChoiceBox<Integer> c = new ChoiceBox<>();
        for (int i = 0; i < 128; i++) {
            c.getItems().addAll(i);
        }
        c.setValue(v);
        c.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.SECONDARY)) {
                choices.remove(c);
                getChildren().setAll(choices);
                getChildren().addAll(addButton);
                valueProperty.set(valueProperty.get()+1);//to update backend
            }
        });
        c.getSelectionModel().selectedIndexProperty().addListener(event -> valueProperty.set(valueProperty.get()+1)); //change event
        choices.add(c);
        getChildren().setAll(choices);
        getChildren().addAll(addButton);
        valueProperty.set(valueProperty.get()+1);

    }

    public ArrayList<Integer> getChoices() {
        ArrayList<Integer> j = new ArrayList<>();
        for (int i = 0; i < choices.size(); i++) {
            j.add(choices.get(i).getValue());
        }

        System.out.println("Getting " + j.get(0));
        return j;
    }

    public void setChoices(ArrayList<Integer> c) {
        System.out.println("SETTT");
        choices.clear();
        getChildren().setAll(addButton);
        for (int i = 0; i < c.size(); i++) {
            addChoice(c.get(i));
            System.out.println(c.get(i));
        }
    }

    public SimpleIntegerProperty valueProperty() {
        return valueProperty;
    }



}
