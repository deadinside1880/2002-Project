package restaurant;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class reservationApp {
    static int customerID = 0;
    static int tableID = 0;
    static int totalReservationCounter = 0;
    static reservation[] reservationList = new reservation[10];

    public static void main(String[] args) throws Exception {
        
    	reservationApp res=new reservationApp();
        Scanner scc = new Scanner(System.in);
        int output = 0;
        System.out.println("Hello! Would you like to create a reservation?");
        System.out.println("1. Yes\n" + "2. No\n");
        
        output = scc.nextInt();
        if(output == 1){
            res.createReservation();
        }
        else{
            System.out.println("Thanks for your time!");
        }

    }

    public  void createReservation() throws IOException {
        int reservationCounter = 0;
        Scanner sc = new Scanner(System.in);
        int j= 0;
       
         int k = 0;
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
            tableCount = (Math.floorDiv(pax, 5))+1;
            table[] tableList = new table[tableCount];
            int temppax=pax;
            while(k<tableCount && temppax > 0){
                tableList[k] = new table(tableID, temppax, customerID ,true);
                URL url = getClass().getResource("table.txt");
         		File file = new File(url.getPath());
         		FileWriter fw = new FileWriter(file.getAbsolutePath(), true);
         		BufferedWriter bw = new BufferedWriter(fw);
        		PrintWriter pw = new PrintWriter(bw);
         		pw.println(tableList[k].getTableID()+","+tableList[k].getCapacity()+","+tableList[k].getCustomerID()+","+tableList[k].getIsOccupied());
         		System.out.println("Data Successfully appended into file");
        		pw.flush();
         		pw.close();
        	    bw.close();
        	    fw.close();
                tableID++;
                customerID++;
                k++;
                temppax = temppax -5;

            }
           
            reservationList[totalReservationCounter] = new reservation(id, pax, name, contact,date, tableList);
        	URL url = getClass().getResource("reservations.txt");
     		File file = new File(url.getPath());
     		FileWriter fw = new FileWriter(file.getAbsolutePath(), true);
     		BufferedWriter bw = new BufferedWriter(fw);
    		PrintWriter pw = new PrintWriter(bw);
     		pw.println(reservationList[totalReservationCounter].getreservationID()+','+reservationList[totalReservationCounter].getnoOfPax()+','+reservationList[totalReservationCounter].getName()+','+reservationList[totalReservationCounter].getContact()+','+reservationList[totalReservationCounter].getDate()+','+reservationList[totalReservationCounter].getTableList().length);
     		System.out.println("Data Successfully appended into file");
    		pw.flush();
     		pw.close();
    	    bw.close();
    	    fw.close();
            
            totalReservationCounter++;


       

    
        System.out.println("Thanks for making your reservations!");
        updateReservation(id);
	}


    public static void deleteReservation(String reservationID){

    }
    public void updateReservation(String reservationID) throws IOException{
    	String s;
    	String[] words=null;
    	URL url = getClass().getResource("reservations.txt");
 		File file = new File(url.getPath());
 		FileReader fr=new FileReader(file);   
		BufferedReader br=new BufferedReader(fr);  
		while((s=br.readLine())!=null)
	 	{
	 			words=s.split(",");
	 			System.out.println(words[0]+','+words[1]+','+words[2]+','+words[3]+','+words[4]+','+words[5]);
	 	}
		fr.close();
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
