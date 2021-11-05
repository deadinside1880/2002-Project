package restaurant.Menu;

public class Beverages extends MenuItems {
	public Beverages(String name, String description, double price) {
		super(name, description, price); 
		type = itemType.Beverages;
	}
}
