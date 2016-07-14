package restaurant;

import static restaurant.Restaurant.cuisines;
import static restaurant.Restaurant.order;
import static restaurant.Restaurant.foods;
import static restaurant.Restaurant.drinks;

/**
 *
 * @author Rubén Manzano
 */
public class Control {
        /************************************* CONTROL ****************************************/
    /**
     * This function checks if the cuisin exists
     * @param cuis cuisin
     * @return true if it exists or false if it doesn't exist
     */
    public static boolean checkCuisin(int cuis){
        for(int i=0;i<cuisines.size();i++){
            if(cuisines.get(i).getId()==cuis)
                return true;
        } 
        return false;
    }

    
    /**
     * This function checks the correct order number 
     * @param o order number
     * @return true if it is correct
     */
    public static boolean checkOrderExits(int o){
        if(o>=0&&o<order.size())
            return true;
        return false;
    }
    
    /**
     * This function checks the correct drink number
     * @param d id drink
     * @return true if it is correct
     */
    public static boolean checkDrinkNumber(int d){
        for(int i=0;i<drinks.size();i++){
            if(drinks.get(i).getId()==d){
                return true;
            }
        }
        return false;
    }
    
    /**
     * This function checks the correct food for a cuisin and the type
     * @param id id food
     * @param cuis id cuisine
     * @param type main or dessert
     * @return true if it is correct
     */
    public static boolean checkFood(int id,int cuis,String type ){
        for(int i=0;i<foods.size();i++){
            if(foods.get(i).getId()==id&&foods.get(i).getCousin()==cuis&&foods.get(i).getType().equalsIgnoreCase(type))
                return true;
        }
        return false;
    }
    
    /**
     * This function gets the price of a food
     * @param id idfood
     * @return price
     */
    public static float getPriceFood(int id){
        for(int i=0;i<foods.size();i++){
            if(foods.get(i).getId()==id)
                return foods.get(i).getPrice();
        }
        return 0;
    }
    
    /**
     * This function gets the price of a drink
     * @param id iddrink
     * @return price
     */
    public static float getPriceDrink(int id){
        for(int i=0;i<drinks.size();i++){
            if(drinks.get(i).getId()==id)
                return drinks.get(i).getPrice();
        }
        return 0;
    }
}
