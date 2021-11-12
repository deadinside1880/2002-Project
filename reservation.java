package restaurant;
import java.time.LocalTime;
import java.util.*;


public class reservation {

	
	private int tableID; 
	private String name;
	private String contact;
	private String date;
	private LocalTime time;
    public reservation(int tableID,String name,String contact,String date,LocalTime time2){
		this.tableID=tableID;
    	this.name=name;
		this.date=date;
		this.contact=contact;
    	this.date = date;
		this.time=time2;

    }
	public int getTableID() {
		return this.tableID;
	}
	
	public String getName() {
		return name;
	}
	
	public String getContact() {
		return contact;
	}
	//note that date and reservation id cant be edited here by setters
	public String getDate() {
		return this.date;
	}
	public LocalTime getTime()
	{
		return this.time;
	}
	public void setTime(LocalTime time)
	{
		this.time=time;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void settableID(int tableID) {
		this.tableID = tableID;
	}
    public void setContact(String contact) {
		this.contact = contact;
	}

    public void setDate(String date) {
    	this.date = date;
    }



    
}