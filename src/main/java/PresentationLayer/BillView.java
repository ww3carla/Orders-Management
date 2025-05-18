package PresentationLayer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * GUI class for viewing all generated bills
 * Displays a table of all bills stored in the database
 */
public class BillView extends JFrame {
    public JTable billTable = new JTable();
    public DefaultTableModel tableModel = new DefaultTableModel();
    public JButton refreshButton = new JButton("Refresh Bills");

    /**
     * Constructs the Billview GUI window
     */
    public BillView() {
        setTitle("View Bills");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        billTable.setModel(tableModel);
        JScrollPane scrollPane = new JScrollPane(billTable);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(refreshButton);

        add(buttonPanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);
    }
}
