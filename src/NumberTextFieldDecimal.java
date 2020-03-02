import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * Created by edisongrauman on 11/17/19.
 */
public class NumberTextFieldDecimal extends HBox {

    private TextField tf;
    private Label label;
    private SimpleDoubleProperty value;

    public NumberTextFieldDecimal(int lb, int ub, String l) {
        this(lb,ub);

        label.setText(l);
    }

    public NumberTextFieldDecimal(int lowerBound, int upperBound) {

        label = new Label();
        tf = new TextField(String.valueOf(lowerBound));
        tf.setMaxWidth(50);
        value = new SimpleDoubleProperty(lowerBound);
        value.addListener(event -> tf.textProperty().setValue(value.getValue().toString()));

        tf.textProperty().addListener(event -> {
            String t = tf.textProperty().getValue();
            try {

                if (t.equals("")) {
                    value.set(0);
                    throw new Exception();
                }
                double num = Double.parseDouble(t);

                if (num > upperBound || num < lowerBound) {
                    throw new Exception();
                }
                value.set(Double.parseDouble(t));


            } catch(Exception e) {
                tf.textProperty().setValue(value.getValue().toString());
            }
        });

        getChildren().addAll(label,tf);

    }

    public SimpleDoubleProperty valueProperty() {
        return value;
    }

    public double getValue() {
        return value.get();
    }

    public void setValue(double v) {
        value.set(v);
    }



}
