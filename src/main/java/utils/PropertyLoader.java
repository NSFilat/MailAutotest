package utils;

import java.io.*;
import java.util.Properties;

public class PropertyLoader {

    public static String loadProperty(String key) {
        Properties properties = getAllPropertiesFromFile();
        return properties.getProperty(key);
    }

    public static Properties getAllPropertiesFromFile() {
        Properties properties = new Properties();
        FileInputStream fs = null;
        InputStreamReader is = null;
        try {
            try {
                fs = new FileInputStream("src/main/resources/main.properties");
                is = new InputStreamReader(fs, "UTF-8");
            } catch (FileNotFoundException | UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            try {
                properties.load(is);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        finally {
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return properties;
    }
}
