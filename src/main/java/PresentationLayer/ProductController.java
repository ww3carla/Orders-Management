package PresentationLayer;

import BussinessLogic.ProducBLL;
import Model.*;
import Utils.TableGenerator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.util.List;

/**
 * Controller class responsilble for handling user interactions related to products
 * @author Carla Bozintan
 * @version 1
 */
public class ProductController {
    private ProductView view;
    private ProducBLL productBll;

    /**
     * Constructs the ProductController and initializes listeners for user actions
     * @param view the GUI view for product management
     */
    public ProductController(ProductView view) {
        this.view = view;
        productBll = new ProducBLL();

        initListeners();
    }

    /**
     * Initializes all action listeners for the GUI buttons and table events
     */
    private void initListeners() {
        view.addButton.addActionListener(e -> {
            String name = view.nameField.getText();
            String priceText = view.priceField.getText();
            String stockText = view.stockField.getText();

            if(name.isEmpty() || priceText.isEmpty() || stockText.isEmpty()) {
                showMessage("Please fill all the fields");
                return;
            }

            try{
                double price = Double.parseDouble(priceText);
                int stock = Integer.parseInt(stockText);

                Product product = new Product(0, name, price, stock);
                int result = productBll.insertProduct(product);
                if(result != -1){
                    showMessage("Product added successfully");
                    clearFields();
                }else{
                    showMessage("Error adding product");
                }
            }catch(NumberFormatException ex){
                showMessage("Price and Stock must be valid numbers!");
            }
        });

        view.editButton.addActionListener(e -> {
            try{
                int id = Integer.parseInt(view.idField.getText());
                String name = view.nameField.getText();
                double price = Double.parseDouble(view.priceField.getText());
                int stock = Integer.parseInt(view.stockField.getText());

                Product product = new Product(id, name, price, stock);
                int result = productBll.updateProduct(product);
                if(result != -1){
                    showMessage("Product updated successfully");
                    clearFields();
                }else{
                    showMessage("Error updating product");
                }
            }catch(NumberFormatException ex){
                showMessage("Please enter valid numbers!");
            }
        });

        view.deleteButton.addActionListener(e -> {
            try{
                int id = Integer.parseInt(view.idField.getText());
                int result = productBll.deleteProduct(id);
                if(result != -1){
                    showMessage("Product deleted successfully");
                    clearFields();
                }else{
                    showMessage("Error deleting product");
                }
            }catch(NumberFormatException ex){
                showMessage("Please enter valid ID to delete!");
            }
        });

        view.viewButton.addActionListener(e -> loadTableData());

        view.table.getSelectionModel().addListSelectionListener(e -> {
            int row = view.table.getSelectedRow();
            if(row <= 0){
                view.idField.setText(view.table.getModel().getValueAt(row, 0).toString());
                view.nameField.setText(view.table.getModel().getValueAt(row, 1).toString());
                view.priceField.setText(view.table.getModel().getValueAt(row, 2).toString());
                view.stockField.setText(view.table.getModel().getValueAt(row, 3).toString());
            }
        });
    }

    /**
     * Loads all clients from the database and displays them in the table
     */
    private void loadTableData(){
        List<Product> products = productBll.getAllProducts();
        TableGenerator.populateTable(products, view.model);
    }

    /**
     * Shows a dialog message to the user
     * @param msg the message to display
     */
    private void showMessage(String msg){
        JOptionPane.showMessageDialog(view, msg);
    }

    /**
     * Clears all input fields in the form
     */
    private void clearFields() {
        view.idField.setText("");
        view.nameField.setText("");
        view.priceField.setText("");
        view.stockField.setText("");
    }
}
