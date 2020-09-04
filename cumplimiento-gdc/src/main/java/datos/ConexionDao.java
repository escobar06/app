package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionDao {
    private static final String URL_DB = "jdbc:mysql://localhost:3306/gdcprod?useSSL=false&useTimezone=true&serverTimezone=UTC";
    private static final String USER_DB = "root";
    private static final String PASS_DB = "admin";
    
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL_DB, USER_DB, PASS_DB);
    }
    
    public static void close(Connection cnn,PreparedStatement ps,ResultSet rs){
        try {
            rs.close();
            ps.close();
            cnn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }   
    
    public static void closeInsert(Connection cnn,PreparedStatement ps){
        try {
            ps.close();
            cnn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }    
}
