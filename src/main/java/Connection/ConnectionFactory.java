package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ConnectionFactory is a singleton class that manages database connections.
 * It loads the JDBC driver, creates and provides connections, and handles closing of resources
 */
public class ConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/orderdb";
    private static final String USER = "root";
    private static final String PASS = "BernardoCarla2frumosi";

    private static ConnectionFactory singleInstance = new ConnectionFactory();

    /**
     * Private constructor that loads the JDBC driver
     */
    private ConnectionFactory() {
        try{
            Class.forName(DRIVER);
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Creates a database connection
     * @return a Connection object to the databasde, or null if failed
     */
    private Connection createConnection(){
        Connection con = null;
        try{
            con = DriverManager.getConnection(DBURL, USER, PASS);
        }catch(SQLException ex){
            LOGGER.log(Level.WARNING, "An error occurred while connecting to the database");
            ex.printStackTrace();
        }
        return con;
    }

    /**
     * Returns a connection instance
     * @return a new database connection
     */
    public static Connection getConnection() {
        return singleInstance.createConnection();
    }

    /**
     * Closes a given Connection object
     * @param con the Connection to close
     */
    public static void close(Connection con) {
        if(con != null){
            try{
                con.close();
            }catch(SQLException ex){
                LOGGER.log(Level.WARNING, "An error occurred while closing the database");
            }
        }
    }

    /**
     * Closes a given Statement object
     * @param stmt the Statement to close
     */
    public static void close(Statement stmt) {
        if(stmt != null){
            try{
                stmt.close();
            }catch(SQLException ex){
                LOGGER.log(Level.WARNING, "An error occurred while closing the statement");
            }
        }
    }

    /**
     * Closes a given ResultSet object
     * @param rs the ResultSet to close
     */
    public static void close(ResultSet rs) {
        if(rs != null){
            try{
                rs.close();
            }catch(SQLException ex){
                LOGGER.log(Level.WARNING, "An error occurred while closing the ResultSet");
            }
        }
    }
}
