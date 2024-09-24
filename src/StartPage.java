import javax.swing.*;

public class StartPage extends JFrame {
    public StartPage() {
        setTitle("Product Management System");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create buttons
        JButton postButton = new JButton("Insert Products");
        JButton getButton = new JButton("Find Antik Moto2");

        // Add action listeners to buttons
        postButton.addActionListener(e -> new PostPage());
        getButton.addActionListener(e -> new GetPage());

        // Create a panel to hold the buttons
        JPanel panel = new JPanel();
        panel.add(postButton);
        panel.add(getButton);

        // Add the panel to the frame
        add(panel);
        setVisible(true);
    }

    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(StartPage::new);
    }
}

