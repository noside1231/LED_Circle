import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by edisongrauman on 12/30/19.
 */
public class Sequencer extends HBox{

    private final Timeline timeline;

    private int measureBPMCount;
    private long measureBPMFirst;
    private long measureBPMPrevious;
    private double bpmAverge = 120;

    private Button bpmButton;
    private Button doubleBPM;
    private Button halfBPM;
    private Label bpmLabel;
    private Circle bpmCircle;

    private SimpleBooleanProperty beatBool;


    public Sequencer() {
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        bpmLabel = new Label(Double.toString(bpmAverge));
        bpmButton = new Button("BPM");
        bpmButton.setOnAction(event -> {
            calculateBPM();
            setBPMLabel();
            resetSequencerTimer();
        });

        doubleBPM = new Button("2X");
        doubleBPM.setOnAction(event -> {
            doubleBPM();
        });

        halfBPM = new Button("0.5X");
        halfBPM.setOnAction(event -> {
            halfBPM();
        });


        bpmCircle = new Circle(10);
        bpmCircle.setFill(Color.RED);
        bpmCircle.setOnMouseClicked(event -> {
            if (timeline.getStatus().equals(Animation.Status.RUNNING)) {
                stop();

            } else if (timeline.getStatus().equals(Animation.Status.STOPPED)) {
                resetSequencerTimer();
            }
        });

        getChildren().addAll(bpmButton,halfBPM,doubleBPM,bpmLabel,bpmCircle);
        setAlignment(Pos.CENTER);
        setSpacing(10);

        beatBool = new SimpleBooleanProperty(false);



    }

    public double calculateBPM() {
         long curTime = System.currentTimeMillis();
        if ((curTime - measureBPMPrevious) > 2000) {
            measureBPMCount = 0;
        }
        if (measureBPMCount == 0) {
            measureBPMFirst = curTime;
            measureBPMCount++;
        } else {
            bpmAverge = 60000 * measureBPMCount / (double)(curTime - measureBPMFirst);
            measureBPMCount++;
        }
        measureBPMPrevious = curTime;
        return bpmAverge;
    }

    private void resetSequencerTimer() {
        double seconds = 60 / bpmAverge;
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(seconds), event -> advanceSequencer());
        timeline.stop();
        timeline.getKeyFrames().setAll(keyFrame);
        timeline.play();
//        beatBool.set(true);
    }

    private void advanceSequencer() {
        if (beatBool.get()) {
            bpmCircle.setFill(Color.LIME);
        } else {
            bpmCircle.setFill(Color.WHITE);
        }
        beatBool.set(!beatBool.get());

    }

    public void stop() {
        timeline.stop();
        bpmCircle.setFill(Color.RED);
    }

    public void start() {
        resetSequencerTimer();
    }

    public void doubleBPM() {
        bpmAverge *= 2.0;
        resetSequencerTimer();
        setBPMLabel();
    }

    public void halfBPM() {
        bpmAverge *= 0.5;
        resetSequencerTimer();
        setBPMLabel();
    }

    public SimpleBooleanProperty getBeatProperty() {
        return beatBool;
    }

    private void setBPMLabel() {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        bpmLabel.setText(df.format(bpmAverge));
    }
}
