package DataAccess;

import Model.Client;

/**
 * Data Access Object for the client entity
 * Inherits generic operations from AbstractDAO
 */
public class ClientDAO extends AbstractDAO<Client> {
    /**
     * Constructs a new ClientDAO
     * Uses reflection in AbstractDAO to handle operations for the Client class
     */
    public ClientDAO() {
        super(Client.class);
    }

}
