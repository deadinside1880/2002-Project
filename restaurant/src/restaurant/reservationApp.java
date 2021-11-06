package restaurant;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;




public class reservationApp {
	
	ArrayList<table> tab=new ArrayList<>();
	static ArrayList<reservation> allres=new ArrayList<>();
	TableManager tb=new TableManager();
	public void createReservation()throws IOException
	{
		Scanner sc=new Scanner(System.in);
		boolean reserve;
		String name,contact,date,time;
		int pax=0;
		LocalTime time1;
		System.out.println("Enter the number of people(max 10 pax)");
		pax=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter the name");
		name=sc.nextLine();
		System.out.println("Enter the contact number");
		contact=sc.nextLine();
		System.out.println("Enter the date in the following format(dd/mm/yyyy):");
		date=sc.nextLine();
		System.out.println("Enter the time in the following format(hh:mm:ss):(tables can only be reserved on an hourly basis)");
		time=sc.nextLine();
		time1=getTimeInputString(time);
		reserve=tb.canReserve(pax,date,time1);
		if(reserve == true)
		{
			table temp=tb.getEmptyTable(pax, date, time1);
			reservation re=new reservation(temp.getTableID(),name,contact,date,time1);
			allres.add(re);
			temp.reservations.add(re);
			writeToFile("reservations.txt");
			
		}
		else
		{
			System.out.println("Sorry!!! No more reservations are available at this time!!!");
		}
		for(reservation x: allres)
		{
			System.out.println(x.getName()+x.getTime()+x.getTableID());
		}
		
		
	}
	public void deleteReservation(String name)throws IOException{   
    	
		int i=0,j=0,flag1=0,flag2=1,pax=0,index=0;
    	for(reservation x:allres)
    	{
    		if(x.getName().toLowerCase().trim().equals(name.toLowerCase().trim()))
    		{
    			pax=allres.get(i).getTableID()/10;
    			index=allres.get(i).getTableID()%10;
    			allres.remove(i);
    			flag1=1;
    			break;
    		}
    		i++;
    	}
    	writeToFile("reservations.txt");
		switch(pax)
		{
		case 2:
			for(reservation y:TableManager.pax2[index].reservations)
			{
	    		if(y.getName().toLowerCase().trim().equals(name.toLowerCase().trim()))
	    		{	
	    			TableManager.pax2[index].reservations.remove(j);
	    			break;
	    		}
	    		j++;
	    	}
			break;
		case 4:
			for(reservation y:TableManager.pax4[index].reservations)
			{
	    		if(y.getName().toLowerCase().trim().equals(name.toLowerCase().trim()))
	    		{	
	    			TableManager.pax4[index].reservations.remove(j);
	    			break;
	    		}
	    		
	    		j++;
	    	}
			break;
		case 6:
			for(reservation y:TableManager.pax6[index].reservations)
			{
	    		if(y.getName().toLowerCase().trim().equals(name.toLowerCase().trim()))
	    		{	
	    			TableManager.pax6[index].reservations.remove(j);
	    			break;
	    		}	    		
	    		j++;
	    	}
			break;
		case 8:
			for(reservation y:TableManager.pax8[index].reservations)
			{
	    		if(y.getName().toLowerCase().trim().equals(name.toLowerCase().trim()))
	    		{	
	    			TableManager.pax8[index].reservations.remove(j);
	    			break;
	    		}	    		
	    		j++;
	    	}
			break;
		case 10:
			for(reservation y:TableManager.pax2[index].reservations)
			{
	    		if(y.getName().toLowerCase().trim().equals(name.toLowerCase().trim()))
	    		{	
	    			TableManager.pax10[index].reservations.remove(j);
	    			break;
	    		}	    		
	    		j++;
	    	}
			break;
		case 0:
			flag2=0;
			break;
		}

    	if(flag1==0 && flag2==0)
    	{
    		System.out.println("Reservation not found!!! Please try again");
    	}
    	else
    	{
    		System.out.println("Reservation has been deleted!!!");
    	}
    }
   
