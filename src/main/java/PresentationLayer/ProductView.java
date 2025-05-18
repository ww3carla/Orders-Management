package PresentationLayer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * GUI class for managing products
 * @author Carla Bozintan
 * @version 1
 */
public class ProductView extends JFrame {
    public JTextField idField = new JTextField(5);
    public JTextField nameField = new JTextField(15);
    public JTextField priceField = new JTextField(15);
    public JTextField stockField = new JTextField(15);

    public JButton addButton = new JButton("Add Product");
    public JButton editButton = new JButton("Edit Product");
    public JButton deleteButton = new JButton("Delete Product");
    public JButton viewButton = new JButton("View ALL Products");

    public JTable table = new JTable();
    public DefaultTableModel model;

    /**
     * Construct the ProductView window
     * Initializes all form fields, buttons, table and layout
     */
    public ProductView() {
        setTitle("Product Management");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Product Data"));

        panel.add(new JLabel("ID"));
        panel.add(idField);

        panel.add(new JLabel("Name"));
        panel.add(nameField);

        panel.add(new JLabel("Price"));
        panel.add(priceField);

        panel.add(new JLabel("Stock"));
        panel.add(stockField);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);

        model = new DefaultTableModel();
        table.setModel(model);
        JScrollPane scrollPanel = new JScrollPane(table);

        add(panel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
        add(scrollPanel, BorderLayout.CENTER);
    }
}
