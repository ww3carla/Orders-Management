package BussinessLogic;

import java.util.logging.Logger;

import Connection.ConnectionFactory;
import DataAccess.BillDAO;
import Model.Client;
import Model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import DataAccess.OrderDAO;
import Model.Product;
import Model.Bill;

/**
 * Business Logic Layer class that handles operations related to orders
 * It communicates with the OrderDAO for database access and generates a Bill for each order
 */
public class OrderBLL {
    private OrderDAO orderDAO;

    /**
     * Constructs an OrderBLL and initializes the OrderDAO
     */
    public OrderBLL() {
        orderDAO = new OrderDAO();
    }

    /**
     * Finds an order in the database by its ID
     * @param id the ID of the order to find
     * @return the Order object if found
     * @throws NoSuchElementException if the order with the given ID does not exist
     */
    public Order findOrderById(int id) {
        Order order = orderDAO.findById(id);
        if(order == null){
            throw new NoSuchElementException("The order with id " + id + " does not exist");
        }
        return order;
    }

    /**
     * Inserts a new order into the database
     * @param order the Order object to insert
     * @return the generated ID of the inserted order
     */
    public int insertOrder(Order order) {
        return orderDAO.insert(order);
    }

    /**
     * Retrieves all orders from database
     * @return a list off all Order objects
     */
    public List<Order> getAllOrders() {
        return orderDAO.findAll();
    }

    /**
     * Updates an existing order in the database
     * @param order the modified Order object
     * @return the number of rows affected by the update
     */
    public int updateOrder(Order order) {
        return orderDAO.update(order);
    }

    /**
     * Deletes an order from the database by its ID
     * @param id the ID of the order to delete
     * @return the number of rows affected by the delete op
     */
    public int deleteOrder(int id) {
        return orderDAO.delete(id);
    }

    /**
     * Generates a Bill object based on a given order, client, and product
     * The bill is inserted into the 'log' table
     * @param order the order details
     * @param client the client who placed the order
     * @param product the product included in the order
     */
    public void generateBill(Order order, Client client, Product product) {
        double total = product.getPrice() * order.getQuantity();

        Bill bill = new Bill(
                0,
                order.getId(),
                client.getName(),
                product.getName(),
                order.getQuantity(),
                total
        );

        BillDAO billDAO = new BillDAO();
        billDAO.insert(bill);
    }
}
