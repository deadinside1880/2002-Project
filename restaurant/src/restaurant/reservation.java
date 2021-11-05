package restaurant;

import java.util.*;


public class reservation {

	
	private int noOfPax; 
	private String name;
	private int contact;
	private Date date;
	private String reservationID;
	private table[] tableList;
    public reservation(String reservationID, int noOfPax, String name, int contact, Date date, table[] tableList ){
		this.reservationID = reservationID;
        this.noOfPax = noOfPax;
        this.name = name;
        this.contact = contact;
		this.date = date;
		this.tableList = tableList;

    }

	public int getnoOfPax() {
		return this.noOfPax;
	}
	
	public String getName() {
		return name;
	}
	
	public double getContact() {
		return contact;
	}
	public table[] getTableList() {
		// for(table temp:tableList ){
		// 	System.out.println(temp.getTableID());
		// }
		return tableList;
	}

	//note that date and reservation id cant be edited here by setters
	public Date getDate() {
		return this.date;
	}
	public String getreservationID() {
		return this.reservationID;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setnoOfPax(int noOfPax) {
		this.noOfPax = noOfPax;
	}
    public void setContact(int contact) {
		this.contact = contact;
	}




    
}