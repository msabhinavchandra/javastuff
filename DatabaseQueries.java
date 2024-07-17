import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseQueries {

    public static void getTotalNumberOfiPhones(Connection conn) throws SQLException {
        String sql = "SELECT COUNT(*) AS total_iphones FROM smartphones WHERE Brand like '%Apple%' ";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                System.out.println("Total number of iPhones: " + rs.getInt("total_iphones"));
            }
        }
    }

    public static void getPhonesUnder500(Connection conn) throws SQLException {
        String sql = "SELECT Smartphone, Model, Final_Price FROM smartphones WHERE Final_Price < 500 limit 5";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Smartphone: " + rs.getString("Smartphone") + ", Model: " + rs.getString("Model")
                        + ", Price: " + rs.getBigDecimal("Final_Price"));
            }
        }
    }

    public static void getAllSamsungModels(Connection conn) throws SQLException {
        String sql = "SELECT * FROM smartphones WHERE Brand = 'Samsung' limit 5";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // Assuming you have columns named 'Model' and 'Final_Price'
                System.out.println("Model: " + rs.getString("Model") + ", Price: " + rs.getBigDecimal("Final_Price"));
            }
        }
    }

    public static void getCheapestNothingPhone(Connection conn) throws SQLException {
        String sql = "SELECT * FROM smartphones WHERE Brand = 'Nothing' ORDER BY Final_Price ASC LIMIT 1";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                System.out.println("Cheapest Nothing phone: " + rs.getString("Smartphone") + ", Model: "
                        + rs.getString("Model") + ", Price: " + rs.getBigDecimal("Final_Price"));
            }
        }
    }

    public static void main(String[] args) {
        try (Connection conn = DatabaseUtils.getConnection()) {
            getTotalNumberOfiPhones(conn);
            getPhonesUnder500(conn);
            getAllSamsungModels(conn);
            getCheapestNothingPhone(conn);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}