import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by edisongrauman on 11/15/19.
 */
public class MainWindow extends Parent {


//    private BackEnd backEnd;
    //Back End Stuff
    private int noteAmount = 128;
    private Serial serial;
    private Midi midi;
    private Config config;
    private NoteContainer noteContainer;
    private boolean refreshing = false;

    private Timeline autosaveTimeline;
    private boolean autoSaveOn = false;

    //Window Setup
    private Scene mainSceen;
    private VBox exteriorPane;
    private VBox mainWindowContainer;

    private ScrollPane presetScrollPane;
    private VBox presetContainer;
    private HBox noteContentContainer;

    private int presetParameterPadding = 20;

    private ToolBar toolbar;
    private MenuButton fileButton;
    private MenuItem newItem;
    private MenuItem openItem;
    private MenuItem saveItem;
    private MenuItem saveAsItem;
    private CheckMenuItem autoSaveItem;
    private MenuItem fixtureSettingsItem;
//    private MenuButton serialButton;
    private MenuItem serialStatusItem;
    private ArrayList<MenuItem> serialPortItems;
//    private MenuButton midiButton;
    private MenuItem midiStatusItem;
    private ArrayList<MenuItem> midiItems;
    private MenuButton editButton;
    private MenuItem copyToButton;
    private MenuButton viewButton;
    private MenuItem gridViewButton;
    private MenuItem editViewButton;
    private MenuItem newViewButton;
    private Label previewTextLabel;

    private MenuButton peripheralsButton;
    private Menu midiMenu;
    private Menu serialMenu;

    //Fixture Settings
    FixturePreferencesWindow fixturePreferencesWindow;
    FixtureGeneralWindow fixtureGeneralWindow;

    //Sequencer
    private Sequencer sequencer;
    private int sequencerInd;


    private final int screenWidth = 1280;
    private final int screenHeight = 600;

    //Copy To Window Settings
    ChoiceDialog<String> copyToDialog;

    //Note Settings
    private HBox noteInfo;
    private TextField noteName;
    private Label currentNoteLabel;
    private Button incrementButton;
    private Button decrementButton;
    private Button triggerButton;

    private VBox noteSettingsWindow;
    private int presetCheckBoxWidth = 90;
    private int presetParamterSpacing = 10;

    private GridPane noteGridWindow;
    private NoteGrid noteGrid;
    private StackPane[][] noteGridRects;

    //New Window Containers
    private HBox newContentContainer;
    private VBox mainNoteContainer;
    private HBox fixtureContainer;
    private ScrollPane fixtureScrollPane;


    //Note Color
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

    private RadioGroup colorRadio;
    private VBox colorContainer;

    //Note Timing
    private VBox timingContainer;
    private VBox timingParameters;
    private NumberTextFieldDecimal fadeIn;
    private NumberTextFieldDecimal hold;
    private NumberTextFieldDecimal fadeOut;
    private CheckBox alwaysOn;


    //Strobe
    private CheckBox strobeEnabled;
    private SliderTextField strobeSpeed;
    private SliderTextField strobeDecay;
    private RadioGroup strobeRadio;
    private VBox strobeParameters;
    private VBox strobeContainer;

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

    //PinWheel
    private CheckBox pinWheelEnabled;
    private CheckBox pinWheelTiming;
    private SliderTextField pinWheelSpeed;
    private SliderTextField pinWheelParts;
    private VBox pinWheelParameters;
    private VBox pinWheelContainer;

//    private Preset testPreset;

    private FixturePresetWindow fixturePresetWindow;

    //Angle
    private CheckBox angleEnabled;
    private CheckBox angleTiming;
    private SliderTextField angleSpeed;
    private SliderTextField angleAngle;
    private SliderTextField angleOffset;
    private VBox angleParameters;
    private VBox angleContainer;

    //Ring
    private CheckBox ringEnabled;
    private CheckBox ringTiming;
    private SliderTextField ringSpeed;
    private SliderTextField ringLength;
    private RadioGroup ringRadio;
    private CheckBox ringInvert;
    private VBox ringParameters;
    private VBox ringContainer;

    //Scan
    private CheckBox scanEnabled;
    private SliderTextField scanSpeed;
    private SliderTextField scanWidth;
    private CheckBox scanInvert;
    private VBox scanParameters;
    private HBox scanContainer;

    //Sequence
    private CheckBox sequenceEnabled;
    private ArrayList<ChoiceBox<Integer>> sequenceChoices;
    private Button sequenceAddChoiceButton;
    private VBox sequenceParameters;
    private HBox sequenceContainer;

    //BPM
    private CheckBox bpmSettingEnabled;
    private RadioGroup bpmSettings;
    private VBox bpmSettingParameters;
    private HBox bpmSettingContainer;

    //Note View
    private HBox noteViewBox;
    private Rectangle[] noteRects;
    private FadeTransition[] noteRectTransitions;

    CirclePresetParameters cp;


