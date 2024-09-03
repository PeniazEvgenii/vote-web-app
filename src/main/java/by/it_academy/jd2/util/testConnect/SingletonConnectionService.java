package by.it_academy.jd2.util.testConnect;

public class SingletonConnectionService {
    private final static ConnectionService connectionService = new ConnectionService(
            "db.url",
            "db.username",
            "db.password");

    private SingletonConnectionService() {}

    public static ConnectionService getConnectionService() {
        return connectionService;
    }
}
