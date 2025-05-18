package PresentationLayer;

import javax.swing.*;
import java.awt.*;

/**
 * The main GUI window for the Orders ManagementSystem
 * this is the first window showed to user when the app starts
 * @author Carla Bozintan
 * @version 1
 */
public class MainView extends JFrame {
    public JButton clientsButton = new JButton("Clients");
    public JButton productsButton = new JButton("Products");
    public JButton ordersButton = new JButton("Orders");
    public JButton billsButton = new JButton("View Bills");

    /**
     * Construct the main menu window of the Orders Management System\
     * Sets up the title, size, layout and the navigation buttons
     */
    public MainView() {
        setTitle("Orders Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.add(clientsButton);
        panel.add(productsButton);
        panel.add(ordersButton);
        panel.add(billsButton);

        add(panel);
    }
}
