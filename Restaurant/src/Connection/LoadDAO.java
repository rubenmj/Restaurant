package Connection;

import Types.Cuisines;
import Types.Drink;
import Types.Food;
import Types.Order;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Rubén Manzano
 */
public class LoadDAO {
    private static Connection con=null;
    private static Statement st=null;
    private static ArrayList<Drink> drinks;
    private static ArrayList<Food> foods;
    private static ArrayList<Cuisines> cuisines;
    private static ArrayList<Order> order;
    
    /**
     * Load all cuisines from DB
     * @return arrayList with all cuisines
     */
    public static ArrayList<Cuisines> loadCuisin(){
        cuisines=new ArrayList<>();
        Cuisines c;
        try{
            con=ConnectionDAO.conect();
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM cuisines;");
            if(rs!=null){
                while(rs.next()){
                    c=new Cuisines();
                    c.setId(rs.getInt(1));
                    c.setName(rs.getString(2));
                    cuisines.add(c);
                }
                rs.close();
            }
            
        }catch(SQLException e){
            
        }finally{
            try{
                st.close();
            }catch(SQLException e){}
        }
        return cuisines;
    }
    /**
     * Load all drinks from DB
     * @return arrayList with all drinks
     */
    public static ArrayList<Drink> loadDrink(){
        drinks =new ArrayList<>();
        Drink d;
        try{
            con=ConnectionDAO.conect();
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM drink;");
            if(rs!=null){
                while(rs.next()){
                    d=new Drink();
                    d.setId(rs.getInt("id_drink"));
                    d.setName(rs.getString("name"));
                    d.setPrice(rs.getFloat("price"));
                    drinks.add(d);
                }
                rs.close();
            }
            
        }catch(SQLException e){
            
        }finally{
            try{
                st.close();
            }catch(SQLException e){}
        }
        return drinks;
    }
    
    /**
     * Load all foods from DB
     * @return arrayList with all foods
     */
    public static ArrayList<Food> loadFood(){
        foods =new ArrayList<>();
        Food f;
        try{
            con=ConnectionDAO.conect();
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM food;");
            if(rs!=null){
                while(rs.next()){
                    f=new Food();
                    f.setId(rs.getInt("id_food"));
                    f.setName(rs.getString("name"));
                    f.setCousin(rs.getInt("id_cuisin"));
                    f.setDescription(rs.getString("description"));
                    f.setType(rs.getString("type"));
                    f.setPrice(rs.getFloat("price"));
                    foods.add(f);
                }
                rs.close();
            }
            
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }finally{
            try{
                st.close();
            }catch(SQLException e){}
        }
        return foods;
    }
    /**
     * Load all orders from DB
     * @return arrayList with all orders
     */
    public static ArrayList<Order> loadOrder(){
        order =new ArrayList<>();
        ArrayList<Drink> auxDrink=new ArrayList<>();
        ArrayList<Food> auxFood=new ArrayList<>();
        Order o;
        try{
            con=ConnectionDAO.conect();
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM order;");
            if(rs!=null){
                while(rs.next()){
                    o=new Order();
                    o.setId(rs.getInt("id_order"));
                    o.setTotal(rs.getFloat("total"));
                    auxDrink=LoadOrderDrink(o.getId());
                    for(int i=0;i<auxDrink.size();i++){
                        o.addDrink(auxDrink.get(i).getId());
                    }
                    auxFood=LoadOrderFood(o.getId());
                    for(int i=0;i<auxFood.size();i++){
                        o.addFood(auxFood.get(i).getId());
                    }
                    order.add(o);
                }
                rs.close();
            }
            
        }catch(SQLException e){
            
        }finally{
            try{
                st.close();
            }catch(SQLException e){}
        }
        return order;
    }
    
    /**
     * Load all drinks of an order
     * @param idOrder id of order which we want load drinks
     * @return arrayList with all drinks of an order
     */
    private static ArrayList<Drink> LoadOrderDrink (int idOrder){
        ArrayList<Drink> idDrink=new ArrayList<>();
        loadDrink();
        int aux=0;
        try{
            con=ConnectionDAO.conect();
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM orderdrink WHERE 'id_order='"+idOrder+";");
            if(rs!=null){
                while(rs.next()){
                    aux=rs.getInt("id_drink");
                    for(int i=0;i<drinks.size();i++){
                        if(aux==drinks.get(i).getId())
                            idDrink.add(drinks.get(i));
                    }
                }
                rs.close();
            }
            
        }catch(SQLException e){
            
        }finally{
            try{
                st.close();
            }catch(SQLException e){}
        }
        return idDrink;
    }
    /**
     * Load all foods of an order
     * @param idOrder id of order which we want load foods
     * @return arrayList with all foods of an order
     */    
    private static ArrayList<Food> LoadOrderFood (int idOrder){
        ArrayList<Food> idFood=new ArrayList<>();
        loadFood();
        int aux=0;
        try{
            con=ConnectionDAO.conect();
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM orderfood WHERE 'id_order='"+idOrder+";");
            if(rs!=null){
                while(rs.next()){
                    aux=rs.getInt("id_food");
                    for(int i=0;i<foods.size();i++){
                        if(aux==foods.get(i).getId())
                            idFood.add(foods.get(i));
                    }
                }
                rs.close();
            }
            
        }catch(SQLException e){
            
        }finally{
            try{
                st.close();
            }catch(SQLException e){}
        }
        return idFood;
    }
    
    /**
     * load id of the last order
     * @return id last order
     */
    public static int lastOrder(){
        int aux=0;
        try{
            con=ConnectionDAO.conect();
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT count(*) FROM order;");
            if(rs!=null){
                while(rs.next()){
                    aux=rs.getInt("0");
                }
                rs.close();
            }
            
        }catch(SQLException e){
            
        }finally{
            try{
                st.close();
            }catch(SQLException e){}
        }
        return aux;
    }
}
