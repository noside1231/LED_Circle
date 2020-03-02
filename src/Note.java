import javafx.scene.paint.Color;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by edisongrauman on 11/15/19.
 */
public class Note {

    private String name;

    private int noteNumber;

    private double fadeIn;
    private double hold;
    private double fadeOut;
    private boolean alwaysOn;

    private int r;
    private int g;
    private int b;

    private byte colorType;

    private int hue;
    private int saturation;
    private int lightness;

    private boolean strobeEnabled;
    private byte strobeSpeed;
    private int strobeDecay;
    private byte strobeType;

    private boolean rainbowEnabled;
    private boolean rainbowTiming;
    private int rainbowSpeed;
    private byte rainbowSpread;
    private int rainbowAngle;
    private int rainbowOffset;
    private int rainbowSaturation;
    private int rainbowLightness;
    private byte rainbowDirection;


    private boolean pinWheelEnabled;
    private boolean pinWheelTiming;
    private int pinWheelSpeed;
    private int pinWheelParts;

    private boolean angleEnabled;
    private boolean angleTiming;
    private int angleAngle;
    private int angleOffset;
    private int angleSpeed;

    private boolean ringEnabled;
    private boolean ringTiming;
    private int ringSpeed;
    private int ringLength;
    private boolean ringInvert;
    private byte ringType;

    private boolean scanEnabled;
    private int scanSpeed;
    private int scanLength;
    private boolean scanInvert;

    private boolean sequenceEnabled;
    private ArrayList<Integer> sequenceChoices;

    private boolean bpmSettingsEnabled;
    private byte bpmSetting;



    private ArrayList<Byte> serialData;


    public Note(int n) {
        noteNumber = n;
        name = "";

        fadeIn = 0;
        hold = 0;
        fadeOut = 0;
        alwaysOn = true;

        r = 0;
        g = 0;
        b = 0;
        colorType = 'C';

        strobeEnabled = false;
        strobeSpeed = 10;
        strobeDecay = 0;
        strobeType = 'O';

        rainbowEnabled = false;
        rainbowTiming = true;
        rainbowSpeed = 5;
        rainbowSpread = 0;
        rainbowAngle = 0;
        rainbowOffset = 0;
        rainbowSaturation = 100;
        rainbowLightness = 100;
        rainbowDirection = 'C';

        pinWheelEnabled = false;
        pinWheelTiming = true;
        pinWheelSpeed = 5;
        pinWheelParts = 5;

        angleEnabled = false;
        angleTiming = true;
        angleSpeed = 0;
        angleAngle = 0;
        angleOffset = 0;

        ringEnabled = false;
        ringTiming = true;
        ringSpeed = 1;
        ringLength = 1;
        ringInvert = false;
        ringType = 'O';

        sequenceEnabled = false;
        sequenceChoices = new ArrayList<>();

        bpmSettingsEnabled = false;
        bpmSetting = 'S';


        serialData = new ArrayList<>();


    }

    public int getNoteNumber() {
        return noteNumber;
    }

    public void setNoteNumber(int i) {
        noteNumber = i;
    }

    public String getNoteName() {
        return name;
    }

    public void setNoteName(String name) {
        this.name = name;
    }

    public double getFadeIn() {
        return fadeIn;
    }

    public void setFadeIn(double fadeIn) {
        this.fadeIn = fadeIn;
    }

    public double getHold() {
        return hold;
    }

    public void setHold(double hold) {
        this.hold = hold;
    }

    public double getFadeOut() {
        return fadeOut;
    }

    public void setFadeOut(double fadeOut) {
        this.fadeOut = fadeOut;
    }

    public boolean isAlwaysOn() {
        return alwaysOn;
    }

