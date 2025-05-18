package Model;

/**
 * Represent a product in the system
 * Contains details about the product
 */
public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;

    /**
     * Default constructor for Product
     */
    public Product(){
    }

    /**
     * Constructs a Product with specified details
     * @param id
     * @param name
     * @param price
     * @param stock
     */
    public Product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
}
