import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;

/**
 * Created by edisongrauman on 11/15/19.
 */
public class SliderTextField extends HBox{

    private Slider slider;
    private NumberTextField numberTextField;
    private Label label;




    public SliderTextField(int upperBound, int lowerBound, String name) {
        numberTextField = new NumberTextField(lowerBound, upperBound);
        slider = new Slider(lowerBound, upperBound, 0);
        label = new Label(name);
        label.setAlignment(Pos.CENTER_RIGHT);
        label.setMinWidth(70);
        numberTextField.valueProperty().bindBidirectional(slider.valueProperty());

        getChildren().addAll(label, slider, numberTextField);
    }

    public DoubleProperty valueProperty() {
        return slider.valueProperty();
    }

    public int getValue() {
        return (int)slider.getValue();
    }

    public void setValue(int sliderValue) {
       slider.setValue(sliderValue);
    }



}
