import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;

/**
 * Created by edisongrauman on 3/3/20.
 */
public class Config {

    private Properties defaultProperties;
    private Properties properties;

    private String path;

    public Config(String path) {
        this.path = path;
        defaultProperties = new Properties();
        properties = new Properties(defaultProperties);
    }

    public boolean saveConfig() {
        File configFile = new File(path);
        try {
            FileWriter writer = new FileWriter(configFile);
            properties.store(writer, "Properties");
            writer.close();
        } catch (Exception e) {
            System.out.println("error");
            return false;
        }


        return true;
    }

    public boolean loadConfig() {

        File configFile = new File(path);
        try {
            FileReader reader = new FileReader(configFile);
            properties.load(reader);
            reader.close();
        } catch (Exception e) {
            System.out.println("Error");
            return false;
        }
        return true;
    }

    public void setProperty(String key, String val) {
        properties.setProperty(key,val);
        saveConfig();
    }

    public String loadProperty(String key) {
        return properties.getProperty(key, "");
    }
}
