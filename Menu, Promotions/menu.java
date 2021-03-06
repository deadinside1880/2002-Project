import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// menu consists of the collection of ala Carte items and promotion items 
public class menu {
	
	
	// FOR TESTING PURPOSES 
	public static void main(String[] args) throws IOException {
		menu Menu = new menu("MenuItems.csv", "Promotions.csv"); 
		// Menu.displayMenu();

		Scanner sc = new Scanner(System.in);
		int choice; 
		String name, description;
		double price; 
		
		do {
			System.out.println("Welcome to Menu! What would you like to do?"); 
			System.out.println("(1) Add AlaCarte Items (2) Update AlaCarte Items (3) Remove AlaCarte Items"); 
			System.out.println("(4) Add Promo Items (5) Update Promo Items (6) Remove Promo Items");
			System.out.println("(7) Display Menu");
			System.out.println("(8) Quit");
			
			choice = sc.nextInt();
			sc.nextLine(); 
			
			switch(choice) {
			case 1:
				// name, description, price to be input
				System.out.println("Enter the new item name");
				name = sc.nextLine(); 
				System.out.println("Enter the new item description");
				description = sc.nextLine(); 
				System.out.println("Enter the new item price");
				price = sc.nextDouble();
				Menu.addAlaCarteItem(name, description, price);
				System.out.println("Item added!"); 
				break;
			case 2:
				System.out.println("Which item would you like to update?");
				name = sc.nextLine(); 
				Menu.updateAlaCarte(name);
				break;
			case 3:
				System.out.println("Which item would you like to remove?");
				name = sc.nextLine(); 
				Menu.removeAlaCarteItem(name);
				break;
			case 4:
				System.out.println("Enter the new promo name");
				name = sc.nextLine(); 
				System.out.println("Enter the new promo description");
				description = sc.nextLine(); 
				System.out.println("Enter the new promo price");
				price = sc.nextDouble();
				System.out.println("Enter the new promo items");
				String items = sc.nextLine();
				Menu.addPromotionItem(name, description, items, price);
				System.out.println("Promo added!"); 
				break;
			case 5:
				System.out.println("Which promo would you like to update?");
				name = sc.nextLine(); 
				Menu.updatePromo(name);
				break;
			case 6:
				System.out.println("Which item would you like to remove?");
				name = sc.nextLine(); 
				Menu.removePromoItem(name);
				break;
			case 7: 
				Menu.displayMenu();
				break;
			}
			
		}while(choice < 8);
		sc.close(); 
		System.exit(0);
		
	}
	
