package restaurant;
import java.util.ArrayList;

public class table {
    private int tableID; 
    private int capacity;
    private int customerID;
    private boolean isOccupied;
    ArrayList<reservation> reservations = new ArrayList<>();
   
    public table(int tableID, int capacity, boolean isOccupied){
        this.tableID = tableID;
        this.capacity = capacity;
        this.isOccupied = isOccupied;

    }
  public int getTableID() {
    return this.tableID;
  }
  
  public int getCapacity() {
    return capacity;
  }
  
  public int getCustomerID() {
    return customerID;
  }
  public boolean getIsOccupied() {
    return isOccupied;
  }

  public void setTableID(int tableID) {
    this.tableID = tableID;
  }
  
  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }
    public void setCustomerID(int customerID) {
    this.customerID = customerID;
  }
    public void setIsOccupied(boolean isOccupied) {
    this.isOccupied = isOccupied;
  }
    public void addReservation(reservation r){
        this.reservations.add(r);
    }

    public ArrayList<reservation> getReservations(){
        return reservations;
    }



}




