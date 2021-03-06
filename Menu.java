package restaurant;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.net.URL;
import java.util.Scanner;

// menu consists of the collection of ala Carte items and promotion items 
public class Menu {

	public static void main(String[] args) throws IOException {
		Menu Menu = new Menu(); 
		Menu.displayMenu();
	}
	
	ArrayList<MenuItems> alaCarteMenu  = new ArrayList<>(); 
	ArrayList<Promotions> promoMenu = new ArrayList<>(); 
	
	private BufferedReader getBR(String filename)throws FileNotFoundException, IOException{
		
		URL url = getClass().getResource(filename);
 		File file = new File(url.getPath());
		FileReader fr=new FileReader( file.getAbsolutePath());   
 		BufferedReader br=new BufferedReader(fr);
		return br;
	}
	 public void menuOP() throws IOException{
	        System.out.println("What would you like to do?");
	        System.out.println("(1) Add an Item");
	        System.out.println("(2) Remove an Item");
	        System.out.println("(3) Update an Item");
	        System.out.println("(4) View Menu");
	        Scanner AT=new Scanner(System.in);
	        int choice = AT.nextInt();

	        try{
	            //Menu obj = new Menu();
	            AT.nextLine();
	            switch(choice){
	                case 1:
	                    String [] metadata = new String[4];
	                    System.out.println("Enter name");
	                    metadata[0] = AT.nextLine();
	                    System.out.println("Enter description");
	                    metadata[1] = AT.nextLine();
	                    System.out.println("Enter price");
	                    metadata[2] = AT.nextLine();
	                    addAlaCarteItem(metadata[0], metadata[1], Double.parseDouble(metadata[2]));
	                    break;
	                case 2:System.out.println("Enter name of the item to be removed (a la carte)");
	                    String name = AT.nextLine();
	                    removeAlaCarteItem(name);
	                    break;
	                case 3: System.out.println("Enter name of the item to be edited (a la carte)");
	                    String nam = AT.nextLine();
	                    updateAlaCarte(nam);
	                    break;
	                case 4: 
	                    displayMenu();
	                    break;
	                default: System.out.println("Invalid choice");
	            }

	        }catch(IOException e) {
	            System.out.println(e);
	            System.out.println("Couldnt make a menu object");
	        }
	        catch(Error e) {System.out.println(e);}

	    }

	public Menu() throws IOException {
		alaCarteMenu = readcsvAlaCarte(getBR("MenuItems.csv")); 
		promoMenu = readCSVPromo(getBR("Promotions.csv"));
	}
	
