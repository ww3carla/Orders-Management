package Model;

/**
 * Represents an order placed by a client for a product
 * Contains info about the client, product, quantity and order ID
 */
public class Order {
    private int id;
    private int clientId;
    private int productId;
    private int quantity;

    /**
     * Default constructor for Order
     */
    public Order() {}

    /**
     * Constructs an Order with specified details
     * @param id
     * @param clientId
     * @param productId
     * @param quantity
     */
    public Order(int id, int clientId, int productId, int quantity) {
        this.id = id;
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getClientId() {
        return clientId;
    }
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns a string representation of the Order
     * @return a string containing order details
     */
    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", clientId=" + clientId + ", productId=" + productId + ", quantity=" + quantity + '}';
    }
}
