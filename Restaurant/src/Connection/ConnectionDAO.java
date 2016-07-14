package Connection;
import java.sql.*;

/**
 *
 * @author Rubén Manzano
 */
public class ConnectionDAO {
    private static Connection con=null;
    /**
     * This function conect to DB, user: root, psw:""
     * @return connection
     */
    public static Connection conect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/restaurant","root","");
        }catch(ClassNotFoundException | SQLException e){
            System.err.println("Error: DB can not been connected"+e.getMessage());
        }
        return con;
    }
    
    
    
}
