import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// menu consists of the collection of ala Carte items and promotion items 
public class menu {

	public static void main(String[] args) throws IOException {
		menu Menu = new menu(); 
		Menu.displayMenu();
	}
	
	ArrayList<MenuItems> alaCarteMenu  = new ArrayList<>(); 
	ArrayList<Promotions> promoMenu = new ArrayList<>(); 
	
	
	public menu() throws IOException {
		alaCarteMenu = readcsvAlaCarte("demoCSV.csv"); 
		promoMenu = readCSVPromo("demoCSVPromos.csv");
	}
	
	public ArrayList<MenuItems> readcsvAlaCarte(String fileName) {
		ArrayList<MenuItems> alaCarteMenu = new ArrayList<>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName)); 
			while(br.readLine() != null) {
				String line = br.readLine(); 
				// System.out.println(line);
				while(line != null) {
					String[] attributes = line.split(","); 
					
					MenuItems alaCarteItem = createAlaCarteMenu(attributes); //
					alaCarteMenu.add(alaCarteItem);

					line = br.readLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// MenuItems[] alaCarteMenu = (MenuItems[]) alaCarte;
		return alaCarteMenu; 
	}
	
	public ArrayList<Promotions> readCSVPromo(String fileName) {
		ArrayList<Promotions> promoMenu = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while(br.readLine() != null) {
				String line = br.readLine(); 
				while(line != null) {
					String[] attributes = line.split(","); // 0: promoName 1: itemName 2:promoPrice 3: item description 
					
					Promotions promoItem = createPromoMenu(attributes); 
					promoMenu.add(promoItem);

					line = br.readLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return promoMenu; 
	}

	public MenuItems createAlaCarteMenu(String[] metadata) {
		// Read file to create the ala carte menu 
		String name = metadata[0];
		String description = metadata[1]; 
		double price = Double.parseDouble(metadata[2]);
		String type = metadata[3]; 
		MenuItems alaCarteItem = null; 
		
		switch(type) {
		case "MainCourse": 
			alaCarteItem = new MainCourse(name, description, price); 
			break; 
		case "SideDishes":
			alaCarteItem = new SideDishes(name, description, price); 
			break;
		case "Beverages":
			alaCarteItem = new Beverages(name, description, price); 
			break;
		case "Desserts": 
			alaCarteItem = new Desserts(name, description, price); 
			break;
		default:
			alaCarteItem = new MenuItems(name, description, price); 
		}
		
		return alaCarteItem;
	}
	
	public Promotions createPromoMenu(String[] metadata) {
		String promoName = metadata[0]; 
		String itemName = metadata[1]; 
		double itemPrice = Double.parseDouble(metadata[2]);
		String itemDescription = metadata[2]; 
		
		return new Promotions(promoName, itemName, itemDescription, itemPrice); 
	}
	
	// TO CHECK THAT ALL READ CORRECTLY
	public void displayMenu() {
		
		// Display the alaCarte Menu
		System.out.println("----------- ALA CARTE MENU ---------------------");
		for (int i = 0; i < alaCarteMenu.size(); i++) {
			System.out.println(alaCarteMenu.get(i).getName() + " --- " + alaCarteMenu.get(i).getPrice()); 
			System.out.println("Description: " + alaCarteMenu.get(i).getDescription());
			System.out.println("Type: " + alaCarteMenu.get(i).getType());
		}
		System.out.println();
		
		System.out.println("----------- PROMOTION MENU ---------------------");
		// Display the Promotion Menu 
		for (int i = 0; i < promoMenu.size(); i++) {
			System.out.println("[" + promoMenu.get(i).getPromoName() + " Promotion] "
							+ promoMenu.get(i).getItemName() + " ----- " + promoMenu.get(i).getPromoPrice()); 
		}
	}
	
}


// Pseudo Code
// Read CSV File to determine size of the file --> Size of the array to be initialised 
// 1. Read the CSV File to create menu (createMenu Method)
// 2. Read the CSV File to create promotions (createPromo Method) 
// 3. Display menu methods


