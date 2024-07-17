import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseUtils {

    // private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    // private static final String DB_URL = "jdbc:mysql://localhost:3306/mysql";
    // private static final String USER = "root";
    // private static final String PASS = "Abhinav@ms1";

    private static Map<String, String> env;
    static {
        try {
            env = loadEnv();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load .env file");
        }
    }

    private static final String JDBC_DRIVER = env.get("JDBC_DRIVER");
    private static final String DB_URL = env.get("DB_URL");
    private static final String USER = env.get("USER");
    private static final String PASS = env.get("PASS");

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
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

    public static Map<String, String> loadEnv() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(".env"));
        Map<String, String> env = new HashMap<>();
        for (String line : lines) {
            if (line.contains("=")) {
                String[] parts = line.split("=", 2);
                env.put(parts[0], parts[1]);
            }
        }
        return env;
    }
}