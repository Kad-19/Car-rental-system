import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DatabaseManagement {
    public static Connection createConnection() throws SQLException{
        final String username = "root";
        final String password = "1919";
        final String dbName = "carrentalsystem";

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName, username, password);
        return conn;
    }
}
