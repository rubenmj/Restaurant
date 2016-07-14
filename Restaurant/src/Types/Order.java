package Types;

import java.util.ArrayList;


/**
 *
 * @author Rubén Manzano
 */
public class Order {
    private int id;
    private float total;
    private ArrayList<Integer>idDrink=new ArrayList<>();
    private ArrayList<Integer>idFood= new ArrayList<>();

    /*Setters*/
    public void setId(int id){
        this.id=id;
    }
    
    public void addDrink(int id){
        idDrink.add(id);
    }
    
    public void addFood(int id){
        idFood.add(id);
    }
    
    public void setTotal(float total){
        this.total=total;
    }
    
    /*Getters*/
    public int getId(){
        return this.id;
    }
    
    public ArrayList<Integer> getDrinks(){
        return this.idDrink;
    }
    
    public ArrayList<Integer> getFoods(){
        return this.idFood;
    }
    
    public float getTotal(){
        return this.total;
    }
    
}
