import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class start {

    public static void main(String[] args) {
        
        Scanner AT = new Scanner(System.in);
        int choice = -1;
        start obj = new start();

        while(choice != 10){
            System.out.println("Welcome to this restaurant! What would you like to do?");
            System.out.println("(1) Get Invoice");
            System.out.println("(2) Check Tables");
            System.out.println("(3) Place/Remove/Update/View an Order");
            System.out.println("(4) Make/Remove/Update/View Reservations");
            System.out.println("(5) View/Edit/ Menu");
            System.out.println("(6) Get Revenue/Sales Report");
            System.out.println("(7) Make/Remove/Update Promos");
            System.out.println("(8) Write to File");
            System.out.println("(9) Read from File");
            System.out.println("(10) Exit application");
            
            choice = AT.nextInt();
            FileReader fr = new FileReader();
            String path;
           
            switch(choice){
                case 1:
                    break;
                case 2:
                    break;
                case 3: obj.order(AT);
                    break;
                case 4: obj.reservations(AT);
                    break;
                case 5: obj.menu(AT);
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 9:System.out.println("Enter path");
                    path = AT.next();
                    int count = 0;
                    try{
                        System.out.println("-------- MENU---------");
                        for(String x: fr.readFile(path)){
                            System.out.print(x+ " ");
                            if(count ==2){
                                System.out.println();
                                count =0;
                            }
                            else{
                                count++;
                            }
                        }
                        System.out.println("---------------------");
                        System.out.println();
                    }catch(FileNotFoundException e ) {System.out.println(e);}
                    break;
                case 8:System.out.println("Enter data");
                    String temp = AT.nextLine();
                    String[] bleh = AT.nextLine().split(",");
                    ArrayList<String> data = new ArrayList<>(Arrays.asList(bleh));
                    System.out.println("Enter path");
                    path = AT.nextLine();
                    try{
                        for(String x: fr.readFile(path))
                            data.add(x);    
                        fr.writeFile(data, path);
                    }catch(FileNotFoundException fnfe){ System.out.println(fnfe);}
                    break;
                case 10: break;
                default: System.out.println("Please choose a valid option between 1 and 10");
            }
        }
        AT.close();
    }

    private void order(Scanner AT){
        System.out.println("What would you like to do?");
        System.out.println("(1) Place an Order");
        System.out.println("(2) Cancel an Order");
        System.out.println("(3) Update an Order");
        System.out.println("(4) View an Order");

        int choice = AT.nextInt();

        switch(choice){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default: System.out.println("Invalid choice");
        }
    }

    private void reservations(Scanner AT){
        System.out.println("What would you like to do?");
        System.out.println("(1) Make a Reservation");
        System.out.println("(2) Cancel a Reservation");
        System.out.println("(3) Update a Reservation");
        System.out.println("(4) View Reservations");

        int choice = AT.nextInt();

        switch(choice){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                System.out.println("----------- Reservations -----------");
                printStuff("reservations.txt", 4);
                break;
            default: System.out.println("Invalid choice");
        }
    }

    private void menu(Scanner AT){
        System.out.println("What would you like to do?");
        System.out.println("(1) Add an Item");
        System.out.println("(2) Remove an Item");
        System.out.println("(3) Update an Item");
        System.out.println("(4) View Menu");

        int choice = AT.nextInt();

        switch(choice){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                System.out.println("----------- MENU ------------");
                printStuff("menu.txt", 3);    
                break;
            default: System.out.println("Invalid choice");
        }
    }

    private void printStuff(String path, int linewrap){
        int count = 1;
        FileReader fr = new FileReader();
        try{
            for(String x: fr.readFile(path)){
                System.out.print(x+ " ");
                if(count ==linewrap){
                    System.out.println();
                    count =1;
                }
                else{
                    count++;
                }
            }
            System.out.println("-------------------------------");
            System.out.println();
        }catch(FileNotFoundException e ) {System.out.println(e);}
    }
}