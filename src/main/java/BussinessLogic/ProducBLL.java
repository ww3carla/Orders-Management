package BussinessLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import Model.Client;
import Model.Product;
import DataAccess.ProductDAO;

/**
 * Bussimess Logic Layer that handles operations related to product
 * Provides methods for finding, inserting, updating and deleting products
 */
public class ProducBLL {
    private ProductDAO productDAO;

    /**
     * Constructs a ProductBLL and initializes the ProductDAO
     */
    public ProducBLL() {
        productDAO = new ProductDAO();
    }

    /**
     * Finds a product in the database
     * @param id the ID of the product to find
     * @return the Product object if found
     * @throws NoSuchElementException if the product given with ID does not exist
     */
    public Product findProductById(int id) {
        Product product = productDAO.findById(id);
        if(product == null){
            throw new NoSuchElementException("The product with id=" + id + " does not exist");
        }
        return product;
    }

    /**
     * Inserts a new product into the database
     * @param product the Product object inserted
     * @return the generated ID of the inserted product
     */
    public int insertProduct(Product product) {
        return productDAO.insert(product);
    }

    /**
     * Retrieves all product from database
     * @return a list of all Product objects
     */
    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    /**
     * Updates an existing product in the database
     * @param product the modified Product object
     * @return the number of rows affected by the update
     */
    public int updateProduct(Product product) {
        return productDAO.update(product);
    }

    /**
     * Deletes a product from the database by its ID
     * @param id the ID of the product to delete
     * @return the number of rows affected by the delete op
     */
    public int deleteProduct(int id) {
        return productDAO.delete(id);
    }
}
