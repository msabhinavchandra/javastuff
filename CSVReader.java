import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CSVReader {

    private static final String CSV_FILE = "smartphones.csv";
    private static final String cvsSplitBy = ",";

    public static int readCSVAndInsertData(Connection conn) {
        int x = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            br.readLine(); // Skip header line
            String line;
            while ((line = br.readLine()) != null) {
                x++;
                String[] smartphone = line.split(cvsSplitBy);
                // Extract and parse the smartphone details
                String brand = smartphone[0];
                String model = smartphone[1];
                String operatingSystem = smartphone[2];

                int ram = smartphone[3].isEmpty() ? 0 : Integer.parseInt(smartphone[3]);
                int storage = smartphone[4].isEmpty() ? 0 : Integer.parseInt(smartphone[4]);

                String color = smartphone[5];
                boolean isAvailable = "Yes".equals(smartphone[6]);

                BigDecimal price = new BigDecimal(smartphone[7]);

                // Insert the new row with the extracted and parsed details
                insertNewRow(conn,
                        brand,
                        model,
                        operatingSystem,
                        ram,
                        storage,
                        color,
                        isAvailable,
                        price);

            }
        } catch (IOException e) {
            e.printStackTrace();
            x--;
        }
        return x;
    }

    public static void insertNewRow(Connection conn, String smartphone, String brand, String model, int ram,
            int storage, String color, boolean free, BigDecimal finalPrice) {
        String INSERT_SQL = "INSERT INTO smartphones (Smartphone, Brand, Model, RAM, Storage, Color, Free, Final_Price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_SQL)) {
            preparedStatement.setString(1, smartphone);
            preparedStatement.setString(2, brand);
            preparedStatement.setString(3, model);
            preparedStatement.setInt(4, ram);
            preparedStatement.setInt(5, storage);
            preparedStatement.setString(6, color);
            preparedStatement.setBoolean(7, free);
            preparedStatement.setBigDecimal(8, finalPrice);
            preparedStatement.executeUpdate();
            // System.out.println("New row inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}