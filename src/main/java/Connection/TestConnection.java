package Connection;

import java.sql.SQLException;
import java.sql.Connection;
import Connection.ConnectionFactory;

public class TestConnection {
    public static void main(String[] args) {
        Connection con = ConnectionFactory.getConnection();
        if(con != null) {
            System.out.println("Conexiune reusita la baza de date!\n");
            try{
                con.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }else{
            System.out.println("Conexiunea la baza de date a esuat!\n");
        }
    }
}
