import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GetPage extends JFrame {
    private DefaultTableModel model; // Model for the JTable
    private JTable table; // JTable to display product data
    private JTextField productNameField;
    private JTextField quantityField;
    private JTextField catNumField;
    private JTextField productCodeField;

    public GetPage() {
        setTitle("Product Management");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set up the table model and JTable
        String[] columnNames = {"Product ID", "Product Name", "Quantity", "Category Number", "Product Code"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Create input fields
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        productNameField = new JTextField();
        quantityField = new JTextField();
        catNumField = new JTextField();
        productCodeField = new JTextField();

        inputPanel.add(new JLabel("Product Name:"));
        inputPanel.add(productNameField);
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(quantityField);
        inputPanel.add(new JLabel("Category Number:"));
        inputPanel.add(catNumField);
        inputPanel.add(new JLabel("Product Code:"));
        inputPanel.add(productCodeField);
        add(inputPanel, BorderLayout.NORTH);

        // Create buttons
        JButton insertButton = new JButton("Insert Product");
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleInsertProduct();
            }
        });

        JButton showDataButton = new JButton("Show Products");
        showDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showProducts();
            }
        });

        JButton filterButton = new JButton("Filter by Category");
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String category = JOptionPane.showInputDialog("Enter Category Number:");
                if (category != null) {
                    filterProductsByCategory(Integer.parseInt(category));
                }
            }
        });

        // Add buttons to the frame
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(insertButton);
        buttonPanel.add(showDataButton);
        buttonPanel.add(filterButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void handleInsertProduct() {
        String productName = productNameField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        int catNum = Integer.parseInt(catNumField.getText());
        String productCode = productCodeField.getText();

        Database.insertProduct(productName, quantity, catNum, productCode);
    }

    private void showProducts() {
        model.setRowCount(0);
        List<Product> products = Database.getProducts();
        for (Product product : products) {
            model.addRow(new Object[]{product.getProductID(), product.getProductName(), product.getQuantity(), product.getCatNum(), product.getProductCode()});
        }
    }

    private void filterProductsByCategory(int category) {
        model.setRowCount(0);
        List<Product> products = Database.getProductsByCategory(category);
        for (Product product : products) {
            model.addRow(new Object[]{product.getProductID(), product.getProductName(), product.getQuantity(), product.getCatNum(), product.getProductCode()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GetPage());
    }
}