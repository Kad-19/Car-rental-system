import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DatabaseManagement {
    public static Connection createConnection() throws SQLException{
        final String username = "carDealer";
        final String password = "12345678";
        final String dbName = "CarRentalSystem";

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName, username, password);
        return conn;
    }
}
