package restaurant;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class start {

    public static void main(String[] args) throws IOException, ParseException {
        
        Scanner AT = new Scanner(System.in);
        int choice = -1;
        start obj = new start();
        
        Menu menux = new Menu();
        reservationApp reservationapp = new reservationApp();
        TableManager tb=new TableManager();
        OrderManager ordman = new OrderManager();

        tb.initTables();
        tb.initReservations();
        OrderInvoice oi = new OrderInvoice();
        SalesRevenue sr = new SalesRevenue();

        while(choice != 9){
            System.out.println("Welcome to this restaurant! What would you like to do?");
            System.out.println("(1) Get Invoice");
            System.out.println("(2) Check Tables");
            System.out.println("(3) Place/Remove/Update/View an Order");
            System.out.println("(4) Make/Remove/Update/View Reservations");
            System.out.println("(5) View/Edit Menu");
            System.out.println("(6) Get Revenue/Sales Report");
            System.out.println("(7) Make/Remove/Update Promos");
            System.out.println("(8) View/Edit Staff");
            System.out.println("(9) Exit application");
        
            choice = AT.nextInt();
           
            switch(choice){
                case 1: System.out.println("Enter Table ID");
                    AT.nextLine();
                    oi.generateInovice(AT.nextLine());
                    break;
                case 2:
                	tb.printEmpTables();
                    break;
                case 3: ordman.createOrder();
                    break;
                case 4: reservationapp.reservations();
                    break;
                case 5: menux.menuOP();
                    break;
                case 6: sr.generateRevenue();
                    break;
                case 7:(new OrderManager()).viewAllOrders();
                    break;
                case 9: break;
                case 8:
                    ordman.staff();
                    break;
                default: System.out.println("Please choose a valid option between 1 and 10");
            }
        }
        AT.close();
    }
    
}

 
    

   
