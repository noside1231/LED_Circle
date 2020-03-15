import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by edisongrauman on 3/10/20.
 */
public class CirclePresetParameters {

    private JSONObject data;


    public CirclePresetParameters() {
        data = new JSONObject();
        loadPresets("/Users/edisongrauman/IdeaProjects/LedCircle/Resources/BigCircle.json");
    }

    public JSONObject getData() {
        return data;
    }

    public void updateData(JSONArray o) {
        try {
//            System.out.println(data.get(o.getString(0)));
            data.put(o.getString(0),o.get(1));
//            System.out.println(o.getString(0) + " " + o.get(1));
        } catch (Exception e) {}
    }

    public void loadPresets(String path) {
        JSONArray a = JSONHelp.loadFromFile(path);

        for (int i = 0; i < a.length(); i++) { //iterate through presets
            try {
                JSONArray b = a.getJSONArray(i); //get preset
//                System.out.println(b.get(0));
                data.put(b.getString(0)+"Enabled", false);

                for (int j = 1; j < b.length(); j++) { //iterate through preset values
                    try {
                        JSONArray c = b.getJSONArray(j);
//                        System.out.println("-"+c.get(0));

                        if (c.get(0).equals("Slider")) {
                            data.put(b.getString(0)+c.getString(1),c.getInt(2));
//                            System.out.println(b.getString(0)+c.getString(1) + " " + c.getInt(2));
                        } else if (c.get(0).equals("Check")) {
                            data.put(b.getString(0)+c.getString(1),c.getBoolean(2));
//                            System.out.println(b.getString(0)+c.getString(1) + " " + c.getBoolean(2));
                        } else if (c.get(0).equals("Radio")) {
                            data.put(b.getString(0)+c.getString(1),c.getString(2));
//                            System.out.println(b.getString(0)+c.getString(1)+ " " + c.getString(2));
                        }

//                        for (int k = 1; k < c.length(); k++) {
//                            System.out.println("+++" + c.get(k));
//                        }

                    } catch (Exception e) {}
                }
            } catch (Exception e) {}

        }

    }
}
