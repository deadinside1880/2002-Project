package restaurant;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.UUID;
public class Order {

    private LocalDateTime TOCreation;
    private LocalDateTime TOCompletion;
    private ArrayList<String> items = new ArrayList<>();
    private ArrayList<Integer> itemquantity = new ArrayList<>();
    private ArrayList<Double> itemcost = new ArrayList<>(); 
    private String staff;
    private Double total =0.0;
    private boolean member;
    private String orderID;
    private int tableID;


    public void init(){
        this.TOCompletion = null;
        this.TOCreation = null;
        this.itemcost.clear();
        this.itemquantity.clear();
        this.items.clear();
        this.staff = null;
        this.total = 0.0;
        this.member =false;
        this.orderID=null;
        this.tableID = 0;
    }

    public void setMember(boolean b){
        this.member = b;
    }

    public void setTableID(int id){
        this.tableID = id;
    }

    public void setOrderID(){
        this.orderID = UUID.randomUUID().toString();
    }

    public void setTOCreation(LocalDateTime toc){
        this.TOCreation = toc;
    }

    public void setTOCompletion(LocalDateTime toc){
        this.TOCompletion = toc;
    }

    public void addItems(String s){
        this.items.add(s);
    }

    public void setItemQuantity(int q){
        this.itemquantity.add(q);
    }

    public void setStaff(String s){
        this.staff = s; 
    }

    public void setItemCost(Double c){
        this.itemcost.add(c);
        this.total+=c;
    }

    public int getTableID(){
        return tableID;
    }

    public String getOrderID(){
        return orderID;
    }

    public LocalDateTime getTOCreation(){
        return TOCreation;
    }

    public LocalDateTime getTOCompletion(){
        return TOCompletion;
    }

    public ArrayList<String> getOrderItems(){
        return items;
    }

    public ArrayList<Integer> getItemQuantity(){
        return itemquantity;
    }

    public ArrayList<Double> getItemCost(){
        return itemcost;
    }

    public String getOrderStaff(){
        return staff;
    }

    public Double getTotal(){
        return total;
    }

    public boolean getMember(){
        return member;
    }

    public void removeItem(String s){
        int index = this.items.indexOf(s);
        this.items.remove(s);
        this.itemquantity.remove(index);
        Double cost = this.itemcost.get(index);
        this.itemcost.remove(index);
        this.total-=cost;
    }

}
