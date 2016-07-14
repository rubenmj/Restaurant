package Types;


/**
 *
 * @author Rubén Manzano
 */
public class Drink {
    private int id;
    private String name;
    private float price;
    
    /*Setters*/
    public void setId(int id){
        this.id=id;
    }
    public void setName (String name){
        this.name=name;
    }
    
    public void setPrice (float price){
        this.price=price;
    }
    
    /*Getters*/
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    
    public float getPrice(){
        return this.price;
    }
    
    @Override
    public String toString(){
        return this.getName()+"-"+this.getPrice();
    }
}

