package confData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigRW {

    public static Map<Object, Object> propsMap = new HashMap<>();
    public static Map afterMap = propsMap;
    //    static ConfData configData = new ConfData();
    static Properties config = new Properties();
    static InputStream iStream;

    static {
        try {
            iStream = new FileInputStream("Config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            config.load(iStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adding all <K,V> from config.properties file into Map
     *
     * @return
     */
    public static Map addConfigToMap() {
        propsMap.putAll(config);
        return propsMap;
    }

    public static String getString(String key) {
        String stringValue = propsMap.get(key).toString();
        return stringValue;
    }


    public static int getInt(String key) {
        String strValue = propsMap.get(key).toString();
        int intValue = Integer.parseInt(strValue);
        return intValue;
    }


    public static boolean getBool(String key) {
        String value = propsMap.get(key).toString();
        if (value == null) return false;
        if (value.length() == 0) return false;
        if (value.isEmpty()) return false;
        if (value.equals("true")) return true;
        if (value.equals("TRUE")) return true;
        if (value.equals("1")) return true;
        if (value.equals("YES")) return true;
        if (value.equals("yes")) return true;
        if (value.equals("ON")) return true;
        if (value.equals("on")) return true;
        return false;
    }


}
