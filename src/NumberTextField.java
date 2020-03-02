import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.Event;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * Created by edisongrauman on 11/17/19.
 */
public class NumberTextField extends HBox {


    private TextField tf;
    private Label label;
    private SimpleIntegerProperty value;

    public NumberTextField(int lb, int ub, String l) {
        this(lb,ub);

        label.setText(l);
    }
    public NumberTextField(int lowerBound, int upperBound) {

        label = new Label();

        tf = new TextField(String.valueOf(lowerBound));
        tf.setMaxWidth(50);
        value = new SimpleIntegerProperty(lowerBound);
        value.addListener(event -> tf.textProperty().setValue(value.getValue().toString()));

        tf.textProperty().addListener(event -> {
            String t = tf.textProperty().getValue();
            try {

                if (t.equals("")) {
                    value.set(0);
                    throw new Exception();
                }
                int num = Integer.parseInt(t);

                if (num > upperBound || num < lowerBound) {
                    throw new Exception();
                }
                value.set(Integer.parseInt(t));


            } catch(Exception e) {
                tf.textProperty().setValue(value.getValue().toString());
            }
        });

        getChildren().addAll(label,tf);

    }

    public SimpleIntegerProperty valueProperty() {
        return value;
    }

    public int getValue() {
        return value.get();
    }





}
