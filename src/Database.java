import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database"; // Update with your DB
    private static final String USER = "root"; // Update with your DB user
    private static final String PASS = "password"; // Update with your DB password

    // Method to insert a product into the database
    public static String insertProduct(String productName, int quantity, int catNum, String productCode) {
        // Validate the ProductCode before proceeding
        if (!Validator.isValidProductCode(productCode)) {
            return "Invalid ProductCode. It must start with 'ATM'.";
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO Products (ProductName, Quantity, CatNum, ProductCode) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, productName);
            stmt.setInt(2, quantity);
            stmt.setInt(3, catNum);
            stmt.setString(4, productCode); // Add ProductCode to the insert statement
            stmt.executeUpdate();
            return "Product inserted successfully."; // Return success message
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error inserting product: " + e.getMessage(); // Return error message
        }
    }

    // Method to retrieve all products from the database
    public static List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Products"; // Query to retrieve all products
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Product product = new Product(rs.getInt("ProductID"), rs.getString("ProductName"), rs.getInt("Quantity"), rs.getInt("CatNum"), rs.getString("ProductCode"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Method to retrieve products filtered by category
    public static List<Product> getProductsByCategory(int catNum) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Products WHERE CatNum = ?"; // Prepared statement to filter by category
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, catNum);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("ProductID"), rs.getString("ProductName"), rs.getInt("Quantity"), rs.getInt("CatNum"), rs.getString("ProductCode"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}