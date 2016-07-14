package restaurant;


import static restaurant.Restaurant.order;
import static restaurant.Restaurant.drinks;
import static restaurant.Restaurant.foods;
import static restaurant.Restaurant.cuisines;
import java.util.Scanner;

/**
 *
 * @author Rubén Manzano
 */
public class ShowFunctions {
        /************************************* MENU ****************************************/
    /**
     * Show principal menu
     */
    public static void printMenu(){
        try{
            Thread.sleep(100);
        }catch(Exception e){}
        System.out.println("Use numbers to choose a option:");
        System.out.println("1.- New Order.");
        System.out.println("2.- Add to exist Order.");
        System.out.println("3.- Show Order.");
        System.out.println("4.- Administration.");
        System.out.println("5.- Exit.");
        System.out.print("-> ");
    }
    
    /**
     * Show admin menu
     */
    public static void showAdminMenu(){
        System.out.println("\nUse numbers to choose a option:");
        System.out.println("1.- Add cuisin.");
        System.out.println("2.- Add drink.");
        System.out.println("3.- Add food.");
        System.out.println("4.- Back.");
        System.out.print("-> ");
    }
    
    /************************************* SHOW FUNCTIONS ****************************************/
    
    /**
     * Show all cuisines
     * @return 1 if it shows some cuisine
     */
    public static int showCuisines(){
        int check=0;
        for(int i=0;i<cuisines.size();i++){
            System.out.println(cuisines.get(i).getId()+".-"+cuisines.get(i).getName());
            check=1;
        }
        if(check==0)
            System.out.println("There are not cuisines in this category");
        return check;
    }
    
    /**
     * Show all drinks
     * @return 1 if it shows some drink
     */
    public static int showDrinks(){
        int check=0;
        for(int i=0;i<drinks.size();i++){
            System.out.println(drinks.get(i).getId()+".-"+drinks.get(i).getName()+" --> "+drinks.get(i).getPrice());
            check=1;
        }
        if(check==0)
            System.out.println("There are not food in this category");
        return check;
    }
    
    /**
     * Show all foods
     * @return 1 if this function show some register
     */
    public static int showFoods(){
        int check=0;
        for(int i=0;i<foods.size();i++){
            System.out.println(foods.get(i).getId()+".-"+foods.get(i).getName()+" --> "+foods.get(i).getDescription()+" --> "+foods.get(i).getType()+" --> "+foods.get(i).getPrice());
            check=1;
        }
        if(check==0)
            System.out.println("There are not food in this category");
        return check;
    }
    
    /**
     * Show all foods from a cuisin
     * @param cuisin for show
     * @return 1 if it shows some food
     */
    public static int showFoods(int cuisin){
        int check=0;
        for(int i=0;i<foods.size();i++){
            if(foods.get(i).getCousin()==cuisin){
                System.out.println(foods.get(i).getId()+".-"+foods.get(i).getName()+" --> "+foods.get(i).getDescription()+" --> "+foods.get(i).getType()+" --> "+foods.get(i).getPrice());
                check=1;
            }
        }
        if(check==0)
            System.out.println("There are not food in this category");
        return check;
    }
    
    /**
     * Show all foods from a cuisin and type (main or dessert)
     * @param cuisin for show
     * @param type for show
     * @return 1 if it shows some food
     */
    public static int showFoods(int cuisin, String type){
        int check=0;
        for(int i=0;i<foods.size();i++){
            if(foods.get(i).getCousin()==cuisin
                    && foods.get(i).getType().toLowerCase().equals(type)){
                System.out.println(foods.get(i).getId()+".-"+foods.get(i).getName()+" --> "+foods.get(i).getDescription()+" --> "+foods.get(i).getPrice());
                check=1;
            }
        }
        if(check==0)
            System.out.println("There are not food in this category");
        return check;
    }
    
    /**
     * Show one order
     */
    public static void showOrder(){
        Scanner in=new Scanner(System.in);
        System.out.println("Order number: ");
        System.out.print("-> ");
        int number=in.nextInt();
        if(Control.checkOrderExits(number)){
            System.out.println("\n****************************************** ");
            try{
                Thread.sleep(10);
            }catch(Exception e){}
            System.err.println("Drinks: ");
            

            for(int j=0;j<order.get(number).getDrinks().size();j++){
                for(int k=0;k<drinks.size();k++){
                    if(order.get(number).getDrinks().get(j)==drinks.get(k).getId()){
                        System.out.println(drinks.get(k).getName()+" -> "+drinks.get(k).getPrice());
                    }
                }
            }

            try{
                Thread.sleep(10);
            }catch(Exception e){}
            System.err.println("Foods: ");
            for(int j=0;j<order.get(number).getFoods().size();j++){
                for(int k=0;k<foods.size();k++){
                    if(order.get(number).getFoods().get(j)==foods.get(k).getId()){
                        System.out.println(foods.get(k).getName()+" -> "+foods.get(k).getPrice());
                    }
                }
            }
            try{
                Thread.sleep(10);
            }catch(Exception e){}
                
            System.out.println("------------------------------------------ ");
            System.err.println(" TOTAL:                        "+order.get(number).getTotal());
            System.out.println("\n****************************************** ");
        }else{
            System.err.println("The order "+number+" doesn't exits.");
        }
    }
    

}
