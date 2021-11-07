package restaurant;


public class Promotions {
	String promoName; 
	String promoDescription;
	double promoPrice; 
	String promoItems;
	// Constructor 

	public Promotions(String promoName, String description, double itemPrice, String promoItems) { 
		this.promoName = promoName; 
		this.promoDescription = description;
		this.promoPrice = itemPrice;
		this.promoItems = promoItems;
	}
	
	// Getters
	public double getPromoPrice() {
		return promoPrice; 
	}
	public String getPromoName() {
		return promoName; 
	}
	public String getPromoDescription() {
		return promoDescription;
	}
	public String getPromoItems() {
		return promoItems;
	}
	
	// Setters
	public void setPackagePrice(double price) {
		promoPrice = price; 
	}
	public void setPromoName(String name) {
		promoName = name; 
	}
	
	public void setPromoItems(String promoItems) {
		this.promoItems = promoItems;
	}
	
	public void setDescription(String name) {
		promoDescription = name; 
	}

	
}