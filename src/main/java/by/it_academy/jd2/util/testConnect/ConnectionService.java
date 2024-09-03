package by.it_academy.jd2.util.testConnect;

import by.it_academy.jd2.util.PropertiesUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionService {
    private final String urlKey;            // = "db.url";
    private final String usernameKey;        // = "db.username";
    private final String passwordKey;        // = "db.password";

    public ConnectionService(String urlKey, String usernameKey, String passwordKey) {
        this.urlKey = urlKey;
        this.usernameKey = usernameKey;
        this.passwordKey = passwordKey;
    }

    static {
        loadDriver();
    }

    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection open() {
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.get(urlKey),
                    PropertiesUtil.get(usernameKey),
                    PropertiesUtil.get(passwordKey)
            );
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка подключения",e);
        }
    }
}
