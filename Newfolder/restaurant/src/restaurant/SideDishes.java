package restaurant;

public class SideDishes extends MenuItems{
	public SideDishes(String name, String description, double price) {
		super(name, description, price); 
		type = itemType.SideDishes;
	}
}