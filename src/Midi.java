import javafx.beans.property.SimpleIntegerProperty;

import javax.sound.midi.*;
import java.util.ArrayList;

/**
 * Created by edisongrauman on 11/15/19.
 */
public class Midi {

    private MidiDevice midiDevice;
    private MidiDevice.Info[] midiDeviceInfos = MidiSystem.getMidiDeviceInfo();

    private SimpleIntegerProperty value;

    public Midi() {


        for (int i = 0; i < midiDeviceInfos.length; i++) {
            try {
                System.out.print(MidiSystem.getMidiDevice(midiDeviceInfos[i]).getDeviceInfo().getName());
                System.out.print(",");
            } catch (Exception e) {
                System.out.println("Could not get midi devices");
            }
        }
        System.out.println();

        value = new SimpleIntegerProperty(0);
    }

    public ArrayList<String> getMidiNames() {

        ArrayList<String> m = new ArrayList<>();
        try {
            for (int i = 0; i < midiDeviceInfos.length; i++) {
                m.add(MidiSystem.getMidiDevice(midiDeviceInfos[i]).getDeviceInfo().getName());
            }
        } catch (Exception e) {};
        return m;
    }

    public SimpleIntegerProperty valueProperty() {
        return value;
    }

    public int getValue() {
        return value.get();
    }

    public void openMidiDevice(String deviceName) {
        for (MidiDevice.Info i : midiDeviceInfos) {
            try {
                if (deviceName.equals(MidiSystem.getMidiDevice(i).getDeviceInfo().getName())) {

                    midiDevice = MidiSystem.getMidiDevice(i);
                    Transmitter transmitter = midiDevice.getTransmitter();
                    transmitter.setReceiver(new MidiInputReceiver());
                    midiDevice.open();
                    return;
                }
            } catch (Exception e) {}

        }
    }


    class MidiInputReceiver implements Receiver {

        public MidiInputReceiver() {
        }

        public void send(MidiMessage msg, long timeStamp) {
            if ((msg.getMessage()[2] & 0xFF) > 90) { //note on
//                System.out.println(msg.getMessage()[1] & 0xFF);
                value.set(msg.getMessage()[1] & 0xFF);
                value.set(-1);
            }
        }

        public void close() {
        }
    }
}