    public MainWindow(Stage mainWindow) {
        cp = new CirclePresetParameters();

        FileManager fileManager = new FileManager();
        noteContainer = new NoteContainer(noteAmount);

        autosaveTimeline = new Timeline();
        autosaveTimeline.setCycleCount(Animation.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(10), event -> {
            System.out.println("Saving");
            fileManager.saveFile(noteContainer.saveNotes());
        });
        autosaveTimeline.getKeyFrames().setAll(keyFrame);

        exteriorPane = new VBox();
        VBox.setVgrow(exteriorPane,Priority.ALWAYS);

        mainSceen = new Scene(exteriorPane, screenWidth, screenHeight);

        mainWindow.setScene(mainSceen);
        mainWindow.setTitle("Untitled");
        mainWindow.show();

        List<String> copyToDialogChoices = new ArrayList<>();
        for (int i = 0; i < noteAmount; i++) {
            copyToDialogChoices.add(Integer.toString(i) + " " + noteContainer.getNote(i).getNoteName());
        }
        copyToDialog = new ChoiceDialog<>(copyToDialogChoices.get(0), copyToDialogChoices);
        copyToDialog.setTitle("Copy To");
        copyToDialog.setHeaderText("Copy Note To Another");
        copyToDialog.setContentText("Choose Destination Note:");



        //main container
        mainWindowContainer = new VBox();

        toolbar = new ToolBar();
        newItem = new MenuItem("New");
        newItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.META_DOWN));

        newItem.setOnAction(event -> {
            fileManager.newFile();
            mainWindow.setTitle("Untitled");
        });
        openItem = new MenuItem("Open");
        openItem.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.META_DOWN));

        openItem.setOnAction(event -> {
            noteContainer.loadNotes(fileManager.openFile());
            mainWindow.setTitle(fileManager.getCurrentFileName());
            refreshDisplay();
        });
        saveItem = new MenuItem("Save");
        saveItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.META_DOWN));

        saveItem.setOnAction(event -> {
            fileManager.saveFile(noteContainer.saveNotes());
            mainWindow.setTitle(fileManager.getCurrentFileName());
        });
        saveAsItem = new MenuItem("Save As");
        saveAsItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.SHIFT_DOWN, KeyCombination.META_DOWN));
        saveAsItem.setOnAction(event -> {
            fileManager.saveAsFile(noteContainer.saveNotes());
            mainWindow.setTitle(fileManager.getCurrentFileName());
        });
        autoSaveItem = new CheckMenuItem("Auto-Save");
        autoSaveItem.selectedProperty().addListener(event -> {
            System.out.println(autoSaveItem.isSelected());
            if (autoSaveItem.isSelected()) {
                if (fileManager.saveFile(noteContainer.saveNotes())) {
                    autosaveTimeline.play();
                } else {
                    autoSaveItem.setSelected(false);
                }
            } else {
                autosaveTimeline.stop();
            }
        });

        fixtureSettingsItem = new MenuItem("Fixture Settings");
        fixtureSettingsItem.setOnAction(event -> {
            fixturePreferencesWindow.showFixturePreferences();
        });

        fileButton = new MenuButton("File");
        fileButton.getItems().addAll(newItem, openItem, saveItem, saveAsItem, autoSaveItem,fixtureSettingsItem);

        copyToButton = new MenuItem("Copy To...");
        copyToButton.setOnAction(event -> {
            Optional<String> result = copyToDialog.showAndWait();
            if (result.isPresent()) {
                noteContainer.copyNote(Integer.parseInt(result.get().substring(0,1)), noteContainer.getCurrentNote().getNoteNumber());
                updateBackEnd();
            }
        });
        editButton = new MenuButton("Edit");

        editButton.getItems().addAll(copyToButton);

        gridViewButton = new MenuItem("Grid");
        gridViewButton.setAccelerator(new KeyCodeCombination(KeyCode.G));
        gridViewButton.setOnAction(event -> setWindow("Grid"));

        editViewButton = new MenuItem("Edit");
        editViewButton.setAccelerator(new KeyCodeCombination(KeyCode.E));
        editViewButton.setOnAction(event -> setWindow("Edit"));

        newViewButton = new MenuItem("New");
        newViewButton.setAccelerator(new KeyCodeCombination(KeyCode.N));
        newViewButton.setOnAction(event -> setWindow("New"));

        viewButton = new MenuButton("View");
        viewButton.getItems().addAll(gridViewButton,editViewButton, newViewButton);


        serialStatusItem = new MenuItem("Not Connected");
        serialPortItems = new ArrayList<>();
//        serialButton = new MenuButton("Serial");
//        serialButton.showingProperty().addListener(event -> {
//            if (serialButton.isShowing()) {
//                refreshSerial();
//            }
//        });

//        serialButton.getItems().add(serialStatusItem);

        midiStatusItem = new MenuItem("Not Connected");
        midiItems = new ArrayList<>();

//        midiButton = new MenuButton("Midi");
//        midiButton.showingProperty().addListener(event -> {
//            if (midiButton.isShowing()) {
//                refreshMidi();
//            }
//        });
//        midiButton.getItems().add(midiStatusItem);

        midiMenu = new Menu("Midi");
        serialMenu = new Menu("Serial");
        serialMenu.getItems().add(serialStatusItem);


        peripheralsButton = new MenuButton("Peripherals");
        peripheralsButton.getItems().addAll(midiMenu,serialMenu);
        peripheralsButton.showingProperty().addListener(event -> {
            if (peripheralsButton.isShowing()) refreshPeripherals();
        });

        //Note Name preview
        previewTextLabel = new Label();

        //Sequencer
        sequencer = new Sequencer();
        sequencer.getBeatProperty().addListener(event -> sequencerTrigger());

        toolbar.getItems().addAll(fileButton,editButton, viewButton,peripheralsButton,sequencer,previewTextLabel);

        presetContainer = new VBox();
        presetContainer.setSpacing(10);
        presetScrollPane = new ScrollPane();
        presetScrollPane.setMinWidth(600);
        presetScrollPane.setMaxWidth(600);
        presetScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        presetScrollPane.setContent(presetContainer);


        initNoteSettings();
        initNoteGrid();
        initializeKeyMap();

        //new window
        initNewContainer();

        VBox noteInfoContainer = new VBox(noteInfo);
