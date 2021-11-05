package restaurant.Menu;

public class MainCourse extends MenuItems{
	public MainCourse(String name, String description, double price) {
		super(name, description, price);
		type = itemType.MainCourse; 
	}

}
