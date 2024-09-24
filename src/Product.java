public class Product {
    private int productID;  // For database retrieval
    private String productName;
    private int quantity;
    private int catNum;
    private String productCode; // ProductCode field

    // Constructor without ProductID for user input
    public Product(String productName, int quantity, int catNum, String productCode) {
        this.productName = productName;
        this.quantity = quantity;
        this.catNum = catNum;
        this.productCode = productCode;
    }

    // Constructor for retrieving from the database
    public Product(int productID, String productName, int quantity, int catNum, String productCode) {
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.catNum = catNum;
        this.productCode = productCode;
    }

    // Getters for the fields
    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCatNum() {
        return catNum;
    }

    public String getProductCode() {
        return productCode;
    }
}