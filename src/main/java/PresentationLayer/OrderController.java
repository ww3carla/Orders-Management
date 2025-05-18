package PresentationLayer;

import BussinessLogic.*;
import DataAccess.*;
import Model.*;
import Utils.TableGenerator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.HashMap;

/**
 * Controller class that manages the logic for handling orders in the application
 * @author Carla
 * @version 1
 */
public class OrderController {
    private OrderView view;
    private OrderBLL orderBLL;
    private ProducBLL productBLL;
    private ClientBLL clientBLL;

    private HashMap<String, Integer> clientMap = new HashMap<>();
    private HashMap<String, Product> productMap = new HashMap<>();

    /**
     * Constructs the OrderController and initializes listeners for user actions
     * @param view the GUI view for order management
     */
    public OrderController(OrderView view) {
        this.view = view;
        this.orderBLL = new OrderBLL();
        this.clientBLL = new ClientBLL();
        this.productBLL = new ProducBLL();

        loadClients();
        loadProducts();
        loadOrders();
        initListeners();
    }

    /**
     * Initializes all action listeners for the GUI buttons and table events
     * Handles the logic for placing order, checking stock, updating the product
     * inserting the order, and generating bill
     */
    private void initListeners(){
        view.placeOrderButton.addActionListener(e -> {
            String clientName = (String) view.clientComboBox.getSelectedItem();
            String productName = (String) view.productComboBox.getSelectedItem();
            String quantityText = view.quantityField.getText();

            if(clientName.isEmpty() || productName.isEmpty() || quantityText.isEmpty()){
                showMessage("Please complete all the fields");
                return;
            }

            try{
                int quantity = Integer.parseInt(quantityText);
                int clientId = clientMap.get(clientName);
                Product product = productMap.get(productName);

                if(product.getStock() < quantity){
                    showMessage("Insufficient Stock");
                    return;
                }

                Order order = new Order(0, clientId, product.getId(), quantity);
                int orderId = orderBLL.insertOrder(order);
                order.setId(orderId);

                product.setStock(product.getStock() - quantity);
                productBLL.updateProduct(product);

                Client client = clientBLL.findClientByID(clientId);
                orderBLL.generateBill(order, client, product);

                showMessage("Order placed and bill generated successfully");
                loadOrders();
                view.quantityField.setText("");
            }catch (NumberFormatException ex){
                showMessage("Quantity is not valid");
            }
        });
    }

    /**
     * Loads all clients from a database into the clientComboBox
     * Also updates the internal clientMap used to retrieve client IDs name
     */
    private void loadClients(){
        List<Client> clients = clientBLL.getAllClients();
        view.clientComboBox.removeAllItems();
        clientMap.clear();

        for(Client client : clients){
            String name = client.getName();
            view.clientComboBox.addItem(name);
            clientMap.put(name, client.getId());
        }
    }

    /**
     * Loads all products from the database into the productComboBox
     * Also updates the internal productMap used to retrieve Product objects by name
     */
    public void loadProducts(){
        List<Product> products = productBLL.getAllProducts();
        view.productComboBox.removeAllItems();
        productMap.clear();

        for(Product product : products){
            String name = product.getName();
            view.productComboBox.addItem(name);
            productMap.put(name, product);
        }
    }

    /**
     * Loads all clients from the database and displays them in the table
     */
    private void loadOrders(){
        List<Order> orders = orderBLL.getAllOrders();
        TableGenerator.populateTable(orders, view.orderTableModel);
    }

    /**
     * Shows a dialog message to the user
     * @param message the message to display
     */
    private void showMessage(String message){
        JOptionPane.showMessageDialog(view, message);
    }
}
