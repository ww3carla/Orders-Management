package DataAccess;

import Model.Product;

/**
 * Data Access Object for the product entity
 * Inherits generic operations from AbstractDAO
 */
public class ProductDAO extends AbstractDAO<Product> {
    /**
     * Constructs a new ProductDAO
     * Uses reflection in AbstractDAO to handle operations for the Client class
     */
    public ProductDAO() {
        super(Product.class);
    }

}
