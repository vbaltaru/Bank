import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Bazadedate {
    private static final String URL = "jdbc:mysql://backupbanca-backupbanca.i.aivencloud.com:23977/defaultdb?ssl-mode=REQUIRED";
    private static final String USER = "avnadmin";
    private static final String PASSWORD = "AVNS_JdCH05NfR8A2LRFkyj-";

    public static Connection getConnection() throws SQLException {
        try {
            // Încarcă explicit driverul MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driverul MySQL nu a fost găsit.", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}