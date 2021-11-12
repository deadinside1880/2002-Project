package restaurant;

public class MenuItems {
	enum itemType {menuItem, SideDishes, MainCourse, Desserts, Beverages};
	
	String name;
	String description;
	double price; 
	itemType type; 
	
	// constructor
	public MenuItems() {
		this.name = null;
		this.description = null; 
		this.price = 0.0;
		type = itemType.menuItem; 
	}
	
	public MenuItems(String name, String description, double price) {
		this.name = name; 
		this.description = description; 
		this.price = price;
		type = itemType.menuItem; 
	}
	
	// All the getters 
	public String getName() { return name; }
	public String getDescription() { return description; } 
	public double getPrice() { return price;}
	public itemType getType() {return type;}
	
	// All the setters 
	public void setName(String name) {
		this.name = name; 
	}
	
	public void setDescription(String description) {
		this.description = description; 
	}
	
	public void setPrice(double price) {
		this.price = price; 
	}
	
	public void updateMenuItem(String name, String description, double price) {
		if (this.getName() == name) {
			this.description = description; 
			this.price = price;
		}
	}
	
	public void removeMenuItem(String name) {
		if (this.getName() == name) {
			this.description = null;
			this.price = 0.0; 
			this.name = null; 
		}
	}


}
