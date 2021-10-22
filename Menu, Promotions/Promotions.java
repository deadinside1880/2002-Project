import java.util.Scanner;

public class Promotions {
	String promoName; 
	String itemName; 
	String itemDescription;
	double itemPrice; 
	
	// Constructor 

	public Promotions(String promoName, String itemName, String description, double itemPrice) { 
		this.promoName = promoName; 
		this.itemName = itemName; 
		this.itemDescription = description;
		this.itemPrice = itemPrice; 
	}
	
	// Getters
	public double getPromoPrice() {
		return itemPrice; 
	}
	public String getPromoName() {
		return promoName; 
	}
	public String getItemName() {
		return itemName;
	}
	
	// Setters
	public void setPackagePrice(int price) {
		itemPrice = price; 
	}
	public void setPromoName(String name) {
		promoName = name; 
	}
	
	public void setItemName(String name) {
		itemName = name;
	}
	
	public void setDescription(String name) {
	}

	
}
