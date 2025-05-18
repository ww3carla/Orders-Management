package PresentationLayer;

import com.mysql.cj.jdbc.interceptors.ConnectionLifecycleInterceptor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * GUI class responsible for displaying and managing client related data
 * Provides input fields, buttons, and a table to add, edit, delete, and view
 * all clients from the database
 * @author Carla Bozintan
 * @version 1
 */
public class ClientView extends JFrame {
    public JTextField idField = new JTextField(5);
    public JTextField nameField = new JTextField(15);
    public JTextField emailField = new JTextField(15);
    public JTextField addressField = new JTextField(15);

    public JButton addButton = new JButton("Add Client");
    public JButton editButton = new JButton("Edit Client");
    public JButton deleteButton = new JButton("Delete Client");
    public JButton viewButton = new JButton("View ALL Clients");

    public JTable clientTable = new JTable();
    public DefaultTableModel tableModel;

    /**
     * Constructs a client management GUI window
     * Initializes layout, input components, action buttons, and the client table
     */
    public ClientView() {
        setTitle("Client Management");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Client Data"));

        panel.add(new JLabel("ID:"));
        panel.add(idField);

        panel.add(new JLabel("Name:"));
        panel.add(nameField);

        panel.add(new JLabel("Email:"));
        panel.add(emailField);

        panel.add(new JLabel("Address:"));
        panel.add(addressField);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);

        tableModel = new DefaultTableModel();
        clientTable.setModel(tableModel);
        JScrollPane scrollPane = new JScrollPane(clientTable);

        add(panel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);
    }
}
