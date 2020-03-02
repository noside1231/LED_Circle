import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

/**
 * Created by edisongrauman on 12/10/19.
 */
public class FileManager {

    private File currentFile;

    public FileManager() {
        currentFile  = null;
    }

    public void newFile() {
        currentFile = null;
    }

    public boolean saveFile(JSONArray obj) {

        if (currentFile == null) {
            return saveAsFile(obj);
        } else {
            if (writeFile(currentFile, obj)) {
                return true;
            } else{
                return false;
            }
        }
    }

    public boolean saveAsFile(JSONArray obj) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Save As");
        File file = fc.showSaveDialog(new Stage());

        if (writeFile(file, obj)) {
            currentFile = file;
            return true;
        } else {
            return false;
        }

    }

    public JSONArray openFile() {
        FileChooser fc = new FileChooser();

        File file = fc.showOpenDialog(new Stage());

        try {
            InputStream is = new FileInputStream(file);
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));
            String line = buf.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line).append("\n");
                line = buf.readLine();
            }
            String fileString = sb.toString();

            currentFile = file;
            return new JSONArray(fileString);

        } catch (Exception e) {
            return null;
        }
    }

    private boolean writeFile(File file, JSONArray obj) {

        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(obj.toString());
            fileWriter.flush();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public String getCurrentFileName() {
        return currentFile.getName();
    }

}
