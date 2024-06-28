import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtils {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mysql";
    private static final String USER = "root";
    private static final String PASS = "Abhinav@ms1";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public static void createTableIfNotExists(Connection conn) throws SQLException {
        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS smartphones (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "Smartphone VARCHAR(255), " +
                "Brand VARCHAR(255), " +
                "Model VARCHAR(255), " +
                "RAM INT, " +
                "Storage INT, " +
                "Color VARCHAR(255), " +
                "Free BOOLEAN, " +
                "Final_Price DECIMAL(10, 2)" +
                ")";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(CREATE_TABLE_SQL);
        }
    }
}