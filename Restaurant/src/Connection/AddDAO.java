package Connection;

import Types.Cuisines;
import Types.Drink;
import Types.Food;
import Types.Order;
import java.sql.*;

/**
 *
 * @author Rubén Manzano
 */
public class AddDAO {
    private static Connection con=null;
    private static Statement st=null;
    
    /**
     * Insert a cuisin in DB
     * @param c cuisin
     */
    public static void addCuisin(Cuisines c){
        try{
            con=ConnectionDAO.conect();
            st = con.createStatement();
            st.executeUpdate("INSERT INTO cuisines (id_cuisin,name) VALUES (NULL,'"+c.getName()+"');");
        }catch(SQLException e){
            System.err.println("Error: The cuisin can't be saved");
        }
    }
    
    /**
     * Insert a drink in DB
     * @param d drink
     */
    public static void addDrink(Drink d){
        try{
            con=ConnectionDAO.conect();
            st = con.createStatement();
            st.executeUpdate("INSERT INTO drink (id_drink,name,price) VALUES (NULL,'"+d.getName()+"','"+d.getPrice()+"');");
        }catch(SQLException e){
            System.err.println("Error: The drink can't be saved");
        }
    }
    
    /**
     * Insert a food in DB
     * @param f food
     */
    public static void addFood(Food f){
        try{
            con=ConnectionDAO.conect();
            st = con.createStatement();
            st.executeUpdate("INSERT INTO food (id_food,name,id_cuisin,description,type,price)"
                    + " VALUES (NULL,'"+f.getName()+"','"+f.getCousin()+"','"
                    + f.getDescription()+"','"+f.getType()+"','"+f.getPrice()+"');");
        }catch(SQLException e){
            System.err.println("Error: The food can't be saved");
        }
    }
    
    /**
     * Insert a order in DB
     * @param o order
     */
    public static void addOrder(Order o){
        int id=0;
        try{
            con=ConnectionDAO.conect();
            st = con.createStatement();
            st.executeUpdate("INSERT INTO restaurant.order (id_order,total) VALUES (NULL,"+o.getTotal()+");");
            //Get last id, it is necessary to insert drinks and foods
            ResultSet rs = st.executeQuery("SELECT max(id_order) AS id FROM restaurant.order;");
            if(rs!=null){
                while(rs.next()){
                    id=rs.getInt("id");
                }
            }
            //Insert all drinks of an order
            for (int i=0;i<o.getDrinks().size();i++){
                st.executeUpdate("INSERT INTO restaurant.orderdrink(id_orderdrink,id_order,id_drink)"
                        + " VALUES (NULL,'"+id+"','"+o.getDrinks().get(i)+"');");
            }
            //Insert all foods of an order
            for (int i=0;i<o.getFoods().size();i++){
                st.executeUpdate("INSERT INTO restaurant.orderfood(id_orderfood,id_order,id_food)"
                        + " VALUES (NULL,'"+id+"','"+o.getFoods().get(i)+"');");
            }
        }catch(SQLException e){
            System.err.println("Error: The order can't be saved");
        }
    }
}
