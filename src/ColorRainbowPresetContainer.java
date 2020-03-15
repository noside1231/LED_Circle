import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

/**
 * Created by edisongrauman on 3/10/20.
 */
public class ColorRainbowPresetContainer extends VBox{

    private RadioGroup colorRainbowRadio;

    //Color
    private VBox rgbContainer;
    private SliderTextField redSlider;
    private SliderTextField greenSlider;
    private SliderTextField blueSlider;

    private VBox hslContainer;
    private SliderTextField hueSlider;
    private SliderTextField saturationSlider;
    private SliderTextField lightnessSlider;

    private Rectangle colorPreviewRect;
    private HBox colorChooseContainer;

    //Rainbow
    private SliderTextField rainbowSpeed;
    private SliderTextField rainbowSpread;
    private SliderTextField rainbowAngle;
    private SliderTextField rainbowOffset;
    private SliderTextFieldVertical rainbowSaturaton;
    private SliderTextFieldVertical rainbowLightness;
    private CheckBox rainbowTiming;
    private RadioGroup rainbowRadio;
    private VBox rainbowParametersCol;
    private HBox rainbowParametersRow;

    public ColorRainbowPresetContainer() {

//Color Sliders + Preview
        redSlider = new SliderTextField(255,0,"Red");
//        redSlider.valueProperty().addListener(event -> updateBackEnd("RGB"));
        greenSlider = new SliderTextField(255,0,"Green");
//        greenSlider.valueProperty().addListener(event -> updateBackEnd("RGB"));
        blueSlider = new SliderTextField(255,0,"Blue");
//        blueSlider.valueProperty().addListener(event -> updateBackEnd("RGB"));
        rgbContainer = new VBox(redSlider,greenSlider,blueSlider);

        hueSlider = new SliderTextField(360,0,"Hue");
//        hueSlider.valueProperty().addListener(event -> updateBackEnd("HSL"));
        saturationSlider = new SliderTextField(100,0,"Saturation");
//        saturationSlider.valueProperty().addListener(event -> updateBackEnd("HSL"));
        lightnessSlider = new SliderTextField(100,0,"Lightness");
//        lightnessSlider.valueProperty().addListener(event -> updateBackEnd("HSL"));
        hslContainer = new VBox(hueSlider,saturationSlider,lightnessSlider);

        colorPreviewRect = new Rectangle(20,100);
        colorChooseContainer = new HBox(rgbContainer, hslContainer, colorPreviewRect);
        colorChooseContainer.managedProperty().bind(colorChooseContainer.visibleProperty());


        //Rainbow
        rainbowSpeed = new SliderTextField(100,-100,"Speed");
//        rainbowSpeed.valueProperty().addListener(event -> updateBackEnd());
        rainbowSpread = new SliderTextField(50,0,"Spread");
//        rainbowSpread.valueProperty().addListener(event -> updateBackEnd());
        rainbowAngle = new SliderTextField(180,0,"Angle");
//        rainbowAngle.valueProperty().addListener(event -> updateBackEnd());
        rainbowOffset = new SliderTextField(360,0,"Offset");
//        rainbowOffset.valueProperty().addListener(event -> updateBackEnd());
        rainbowSaturaton = new SliderTextFieldVertical(100,0,"Saturation");
//        rainbowSaturaton.valueProperty().addListener(event -> updateBackEnd());
        rainbowLightness = new SliderTextFieldVertical(100,0,"Brightness");
//        rainbowLightness.valueProperty().addListener(event -> updateBackEnd());
        rainbowTiming = new CheckBox("Timing Reset");
//        rainbowTiming.selectedProperty().addListener(event -> updateBackEnd());
        rainbowRadio = new RadioGroup("Circular","Linear","Radial");
//        rainbowRadio.valueProperty().addListener(event -> updateBackEnd());
        rainbowParametersCol = new VBox(rainbowRadio, rainbowTiming, rainbowSpeed,rainbowSpread, rainbowOffset, rainbowAngle);
//        rainbowParametersCol.setPadding(new Insets(0,0,0, presetParameterPadding));
//        rainbowParametersCol.setSpacing(presetParamterSpacing);
        rainbowParametersRow = new HBox(rainbowParametersCol,rainbowSaturaton,rainbowLightness);
//        rainbowParametersRow.setSpacing(presetParamterSpacing);
        rainbowParametersRow.managedProperty().bind(rainbowParametersRow.visibleProperty());

        //Color
        colorRainbowRadio = new RadioGroup("Color", "Rainbow");
//        colorRainbowRadio.valueProperty().addListener(event -> updateBackEnd());

        getChildren().addAll(colorRainbowRadio, colorChooseContainer, rainbowParametersRow);
//        colorContainer.setSpacing(presetParamterSpacing);
    }

}
