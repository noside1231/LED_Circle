import com.serialpundit.serial.SerialComManager;

import java.util.ArrayList;

/**
 * Created by edisongrauman on 11/15/19.
 */
public class Serial {

    private SerialComManager serialComManager;

    private long handle;

    private String[] barredSerial;

    public Serial() {

        try {
            serialComManager = new SerialComManager();
        } catch (Exception e) {
            System.out.println("Serial Unavailable: SerialComManager Failure");
        }

    }

    class SendWirelessData implements Runnable {

        Note n;

        SendWirelessData(Note n) {
            this.n = n;
        }
        @Override
        public void run() {
            try {
                serialComManager.writeBytes(handle, n.getSerialData());
                System.out.println("data sent");
            } catch (Exception e) {
                disconnectPort();
                System.out.println("cant send data");
            }
        }
    }

    public ArrayList<String> getSerialPortNames() {

        ArrayList<String> sp = new ArrayList<>();
        try {
            String[] serialPorts = serialComManager.listAvailableComPorts();
            for (int i = 0; i < serialPorts.length; i++) {
                if (serialPorts[i].startsWith("/dev/cu")) {
                    sp.add(serialPorts[i]);
                }
            }

            for (int i = 0; i < barredSerial.length; i++) {
                sp.remove(barredSerial[i]);
            }

        } catch (Exception e) {};
        return sp;
    }

    public void sendData(Note note) {
        new Thread(new SendWirelessData(note)).start();
    }

    public boolean connectToPort(String portName) {

        disconnectPort();
        try {
            handle = serialComManager.openComPort(portName, true, true, false);
            serialComManager.configureComPortData(handle, SerialComManager.DATABITS.DB_8, SerialComManager.STOPBITS.SB_1, SerialComManager.PARITY.P_NONE, SerialComManager.BAUDRATE.B57600, 0);
            serialComManager.configureComPortControl(handle, SerialComManager.FLOWCONTROL.NONE, 'x', 'x', false, false);
            System.out.println("Connected to port");
            return true;
//            serialComManager.readString(handle);

        } catch (Exception e) {
            System.out.println("cant connect to serial");
            return false;
        }
    }

    public void disconnectPort() {
        try {
            serialComManager.closeComPort(handle);
            handle = 0;
            System.out.println("Closed Port");
            System.out.println(isConnected());
        } catch (Exception e){
            System.out.println("cant disconnect port");

        }
    }

    public String isConnected() {

            try {
                byte[] b = {0};
                serialComManager.writeBytes(handle, b); // to throw error if not connected
                return "Connected to " + serialComManager.getPortName(handle);
            } catch (Exception E) {
                return "Not Connected";
            }
    }

    public void barredSerial(String l) {
        barredSerial = l.split(",");
    }


}
