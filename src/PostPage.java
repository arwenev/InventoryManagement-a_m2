import javax.swing.*;

public class PostPage extends JFrame {
    private JTextField nameField;
    private JTextField quantityField;
    private JTextField catNumField;
    private JTextField productCodeField; // Add ProductCode field

    public PostPage() {
        setTitle("Post a New Product");
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel nameLabel = new JLabel("Product Name:");
        JLabel quantityLabel = new JLabel("Quantity:");
        JLabel catNumLabel = new JLabel("Category Number:");
        JLabel productCodeLabel = new JLabel("Product Code:"); // Label for ProductCode

        nameField = new JTextField(20);
        quantityField = new JTextField(20);
        catNumField = new JTextField(20);
        productCodeField = new JTextField(20); // TextField for ProductCode

        JButton submitButton = new JButton("Submit");

        // Action listener for the submit button
        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String quantity = quantityField.getText();
            String catNum = catNumField.getText();
            String productCode = productCodeField.getText(); // Get ProductCode

            // Validate input before submitting
            if (Validator.isValidProduct(name, quantity, catNum, productCode)) {
                String message = Database.insertProduct(name, Integer.parseInt(quantity), Integer.parseInt(catNum), productCode);

                // Display the result of the insertion
                JOptionPane.showMessageDialog(this, message);

                if (message.equals("Product inserted successfully.")) {
                    clearFields(); // Clear the fields after successful submission
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid input. Please check your entries.");
            }
        });

        // Layout for the input fields and button
        JPanel panel = new JPanel();
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(quantityLabel);
        panel.add(quantityField);
        panel.add(catNumLabel);
        panel.add(catNumField);
        panel.add(productCodeLabel); // Add ProductCode label to panel
        panel.add(productCodeField); // Add ProductCode field to panel
        panel.add(submitButton);

        // Add panel to the frame
        add(panel);
        setVisible(true);
    }

    // Method to clear input fields
    private void clearFields() {
        nameField.setText("");
        quantityField.setText("");
        catNumField.setText("");
        productCodeField.setText("");
    }
}