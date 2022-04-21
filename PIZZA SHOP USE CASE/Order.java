package Multithreading;

public class Order {
    int orderID;
    String item;
    String customer;

    public Order(int orderID, String item, String customer) {
        this.orderID = orderID;
        this.item = item;
        this.customer = customer;
    }
}