    public void setAlwaysOn(boolean alwaysOn) {
        this.alwaysOn = alwaysOn;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getHue() {
        return hue;
    }

    public void setHue(int hue) {
        this.hue = hue;
    }

    public int getSaturation() {
        return saturation;
    }

    public void setSaturation(int saturation) {
        this.saturation = saturation;
    }

    public int getLightness() {
        return lightness;
    }

    public void setLightness(int lightness) {
        this.lightness = lightness;
    }

    public byte getColorType() {
        return colorType;
    }

    public void setColorType(byte colorType) {
        this.colorType = colorType;
    }

    public boolean isStrobeEnabled() {
        return strobeEnabled;
    }

    public void setStrobeEnabled(boolean strobeEnabled) {
        this.strobeEnabled = strobeEnabled;
    }

    public byte getStrobeSpeed() {
        return strobeSpeed;
    }

    public void setStrobeSpeed(byte strobeSpeed) {
        this.strobeSpeed = strobeSpeed;
    }

    public int getStrobeDecay() {
        return strobeDecay;
    }

    public void setStrobeDecay(int strobeDecay) {
        this.strobeDecay = strobeDecay;
    }

    public byte getStrobeType() {
        return strobeType;
    }

    public void setStrobeType(byte strobeType) {
        this.strobeType = strobeType;
    }

    public boolean isRainbowEnabled() {
        return rainbowEnabled;
    }

    public void setRainbowEnabled(boolean rainbowEnabled) {
        this.rainbowEnabled = rainbowEnabled;
    }

    public boolean isRainbowTiming() {
        return rainbowTiming;
    }

    public void setRainbowTiming(boolean rainbowTiming) {
        this.rainbowTiming = rainbowTiming;
    }

    public int getRainbowSpeed() {
        return rainbowSpeed;
    }

    public void setRainbowSpeed(int rainbowSpeed) {
        this.rainbowSpeed = rainbowSpeed;
    }

    public int getRainbowSpread() {
        return rainbowSpread;
    }

    public void setRainbowSpread(byte rainbowSpread) {
        this.rainbowSpread = rainbowSpread;
    }

    public int getRainbowAngle() {
        return rainbowAngle;
    }

    public void setRainbowAngle(int rainbowAngle) {
        this.rainbowAngle = rainbowAngle;
    }

    public int getRainbowOffset() {
        return rainbowOffset;
    }

    public void setRainbowOffset(int rainbowOffset) {
        this.rainbowOffset = rainbowOffset;
    }

    public int getRainbowSaturation() {
        return rainbowSaturation;
    }

    public void setRainbowSaturation(int rainbowSaturation) {
        this.rainbowSaturation = rainbowSaturation;
    }

    public int getRainbowLightness() {
        return rainbowLightness;
    }

    public void setRainbowLightness(int rainbowLightness) {
        this.rainbowLightness = rainbowLightness;
    }

    public byte getRainbowDirection() {
        return rainbowDirection;
    }

    public void setRainbowDirection(byte rainbowDirection) {
        this.rainbowDirection = rainbowDirection;
    }

    public boolean isPinWheelEnabled() {
        return pinWheelEnabled;
    }

    public void setPinWheelEnabled(boolean pinWheelEnable) {
        this.pinWheelEnabled = pinWheelEnable;
    }

    public boolean isPinWheelTiming() {
        return pinWheelTiming;
    }

    public void setPinWheelTiming(boolean pinWheelTiming) {
        this.pinWheelTiming = pinWheelTiming;
    }

    public int getPinWheelSpeed() {
        return pinWheelSpeed;
    }

    public void setPinWheelSpeed(int pinWheelSpeed) {
        this.pinWheelSpeed = pinWheelSpeed;
    }

    public int getPinWheelParts() {
        return pinWheelParts;
    }

    public void getPinWheelParameters() {

    }

    public void setPinWheelParts(int pinWheelParts) {
        this.pinWheelParts = pinWheelParts;
    }

    public boolean isAngleEnabled() {
        return angleEnabled;
    }

    public void setAngleEnabled(boolean angleEnabled) {
        this.angleEnabled = angleEnabled;
    }

    public boolean isAngleTiming() {
        return angleTiming;
    }

    public void setAngleTiming(boolean angleTiming) {
        this.angleTiming = angleTiming;
    }

    public int getAngleAngle() {
        return angleAngle;
    }

    public void setAngleAngle(int angleAngle) {
        this.angleAngle = angleAngle;
    }

    public int getAngleOffset() {
        return angleOffset;
    }

    public void setAngleOffset(int angleOffset) {
        this.angleOffset = angleOffset;
    }

    public int getAngleSpeed() {
        return angleSpeed;
    }

    public void setAngleSpeed(int angleSpeed) {
        this.angleSpeed = angleSpeed;
    }

    public boolean isRingEnabled() {
        return ringEnabled;
    }

    public void setRingEnabled(boolean ringEnabled) {
        this.ringEnabled = ringEnabled;
    }

    public boolean isRingTiming() {
        return ringTiming;
    }

    public void setRingTiming(boolean ringTiming) {
        this.ringTiming = ringTiming;
    }

    public int getRingSpeed() {
        return ringSpeed;
    }

    public void setRingSpeed(int ringSpeed) {
        this.ringSpeed = ringSpeed;
    }

    public int getRingLength() {
        return ringLength;
    }

    public void setRingLength(int ringLength) {
        this.ringLength = ringLength;
    }

    public boolean isRingInvert() {
        return ringInvert;
    }

    public void setRingInvert(boolean ringInvert) {
        this.ringInvert = ringInvert;
    }

    public byte getRingType() {
        return ringType;
    }

    public void setRingType(byte ringType) {
        this.ringType = ringType;
    }

    public boolean isScanEnabled() {
        return scanEnabled;
    }

    public void setScanEnabled(boolean scanEnabled) {
        this.scanEnabled = scanEnabled;
    }

    public int getScanSpeed() {
        return scanSpeed;
    }

    public void setScanSpeed(int scanSpeed) {
        this.scanSpeed = scanSpeed;
    }

    public int getScanLength() {
        return scanLength;
    }

    public void setScanLength(int scanLength) {
        this.scanLength = scanLength;
    }

    public boolean isScanInvert() {
        return scanInvert;
    }

    public void setScanInvert(boolean scanInvert) {
        this.scanInvert = scanInvert;
    }

    public boolean isSequenceEnabled() {
        return sequenceEnabled;
    }

    public void setSequenceEnabled(boolean sequenceEnabled) {
        this.sequenceEnabled = sequenceEnabled;
    }

    public ArrayList<Integer> getSequenceChoices() {
        return sequenceChoices;
    }

    public void setSequenceChoices(ArrayList<Integer> sequenceChoices) {
        this.sequenceChoices = new ArrayList<>(sequenceChoices);
    }

    public boolean isBpmSettingsEnabled() {
        return bpmSettingsEnabled;
    }

    public void setBpmSettingsEnabled(boolean bpmSettingsEnabled) {
        this.bpmSettingsEnabled = bpmSettingsEnabled;
    }

    public byte getBpmSetting() {
        return bpmSetting;
    }

    public void setBpmSetting(byte bpmSetting) {
        this.bpmSetting = bpmSetting;
    }

    public byte[] getSerialData() {

        serialData.clear();
        serialData.add((byte)'!');

        //set time
        if (alwaysOn) {
            byte b = 0;
            b = bitWrite(b,1,7); //mark B11000000
            b = bitWrite(b,1,6);
            serialData.add(b);
        } else {
            if (fadeIn != 0) {
                byte b = processTime(fadeIn);
                serialData.add(b);
            }
            if (hold != 0) {
                byte b = processTime(hold);
                b = bitWrite(b,1,6);//mark 01
                serialData.add(b);
            }
            if (fadeOut != 0) {
                byte b = processTime(fadeOut);
                b = bitWrite(b,1,7);//mark 1
                serialData.add(b);
            }
            byte b = 0;
            b = bitWrite(b,1,7);//mark B11100000 to finish
            b = bitWrite(b,1,6);
            b = bitWrite(b,1,5);
            serialData.add(b);
        }

        if (strobeEnabled) {
            serialData.add((byte)'S');
            byte strobeP = (byte)(strobeSpeed << 2);
            if (strobeType == 'O') {
                strobeP |= 1;
            } else if (strobeType == 'D') {
                strobeP |= 2;
            } else if (strobeType == 'B') {
                strobeP |= 3;
            }
            serialData.add(strobeP);
//            serialData.add((byte)strobeSpeed);
//            serialData.add((byte)strobeDecay);
        }

        if (rainbowEnabled) {
            serialData.add((byte)'R');

            byte rainbowSpeedByte = (byte) (rainbowSpeed+100); //convert -100.100 to 0-200 (7 bits)
            byte rainbowSpreadTypeByte = (byte) (rainbowSpread << 2);
            byte rainbowOffsetByte = (byte) ((rainbowOffset << 1) | (rainbowTiming ? 1:0));
            if (rainbowDirection == 'C') {
                rainbowSpreadTypeByte |= 1;
            } else if (rainbowDirection == 'L') {
                rainbowSpreadTypeByte |= 2;
            } else if (rainbowDirection == 'R') {
                rainbowSpreadTypeByte |= 3;
            }
            serialData.add(rainbowSpeedByte);
            serialData.add(rainbowSpreadTypeByte);
            serialData.add((byte)rainbowAngle);
            serialData.add(rainbowOffsetByte);
            serialData.add((byte)rainbowSaturation);
            serialData.add((byte)rainbowLightness);


        } else {
            serialData.add((byte)'C');
            serialData.add((byte)r);
            serialData.add((byte)g);
            serialData.add((byte)b);
        }

        if (pinWheelEnabled) {

            byte pinWheelSpeedByte = (byte) ((pinWheelParts << 1) | (pinWheelTiming ? 1:0));
            serialData.add((byte)'P');
            serialData.add((byte) (pinWheelSpeed+100));
            serialData.add(pinWheelSpeedByte);
        }

        if (angleEnabled) {
            byte angleSpeedByte = (byte) (((50+angleSpeed) << 1) | (angleTiming ? 1:0));
            serialData.add((byte)'A');
            serialData.add(angleSpeedByte);
            serialData.add((byte) (((float)angleAngle/360.)*255));
            serialData.add((byte) (((float)angleOffset/360.)*255));
        }

        if (ringEnabled) {
            serialData.add((byte) 'r');
            byte b = (byte)((ringSpeed << 3) | ringLength);
            serialData.add(b);
            byte r = 0;
            if (ringType == 'O') {
                r = 1;
            } else if (ringType == 'I') {
                r = 2;
            } else if (ringType == 'B') {
                r = 3;
            }
            if (ringInvert) {
                r |= 1 << 2;
            }
            if (ringTiming) {
                r |= 1 << 3;
            }
            serialData.add(r);
        }

        if (scanEnabled) {
            serialData.add((byte)'s');
            serialData.add((byte)scanSpeed);
            serialData.add((byte)scanLength);
            serialData.add((byte)(scanInvert ? (byte)1 : (byte)0));
        }

        serialData.add((byte)'@');

        while (serialData.size() < 32) {
            serialData.add((byte)0);
        }

        byte[] toSend = new byte[serialData.size()];
        for (int i = 0; i < toSend.length; i++) {
            toSend[i] = serialData.get(i);
        }

        return toSend;
    }

    public JSONObject saveNote() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("noteName", name);

            obj.put("alwaysOn", alwaysOn);
            obj.put("fadeIn", fadeIn);
            obj.put("hold", hold);
            obj.put("fadeOut", fadeOut);

            obj.put("r", r);
            obj.put("g", g);
            obj.put("b", b);

            obj.put("strobeEnabled", strobeEnabled);
            obj.put("strobeSpeed", strobeSpeed);
            obj.put("strobeDecay", strobeDecay);
            obj.put("strobeType", strobeType);

            obj.put("rainbowEnabled", rainbowEnabled);
            obj.put("rainbowTiming", rainbowTiming);
            obj.put("rainbowSpeed", rainbowSpeed);
            obj.put("rainbowSpread", rainbowSpread);
            obj.put("rainbowAngle", rainbowAngle);
            obj.put("rainbowOffset", rainbowOffset);
            obj.put("rainbowSaturation", rainbowSaturation);
            obj.put("rainbowLightness", rainbowLightness);
            obj.put("rainbowDirection", rainbowDirection);

            obj.put("pinWheelEnabled", pinWheelEnabled);
            obj.put("pinWheelTiming", pinWheelTiming);
            obj.put("pinWheelSpeed", pinWheelSpeed);
            obj.put("pinWheelParts", pinWheelParts);

            obj.put("angleEnabled", angleEnabled);
            obj.put("angleTiming", angleTiming);
            obj.put("angleAngle", angleAngle);
            obj.put("angleOffset", angleOffset);
            obj.put("angleSpeed", angleSpeed);

            obj.put("ringEnabled", ringEnabled);
            obj.put("ringTiming", ringTiming);
            obj.put("ringSpeed", ringSpeed);
            obj.put("ringLength", ringLength);
            obj.put("ringInvert", ringInvert);
            obj.put("ringType", ringType);

            obj.put("scanEnabled", scanEnabled);
            obj.put("scanSpeed", scanSpeed);
            obj.put("scanLength", scanLength);
            obj.put("scanInvert", scanInvert);

            obj.put("sequenceEnabled", sequenceEnabled);
            obj.put("sequenceChoices", sequenceChoices);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public void loadNote(JSONObject obj) {
        name = JSONHelp.loadParameterString(obj, "noteName");

        alwaysOn = JSONHelp.loadParameterBoolean(obj,"alwaysOn");
        fadeIn = JSONHelp.loadParameterDouble(obj, "fadeIn");
        hold = JSONHelp.loadParameterDouble(obj, "hold");
        fadeOut = JSONHelp.loadParameterDouble(obj, "fadeOut");

        r = JSONHelp.loadParameterInt(obj, "r");
        g = JSONHelp.loadParameterInt(obj, "g");
        b = JSONHelp.loadParameterInt(obj, "b");

        strobeEnabled =  JSONHelp.loadParameterBoolean(obj, "strobeEnabled");
        strobeSpeed = JSONHelp.loadParameterByte(obj, "strobeSpeed");
        strobeType = JSONHelp.loadParameterByte(obj, "strobeType");
        strobeDecay = JSONHelp.loadParameterInt(obj, "strobeDecay");

        rainbowEnabled = JSONHelp.loadParameterBoolean(obj, "rainbowEnabled");
        rainbowTiming = JSONHelp.loadParameterBoolean(obj, "rainbowTiming");
        rainbowSpeed = JSONHelp.loadParameterInt(obj, "rainbowSpeed");
        rainbowSpread = JSONHelp.loadParameterByte(obj, "rainbowSpread");
        rainbowAngle = JSONHelp.loadParameterInt(obj, "rainbowAngle");
        rainbowOffset = JSONHelp.loadParameterInt(obj, "rainbowOffset");
        rainbowSaturation = JSONHelp.loadParameterInt(obj, "rainbowSaturation");
        rainbowLightness = JSONHelp.loadParameterInt(obj, "rainbowLightness");
        rainbowDirection = JSONHelp.loadParameterByte(obj, "rainbowDirection");

        pinWheelEnabled = JSONHelp.loadParameterBoolean(obj, "pinWheelEnabled");
        pinWheelTiming = JSONHelp.loadParameterBoolean(obj, "pinWheelTiming");
        pinWheelSpeed = JSONHelp.loadParameterInt(obj, "pinWheelSpeed");
        pinWheelParts = JSONHelp.loadParameterInt(obj, "pinWheelParts");

        angleEnabled = JSONHelp.loadParameterBoolean(obj, "angleEnabled");
        angleTiming = JSONHelp.loadParameterBoolean(obj, "angleTiming");
        angleAngle = JSONHelp.loadParameterInt(obj, "angleAngle");
        angleOffset = JSONHelp.loadParameterInt(obj, "angleOffset");
        angleSpeed = JSONHelp.loadParameterInt(obj, "angleSpeed");

        ringEnabled = JSONHelp.loadParameterBoolean(obj, "ringEnabled");
        ringTiming = JSONHelp.loadParameterBoolean(obj, "ringTiming");
        ringSpeed = JSONHelp.loadParameterInt(obj, "ringSpeed");
        ringLength = JSONHelp.loadParameterInt(obj, "ringLength");
        ringInvert = JSONHelp.loadParameterBoolean(obj, "ringInvert");
        ringType = JSONHelp.loadParameterByte(obj, "ringType");

        scanEnabled = JSONHelp.loadParameterBoolean(obj, "scanEnabled");
        scanSpeed = JSONHelp.loadParameterInt(obj, "scanSpeed");
        scanLength = JSONHelp.loadParameterInt(obj, "scanLength");
        scanInvert = JSONHelp.loadParameterBoolean(obj, "scanInvert");

        sequenceEnabled = JSONHelp.loadParameterBoolean(obj, "sequenceEnabled");
        sequenceChoices = JSONHelp.loadParameterIntArray(obj, "sequenceChoices");

    }

    public void hsltoRGB() {

        double h = hue /360.;
        double s = saturation / 100.;
        double l = lightness /100.;

        if (s == 0) {
            r = (int)Math.round(255*l);
            g = (int)Math.round(255*l);
            b = (int)Math.round(255*l);
        } else {

            double q = l < 0.5 ? l * (1 + s) : l + s - l * s;
            double p = 2 * l - q;

            r = (int)Math.round(255*hue2rgb(p, q, h + 1/3.));
            g = (int)Math.round(255*hue2rgb(p, q, h));
            b = (int)Math.round(255*hue2rgb(p, q, h - 1/3.));

        }

    }

    public void rgbToHSL() {
        double red = r/255.;
        double green = g/255.;
        double blue = b/255.;

        double max = Math.max(Math.max(red,green), blue);
        double min = Math.min(Math.min(red, green), blue);

        double h = ((max+min)/2);
        double s = ((max+min)/2);
        double l = ((max+min)/2);

            if(max == min){
                h = s = 0; // achromatic
            }else{
                double d = max - min;

                s = l > 0.5 ? d / (2 - max - min) : d / (max + min);

                if (max == red) {
                    h = (green - blue) / d + (green < blue ? 6 : 0);
                }
                else if (max == green) {
                    h = (blue - red) / d + 2;
                }
                else if (max == blue) {
                    h = (red - green) / d + 4;
                }
                h /= 6;
            }

            hue = (int)Math.round(h * 360);
            saturation = (int)Math.round(s*100);
            lightness = (int)Math.round(l*100);
        }



    private double hue2rgb(double p, double q, double t) {
        if(t < 0) t += 1;
        if(t > 1) t -= 1;
        if(t < 1/6.) return p + (q - p) * 6 * t;
        if(t < 1/2.) return q;
        if(t < 2/3.) return p + (q - p) * (2/3. - t) * 6;
        return p;
    }

    private byte bitWrite(byte b, int bin, int ind) {
        if (bin == 1) {
            return (byte) (b | (1 << ind));
        } else {
            return (byte) (b & ~(1 << ind));
        }
    }

    private byte bitRead(byte b, int ind) {
        return (byte) ((b >> ind) & 1);
    }

    private void printByte(byte b) {
        for (int j = 7; j >= 0; j--) {
            System.out.print(bitRead(b, j));
        }
        System.out.println();
    }

    public byte processTime(double time) {
        byte b = 0;
        if (time < 3) { //if less than 3 seconds multiply by 10 to account for decimal and mark the timescale
            b = bitWrite(b,1,5);
            b |= (byte)(time*10);
        } else {
            b |= (byte)time;
        }
        return b;
    }


}
