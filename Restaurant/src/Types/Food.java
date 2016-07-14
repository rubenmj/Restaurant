package Types;


/**
 *
 * @author Rubén Manzano
 */
public class Food {
    private int id, id_cousin;
    private String name, description, type;
    private float price;
    
    /*Setters*/
    public void setId(int id){
        this.id=id;
    }
    public void setName (String name){
        this.name=name;
    }
    
    public void setCousin (int cousin){
        this.id_cousin=cousin;
    }
    
    public void setDescription (String description){
        this.description=description;
    }
    
    public void setType (String type){
        this.type=type;
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
    
    public int getCousin(){
        return this.id_cousin;
    }
    
    public String getDescription (){
        return this.description;
    }
    
    public String getType (){
        return this.type;
    }
    
    public float getPrice(){
        return this.price;
    }
    
    @Override
    public String toString(){
        return this.getName()+"-"+this.getCousin()+"-"+this.getDescription()
                +"-"+this.getType().toLowerCase()+"-"+this.getPrice();
    }
}
