package DataAccess;

import Connection.ConnectionFactory;
import Model.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.reflect.Field;
import java.sql.*;

/**
 * Data Access Object for handling operations related to the Bill entity
 * Bills are stored in the 'log' table and are immutable
 * Only insert and read operations are allowed
 */
public class BillDAO {
    protected static final Logger LOGGER = Logger.getLogger(BillDAO.class.getName());

    /**
     * Inserts a new Bill into the log table
     * @param bill the Bill object inserted
     * @return the generated ID of the inserted bill, or -1 if the insertion fails
     */
    public int insert(Bill bill) {
        String query = "INSERT INTO log (orderId, clientName, productName, quantity, totalPrice) VALUES (?, ?, ?, ?, ?)";
        try(Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            ps.setInt(1, bill.orderId());
            ps.setString(2, bill.clientName());
            ps.setString(3, bill.productName());
            ps.setInt(4, bill.quantity());
            ps.setDouble(5, bill.totalPrice());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                return rs.getInt(1);
            }
        }catch(SQLException ex){
            LOGGER.log(Level.WARNING, "BillDAO:insert" + ex.getMessage());
        }
        return -1;
    }

    /**
     * Retrieves all Bill records from the log table
     * @return a list of Bill objects
     */
    public List<Bill> findAll() {
        List<Bill> list = new ArrayList<>();
        String query = "SELECT * FROM log";

        try(Connection con = ConnectionFactory.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query)){

            while(rs.next()){
                Bill b = new Bill(
                        rs.getInt("id"),
                        rs.getInt("orderId"),
                        rs.getString("clientName"),
                        rs.getString("productName"),
                        rs.getInt("quantity"),
                        rs.getDouble("totalPrice")
                );
                list.add(b);
            }
        }catch(SQLException ex){
            LOGGER.log(Level.WARNING, "BillDAO:findAll" + ex.getMessage());
        }
        return list;
    }
}
