import java.math.BigDecimal;
import java.sql.Connection;

public class MainApp {

    public static void main(String[] args) {
        try {
            Connection conn = DatabaseUtils.getConnection();
            DatabaseUtils.createTableIfNotExists(conn);
            // CSVReader.readCSVAndInsertData(conn);
            int x = CSVReader.readCSVAndInsertData(conn);
            System.out.println(x + " rows inserted successfully.");
            CSVReader.insertNewRow(conn, "Example Smartphone", "Example Brand", "Example Model", 4, 128, "Black", true,
                    new BigDecimal("9.99"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
