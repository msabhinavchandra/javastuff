import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.SQLException;

public class CSVReader {

    private static final String CSV_FILE = "smartphones.csv";
    private static final String cvsSplitBy = ",";

    public static void readCSVAndInsertData(Connection conn) {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            br.readLine(); // Skip header line
            String line;
            while ((line = br.readLine()) != null) {
                String[] smartphone = line.split(cvsSplitBy);
                Smartphone.insertNewRow(conn, smartphone[0], smartphone[1], smartphone[2],
                        smartphone[3].isEmpty() ? 0 : Integer.parseInt(smartphone[3]),
                        smartphone[4].isEmpty() ? 0 : Integer.parseInt(smartphone[4]),
                        smartphone[5], "Yes".equals(smartphone[6]),
                        new BigDecimal(smartphone[7]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}