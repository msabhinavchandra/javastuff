import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Smartphone {
    // public static void insertNewRow(Connection conn, String smartphone, String brand, String model, int ram,
    //         int storage, String color, boolean free, BigDecimal finalPrice) {
    //     String INSERT_SQL = "INSERT INTO smartphones (Smartphone, Brand, Model, RAM, Storage, Color, Free, Final_Price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    //     try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_SQL)) {
    //         preparedStatement.setString(1, smartphone);
    //         preparedStatement.setString(2, brand);
    //         preparedStatement.setString(3, model);
    //         preparedStatement.setInt(4, ram);
    //         preparedStatement.setInt(5, storage);
    //         preparedStatement.setString(6, color);
    //         preparedStatement.setBoolean(7, free);
    //         preparedStatement.setBigDecimal(8, finalPrice);
    //         preparedStatement.executeUpdate();
    //         // System.out.println("New row inserted successfully.");
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }
}