	ArrayList<MenuItems> alaCarteMenu  = new ArrayList<>(); 
	ArrayList<Promotions> promoMenu = new ArrayList<>(); 
	
	
	public menu(String fileAlaCarte, String filePromo) throws IOException {
		alaCarteMenu = readcsvAlaCarte(fileAlaCarte); 
		promoMenu = readCSVPromo(filePromo);
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
			br.close(); 
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
					// System.out.println(line);
					
					Promotions promoItem = createPromoMenu(attributes); 
					promoMenu.add(promoItem);

					line = br.readLine();
				}
			}
			br.close();
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
		String promoDescription = metadata[1]; 
		double promoPrice = Double.parseDouble(metadata[2]);
		String promoItems = metadata[3]; 
		
		return new Promotions(promoName, promoDescription, promoPrice, promoItems); 
	}
	
	public void addAlaCarteItem(String name, String description, double price) {
		MenuItems newItem = new MenuItems(name, description, price);
		alaCarteMenu.add(newItem);
	}
	
	public void addPromotionItem(String name, String description, String promoItems, double price) {
		Promotions newItem = new Promotions(name, description, price, promoItems);
		promoMenu.add(newItem);
	}

	
	public void updateAlaCarte(String name) {
		// find the item 
		int index = -1;
		Scanner sc = new Scanner(System.in); 
		for (int i = 0; i < alaCarteMenu.size(); i++) {
			if (alaCarteMenu.get(i).getName().equals(name.trim()))
				index = i;
		}
		
		
		// update the item 
		if (index == -1)
			System.out.println("Item cannot be found");
		else {
			int [] toEdit = new int[4]; 
			int choice; 
			int num = 0; 
			
			do {
				System.out.println("What do you want to edit?");
				System.out.println("(1) Item Name (2) Item Description (3) Item Price (4) None");
				System.out.println("Press 5 once you are done with your selection");
				
				choice = sc.nextInt();
				toEdit[num++] = choice;
				
			}while (choice < 4 && num < 3);
			
			// TO EDITTTTT 
			sc.nextLine();
			
			for (int i = 0; i < toEdit.length; i++) {
				// System.out.println(toEdit[i]);
				if (toEdit[i] == 1) {
					System.out.println("Enter the new Item Name");
					String newName = sc.nextLine();
					alaCarteMenu.get(index).setName(newName);
				}
				else if (toEdit[i] == 2) {
					System.out.println("Enter the new item description");
					String newDescription = sc.nextLine();
					alaCarteMenu.get(index).setDescription(newDescription);
				}
				else if (toEdit[i] == 3) {
					System.out.println("Enter the new item price"); 
					double newPrice = sc.nextDouble(); 
					alaCarteMenu.get(index).setPrice(newPrice);
				}			
			}
			 
		}
		
		// write to the file
		// System.out.println(index);
	}
	
	public void updatePromo(String name) {
		// find the item 
		int index = -1;
		Scanner sc = new Scanner(System.in); 
		
		for (int i = 0; i < promoMenu.size(); i++) {
			// System.out.println("Entered");
			if (promoMenu.get(i).getPromoName().trim().equals(name.trim()))
				index = i;
		}
		
		
		// update the item 
		if (index == -1)
			System.out.println("Item cannot be found");
		else {
			
			int [] toEdit = new int[5]; 
			int choice; 
			int num = 0; 
			
			do {
				System.out.println("What do you want to edit?");
				System.out.println("(1) Promo Name (2) Promo Description (3) Promo Price (4) Promo Items (5) None of the above");
				System.out.println("Press 5 once you are done with your selection");
				
				choice = sc.nextInt();
				toEdit[num++] = choice; 
				
			}while (choice < 5 && num < 4);
			
			// TO EDITTTTT 
			sc.nextLine();
			
			for (int i = 0; i < toEdit.length; i++) {
				// System.out.println(toEdit[i]);
				if (toEdit[i] == 1) {
					System.out.println("Enter the new Promo Name");
					String newName = sc.nextLine();
					promoMenu.get(index).setPromoName(newName);
				}
				else if (toEdit[i] == 2) {
					System.out.println("Enter the new promo description");
					String newDescription = sc.nextLine();
					promoMenu.get(index).setDescription(newDescription);
				}
				else if (toEdit[i] == 3) {
					System.out.println("Enter the new promo price"); 
					double newPrice = sc.nextDouble(); 
					promoMenu.get(index).setPackagePrice(newPrice);
				}	
				else if (toEdit[i] == 4) {
					System.out.println("Enter the new promo items"); 
					String newItem= sc.nextLine(); 
					promoMenu.get(index).setPromoItems(newItem);
				}
			}
			 
		}

		// System.out.println(index);
	}
	

	
	public ArrayList<MenuItems> removeAlaCarteItem(String name){
		int index = -1;
		for (int i = 0; i < alaCarteMenu.size(); i++) {
			if (alaCarteMenu.get(i).getName().equals(name)) 
				index = i; 
		}
		 
		System.out.println(index);
		if (index != -1)
			alaCarteMenu.remove(index); 
		return alaCarteMenu;
		
	}
	
	public ArrayList<Promotions> removePromoItem(String promoName){
		int index = -1;
		for (int i = 0; i < promoMenu.size(); i++) {
			if (promoMenu.get(i).getPromoName().equals(promoName)) 
				index = i; 
		}
		 
		System.out.println(index);
		if (index != -1)
			promoMenu.remove(index); 
		return promoMenu;
		
	}
	

	// TO CHECK THAT ALL READ CORRECTLY
	public void displayMenu() {
		
		// Display the alaCarte Menu
		System.out.println("----------- ALA CARTE MENU ---------------------");
		for (int i = 0; i < alaCarteMenu.size(); i++) {
			System.out.printf("%s \t --- \t %.2f \n", alaCarteMenu.get(i).getName(), alaCarteMenu.get(i).getPrice()); 
			System.out.println(alaCarteMenu.get(i).getDescription());
			// System.out.println("Type: " + alaCarteMenu.get(i).getType());
		}
		System.out.println();
		
		System.out.println("----------- PROMOTION MENU ---------------------");
		// Display the Promotion Menu 
		for (int i = 0; i < promoMenu.size(); i++) {
			System.out.printf("[%s] %s \t ----- \t %.2f\n", promoMenu.get(i).getPromoName(), promoMenu.get(i).getPromoItems(), promoMenu.get(i).getPromoPrice()); 
			System.out.println(promoMenu.get(i).getPromoDescription()); 
		}
		System.out.println();
	}
	
}


// Pseudo Code
// Read CSV File to determine size of the file --> Size of the array to be initialised 
// 1. Read the CSV File to create menu (createMenu Method)
// 2. Read the CSV File to create promotions (createPromo Method) 
// 3. Display menu methods

// TODO 
// Make private the stuff 
// Update/Remove items 


