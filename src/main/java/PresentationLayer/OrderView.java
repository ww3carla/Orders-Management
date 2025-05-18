package PresentationLayer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * GUI class for managing and placing orders
 * Provides a form for selecting a clients and product, entering a quantity,
 * and placing an order. Also displays all orders in a table
 * @author Carla Bozintan
 * @version 1
 */
public class OrderView extends JFrame {
    public JComboBox<String> clientComboBox = new JComboBox<>();
    public JComboBox<String> productComboBox = new JComboBox<>();
    public JTextField quantityField = new JTextField(10);
    public JButton placeOrderButton = new JButton("Place Order");

    public JTable orderTable = new JTable();
    public DefaultTableModel orderTableModel;

    /**
     * Constructs an order management GUI window
     * Initializes layout, input components, action buttons, and the orders table
     */
    public OrderView() {
        setTitle("Order Management");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Order Details"));

        panel.add(new JLabel("Client:"));
        panel.add(clientComboBox);

        panel.add(new JLabel("Product:"));
        panel.add(productComboBox);

        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);

        panel.add(new JLabel(""));
        panel.add(placeOrderButton);

        orderTableModel = new DefaultTableModel();
        orderTable.setModel(orderTableModel);
        JScrollPane scrollPane = new JScrollPane(orderTable);

        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.NORTH);
    }
}
