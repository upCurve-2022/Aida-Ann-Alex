package Multithreading;
import java.util.*;

public class PizzaShop {
    List<Order> ordersToBePrepared = new ArrayList<>();
    List<Order> ordersPrepared = new ArrayList<>();
    int orderID;
    synchronized void counter(String customer, List<String> items) {
        System.out.println("----------next order----------");
        System.out.println("Taking order for customer-" + customer + " at counter-" + Thread.currentThread().getName() + " order number: " + orderID);
        int random = new Random().nextInt(6);
        String selectedItem = items.get(random);
        Order order = new Order(orderID, selectedItem, customer);
        orderID++;
        ordersToBePrepared.add(order);
        if(ordersPrepared.isEmpty()){
            try{
                wait();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        notify();
    }
    synchronized void chef() {
        if(ordersToBePrepared.isEmpty()){
            try{
                wait();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        Order order = ordersToBePrepared.get(0);
        ordersPrepared.add(order);
        System.out.println("********** order being prepared **********");
        ordersToBePrepared.remove(0);
        if(!ordersPrepared.isEmpty()) {
            System.out.println("Order id:" + ordersPrepared.get(0).orderID + " " + order.item + " delivered");
            ordersPrepared.remove(0);
        }
        notify();
    }
}
class Counter implements Runnable{
    PizzaShop p;
    List<String> custName;
    List<String> item;
    public Counter(PizzaShop p, List<String> custName, List<String> item){
        this.p = p;
        this.custName = custName;
        this.item = item;
        new Thread(this).start();
    }
    public void run(){
        for(String customer: custName){
            try{
                Thread.sleep(2000);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            p.counter(customer, item);
        }
    }
}
class Chef implements Runnable {
    PizzaShop p;
    List<String> custName;

    public Chef(PizzaShop p, List<String> custName) {
        this.p = p;
        this.custName = custName;
        new Thread(this).start();
    }
    public void run() {
        for (String customer : custName) {
            try {
                Thread.sleep(4000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.chef();
        }
    }
}