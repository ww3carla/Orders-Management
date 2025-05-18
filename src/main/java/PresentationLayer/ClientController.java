package PresentationLayer;

import BussinessLogic.ClientBLL;
import Model.Client;
import PresentationLayer.ClientView;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

import Utils.TableGenerator;

/**
 * Controller class for managing client related actions in th GUI
 * Handles interaction between the ClientView and the business logic layer ClientBLL
 */
public class ClientController {
    private ClientBLL clientBLL;
    private ClientView clientView;

    /**
     * Constructs the ClientController and initializes listeners for user actions
     * @param clientView the GUI view for client management
     */
    public ClientController(ClientView clientView) {
        this.clientView = clientView;
        this.clientBLL = new ClientBLL();

        initListeners();
    }

    /**
     * Initializes all action listeners for the GUI buttons and table events
     */
    private void initListeners() {
        clientView.addButton.addActionListener(e -> {
            String name = clientView.nameField.getText();
            String email = clientView.emailField.getText();
            String address = clientView.addressField.getText();

            if(name.isEmpty() || email.isEmpty() || address.isEmpty()) {
                showMessage("Please fill all fields");
                return;
            }

            Client client = new Client(0, name, email, address);
            int result = clientBLL.insertClient(client);
            if(result != -1){
                showMessage("Client added successfully!");
                clearFields();
            }else{
                showMessage("Error adding client.");
            }
        });

        clientView.editButton.addActionListener(e -> {
            try{
                int id = Integer.parseInt(clientView.idField.getText());
                String name = clientView.nameField.getText();
                String email = clientView.emailField.getText();
                String address = clientView.addressField.getText();

                Client client = new Client(id, name, email, address);
                int result = clientBLL.updateClient(client);
                if(result != -1){
                    showMessage("Client updated!");
                    clearFields();
                }else{
                    showMessage("Error updating client.");
                }
            }catch(NumberFormatException ex){
                showMessage("Please enter a valid ID");
            }
        });

        clientView.deleteButton.addActionListener(e -> {
            try{
                int id = Integer.parseInt(clientView.idField.getText());
                int result = clientBLL.deleteClient(id);
                if(result != -1){
                    showMessage("Client deleted!");
                    clearFields();
                }else{
                    showMessage("Client not found.");
                }
            }catch(NumberFormatException ex){
                showMessage("Please enter a valid ID to delete");
            }
        });

        clientView.viewButton.addActionListener(e -> loadTableData());

        clientView.clientTable.getSelectionModel().addListSelectionListener(e -> {
            int row = clientView.clientTable.getSelectedRow();
            if(row >= 0){
                clientView.idField.setText(clientView.clientTable.getValueAt(row, 0).toString());
                clientView.nameField.setText(clientView.clientTable.getValueAt(row, 1).toString());
                clientView.emailField.setText(clientView.clientTable.getValueAt(row, 2).toString());
                clientView.addressField.setText(clientView.clientTable.getValueAt(row, 3).toString());
            }
        });
    }

    /**
     * Loads all clients from the database and displays them in the table
     */
    private void loadTableData() {
        List<Client> clients = clientBLL.getAllClients();
        TableGenerator.populateTable(clients, clientView.tableModel);
    }

    /**
     * Shows a dialog message to the user
     * @param message the message to display
     */
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(clientView, message);
    }

    /**
     * Clears all input fields in the form
     */
    public void clearFields(){
        clientView.idField.setText("");
        clientView.nameField.setText("");
        clientView.emailField.setText("");
        clientView.addressField.setText("");
    }
}
