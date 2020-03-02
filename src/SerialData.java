/**
 * Created by edisongrauman on 11/22/19.
 */
public class SerialData {

    private byte[] serialData;

    public SerialData() {
        serialData = new byte[32];
        serialData[0] = '!';
    }

    private void setTime(boolean alwaysOn, float fi, float h, float fo) {
        if (alwaysOn) {

        }
//        else {

    }

    private void setColor(int r, int g, int b) {

    }

}
