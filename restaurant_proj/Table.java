package restaurant;

public class Table {
    
    private int tableID; 
	private int capacity = 5;
	private int customerID;
    private boolean isOccupied;

    
    public Table(int tableID, int capacity, int customerID, boolean isOccupied){
        this.tableID = tableID;
        this.capacity = capacity;
        this.customerID = customerID;
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
}