//        noteInfoContainer.setAlignment();
        noteContentContainer = new HBox(noteInfoContainer, presetScrollPane);
        noteContentContainer.setAlignment(Pos.CENTER_RIGHT);

        //happens last
        mainWindowContainer.getChildren().addAll(noteViewBox ,noteContentContainer);
        VBox.setVgrow(mainWindowContainer, Priority.ALWAYS);
        exteriorPane.getChildren().addAll(toolbar,mainWindowContainer);

        //Back End Stuff
        serial = new Serial();
        midi = new Midi();

        midi.valueProperty().addListener(event -> triggerNote(midi.getValue()));

        config = new Config("/Users/edisongrauman/IdeaProjects/LedCircle/Resources/config.properties");
        config.loadConfig();
        midi.openMidiDevice(config.loadProperty("Midi"));
        serial.connectToPort(config.loadProperty("Serial"));
        serial.barredSerial(config.loadProperty("BarredSerial"));

        fixturePreferencesWindow = new FixturePreferencesWindow();


        refreshDisplay();

    }

    public void initNoteSettings() {
//        noteSettingsWindow = new VBox();


        //Color Sliders + Preview
        redSlider = new SliderTextField(255,0,"Red");
        redSlider.valueProperty().addListener(event -> updateBackEnd("RGB"));
        greenSlider = new SliderTextField(255,0,"Green");
        greenSlider.valueProperty().addListener(event -> updateBackEnd("RGB"));
        blueSlider = new SliderTextField(255,0,"Blue");
        blueSlider.valueProperty().addListener(event -> updateBackEnd("RGB"));
        rgbContainer = new VBox(redSlider,greenSlider,blueSlider);

        hueSlider = new SliderTextField(360,0,"Hue");
        hueSlider.valueProperty().addListener(event -> updateBackEnd("HSL"));
        saturationSlider = new SliderTextField(100,0,"Saturation");
        saturationSlider.valueProperty().addListener(event -> updateBackEnd("HSL"));
        lightnessSlider = new SliderTextField(100,0,"Lightness");
        lightnessSlider.valueProperty().addListener(event -> updateBackEnd("HSL"));
        hslContainer = new VBox(hueSlider,saturationSlider,lightnessSlider);

        colorPreviewRect = new Rectangle(20,100);
        colorChooseContainer = new HBox(rgbContainer, hslContainer, colorPreviewRect);
        colorChooseContainer.managedProperty().bind(colorChooseContainer.visibleProperty());


        //Rainbow
//        rainbowEnabled = new CheckBox("Rainbow");
//        rainbowEnabled.setMinWidth(presetCheckBoxWidth);
//        rainbowEnabled.setOnAction(event -> updateBackEnd());
        rainbowSpeed = new SliderTextField(100,-100,"Speed");
        rainbowSpeed.valueProperty().addListener(event -> updateBackEnd());
        rainbowSpread = new SliderTextField(50,0,"Spread");
        rainbowSpread.valueProperty().addListener(event -> updateBackEnd());
        rainbowAngle = new SliderTextField(180,0,"Angle");
        rainbowAngle.valueProperty().addListener(event -> updateBackEnd());
        rainbowOffset = new SliderTextField(360,0,"Offset");
        rainbowOffset.valueProperty().addListener(event -> updateBackEnd());
        rainbowSaturaton = new SliderTextFieldVertical(100,0,"Saturation");
        rainbowSaturaton.valueProperty().addListener(event -> updateBackEnd());
        rainbowLightness = new SliderTextFieldVertical(100,0,"Brightness");
        rainbowLightness.valueProperty().addListener(event -> updateBackEnd());
        rainbowTiming = new CheckBox("Timing Reset");
        rainbowTiming.selectedProperty().addListener(event -> updateBackEnd());
        rainbowRadio = new RadioGroup("Circular","Linear","Radial");
        rainbowRadio.valueProperty().addListener(event -> updateBackEnd());
        rainbowParametersCol = new VBox(rainbowRadio, rainbowTiming, rainbowSpeed,rainbowSpread, rainbowOffset, rainbowAngle);
        rainbowParametersCol.setPadding(new Insets(0,0,0, presetParameterPadding));
        rainbowParametersCol.setSpacing(presetParamterSpacing);
        rainbowParametersRow = new HBox(rainbowParametersCol,rainbowSaturaton,rainbowLightness);
        rainbowParametersRow.setSpacing(presetParamterSpacing);
        rainbowParametersRow.managedProperty().bind(rainbowParametersRow.visibleProperty());

        //Color
        colorRadio = new RadioGroup("Color", "Rainbow");
        colorRadio.valueProperty().addListener(event -> {
            updateBackEnd();
        });

        colorContainer = new VBox(colorRadio, colorChooseContainer, rainbowParametersRow);
        colorContainer.setSpacing(presetParamterSpacing);

        noteName = new TextField();
        noteName.textProperty().addListener(event -> updateBackEnd());
        currentNoteLabel = new Label();
        currentNoteLabel.setPrefWidth(100);
        currentNoteLabel.setFont(Font.font(20));
        incrementButton = new Button(">");
        incrementButton.setOnAction(event -> {
            noteRects[noteContainer.getCurrentNote().getNoteNumber()].setFill(Color.WHITE);
            noteContainer.incrementCurrentNote();
            refreshDisplay();
        });
        decrementButton = new Button("<");
        decrementButton.setOnAction(event -> {
            noteRects[noteContainer.getCurrentNote().getNoteNumber()].setFill(Color.WHITE);
            noteContainer.decrementCurrentNote();
            refreshDisplay();
        });

        triggerButton = new Button("Trigger");
        triggerButton.setOnAction(event -> triggerNote(noteContainer.getCurrentNote().getNoteNumber()));


        noteInfo = new HBox(currentNoteLabel, decrementButton, incrementButton, noteName,triggerButton);

        fadeIn = new NumberTextFieldDecimal(0,255, "Fade In Time");
        fadeIn.valueProperty().addListener(event -> updateBackEnd());
        hold = new NumberTextFieldDecimal(0,255, "Hold Time");
        hold.valueProperty().addListener(event -> updateBackEnd());
        fadeOut = new NumberTextFieldDecimal(0,255, "Fade Out Time");
        fadeOut.valueProperty().addListener(event -> updateBackEnd());
        alwaysOn = new CheckBox("Always on");
        alwaysOn.setOnAction(event -> {
            updateBackEnd();
//            if (alwaysOn.isSelected()) {
//            }
        });
        timingParameters = new VBox(fadeIn,hold,fadeOut);
        timingParameters.visibleProperty().bind(alwaysOn.selectedProperty().not());
        timingParameters.managedProperty().bind(alwaysOn.selectedProperty().not());
        timingContainer = new VBox(alwaysOn, timingParameters);

        strobeEnabled = new CheckBox("Strobe");
        strobeEnabled.setMinWidth(presetCheckBoxWidth);
        strobeEnabled.setOnAction(event ->  updateBackEnd());
        strobeSpeed = new SliderTextField(100,0, "Speed");
        strobeSpeed.valueProperty().addListener(event -> updateBackEnd());
        strobeDecay = new SliderTextField(100,0,"Decay");
        strobeDecay.valueProperty().addListener(event -> updateBackEnd());
        strobeDecay.managedProperty().bind(strobeDecay.visibleProperty());
        strobeRadio = new RadioGroup("On/Off", "Decay", "Bounce");
        strobeRadio.valueProperty().addListener(event -> updateBackEnd());
        strobeParameters = new VBox(strobeRadio, strobeSpeed,strobeDecay);
        strobeParameters.setSpacing(presetParamterSpacing);
        strobeParameters.setPadding(new Insets(0,0,0, presetParameterPadding));
        strobeParameters.visibleProperty().bind(strobeEnabled.selectedProperty());
        strobeParameters.managedProperty().bind(strobeEnabled.selectedProperty());
        strobeContainer = new VBox(strobeEnabled, strobeParameters);
        strobeContainer.setSpacing(presetParamterSpacing);

        pinWheelEnabled = new CheckBox("PinWheel");
        pinWheelEnabled.setMinWidth(presetCheckBoxWidth);
        pinWheelEnabled.setOnAction(event -> updateBackEnd());
        pinWheelTiming = new CheckBox("Timing Reset");
        pinWheelTiming.setOnAction(event -> updateBackEnd());
        pinWheelSpeed = new SliderTextField(100,-100,"Speed");
        pinWheelSpeed.valueProperty().addListener(event -> updateBackEnd());
        pinWheelParts = new SliderTextField(10,1,"Parts");
        pinWheelParts.valueProperty().addListener(event -> updateBackEnd());
        pinWheelParameters = new VBox(pinWheelTiming,pinWheelSpeed,pinWheelParts);
        pinWheelParameters.setPadding(new Insets(0,0,0, presetParameterPadding));
        pinWheelParameters.setSpacing(presetParamterSpacing);
        pinWheelParameters.visibleProperty().bind(pinWheelEnabled.selectedProperty());
        pinWheelParameters.managedProperty().bind(pinWheelEnabled.selectedProperty());
        pinWheelContainer = new VBox(pinWheelEnabled,pinWheelParameters);
        pinWheelContainer.setSpacing(presetParamterSpacing);

//        testPreset = new Preset("Test");
//        testPreset.addSliderTextField("Test Field", 0,100);
//        testPreset.addRadioGroup("1", "two", "3");
//        testPreset.addCheckBox("One");
//        testPreset.addCheckBox("TWOO");

        angleEnabled = new CheckBox("Angle");
        angleEnabled.setMinWidth(presetCheckBoxWidth);
        angleEnabled.setOnAction(event -> updateBackEnd());
        angleTiming = new CheckBox("Timing Reset");
        angleTiming.setOnAction(event -> updateBackEnd());
        angleAngle = new SliderTextField(359,0,"Angle");
        angleAngle.valueProperty().addListener(event -> updateBackEnd());
        angleOffset = new SliderTextField(359,0,"Offset");
        angleOffset.valueProperty().addListener(event -> updateBackEnd());
        angleSpeed = new SliderTextField(50,-50,"Speed");
        angleSpeed.valueProperty().addListener(event -> updateBackEnd());
        angleParameters = new VBox(angleTiming,angleAngle,angleOffset,angleSpeed);
        angleParameters.setPadding(new Insets(0,0,0, presetParameterPadding));
        angleParameters.setSpacing(presetParamterSpacing);
        angleParameters.visibleProperty().bind(angleEnabled.selectedProperty());
        angleParameters.managedProperty().bind(angleEnabled.selectedProperty());
        angleContainer = new VBox(angleEnabled,angleParameters);
        angleContainer.setSpacing(presetParamterSpacing);

        ringEnabled = new CheckBox("Ring");
        ringEnabled.setMinWidth(presetCheckBoxWidth);
        ringEnabled.setOnAction(event -> updateBackEnd());
        ringTiming = new CheckBox("Timing Reset");
        ringTiming.setOnAction(event -> updateBackEnd());
        ringSpeed = new SliderTextField(30,0,"Speed");
        ringSpeed.valueProperty().addListener(event -> updateBackEnd());
        ringLength = new SliderTextField(7,0,"Length");
        ringLength.valueProperty().addListener(event -> updateBackEnd());
        ringRadio = new RadioGroup("Outward","Inward","Bounce");
        ringRadio.valueProperty().addListener(event -> updateBackEnd());
        ringInvert = new CheckBox("Invert");
        ringInvert.setOnAction(event -> updateBackEnd());
        ringParameters = new VBox(ringTiming,ringRadio,ringSpeed,ringLength,ringInvert);
        ringParameters.setPadding(new Insets(0,0,0, presetParameterPadding));
        ringParameters.setSpacing(presetParamterSpacing);
        ringParameters.visibleProperty().bind(ringEnabled.selectedProperty());
        ringParameters.managedProperty().bind(ringEnabled.selectedProperty());
        ringContainer = new VBox(ringEnabled, ringParameters);
        ringContainer.setSpacing(presetParamterSpacing);


        scanEnabled = new CheckBox("Scan");
        scanEnabled.setMinWidth(presetCheckBoxWidth);
        scanEnabled.setOnAction(event -> updateBackEnd());
        scanSpeed = new SliderTextField(100,0,"Speed");
        scanSpeed.valueProperty().addListener(event -> updateBackEnd());
        scanWidth = new SliderTextField(255,0,"Width");
        scanWidth.valueProperty().addListener(event -> updateBackEnd());
        scanInvert = new CheckBox("Invert");
        scanInvert.setOnAction(event -> updateBackEnd());
        scanParameters = new VBox(scanSpeed,scanWidth,scanInvert);
        scanParameters.setPadding(new Insets(0,0,0, presetParameterPadding));
        scanParameters.setSpacing(presetParamterSpacing);
        scanParameters.visibleProperty().bind(scanEnabled.selectedProperty());
        scanParameters.managedProperty().bind(scanEnabled.selectedProperty());
        scanContainer = new HBox(scanEnabled,scanParameters);
        scanContainer.setSpacing(presetParamterSpacing);



        sequenceEnabled = new CheckBox("Sequence");
        sequenceEnabled.setMinWidth(presetCheckBoxWidth);
        sequenceEnabled.setOnAction(event -> updateBackEnd());
        sequenceChoices = new ArrayList<>();
        sequenceAddChoiceButton = new Button("Add");
        sequenceAddChoiceButton.setOnAction(event -> {
            sequenceAddChoice(0);
            updateBackEnd();
        });
        sequenceParameters = new VBox();
        sequenceParameters.getChildren().addAll(sequenceChoices);
        sequenceParameters.getChildren().addAll(sequenceAddChoiceButton);
        sequenceParameters.visibleProperty().bind(sequenceEnabled.selectedProperty());
        sequenceParameters.managedProperty().bind(sequenceEnabled.selectedProperty());
        sequenceContainer = new HBox(sequenceEnabled, sequenceParameters);

        bpmSettingEnabled = new CheckBox("BPM");
        bpmSettingEnabled.setOnAction(event -> updateBackEnd());
        bpmSettings = new RadioGroup("Begin", "Stop", "Double", "Half");
        bpmSettings.valueProperty().addListener(event -> updateBackEnd());
        bpmSettingParameters = new VBox(bpmSettings);
        bpmSettingParameters.visibleProperty().bind(bpmSettingEnabled.selectedProperty());
        bpmSettingParameters.managedProperty().bind(bpmSettingEnabled.selectedProperty());
        bpmSettingContainer = new HBox(bpmSettingEnabled,bpmSettingParameters);

        noteRects = new Rectangle[128];
        noteRectTransitions = new FadeTransition[128];
        for (int i = 0; i < noteRects.length; i++) {
            noteRects[i] = new Rectangle(10,40);
            noteRects[i].setFill(Color.WHITE);
            noteRects[i].setStroke(Color.BLACK);
            noteRects[i].setStrokeType(StrokeType.INSIDE);
            noteRectTransitions[i] = new FadeTransition(Duration.millis(250),noteRects[i]);
            noteRectTransitions[i].setFromValue(0);
            noteRectTransitions[i].setToValue(1);

            Tooltip t = new Tooltip();
            Tooltip.install(noteRects[i], t);

            int ti = i;
            noteRects[i].setOnMouseClicked(event -> noteRectClicked(ti));
            noteRects[i].setOnMouseEntered(event -> {
                previewTextLabel.setText(noteContainer.getNote(ti).getNoteName());
                t.setText(noteContainer.getNote(ti).getNoteName());
            });
            noteRects[i].setOnMouseExited(event -> previewTextLabel.setText(""));
        }

        noteViewBox = new HBox(noteRects);
        noteViewBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

//        noteSettingsWindow.getChildren().addAll(noteViewBox, noteInfo, colorChooseContainer,timingContainer, strobeContainer, rainbowContainer, pinWheelContainer,angleContainer,ringContainer,scanContainer, sequenceContainer,bpmSettingContainer);

        presetContainer.getChildren().addAll(timingContainer, colorContainer, strobeContainer,pinWheelContainer,angleContainer,ringContainer,scanContainer,sequenceContainer,bpmSettingContainer);



    }

    public void initNoteGrid() {
        noteGridWindow = new GridPane();
        noteGridWindow.setVgap(10);
        noteGridWindow.setHgap(10);
        int gridAmount = 5;
        noteGrid = new NoteGrid(gridAmount,gridAmount);
        noteGridRects = new StackPane[gridAmount][gridAmount];
        FadeTransition noteGridTransition[][] = new FadeTransition[gridAmount][gridAmount];

        double gridSize = 80;

        for (int x = 0; x < gridAmount; x++) {
            for (int y = 0; y < gridAmount; y++) {
                int tx = x;
                int ty = y;

                Rectangle rect = new Rectangle(gridSize,gridSize);
                rect.setFill(Color.WHITE);
                rect.setStroke(Color.BLACK);
                rect.setStrokeType(StrokeType.INSIDE);
                rect.setArcWidth(10);
                rect.setArcHeight(10);
                noteGridTransition[x][y] = new FadeTransition(Duration.millis(250),rect);
                noteGridTransition[x][y].setFromValue(0);
                noteGridTransition[x][y].setToValue(1);

                rect.setOnMouseClicked(event -> {

                    if (event.getButton() == MouseButton.PRIMARY){
                        noteRectClicked(noteGrid.getGrid(tx, ty).getNum());
                        noteGridTransition[tx][ty].play();
                    } else if (event.getButton() == MouseButton.SECONDARY) {
                        setWindow("Edit");
                    }
                });

                Text text = new Text();
                text.setWrappingWidth(gridSize);
                text.setTextAlignment(TextAlignment.CENTER);
                text.setOnMousePressed(event -> {
                    if (event.getButton() == MouseButton.PRIMARY){
                        noteRectClicked(noteGrid.getGrid(tx, ty).getNum());
                        noteGridTransition[tx][ty].play();
                    } else if (event.getButton() == MouseButton.SECONDARY) {
                        setWindow("Edit");
                    }
                });

                noteGridRects[x][y] = new StackPane();
                noteGridRects[x][y].getChildren().addAll(rect, text);

                noteGridWindow.add(noteGridRects[x][y],y,x);

            }
        }

    }

    public void initNewContainer() {

        NumberTextFieldDecimal fadeIn = new NumberTextFieldDecimal(0,100,"FadeIn");
        NumberTextFieldDecimal hold = new NumberTextFieldDecimal(0,100,"Hold");
        NumberTextFieldDecimal fadeOut = new NumberTextFieldDecimal(0,100,"FadeOut");
        TextField noteName = new TextField("Note Name");

        HBox timingBox = new HBox(fadeIn,hold,fadeOut,noteName);

        ColorRainbowPresetContainer colorRainbowPresetContainer = new ColorRainbowPresetContainer();
        colorRainbowPresetContainer.setVisible(false);


        ObservableList<String> fixturePresets = FXCollections.observableArrayList("Color/Rainbow", "Strobe");
        ComboBox<String> masterPresetBox = new ComboBox<>(fixturePresets);

        ListView<String> testview = new ListView<>(fixturePresets);
        masterPresetBox.setOnAction(event -> {
            if (masterPresetBox.getValue().equals(fixturePresets.get(0))) {
                colorRainbowPresetContainer.setVisible(true);

                masterPresetBox.getItems().remove(masterPresetBox.getValue());
                masterPresetBox.setValue("Select Preset to Add");
            }
        });





//        mainNoteContainer = new VBox(timingBox,colorRainbowPresetContainer,testview,masterPresetBox);
//        mainNoteContainer.setMinWidth(500);

        //Left Column with main information(timing, common presets etc.)
        fixtureGeneralWindow = new FixtureGeneralWindow();
        fixtureGeneralWindow.getValue().addListener(event -> {
            try {
                if (fixtureGeneralWindow.getValue().get().get(0).equals("Trigger")) {
                    triggerNote(noteContainer.getCurrentNote().getNoteNumber());
                } else if (fixtureGeneralWindow.getValue().get().get(0).equals("Increment")) {
                    noteRects[noteContainer.getCurrentNote().getNoteNumber()].setFill(Color.WHITE);
                    noteContainer.incrementCurrentNote();
                    refreshDisplay();
                } else if (fixtureGeneralWindow.getValue().get().get(0).equals("Decrement")) {
                    noteRects[noteContainer.getCurrentNote().getNoteNumber()].setFill(Color.WHITE);
                    noteContainer.decrementCurrentNote();
                    refreshDisplay();
                } else {
                    System.out.println("Update: " + fixtureGeneralWindow.getValue().get());
                    noteContainer.updateCurrentNote(fixtureGeneralWindow.getValue().get());
                }
            } catch (Exception e) {}
        });

        fixturePresetWindow = new FixturePresetWindow("Fixture 1");
        fixturePresetWindow.getValue().addListener(event -> {
            System.out.println("Update: " + fixturePresetWindow.getValue().get());
            noteContainer.updateCurrentNote(fixturePresetWindow.getValue().get());
        });


        FixturePresetWindow f2 = new FixturePresetWindow("Fixture 2");
        FixturePresetWindow f3 = new FixturePresetWindow("Fixture 2");

        fixtureContainer = new HBox(fixturePresetWindow,f2,f3);

        fixtureScrollPane = new ScrollPane(fixtureContainer);



        //Main Container that will show
        newContentContainer = new HBox(fixtureGeneralWindow,fixtureScrollPane);
        newContentContainer.setMinHeight(400);

    }

    public void noteRectClicked(int i) {
        triggerNote(i);
        noteRects[noteContainer.getCurrentNote().getNoteNumber()].setFill(Color.WHITE);
        noteContainer.setCurrentNote(i);

        refreshDisplay();
    }

    private void triggerNote(int i) {
        if (i != -1) {
            serial.sendData(noteContainer.getNote(i));

            //
            if (noteContainer.getNote(i).isBpmSettingsEnabled()){
                byte b = noteContainer.getNote(i).getBpmSetting();
                if (b == 'S') {
                    sequencer.stop();
                } else if (b == 'B') {
                    sequencer.start();
                } else if (b == 'D') {
                    sequencer.doubleBPM();
                } else if (b == 'H') {
                    sequencer.halfBPM();
                }
            }

            noteRectTransitions[i].play();

//            if (i < 25) {
//                notegridt[][] add transition play on grid
//            }
        }
    }

    private void refreshDisplay() {
//        testPreset.refreshPreset();
        refreshing = true; //don't update back end if setting notes
        Note currentNote = noteContainer.getCurrentNote();

        System.out.println("Refreshing " + currentNote.getNoteNumber());
        noteName.setText(currentNote.getNoteName());
        currentNoteLabel.setText("Note: " + currentNote.getNoteNumber());

        byte colorType = currentNote.getColorType();
        if (colorType == 'C') {
            colorRadio.setValue("Color");
            rainbowParametersRow.setVisible(false);
            colorChooseContainer.setVisible(true);
        } else if (colorType == 'R') {
            colorRadio.setValue("Rainbow");
            rainbowParametersRow.setVisible(true);
            colorChooseContainer.setVisible(false);
        }

        redSlider.setValue(currentNote.getR());
        greenSlider.setValue(currentNote.getG());
        blueSlider.setValue(currentNote.getB());
        hueSlider.setValue(currentNote.getHue());
        saturationSlider.setValue(currentNote.getSaturation());
        lightnessSlider.setValue(currentNote.getLightness());
        colorPreviewRect.setFill(Color.rgb(currentNote.getR(),currentNote.getG(),currentNote.getB()));

        fadeIn.setValue(currentNote.getFadeIn());
        hold.setValue(currentNote.getHold());
        fadeOut.setValue(currentNote.getFadeOut());
        alwaysOn.setSelected(currentNote.isAlwaysOn());


        strobeEnabled.setSelected(currentNote.isStrobeEnabled());
        strobeSpeed.setValue(currentNote.getStrobeSpeed());
        strobeDecay.setValue(currentNote.getStrobeDecay());
        byte strobeType = currentNote.getStrobeType();
        if (strobeType == 'O') {
            strobeRadio.setValue("On/Off");
            strobeDecay.setVisible(false);
        } else if (strobeType == 'D') {
            strobeRadio.setValue("Decay");
            strobeDecay.setVisible(true);
        } else if (strobeType == 'B') {
            strobeRadio.setValue("Bounce");
            strobeDecay.setVisible(false);

        }


//        rainbowEnabled.setSelected(currentNote.isRainbowEnabled());
        rainbowTiming.setSelected(currentNote.isRainbowTiming());
        rainbowSpeed.setValue(currentNote.getRainbowSpeed());
        rainbowSpread.setValue(currentNote.getRainbowSpread());
        rainbowAngle.setValue(currentNote.getRainbowAngle());
        rainbowOffset.setValue(currentNote.getRainbowOffset());
        rainbowSaturaton.setValue(currentNote.getRainbowSaturation());
        rainbowLightness.setValue(currentNote.getRainbowLightness());
        byte rainbowDirection = currentNote.getRainbowDirection();
        if (rainbowDirection == 'C') {
            rainbowRadio.setValue("Circular");
        } else if (rainbowDirection == 'L') {
            rainbowRadio.setValue("Linear");
        } else if (rainbowDirection == 'R') {
            rainbowRadio.setValue("Radial");
        }

        pinWheelEnabled.setSelected(currentNote.isPinWheelEnabled());
        pinWheelTiming.setSelected(currentNote.isPinWheelTiming());
        pinWheelSpeed.setValue(currentNote.getPinWheelSpeed());
        pinWheelParts.setValue(currentNote.getPinWheelParts());

//        pinWheelPreset.setValues(currentNote.getPinWheelParameters());
//        currentNote.setPinWheelParameters

        angleEnabled.setSelected(currentNote.isAngleEnabled());
        angleTiming.setSelected(currentNote.isAngleTiming());
        angleSpeed.setValue(currentNote.getAngleSpeed());
        angleAngle.setValue(currentNote.getAngleAngle());
        angleOffset.setValue(currentNote.getAngleOffset());

        ringEnabled.setSelected(currentNote.isRingEnabled());
        ringTiming.setSelected(currentNote.isRingTiming());
        ringSpeed.setValue(currentNote.getRingSpeed());
        ringLength.setValue(currentNote.getRingLength());
        ringInvert.setSelected(currentNote.isRingInvert());
        byte ringType = currentNote.getRingType();

        if (ringType == 'O') {
            ringRadio.setValue("Outward");
        } else if (ringType == 'I') {
            ringRadio.setValue("Inward");
        } else if (ringType == 'B') {
            ringRadio.setValue("Bounce");
        }

        scanEnabled.setSelected(currentNote.isScanEnabled());
        scanSpeed.setValue(currentNote.getScanSpeed());
        scanWidth.setValue(currentNote.getScanLength());
        scanInvert.setSelected(currentNote.isScanInvert());

        sequenceEnabled.setSelected(currentNote.isSequenceEnabled());
        setSequenceChoices(currentNote.getSequenceChoices());

        bpmSettingEnabled.setSelected(currentNote.isBpmSettingsEnabled());
        byte bpmSetting = currentNote.getBpmSetting();
        if (bpmSetting == 'S') {
            bpmSettings.setValue("Stop");
        } else if (bpmSetting == 'B') {
            bpmSettings.setValue("Begin");
        } else if (bpmSetting == 'D') {
            bpmSettings.setValue("Double");
        } else if (bpmSetting == 'H') {
            bpmSettings.setValue("Half");
        }

        noteRects[currentNote.getNoteNumber()].setFill(Color.CYAN);

        refreshing = false;




        //New Notes
        try {
            fixturePresetWindow.refreshPresetWindow(noteContainer.getCurrentNewNote().getJSONObject("F1"));
            fixtureGeneralWindow.refresh(noteContainer.getCurrentNewNote().getJSONObject("GeneralData"));


        } catch (Exception e) {}

    }

