package restaurant;

import Connection.AddDAO;
import Connection.LoadDAO;
import exceptions.PersonalExceptions;
import Types.Food;
import Types.Order;
import Types.Drink;
import Types.Cuisines;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Rubén Manzano
 */
public class Restaurant {

    static ArrayList<Drink> drinks;
    static ArrayList<Food> foods;
    static ArrayList<Cuisines> cuisines;
    static ArrayList<Order> order;
    static int neworder;
    static Order o;

    /**
     * *********************************** MAIN FUNCTION ***************************************
     */
    public static void main(String[] args) {
        int opt1;
        String opt;
        boolean exit = true;
        Scanner in = new Scanner(System.in);
        //load data
        drinks = Connection.LoadDAO.loadDrink();
        cuisines = Connection.LoadDAO.loadCuisin();
        foods = Connection.LoadDAO.loadFood();
        order = new ArrayList<>();
        neworder = LoadDAO.lastOrder();

        //menu
        do {
            ShowFunctions.printMenu();
            opt = in.nextLine();
            try {
                opt1 = Integer.parseInt(opt);
                switch (opt1) {
                    case 1:
                        newOrder();
                        break;
                    case 2:
                        addToOrder();
                        break;
                    case 3:
                        ShowFunctions.showOrder();
                        break;
                    case 4:
                        admin();
                        break;
                    case 5:
                        System.out.println("All order will be save");
                        for (int i = 0; i < order.size(); i++) {
                            AddDAO.addOrder(order.get(i));
                        }
                        exit = false;
                        break;
                    default:
                        System.err.println("Incorrect option.");
                }
            } catch (NumberFormatException nfe) {
                System.err.println("Incorrect value.");
            }
        } while (exit);
    }

    /**
     * *********************************** ORDER ***************************************
     */
    /**
     * Create a new order asking for data to user
     */
    private static void newOrder() {
        boolean exit = true;
        Scanner in = new Scanner(System.in);
        o = new Order();
        do {
            System.out.println("\nUse numbers to choose a option:");
            System.out.println("1.- Order drink.");
            System.out.println("2.- Order lanch.");
            System.out.println("3.- Back.");
            System.out.print("-> ");
            int opt = in.nextInt();
            switch (opt) {
                case 1:
                    orderDrink();
                    break;
                case 2:
                    orderLunch();
                    break;
                case 3:
                    order.add(o);
                    System.err.println("Your order has been assigned the number: " + (order.size() - 1));
                    exit = false;
                    break;
            }
        } while (exit);
    }

    /**
     * Modify an existing order by adding more drinks or foods
     */
    private static void addToOrder() {
        boolean exit = true, check=false, firstTime=true;
        Scanner in = new Scanner(System.in);
        o = new Order();
        int index=0;
        do {
            if(firstTime){
                System.out.println("Order number: ");
                System.out.print("-> ");
                index = in.nextInt();
                if (Control.checkOrderExits(index)) {
                    check = true; //the order exist
                } else {
                    System.err.println("The order " + index + " doesn't exits.");
                    check = false; //the order don't exist
                }
                firstTime=false;
            }
            if (check) {
                System.out.println("\nUse numbers to choose a option:");
                System.out.println("1.- Order drink.");
                System.out.println("2.- Order lunch.");
                System.out.println("3.- Back.");
                System.out.print("-> ");
                int opt = in.nextInt();
                switch (opt) {
                    case 1:
                        orderDrink();
                        break;
                    case 2:
                        orderLunch();
                        break;
                    case 3:
                        Order tmp = new Order();
                        tmp = order.get(index);
                        for (int i = 0; i < o.getDrinks().size(); i++) {
                            tmp.addDrink(o.getDrinks().get(i));
                        }
                        for (int i = 0; i < o.getFoods().size(); i++) {
                            tmp.addFood(o.getFoods().get(i));
                        }
                        order.set(index, tmp);
                        exit = false;
                        break;
                }
            }
        } while (exit);
    }

