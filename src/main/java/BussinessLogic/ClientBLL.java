package BussinessLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import DataAccess.ClientDAO;
import Model.Client;

/**
 * Business Logic Layer for the Client entity
 * Provides methods for searching and managing clients
 * Connects database operations with the graphical user interface
 */

public class ClientBLL {
    private ClientDAO clientDAO;

    /**
     * Constructs a ClientBLL and initializes the ClientDAO
     */
    public ClientBLL() {
        clientDAO = new ClientDAO();
    }

    /**
     * Finds a client by their ID
     * @param id the ID of the client to find
     * @return the found Client object
     * @throws NoSuchElementException if no client with the given ID exists
     */
    public Client findClientByID(int id) {
        Client client = clientDAO.findById(id);
        if(client == null){
            throw new NoSuchElementException("The client with id =" + id + " does not exist");
        }
        return client;
    }

    /**
     * Inserts a new client into the database
     * @param client the Clients object to insert
     * @return the generated ID of the inserted Client
     */
    public int insertClient(Client client) {
        return clientDAO.insert(client);
    }

    /**
     * Retrieves all clients from database
     * @return a lists of all Client objects
     */
    public List<Client> getAllClients() {
        return clientDAO.findAll();
    }

    /**
     * Updates an existing client in the database
     * @param client the modified Client object
     * @return the number of affected rows
     */
    public int updateClient(Client client) {
        return clientDAO.update(client);
    }

    /**
     * Deletes a client from database by ID
     * @param id the ID of the client to delete
     * @return the number of affected rows
     */
    public int deleteClient(int id) {
        return clientDAO.delete(id);
    }
}