//    private void refreshSerial() {
//        System.out.println("Refreshing Serial Menu");
//        serialPortItems.clear();
//        serialButton.getItems().clear();
//        serialButton.getItems().add(serialStatusItem);
//        for (int i = 0; i < serial.getSerialPortNames().size(); i++) {
//            int ti = i;
//            serialPortItems.add(new MenuItem(serial.getSerialPortNames().get(ti)));
//            serialPortItems.get(i).setOnAction(event -> serial.connectToPort(serialPortItems.get(ti).getText()));
//        }
//        serialButton.getItems().addAll(serialPortItems);
//
//        serialStatusItem.setText(serial.isConnected());
//    }
//
//    private void refreshMidi() {
//        System.out.println("Refreshing Midi Menu");
//        midiItems.clear();
//        midiButton.getItems().clear();
////        midiButton.getItems().add(midiStatusItem);
//        for (int i = 0; i < midi.getMidiNames().size(); i++) {
//            int ti = i;
//            midiItems.add(new MenuItem(midi.getMidiNames().get(ti)));
//            midiItems.get(i).setOnAction(event -> midi.openMidiDevice(midiItems.get(ti).getText()));
//        }
//        midiButton.getItems().addAll(midiItems);
//    }

    private void refreshPeripherals() {
        //Refresh Midi
        midiItems.clear();
        midiMenu.getItems().clear();
        for (int i = 0; i < midi.getMidiNames().size(); i++) {
            int ti = i;
            midiItems.add(new MenuItem(midi.getMidiNames().get(ti)));
            midiItems.get(i).setOnAction(event -> {
                if (midi.openMidiDevice(midiItems.get(ti).getText())) {
                    config.setProperty("Midi", midiItems.get(ti).getText());
                }
            });
        }
        midiMenu.getItems().addAll(midiItems);

        //Refresh Serial
        serialStatusItem.setText(serial.isConnected());
        serialPortItems.clear();
        serialMenu.getItems().clear();
        serialMenu.getItems().add(serialStatusItem);
        for (int i = 0; i < serial.getSerialPortNames().size(); i++) {
            int ti = i;
            serialPortItems.add(new MenuItem(serial.getSerialPortNames().get(ti)));
            serialPortItems.get(i).setOnAction(event -> {
                if (serial.connectToPort(serialPortItems.get(ti).getText())) {
                    config.setProperty("Serial", serialPortItems.get(ti).getText());

                };
            });
        }

        serialMenu.getItems().addAll(serialPortItems);

    }

    private void updateBackEnd(String type) {//for color adjustments, maybe theres a better way?
        if (!refreshing) { //dont update back end if setting notes
            System.out.println("updating color");
            Note n = new Note(-1);
            n.setNoteName(noteName.getText());

            n.setHue(hueSlider.getValue());
            n.setSaturation(saturationSlider.getValue());
            n.setLightness(lightnessSlider.getValue());
            n.setR(redSlider.getValue());
            n.setG(greenSlider.getValue());
            n.setB(blueSlider.getValue());
            n.setColorType((byte)colorRadio.getValue().charAt(0));

            n.setFadeIn(fadeIn.getValue());
            n.setHold(hold.getValue());
            n.setFadeOut(fadeOut.getValue());
            n.setAlwaysOn(alwaysOn.isSelected());

            n.setStrobeEnabled(strobeEnabled.isSelected());
            n.setStrobeSpeed((byte)strobeSpeed.getValue());
            n.setStrobeDecay((int) strobeDecay.getValue());
            n.setStrobeType((byte) strobeRadio.getValue().charAt(0));

            n.setRainbowEnabled(colorRadio.getValue().charAt(0) == 'R');
            n.setRainbowTiming(rainbowTiming.isSelected());
            n.setRainbowSpeed(rainbowSpeed.getValue());
            n.setRainbowSpread((byte)rainbowSpread.getValue());
            n.setRainbowAngle(rainbowAngle.getValue());
            n.setRainbowOffset(rainbowOffset.getValue());
            n.setRainbowSaturation(rainbowSaturaton.getValue());
            n.setRainbowLightness(rainbowLightness.getValue());
            n.setRainbowDirection((byte) rainbowRadio.getValue().charAt(0));

            n.setPinWheelEnabled(pinWheelEnabled.isSelected());
            n.setPinWheelTiming(pinWheelTiming.isSelected());
            n.setPinWheelSpeed((int) pinWheelSpeed.getValue());
            n.setPinWheelParts((int) pinWheelParts.getValue());

            n.setAngleEnabled(angleEnabled.isSelected());
            n.setAngleTiming(angleTiming.isSelected());
            n.setAngleAngle((int) angleAngle.getValue());
            n.setAngleOffset((int) angleOffset.getValue());
            n.setAngleSpeed((int) angleSpeed.getValue());

            n.setRingEnabled(ringEnabled.isSelected());
            n.setRingTiming(ringTiming.isSelected());
            n.setRingSpeed(ringSpeed.getValue());
            n.setRingLength(ringLength.getValue());
            n.setRingInvert(ringInvert.isSelected());
            n.setRingType((byte)ringRadio.getValue().charAt(0));

            n.setScanEnabled(scanEnabled.isSelected());
            n.setScanSpeed(scanSpeed.getValue());
            n.setScanLength(scanWidth.getValue());
            n.setScanInvert(scanInvert.isSelected());

            n.setSequenceEnabled(sequenceEnabled.isSelected());
            n.setSequenceChoices(getSequenceChoices());

            n.setBpmSettingsEnabled(bpmSettingEnabled.isSelected());
            n.setBpmSetting((byte)bpmSettings.getValue().charAt(0));

            if (type == "RGB") {
                n.rgbToHSL();
            } else if (type == "HSL") {
                n.hsltoRGB();
            }

            noteContainer.setNote(n);

            List<String> copyToDialogChoices = new ArrayList<>();
            for (int i = 0; i < noteAmount; i++) {
                copyToDialogChoices.add(Integer.toString(i) + " " + noteContainer.getNote(i).getNoteName());
            }
            copyToDialog.getItems().clear();
            copyToDialog.getItems().addAll(copyToDialogChoices);

            updateNoteGrid();

            refreshDisplay();

        }
    }

    private void updateBackEnd() {
        if (!refreshing) { //don't update back end if setting notes
            System.out.println("updating");
            Note n = new Note(-1);
            n.setNoteName(noteName.getText());

            n.setR(redSlider.getValue());
            n.setG(greenSlider.getValue());
            n.setB(blueSlider.getValue());
            n.setHue(hueSlider.getValue());
            n.setSaturation(saturationSlider.getValue());
            n.setLightness(lightnessSlider.getValue());
            n.setColorType((byte)colorRadio.getValue().charAt(0));

            n.setFadeIn(fadeIn.getValue());
            n.setHold(hold.getValue());
            n.setFadeOut(fadeOut.getValue());
            n.setAlwaysOn(alwaysOn.isSelected());

            n.setStrobeEnabled(strobeEnabled.isSelected());
            n.setStrobeSpeed((byte) strobeSpeed.getValue());
            n.setStrobeDecay((int) strobeDecay.getValue());
            n.setStrobeType((byte) strobeRadio.getValue().charAt(0));

            n.setRainbowEnabled(colorRadio.getValue().charAt(0) == 'R');
            n.setRainbowTiming(rainbowTiming.isSelected());
            n.setRainbowSpeed(rainbowSpeed.getValue());
            n.setRainbowSpread((byte)rainbowSpread.getValue());
            n.setRainbowAngle(rainbowAngle.getValue());
            n.setRainbowOffset(rainbowOffset.getValue());
            n.setRainbowSaturation(rainbowSaturaton.getValue());
            n.setRainbowLightness(rainbowLightness.getValue());
            n.setRainbowDirection((byte) rainbowRadio.getValue().charAt(0));

            n.setPinWheelEnabled(pinWheelEnabled.isSelected());
            n.setPinWheelTiming(pinWheelTiming.isSelected());
            n.setPinWheelSpeed((int) pinWheelSpeed.getValue());
            n.setPinWheelParts((int) pinWheelParts.getValue());

            n.setAngleEnabled(angleEnabled.isSelected());
            n.setAngleTiming(angleTiming.isSelected());
            n.setAngleAngle((int) angleAngle.getValue());
            n.setAngleOffset((int) angleOffset.getValue());
            n.setAngleSpeed((int) angleSpeed.getValue());

            n.setRingEnabled(ringEnabled.isSelected());
            n.setRingTiming(ringTiming.isSelected());
            n.setRingSpeed(ringSpeed.getValue());
            n.setRingLength(ringLength.getValue());
            n.setRingInvert(ringInvert.isSelected());
            n.setRingType((byte)ringRadio.getValue().charAt(0));

            n.setScanEnabled(scanEnabled.isSelected());
            n.setScanSpeed(scanSpeed.getValue());
            n.setScanLength(scanWidth.getValue());
            n.setScanInvert(scanInvert.isSelected());

            n.setSequenceEnabled(sequenceEnabled.isSelected());
            n.setSequenceChoices(getSequenceChoices());

            n.setBpmSettingsEnabled(bpmSettingEnabled.isSelected());
            n.setBpmSetting((byte)bpmSettings.getValue().charAt(0));

            noteContainer.setNote(n);

            List<String> copyToDialogChoices = new ArrayList<>();
            for (int i = 0; i < noteAmount; i++) {
                copyToDialogChoices.add(Integer.toString(i) + " " + noteContainer.getNote(i).getNoteName());
            }
            copyToDialog.getItems().clear();
            copyToDialog.getItems().addAll(copyToDialogChoices);

            updateNoteGrid();

            refreshDisplay();

        }

    }

    private void initializeKeyMap() {
        mainSceen.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                noteRects[noteContainer.getCurrentNote().getNoteNumber()].setFill(Color.WHITE);
                noteContainer.decrementCurrentNote();
                refreshDisplay();
            }
            else if (event.getCode() == KeyCode.RIGHT) {
                noteRects[noteContainer.getCurrentNote().getNoteNumber()].setFill(Color.WHITE);
                noteContainer.incrementCurrentNote();
                refreshDisplay();
            } else if (event.getCode() == KeyCode.UP) {
                triggerNote(noteContainer.getCurrentNote().getNoteNumber());
            }
        });
    }

    private void sequencerTrigger() {

        if (!noteContainer.getCurrentNote().isSequenceEnabled()) {
            triggerNote(noteContainer.getCurrentNote().getNoteNumber());
        } else {

            if (noteContainer.getCurrentNote().getSequenceChoices().size() != 0) {
//                System.out.println(sequencerInd);
                try {
                    triggerNote(noteContainer.getCurrentNote().getSequenceChoices().get(sequencerInd++));
                } catch (Exception e) {
                    sequencerInd = 0;
                }
                if (sequencerInd >= noteContainer.getCurrentNote().getSequenceChoices().size()) {
                    sequencerInd = 0;
                }
            }
        }
    }

    //Sequence Preset
    private void sequenceAddChoice(int v) {
        ChoiceBox<Integer> c = new ChoiceBox<>();
        for (int i = 0; i < 128; i++) {
            c.getItems().addAll(i);
        }
        c.setValue(v);
        c.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.SECONDARY)) {
                sequenceChoices.remove(c);
                sequenceParameters.getChildren().setAll(sequenceChoices);
                sequenceParameters.getChildren().addAll(sequenceAddChoiceButton);
                updateBackEnd();
            }
        });
        c.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                updateBackEnd();
            }
        });
        sequenceChoices.add(c);

        sequenceParameters.getChildren().setAll(sequenceChoices);
        sequenceParameters.getChildren().addAll(sequenceAddChoiceButton);
        updateBackEnd();


    }

    public ArrayList<Integer> getSequenceChoices() {
        ArrayList<Integer> j = new ArrayList<>();
        for (int i = 0; i < sequenceChoices.size(); i++) {
            j.add(sequenceChoices.get(i).getValue());
        }

        return j;
    }

    public void setSequenceChoices(ArrayList<Integer> c) {
        sequenceChoices.clear();
        sequenceParameters.getChildren().setAll(sequenceAddChoiceButton);
        for (int i = 0; i < c.size(); i++) {
            sequenceAddChoice(c.get(i));
//            System.out.println(c.get(i));
        }
    }

    public void setWindow(String view) {
        if (view.equals("Grid")) {
            mainWindowContainer.getChildren().clear();
            mainWindowContainer.getChildren().addAll(noteGridWindow);
        } else if (view.equals("Edit")) {
            mainWindowContainer.getChildren().clear();
            mainWindowContainer.getChildren().addAll(noteViewBox ,noteContentContainer);
        } else if (view.equals("New")) {
            mainWindowContainer.getChildren().clear();
            mainWindowContainer.getChildren().addAll(noteViewBox ,newContentContainer);
        }
    }

    public void updateNoteGrid() {
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                int n = noteGrid.getGrid(x,y).getNum();
                ((Text)(noteGridRects[x][y].getChildren().get(1))).setText(noteContainer.getNote(n).getNoteName());
            }
        }
    }

}