	public void updateReservation(String name)throws IOException{
		
		int index=0,pax=0,i=0,j=0,flag=0;
		String name1,contact,date,time;
		int noOfPax=0;
		LocalTime time1;
		reservation temp=new reservation(1,name,"contact","date",getTimeInputString("00:00:00"));

		for(reservation x : allres)
		{
			if(x.getName().toLowerCase().trim().equals(name.toLowerCase().trim()))
			{
				pax=x.getTableID()/10;
				index=x.getTableID()%10;
				temp=x;
				flag=1;
				deleteReservation(x.getName());
				break;
			}
		
		}

		if(flag==1)
		{
			Scanner sc=new Scanner(System.in);
			int choice=-1;
			System.out.println("Reservation found!!!");
			while(choice!=6)
			{
			System.out.println("1. Update the name");
			System.out.println("2. Update the Date");
			System.out.println("3. Update the time");
			System.out.println("4. Update the contact info");
			System.out.println("5. Update pax");
			System.out.println("6. Exit");
			choice=sc.nextInt();
			sc.nextLine();
			
			switch(choice)
			{
			case 1:
				table tempTable=tb.getEmptyTable(pax,temp.getDate(),temp.getTime());
				System.out.println("Enter the new name");
				name1=sc.nextLine();
				temp.setName(name1);
				allres.add(temp);
				tempTable.reservations.add(temp);
				System.out.println("Name updated successfully!!");
				break;
			case 2:
				tempTable=tb.getEmptyTable(pax,temp.getDate(),temp.getTime());
				System.out.println("Enter the new Date");
				date=sc.nextLine();
				temp.setDate(date);
				allres.add(temp);
				tempTable.reservations.add(temp);
				System.out.println("Date updated successfully!!");

				break;
			case 3:
				tempTable=tb.getEmptyTable(pax,temp.getDate(),temp.getTime());
				System.out.println("Enter the new time in the format (hh:mm:ss):(tables can only be reserved on an hourly basis)");
				time=sc.nextLine();
				time1=getTimeInputString(time);
				temp.setTime(time1);
				allres.add(temp);
				tempTable.reservations.add(temp);
				System.out.println("Time updated successfully!!");
				break;
			case 4:
				tempTable=tb.getEmptyTable(pax,temp.getDate(),temp.getTime());
				System.out.println("Enter the new contact number");
				contact=sc.nextLine();
				temp.setContact(contact);;
				allres.add(temp);
				tempTable.reservations.add(temp);
				System.out.println("Contact updated successfully!!");
				break;
			case 5:
				tempTable=tb.getEmptyTable(pax,temp.getDate(),temp.getTime());

				System.out.println("Enter the new number of pax");
				noOfPax=sc.nextInt();
				tempTable=tb.getEmptyTable(noOfPax,temp.getDate(),temp.getTime());
				allres.add(temp);
				tempTable.reservations.add(temp);
				System.out.println("Number of people updated successfully!!");
				break;
			case 6:
				System.out.println("Cancelling the updation");
				break;
			}
			}
		}
		else
		{
			System.out.println("Reservation not found!!! Please try again");
		}
		writeToFile("reservations.txt");
	}
	public void checkReservation(String name)
	{
		reservation temp=new reservation(1,name,"contact","date",getTimeInputString("00:00:00"));
		int i=0,flag=0;
		for (reservation x: allres)
		{
			if(x.getName().toLowerCase().trim().equals(name.toLowerCase().trim()))
    		{
    			temp=x;
    			flag=1;
    			break;
    		}
    		i++;
    	}
		if(flag==1)
		{
			System.out.println("Reservation has been found!!!");
			System.out.println("The details are:");
			System.out.println("Name: "+temp.getName());
			System.out.println("Contact: "+temp.getContact());
			System.out.println("Date: "+temp.getDate());
			System.out.println("Time: "+temp.getTime());
			System.out.println("TableId: "+temp.getTableID());
		}
		else
		{
			System.out.println("Reservation not found!!Please try again");
		}
		
	}
	public void writeToFile(String filename)throws IOException
	{
		URL url = getClass().getResource(filename);
 		File file = new File(url.getPath());
 		FileWriter f=new FileWriter(file.getAbsolutePath());   
 		BufferedWriter b=new BufferedWriter(f);   
 		PrintWriter pw = new PrintWriter(b);  
 		for(reservation x: allres)
 		{
 			pw.println(x.getTableID()+","+x.getName()+','+x.getContact()+','+x.getDate()+','+x.getTime());
 		}
 		pw.flush();
 		pw.close();
 		b.close();
 		f.close();
	}
	public static LocalTime getTimeInputString(String time) {

        //Parsing the String
		LocalTime lt=LocalTime.parse(time);
        return lt;
    }
   
   
}
