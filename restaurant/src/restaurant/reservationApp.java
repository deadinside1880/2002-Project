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
    int customerID = 0;
    int tableID = 0;
    static int totalReservationCounter = 0;
    reservation[] reservationList = new reservation[10];

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


    public void deleteReservation(String name){
    	int blah = checkReservation(name);
    	if(blah>=0) {
    		reservationList[blah] = null;    
    		System.out.println("Reservation cancelled!!");
    	}
    }
    public void updateReservation(String name) throws IOException{
//    	String s;
//    	String[] words=null;
//    	URL url = getClass().getResource("reservations.txt");
// 		File file = new File(url.getPath());
// 		FileReader fr=new FileReader(file);   
//		BufferedReader br=new BufferedReader(fr);  
//		while((s=br.readLine())!=null)
//	 	{
//	 			words=s.split(",");
//	 			System.out.println(words[0]+','+words[1]+','+words[2]+','+words[3]+','+words[4]+','+words[5]);
//	 			
//	 	}
//		fr.close();
    	
    	int index = checkReservation(name);
    	if(index>-1) {
    		printReservation(reservationList[index]);
    		System.out.println("What would you like to change?");
    		System.out.println("1 Name");
    		System.out.println("2 Contact");
    		System.out.println("3 No of Pax");
    		System.out.println("4 Date");
    		
    		Scanner AT = new Scanner(System.in);
    		int choice = AT.nextInt();
    		
    		switch(choice) {
    			case 1:
    				System.out.println("Enter new name");
    				reservationList[index].setName(AT.nextLine());
    				break;
    			case 2:
    				System.out.println("Enter new Contact");
    				reservationList[index].setContact(AT.nextInt());
    				break;
    			case 3: 
    				System.out.println("Enter new No of Pax");
    				reservationList[index].setnoOfPax(AT.nextInt());
    				break;
    			case 4: 
    				System.out.println("Enter new Date");
    				reservationList[index].setDate(getDateInputString(AT.nextLine()));
    				break;
    		}
    	}
    }

    public int checkReservation(String name) {
    	boolean flag = true;
    	int counter = -1;
    	for(reservation x : reservationList) {
    		if(x.getName().equals(name)) {
    			counter++;
    			System.out.println("Reservation found!!");
    			flag =false;
    			
    			return counter;
    		}
    	}
    	
    	if(flag) {
    		System.out.println("Reservation not found!!");
    	}
		return -1;
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

    public void printReservation(reservation x) {
    	System.out.println(x.getName()+"\n"+x.getContact()+"\n"+x.getnoOfPax()+"\n"+x.getreservationID()+"\n"+x.getDate());
    }


}
