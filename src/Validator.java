public class Validator {
    public static boolean isValidProduct(String name, String quantity, String catNum, String productCode) {
        // Validate product name
        if (name == null || name.trim().isEmpty()) return false;

        // Validate quantity
        try {
            int qty = Integer.parseInt(quantity);
            if (qty < 0) return false; // Check for non-negative values
        } catch (NumberFormatException e) {
            return false; // Quantity is not a valid integer
        }

        // Validate category number
        try {
            int category = Integer.parseInt(catNum);
            if (category < 0) return false; // Check for non-negative values
        } catch (NumberFormatException e) {
            return false; // Category number is not a valid integer
        }

        // Validate product code
        return isValidProductCode(productCode); // Call the correct method name
    }

    public static boolean isValidProductCode(String productCode) { // Correct method name
        // Check if the productCode starts with "ATM"
        return productCode != null && productCode.startsWith("ATM");
    }
}