    /**
     * Private function which is used in addToOrder or newOrder when they have to add a drink
     */
    private static void orderDrink() {
        Scanner in = new Scanner(System.in);
        if (ShowFunctions.showDrinks() == 1) {
            System.out.print("-> ");
            int d = in.nextInt();
            if (Control.checkDrinkNumber(d)) {
                o.addDrink(d);
                o.setTotal(o.getTotal() + Control.getPriceDrink(d));
            } else {
                System.err.println("Incorrect option.");
            }
        } else {
            System.err.println("No drinks to show.");
        }
    }
    /**
     * Private function which is used in addToOrder or newOrder when they have to add a food
     */
    private static void orderLunch() {
        Scanner in = new Scanner(System.in);
        boolean check;
        if (ShowFunctions.showCuisines() == 1) {
            System.out.print("-> ");
            int cuis = in.nextInt();
            if (Control.checkCuisin(cuis)) {
                //Select main course
                do {
                    check = false;
                    if (ShowFunctions.showFoods(cuis, "main") == 1) {
                        System.out.print("-> ");
                        int main = in.nextInt();
                        if (Control.checkFood(main, cuis, "main")) {
                            o.addFood(main);
                            o.setTotal(o.getTotal() + Control.getPriceFood(main));
                        } else {
                            System.err.println("Incorrect option.");
                            check = true;
                        }
                    } else {
                        System.err.println("No main course to show.");
                    }
                } while (check);
                //select dessert
                do {
                    check = false;
                    if (ShowFunctions.showFoods(cuis, "dessert") == 1) {
                        System.out.print("-> ");
                        int dessert = in.nextInt();
                        if (Control.checkFood(dessert, cuis, "dessert")) {
                            o.addFood(dessert);
                            o.setTotal(o.getTotal() + Control.getPriceFood(dessert));
                        } else {
                            System.err.println("Incorrect option.");
                            check = true;
                        }
                    } else {
                        System.err.println("No dessert to show.");
                    }
                } while (check);
            } else {
                System.err.println("Incorrect option.");
            }
        } else {
            System.err.println("No cuisines to show.");
        }
    }

    /**
     * *********************************** ADMIN ***************************************
     */
    /**
     * Administrative tasks, in this functions we can add new foods, cuisines, drinks and
     * save all these items in DB
     */
    private static void admin() {
        Scanner in = new Scanner(System.in);
        boolean exit = true;
        do {
            ShowFunctions.showAdminMenu();
            int opt = in.nextInt();
            switch (opt) {
                case 1:
                    Cuisines c = new Cuisines();
                    System.out.print("Name -> ");
                    c.setName(in.next());
                    AddDAO.addCuisin(c);
                    cuisines = LoadDAO.loadCuisin();
                    break;
                case 2:
                    Drink d = new Drink();
                    try {
                        in.skip("\n");
                        System.out.print("Name -> ");
                        d.setName(in.nextLine());
                        System.out.print("Price (use doc for decimals) -> ");
                        d.setPrice(Float.parseFloat(in.next()));
                        //d.setPrice(in.nextFloat()); 
                    } catch (Exception e) {
                        System.err.println("Error: Incorrect format");
                        break;
                    }
                    AddDAO.addDrink(d);
                    drinks = LoadDAO.loadDrink();
                    break;
                case 3:
                    Food f = new Food();
                    try {
                        in.skip("\n");
                        System.out.print("Name -> ");
                        f.setName(in.nextLine());
                        System.out.print("Cousin -> ");
                        String aux = in.next().toLowerCase();
                        for (int k = 0; k < cuisines.size(); k++) {
                            if (cuisines.get(k).getName().equalsIgnoreCase(aux)) {
                                f.setCousin(cuisines.get(k).getId());
                            }
                        }
//                        if(Control.checkCuisin(aux))
//                            
//                        else 
//                            throw new PersonalExceptions.cuisinException();
                        System.out.print("Type (main/dessert) -> ");
                        String type = in.next().toLowerCase();
                        if (type.equals("main") || type.equals("dessert")) {
                            f.setType(type);
                        } else {
                            throw new PersonalExceptions.typeException();
                        }
                        System.out.print("Price (use doc for decimals) -> ");
                        f.setPrice(Float.parseFloat(in.next()));
                        in.skip("\n");
                        System.out.print("Description -> ");
                        f.setDescription(in.nextLine());
                    } catch (PersonalExceptions.typeException e) {
                        System.err.println("Error: Incorrect type");
                        break;
                    }//catch(PersonalExceptions.cuisinException e){
//                        System.err.println("Error: Incorrect cuisin");
//                        break;
//                    }
                    AddDAO.addFood(f);
                    foods = LoadDAO.loadFood();
                    break;
                case 4:
                    exit = false;
                    break;
            }
        } while (exit);
    }
}
