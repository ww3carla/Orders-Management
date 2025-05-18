package DataAccess;

import Model.Order;

/**
 * Data Access Object for the order entity
 * Inherits generic operations from AbstractDAO
 */
public class OrderDAO extends AbstractDAO<Order> {
    /**
     * Constructs a new OrderDAO
     * Uses reflection in AbstractDAO to handle operations for the Client class
     */
    public OrderDAO() {
        super(Order.class);
    }

}
