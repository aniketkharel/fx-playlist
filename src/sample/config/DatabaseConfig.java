package sample.config;

import sample.utils.DatabaseNameUtils;

import java.io.IOException;

public class DatabaseConfig {
    public static String MONGO_URL_REMOTE;

    static {
        try {
            MONGO_URL_REMOTE = new DatabaseNameUtils().getDatabaseURL();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
