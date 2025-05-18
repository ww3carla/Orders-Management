package DataAccess;

import Connection.ConnectionFactory;
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

import Model.Product;
import Model.Client;
import Model.Order;

/**
 * Generic abstract DAO class that provides common database operations for any model type.
 * Uses reflection to dynamically build queries and map result sets.
 * @param <T> the type of the object this DAO manages
 */


public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;

    /**
     * Constructor that sees the type of the object handled by this DAO.
     * @param type Class object representing the model
     */
    public AbstractDAO(Class<T> type) {
        this.type = type;
    }

    /**
     * Finds an object in the database by its ID
     * @param id the primary key of the object
     * @return the object found, or null if not found
     */
    public T findById(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM `" + type.getSimpleName().toLowerCase() + "` WHERE id = ?";
        try{
            con = ConnectionFactory.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if(rs.next()){
                return createObject(rs);
            }
        }catch(Exception e){
            LOGGER.log(Level.WARNING, type.getSimpleName() + "DAO:findById" + e.getMessage());
        }finally{
            ConnectionFactory.close(rs);
            ConnectionFactory.close(ps);
            ConnectionFactory.close(con);
        }
        return null;
    }

    /**
     * Inserts a new object into the database.
     * Automatically ignores the id field, wich is typically auto incremented
     * @param obj the object inserted
     * @return the generated ID of the inserted row
     */
    public int insert(T obj) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int insertedId = -1;

        StringBuilder fields = new StringBuilder();
        StringBuilder values = new StringBuilder();
        List<Field> validFields = new ArrayList<>();

        for(Field field : type.getDeclaredFields()){
            if(!field.getName().equalsIgnoreCase("id")){
                fields.append(field.getName()).append(",");
                values.append("?,");
                validFields.add(field);
            }
        }

        String query = "INSERT INTO `" + type.getSimpleName().toLowerCase() + "` (" + fields.substring(0, fields.length() - 1) + ") VALUES (" +
                values.substring(0, values.length() - 1) + ")";

        try{
            con = ConnectionFactory.getConnection();
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            int index = 1;
            for(Field field : validFields){
                field.setAccessible(true);
                Object value = field.get(obj);
                System.out.println("Inserting " + field.getName() + " with value " + value);
                ps.setObject(index++, value);
            }

            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if(rs.next()){
                insertedId = rs.getInt(1);
            }
        }catch(Exception e){
            LOGGER.log(Level.WARNING, type.getSimpleName() + "DAO:insert" + e.getMessage());
        }finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(ps);
            ConnectionFactory.close(con);
        }
        return insertedId;
    }

    /**
     * Converts a ResultSet row into an object of type T using reflection
     * @param rs the ResultSet pointing to the current row
     * @return an instance of type T with populated fields
     */
    private T createObject(ResultSet rs) {
        T instance = null;
        try{
            instance = type.getDeclaredConstructor().newInstance();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            for(int i = 1; i <= columnCount; i++){
                String columnName = rsmd.getColumnName(i);
                Object value = rs.getObject(i);

                for(Field field : type.getDeclaredFields()){
                    if(field.getName().equalsIgnoreCase(columnName)){
                        field.setAccessible(true);
                        field.set(instance, value);
                        break;
                    }
                }
            }
        }catch(Exception e){
            LOGGER.log(Level.WARNING, type.getSimpleName() + "AbstractDAO:createObject" + e.getMessage());
        }
        return instance;
    }

    /**
     * Retrieves all entries from the table corresponding to type T
     * @return a list of all objects from the database table
     */
    public List<T> findAll() {
        List<T> list = new ArrayList<T>();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String query = "SELECT * FROM `" + type.getSimpleName().toLowerCase() + "`";

        try{
            con = ConnectionFactory.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                list.add(createObject(rs));
            }
        }catch(Exception e){
            LOGGER.log(Level.WARNING, "AbstractDAO:findAll" + e.getMessage());
        }finally{
            ConnectionFactory.close(rs);
            ConnectionFactory.close(st);
            ConnectionFactory.close(con);
        }
        return list;
    }

    /**
     * Updates the object in the database
     * Matches the object by its id field
     * @param obj the object to update
     * @return the number of rows affected
     */
    public int update(T obj) {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE ").append(type.getSimpleName().toLowerCase()).append(" SET ");

        List<Field> fields = new ArrayList<>();
        for(Field field : type.getDeclaredFields()){
            if(!field.getName().equalsIgnoreCase("id")){
                query.append(field.getName()).append(" = ?, ");
                fields.add(field);
            }
        }

        query.setLength(query.length() - 2);
        query.append(" WHERE id = ?");

        try(Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(query.toString())){

            int index = 1;
            for(Field field : fields){
                field.setAccessible(true);
                ps.setObject(index++, field.get(obj));
            }

            Field idField = type.getDeclaredField("id");
            idField.setAccessible(true);
            ps.setObject(index, idField.get(obj));

            return ps.executeUpdate();
        }catch(Exception e){
            LOGGER.log(Level.WARNING, "AbstractDAO:update" + e.getMessage());
            return -1;
        }
    }

    /**
     * Deletes an object from the database by id
     * @param id the id of the object to delete
     * @return the number of rows affected
     */
    public int delete(int id) {
        String query = "DELETE FROM `" + type.getSimpleName().toLowerCase() + "` WHERE id = ?";

        try(Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(query)){

            ps.setInt(1, id);
            return ps.executeUpdate();
        }catch(Exception e){
            LOGGER.log(Level.WARNING, "AbstractDAO:delete" + e.getMessage());
            return -1;
        }
    }
}
