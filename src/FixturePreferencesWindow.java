import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Created by edisongrauman on 3/10/20.
 */
public class FixturePreferencesWindow extends TextInputDialog {

    private VBox rootbox;
    private Button newFixtureButton;

    private ArrayList<FixtureRow> fixtureRows;

    public FixturePreferencesWindow() {

        newFixtureButton = new Button("Add Fixture");
        newFixtureButton.setOnAction(event -> addFixtureRow());

        fixtureRows = new ArrayList<>();
        fixtureRows.add(new FixtureRow(fixtureRows.size()));
        fixtureRows.add(new FixtureRow(fixtureRows.size()));
        fixtureRows.add(new FixtureRow(fixtureRows.size()));
        fixtureRows.add(new FixtureRow(fixtureRows.size()));

        rootbox = new VBox();
        rootbox.setSpacing(20);
        refreshWindow();
        setHeaderText("Fixture Settings");

        getDialogPane().setContent(rootbox);

    }

    public void showFixturePreferences() {
        showAndWait();
    }

private void addFixtureRow() {
        FixtureRow f = new FixtureRow(fixtureRows.size());
        fixtureRows.add(f);
        refreshWindow();
}


    private class FixtureRow extends HBox {
        private ObservableList<String> fixtureTypes = FXCollections.observableArrayList("Big Circle", "Little Circle", "Bar");

        private NumberTextField fixtureID;
        private TextField name;
        private ComboBox<String> fixtureType;
        private Button deleteButton;

        public FixtureRow(int s) {
            name = new TextField("Fixture " + Integer.toString(s));
            fixtureID = new NumberTextField(0,100, "ID");
            fixtureID.setValue(s);
            fixtureID.valueProperty().addListener(event -> {
//                    ArrayList<Integer> fixtureIDs = new ArrayList<>();
//                    for (int i = 0; i < fixtureRows.size(); i++) {
//                        fixtureIDs.add(fixtureRows.get(i).fixtureID.getValue());
//                    }
//                    System.out.println(fixtureIDs);
//                    boolean taken = true;
//                    int v = 0;
//                    while (taken) {
//                        if (fixtureIDs.contains(v)) {
//                            v++;
//                        } else {
//                            taken = false;
                            fixtureID.setValue(fixtureID.getValue());
//                        }
//                    }
        //for making sure there are no duplicate ids

            });
            fixtureType = new ComboBox<>(fixtureTypes);
            fixtureType.setValue(fixtureTypes.get(0));

            deleteButton = new Button("x");
            deleteButton.setOnAction(event -> {
                fixtureRows.remove(this);
                refreshWindow();
            });

            setSpacing(5);
            getChildren().addAll(fixtureID,name, fixtureType,deleteButton);
        }
    }

    private void refreshWindow() {
        rootbox.getChildren().setAll(fixtureRows);
        rootbox.getChildren().add(newFixtureButton);
    }


}
