import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by edisongrauman on 1/9/20.
 */
public class JSONHelp {

    public JSONHelp() {

    }

    public static int loadParameterInt(JSONObject obj, String key) {
        try {
            return obj.getInt(key);
        } catch(Exception e) {
            System.out.println("Could not load" + " " + key);
            return 0;
        }
    }

    public static byte loadParameterByte(JSONObject obj, String key) {
        try {
            return (byte)obj.getInt(key);
        } catch(Exception e) {
            System.out.println("Could not load" + " " + key);
            return 0;
        }
    }

    public static double loadParameterDouble(JSONObject obj, String key) {
        try {
            return obj.getDouble(key);
        } catch(Exception e) {
            System.out.println("Could not load" + " " + key);
            return 0;
        }
    }

    public static String loadParameterString(JSONObject obj, String key) {
        try {
            return obj.getString(key);
        } catch(Exception e) {
            System.out.println("Could not load" + " " + key);
            return "";
        }
    }

    public static Boolean loadParameterBoolean(JSONObject obj, String key) {
        try {
            return obj.getBoolean(key);
        } catch(Exception e) {
            System.out.println("Could not load" + " " + key);
            return false;
        }
    }

    public static ArrayList<Integer> loadParameterIntArray(JSONObject obj, String key) {
        try {
            ArrayList<Integer> list = new ArrayList<>();
            JSONArray j = obj.getJSONArray("sequenceChoices");
            for (int i = 0; i < j.length(); i++) {
                list.add(j.getInt(i));
            }
            return list;
        } catch(Exception e) {
            System.out.println("Could not load" + " " + key);
            return new ArrayList<>();
        }

    }

    public static JSONArray loadFromFile(String path) {
        JSONArray a = new JSONArray();
        try {
            File file = new File(path);
            InputStream is = new FileInputStream(file);
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));
            String line = buf.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line).append("\n");
                line = buf.readLine();
            }
            String fileString = sb.toString();

            return new JSONArray(fileString);

        } catch (Exception e) {
        }
        return null;
    }


}
