package Model;

/**
 * Represents a Clients in th system
 * Contains clients specific info
 */
public class Client {
    private int id;
    private String name;
    private String email;
    private String address;

    /**
     * Default constructor for Client
     */
    public Client(){
    }

    /**
     * Constructs a Client with specified details
     * @param id
     * @param name
     * @param email
     * @param address
     */
    public Client(int id, String name, String email, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns a string representation of the Client
     * @return a string with the client's details
     */
    @Override
    public String toString() {
        return "Client: " + "id=" + id + ", name=" + name + ", email=" + email + ", address=" + address;
    }
}
