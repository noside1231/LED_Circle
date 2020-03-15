import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by edisongrauman on 3/14/20.
 */
public class NewNote {

    private JSONObject data;
    private int noteNumber;
    private CirclePresetParameters circlePresetParameters;

    public NewNote(int i) {
        noteNumber = i;
        circlePresetParameters = new CirclePresetParameters();
        data = new JSONObject();
        try {
            data.put("NoteNumber", noteNumber);
        } catch (Exception e) {}
        try {
            data.put("TimingIn", 0.0);
        } catch (Exception e) {}
        try {
            data.put("TimingHold", 0.0);
        } catch (Exception e) {}
        try {
            data.put("TimingOut", 0.0);
        } catch (Exception e) {}
        try {
            data.put("AlwaysOn", true);
        } catch (Exception e) {}
        try {
            data.put("NoteName", "");
        } catch (Exception e) {}
    }

    public void updateNote(JSONArray o) {
        try {
            if (o.get(0).equals("TimingIn")) {
                data.put(o.getString(0),o.get(1));
            } else if (o.get(0).equals("TimingHold")) {
                data.put(o.getString(0),o.get(1));
            } else if (o.get(0).equals("TimingOut")) {
                data.put(o.getString(0),o.get(1));
            } else if (o.get(0).equals("NoteName")) {
                data.put(o.getString(0),o.get(1));
            } else if (o.get(0).equals("AlwaysOn")) {
                data.put(o.getString(0), o.get(1));
            }
            else {
                circlePresetParameters.updateData(o);
            }

        } catch (Exception e) {}
    }

    public JSONObject getData() {
        JSONObject o = new JSONObject();
        try {
            o.put("GeneralData", data);
            o.put("F1", circlePresetParameters.getData());
        } catch (Exception e) {}
        return o;
    }


}
