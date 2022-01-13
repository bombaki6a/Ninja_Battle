package Settings;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

// Class that extracts the data from the settings file
public final class Configs {
    private final static String user = "user_name";
    private final static String ip = "server_ip";
    private final static String port = "server_port";
    private final static String fileName = "settings.json";

    private static JSONObject jsonData() {
        try (FileReader file = new FileReader(fileName)) {
            return (JSONObject) new JSONParser().parse(file);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getName() {
        return (String) jsonData().get(user);
    }

    public static String getIp() {
        return (String) jsonData().get(ip);
    }

    public static Integer getPort() {
        return ((Number) jsonData().get(port)).intValue();
    }
}
