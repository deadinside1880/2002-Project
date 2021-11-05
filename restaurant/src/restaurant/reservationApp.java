package restaurant;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class reservationApp {
    static int customerID = 0;
    static int tableID = 0;
    static int totalReservationCounter = 0;

    public static void main(String[] args) throws Exception {
        

        Scanner scc = new Scanner(System.in);
        int output = 0;
        System.out.println("Hello! Would you like to create a reservation?");
        System.out.println("1. Yes\n" + "2. No\n");
        
        output = scc.nextInt();
        if(output == 1){
            createReservation();
        }
        else{
            System.out.println("Thanks for your time!");
        }

    }

    public static void createReservation() {
        int reservationCounter = 0;
        Scanner sc = new Scanner(System.in);
        int j= 0;
        System.out.println("Enter the total number of reservations:");
        reservationCounter = sc.nextInt();
        reservation[] reservationList = new reservation[reservationCounter];
        for(int i=0; i<reservationCounter; i++)
		{   int k = 0;
            int  pax = 0;
            String name;
            int contact = 0;
            String id;
            String inputDate;
            Date date;
            int tableCount = 0;
            System.out.println("Input number of people: ");
            pax = sc.nextInt();
            System.out.println("Input your name: ");
            name = sc.next();
            System.out.println("Input your contact number: ");
            contact = sc.nextInt();
            id =  getRandomNumberString();
            System.out.println("Enter the Date in this format (dd-MMM-yyyy) ");
            inputDate = sc.next();
            date = getDateInputString(inputDate);
            tableCount = Math.floorDiv(pax, 5);
            table[] tableList = new table[tableCount];
            while(k<tableCount && pax > 0){
                tableList[k] = new table(tableID, pax, customerID ,true);
                tableID++;
                customerID++;
                k++;
                pax = pax -5;

            }

            reservationList[j] = new reservation(id, pax, name, contact,date, tableList);
            j++;
            totalReservationCounter++;


        }
        System.out.println("Thanks for making your reservations!");
	}


    public static void deleteReservation(String reservationID){

    }
    public static void updateReservation(String reservationID){

    }



    public static Date getDateInputString(String date) {

        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        Date date2=null;
        try {
            //Parsing the String
            date2 = dateFormat.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date2;
    }
    public static String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
    
        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }




}
