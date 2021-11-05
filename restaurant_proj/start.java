package restaurant;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import restaurant.Menu.*;
import java.io.*;

public class start {

    public static void main(String[] args) throws IOException {
        
        Scanner AT = new Scanner(System.in);
        int choice = -1;
        start obj = new start();

        while(choice != 10){
            System.out.println("Welcome to this restaurant! What would you like to do?");
            System.out.println("(1) Get Invoice");
            System.out.println("(2) Check Tables");
            System.out.println("(3) Place/Remove/Update/View an Order");
            System.out.println("(4) Make/Remove/Update/View Reservations");
            System.out.println("(5) View/Edit Menu");
            System.out.println("(6) Get Revenue/Sales Report");
            System.out.println("(7) Make/Remove/Update Promos");
            System.out.println("(11) View/Edit Staff");
            System.out.println("(8) Write to File");
            System.out.println("(9) Read from File");
            System.out.println("(10) Exit application");
            
            choice = AT.nextInt();
            FileHandler fr = new FileHandler();
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
                    String bleh = AT.nextLine();
                    ArrayList<String> data = new ArrayList<>();
                    System.out.println("enter linewrap");
                    int linewrap = AT.nextInt();
                    System.out.println("Enter path");
                    path = AT.nextLine();
                    try{
                        for(String x: fr.readFile(path))
                            data.add(x);  
                        data.add(bleh);  
                        fr.writeFile(data, path, linewrap);
                    }catch(FileNotFoundException fnfe){ System.out.println(fnfe);}
                    break;
                case 10: break;
                case 11:
                    obj.staff(AT);
                    break;
                default: System.out.println("Please choose a valid option between 1 and 10");
            }
        }
        AT.close();
    }

    private void order(Scanner AT)throws IOException{
        System.out.println("What would you like to do?");
        Order obj = new Order();
        obj.createOrder();
    //     System.out.println("(1) Place an Order");
    //     System.out.println("(2) Cancel an Order");
    //     System.out.println("(3) Update an Order");
    //     System.out.println("(4) View an Order");

    //     int choice = AT.nextInt();
    //     Order obj = new Order();

    //     switch(choice){
    //         case 1:obj.createOrder();
    //             break;
    //         case 2:
    //             break;
    //         case 3:
    //             break;
    //         case 4:
    //             break;
    //         default: System.out.println("Invalid choice");
    //     }
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
                //printStuff("reservations.txt");
                break;
            default: System.out.println("Invalid choice");
        }
    }

    private void menu(Scanner AT) throws IOException{
        System.out.println("What would you like to do?");
        System.out.println("(1) Add an Item");
        System.out.println("(2) Remove an Item");
        System.out.println("(3) Update an Item");
        System.out.println("(4) View Menu");

        int choice = AT.nextInt();

        try{
            menu obj = new menu();
            AT.nextLine();
            switch(choice){
                case 1:
                    String [] metadata = new String[4];
                    System.out.println("ENter name");
                    metadata[0] = AT.nextLine();
                    System.out.println("Enter description");
                    metadata[1] = AT.nextLine();
                    System.out.println("Enter price");
                    metadata[2] = AT.nextLine();
                    obj.addAlaCarteItem(metadata[0], metadata[1], Double.parseDouble(metadata[2]));
                    break;
                case 2:System.out.println("Enter name of the item to be removed (a la carte)");
                    String name = AT.nextLine();
                    obj.removeAlaCarteItem(name);
                    break;
                case 3: System.out.println("Enter name of the item to be edited (a la carte)");
                    String nam = AT.nextLine();
                    obj.updateAlaCarte(nam);
                    break;
                case 4: 
                    obj.displayMenu();
                    break;
                default: System.out.println("Invalid choice");
            }

        }catch(IOException e) {
            System.out.println(e);
            System.out.println("Couldnt make a menu object");
        }
        catch(Error e) {System.out.println(e);}

    }

    private void staff(Scanner AT)throws IOException{
        System.out.println("What would you like to do?");
        System.out.println("(1) View Staff");
        System.out.println("(2) Add Staff");
        System.out.println("(3) Remove Staff");
        System.out.println("(4) Update Staff");

        int choice = AT.nextInt();

        Order o = new Order();
        //System.out.println(new File(".").getAbsolutePath());

        switch(choice){
            case 1: Staff staff = o.getStaff();
                System.out.println("Id: "+ staff.getEmployeeID());
                System.out.println("Name: "+ staff.getName());
                System.out.println("Gender: "+ staff.getGender());
                System.out.println("Job: "+ staff.getJobTitle());
                break;
            case 2: System.out.println("Enter employee id");
                AT.nextLine();
                String empid = AT.nextLine();
                o.setStaff(empid);
                break;
            case 3: System.out.println("feature not available rn");
                break;
            case 4: System.out.println("feature unavailable");
                break;
            default: System.out.println("Invalid option");            
        }
    }

    // private void printStuff(String path){
    //     int count = 1;
    //     FileHandler fr = new FileHandler();
    //     try{
    //         for(String x: fr.readFile(path)){
    //             System.out.print(x+ "\n");
    //         }
    //         System.out.println("-------------------------------");
    //         System.out.println();
    //     }catch(FileNotFoundException e ) {System.out.println(e);}
    // }
}