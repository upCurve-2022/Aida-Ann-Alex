package Multithreading;

import java.util.ArrayList;
import java.util.List;

public class ThreadMain {
    public static void main(String[] args){
        PizzaShop obj = new PizzaShop();
        List<String> custName= getCustomers();
        List<String> items= getItems();
        List<String> queue1 = custName.subList(0,3);
        List<String> queue2 = custName.subList(3,6);
        new Counter(obj, queue1, items);
        new Counter(obj, queue2, items);
        new Chef(obj, queue1);
        new Chef(obj, queue2);
    }
    public static List<String> getCustomers(){
        List<String> customers = new ArrayList<>();
        customers.add("Person1");
        customers.add("Person2");
        customers.add("Person3");
        customers.add("Person4");
        customers.add("Person5");
        customers.add("Person6");
        return customers;
    }
    public static List<String> getItems(){
        List<String> items = new ArrayList<>();
        items.add("Margherita");
        items.add("Farm House");
        items.add("Mexican Green Wave");
        items.add("Chicken Golden Delight");
        items.add("Chicken Sausage");
        items.add("Chicken Dominator");
        return items;
    }
}