	public ArrayList<MenuItems> readcsvAlaCarte(BufferedReader br) {
		ArrayList<MenuItems> alaCarteMenu = new ArrayList<>();
		
		try {
			//BufferedReader br = new BufferedReader(new FileReader(fileName)); 
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
	
	public ArrayList<Promotions> readCSVPromo(BufferedReader br) {
		ArrayList<Promotions> promoMenu = new ArrayList<>();
		try {
			//BufferedReader br = new BufferedReader(new FileReader(fileName));
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
		String promoDescription = metadata[1]; 
		double promoPrice = Double.parseDouble(metadata[2]);
		String promoItems = metadata[3]; 
		
		return new Promotions(promoName, promoDescription, promoPrice, promoItems); 
	}
	
	public void addAlaCarteItem(String name, String description, double price)throws FileNotFoundException, IOException {
		for(MenuItems mi : alaCarteMenu) {
			if(name.toLowerCase().equals(mi.getName().toLowerCase())) {
				System.out.println("Item is already in the menu!!!");
				return;
			}
		}
		MenuItems newItem = new MenuItems(name, description, price);
		alaCarteMenu.add(newItem);
		writecsvAlaCarte(alaCarteMenu);
	}
	
	public void printalaCarteItems(ArrayList<MenuItems> menu) {
		for(MenuItems x: menu) {
			System.out.println(x.getName());
		}
	}
	
	public void addPromotionItem(String name, String description, String promoItems, double price)throws FileNotFoundException, IOException {
		for(Promotions mi : promoMenu) {
			if(name.toLowerCase().equals(mi.getPromoName().toLowerCase())) {
				System.out.println("Item is already in the menu!!!");
				return;
			}
		}
		Promotions newItem = new Promotions(name, description, price, promoItems);
		promoMenu.add(newItem);
		writecsvPromo(promoMenu);
	}

	
	public void updateAlaCarte(String name)throws FileNotFoundException, IOException {
		// find the item 
		int index = -1;
		Scanner sc = new Scanner(System.in); 
		for (int i = 0; i < alaCarteMenu.size(); i++) {
			if (alaCarteMenu.get(i).getName().toLowerCase().trim().equals(name.toLowerCase().trim()))
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
			writecsvAlaCarte(alaCarteMenu);

		}
		
		// write to the file
		// System.out.println(index);
	}
	
	public void updatePromo(String name)throws FileNotFoundException, IOException {
		// find the item 
		int index = -1;
		Scanner sc = new Scanner(System.in); 
		
		for (int i = 0; i < promoMenu.size(); i++) {
			// System.out.println("Entered");
			if (promoMenu.get(i).getPromoName().toLowerCase().trim().equals(name.toLowerCase().trim()))
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
				
				//writecsvPromo(promoMenu);
			}
			writecsvPromo(promoMenu);

		}

		// System.out.println(index);
	}
	

	
	public ArrayList<MenuItems> removeAlaCarteItem(String name)throws FileNotFoundException, IOException{
		int index = -1;
		for (int i = 0; i < alaCarteMenu.size(); i++) {
			if (alaCarteMenu.get(i).getName().toLowerCase().trim().equals(name.toLowerCase().trim())) 
				index = i; 
		}
		 
		if (index != -1) {
			alaCarteMenu.remove(index);
		}
		if(index==-1)
		{
			System.out.println("Item to be removed is not in the Menu!");
		}
		writecsvAlaCarte(alaCarteMenu);
		return alaCarteMenu;
		
	}
	
	public ArrayList<Promotions> removePromoItem(String promoName)throws FileNotFoundException, IOException{
		int index = -1;
		for (int i = 0; i < promoMenu.size(); i++) {
			if (promoMenu.get(i).getPromoName().toLowerCase().trim().equals(promoName.toLowerCase().trim())) 
				index = i; 
		}
		 
		if (index != -1) {
			promoMenu.remove(index); 
			//writecsvPromo(promoMenu);
		}
		if(index==-1)
		{
			System.out.println("Promotion meal to be removed is not in the Menu!");
		}
		writecsvPromo(promoMenu);
		return promoMenu;
		
	}
	

	// TO CHECK THAT ALL READ CORRECTLY
	public void displayMenu() {
		
		// Display the alaCarte Menu
		System.out.println("----------- ALA CARTE MENU ---------------------");
		for (int i = 0; i < alaCarteMenu.size(); i++) {
			System.out.printf("%s \t --- \t %.2f \n", alaCarteMenu.get(i).getName(), alaCarteMenu.get(i).getPrice()); 
			System.out.println(alaCarteMenu.get(i).getDescription());
			System.out.println(" ");
		}
		System.out.println();
		
		System.out.println("----------- PROMOTION MENU ---------------------");
		// Display the Promotion Menu 
		for (int i = 0; i < promoMenu.size(); i++) {
			System.out.printf("[%s] %s \t ----- \t %.2f \n", promoMenu.get(i).getPromoName(), promoMenu.get(i).getPromoItems(), promoMenu.get(i).getPromoPrice()); 
			System.out.println(promoMenu.get(i).getPromoDescription()); 
			System.out.println(" ");
		}
		System.out.println();
	}
	
	public void writecsvAlaCarte(ArrayList<MenuItems> ar)throws IOException, FileNotFoundException {
		URL url = getClass().getResource("MenuItems.csv");
 		File file = new File(url.getPath());
 		FileWriter f=new FileWriter(file.getAbsolutePath());   
 		BufferedWriter b=new BufferedWriter(f);   
 		PrintWriter pw = new PrintWriter(b); 
 		
 		//StringBuilder
		for(MenuItems x : ar) {
			pw.println(x.getName()+"," + x.getDescription()+","+x.getPrice()+","+x.getType());
					
		}
		pw.flush();
 		pw.close();
 		b.close();
 		f.close();
	}
	
	public void writecsvPromo(ArrayList<Promotions> ar)throws IOException, FileNotFoundException {
		URL url = getClass().getResource("Promotions.csv");
 		File file = new File(url.getPath());
 		FileWriter f=new FileWriter(file.getAbsolutePath());   
 		BufferedWriter b=new BufferedWriter(f);   
 		PrintWriter pw = new PrintWriter(b); 
		for(Promotions x : ar) {
			pw.println(x.getPromoName()+ "," + x.getPromoDescription()+","+x.getPromoPrice()+","+x.getPromoItems());
		
		}
		pw.flush();
 		pw.close();
 		b.close();
 		f.close();
	}
}


// Pseudo Code
// Read CSV File to determine size of the file --> Size of the array to be initialised 
// 1. Read the CSV File to create menu (createMenu Method)
// 2. Read the CSV File to create promotions (createPromo Method) 
// 3. Display menu methods
