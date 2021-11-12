package restaurant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_TIME;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

public class TableManager {
    
    static table[] pax2 = new table[5];
    static table[] pax4 = new table[4];
    static table[] pax6 = new table[3];
    static table[] pax8 = new table[2];
    static table[] pax10 = new table[1];

    public boolean isAvailable(int pax){
        switch(pax){
        	case 1:
            case 2: return checkTable(pax2);
            case 3:
            case 4: return checkTable(pax4);
            case 5:
            case 6: return checkTable(pax6);
            case 7:
            case 8: return checkTable(pax8);
            case 9:
            case 10: return checkTable(pax10);
            default: return false;
        }
    }

    public boolean checkTable(table[] arr){
        for(table x: arr){
            if(!x.getIsOccupied())
            return true;
        }

        return false;
    }
    public void printEmpTables()
    {
    	System.out.println("The unoccupied tables are");
    	System.out.println("Unoccupied two seater tables are:");
		System.out.println("Capacity:" + "2");

    	for(table x: pax2)
    	{
    		if(x.getIsOccupied()==false)
    		{	
    			System.out.println("Table ID:"+x.getTableID());
    		}
    	}
    	System.out.println("Unoccupied four seater tables are:");
    	System.out.println("Capacity:" + "4");

    	for(table x: pax4)
    	{
    		if(x.getIsOccupied()==false)
    		{
    		
    			System.out.println("Table ID:"+x.getTableID());
    		}
    	}
    	System.out.println("Unoccupied six seater tables are:");
    	System.out.println("Capacity:" + "6");
    	for(table x: pax6)
    	{
    		if(x.getIsOccupied()==false)
    		{
    			

    			System.out.println("Table ID:"+x.getTableID());
    		}
    	}
    	System.out.println("Unoccupied eight seater tables are:");
    	System.out.println("Capacity:" + "8");
    	for(table x: pax8)
    	{
    		if(x.getIsOccupied()==false)
    		{
    			System.out.println("Table ID:"+x.getTableID());
    		}
    	}
    	System.out.println("Unoccupied ten seater tables are:");
    	System.out.println("Capacity:" + "10");
    	for(table x: pax10)
    	{
    		if(x.getIsOccupied()==false)
    		{
    			System.out.println("Table ID:"+x.getTableID());
    		}
    	}
    }

    public table getEmptyTable(int pax){
        switch(pax){
            case 1:
            case 2: return getTable(pax2);
            case 3:
            case 4: return getTable(pax4);
            case 5:
            case 6: return getTable(pax6);
            case 7:
            case 8: return getTable(pax8);
            case 9:
            case 10: return getTable(pax10);
        }

        return null;
    }

    public void freeTable(String TID){
        int id = Integer.parseInt(TID);
        switch(id/10){
            case 1:
            case 2: pax2[id%10].setIsOccupied(false);
                break;
            case 3:
            case 4: pax4[id%10].setIsOccupied(false);
                break;
            case 5:
            case 6: pax6[id%10].setIsOccupied(false);
                break;
            case 7:
            case 8: pax8[id%10].setIsOccupied(false);
                break;
            case 9:
            case 10: pax10[id%10].setIsOccupied(false);
                break;
            default: System.out.println("NO such table found");
        }
        
    }

    public table getTable(table [] arr){
        for(table x: arr){
            if(!x.getIsOccupied())
                return x;
        }
        return null;
    }

    public boolean canReserve(int pax, String date, LocalTime time){

        switch(pax){
        	case 1:
            case 2: return checkReservation(pax2, date, time);
            case 3:
            case 4: return checkReservation(pax4, date, time);
            case 5:
            case 6: return checkReservation(pax6, date, time);
            case 7:
            case 8: return checkReservation(pax8, date, time);
            case 9:
            case 10: return checkReservation(pax10, date, time);
        }

        return false;
    }
    
    public boolean checkReservation(table[] arr, String date, LocalTime time){
        for(table x : arr){
            if(x!=null){
                for(reservation r : x.reservations){
                    if(r!=null){
                    	//System.out.println(r.getDate() + " vs " + date);
                    	//System.out.println(r.getTime() + " vs " + time);
                        if(!compareDates(date)){
                            System.out.println("Reservation date is before the current Date!!! Please enter a new date!!");
                            return false;
                        }
                        if(r.getDate().equals(date) && r.getTime().compareTo(time)==0)
                            return false;
                        
                    }
                }
            }
        }

        return true;
    }


    public table getEmptyTable(int pax,String date, LocalTime time){
        switch(pax){
        	case 1:
        	case 2: return findEmptyTable(pax2,date,time);
        	case 3:
        	case 4: return findEmptyTable(pax4,date,time);
        	case 5:
        	case 6: return findEmptyTable(pax6,date,time);
        	case 7:
        	case 8: return findEmptyTable(pax8,date,time);
        	case 9:
        	case 10: return findEmptyTable(pax10,date,time);
            default: System.out.println("Please divide your people and make multiple reservations");           
        }
        return null;
    }

    public table findEmptyTable(table[] arr,String date,LocalTime time){
        for(table x : arr){
            boolean flag = false;
            if(x!=null){
                for(reservation r : x.reservations){
                    if(r!=null){
                        if(!r.getDate().equals(date) || !(r.getTime().compareTo(time)==0))
                        {
                        	return x;
                        }
                    }

                }
                return x;
            }
        }
        return null;
    }

    public void initTables(){
        for(int i =0; i< pax2.length;i++){
            pax2[i] = new table((2*10 + i), 2, false);
        }

        for(int i =0; i< pax4.length;i++){
            pax4[i] = new table( (4*10 + i), 4, false);
        }

        for(int i =0; i< pax6.length;i++){
            pax6[i] = new table( (6*10 + i), 6, false);
        }

        for(int i =0; i< pax8.length;i++){
            pax8[i] = new table( (8*10 + i), 8, false);
        }

        for(int i =0; i< pax10.length;i++){
            pax10[i] = new table( (10*10 + i), 10, false);
        }
    }

    public void initReservations() throws IOException{
       String s;
       String[] words=null;
       URL url = getClass().getResource("reservations.txt");
        File file = new File(url.getPath());
        FileReader fr=new FileReader( file.getAbsolutePath());   
        BufferedReader br=new BufferedReader(fr);
        int num;   
        while((s=br.readLine())!=null)
        {
            words=s.split(",");
            num = Integer.parseInt(words[0]);
            reservation r = new reservation(num, words[1], words[2], words[3], LocalTime.parse(words[4]));
            reservationApp.allres.add(r);
            switch(num/10){
                case 1:
                case 2:pax2[num%10].addReservation(r);
                    break;
                case 3:
                case 4:pax4[num%10].addReservation(r);
                    break;
                case 5:
                case 6:pax6[num%10].addReservation(r);
                    break;
                case 7:
                case 8:pax8[num%10].addReservation(r);
                    break;
                case 9:
                case 10: pax10[num%10].addReservation(r);
                    break;
            }
            
        }
        
    }   

    public void trimReservation()throws IOException{
        reservationApp ra = new reservationApp();
        String current =(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)).format(ISO_LOCAL_TIME);
        LocalTime cur=LocalTime.parse(current);
        ArrayList<reservation> deleteList = new ArrayList<>();
        for(reservation x: reservationApp.allres){
            if(!compareDates(x.getDate())){
                //System.out.println("deleting reservation: "+x.getDate()+" "+x.getTime());
                deleteList.add(x);
                continue;
            }
            //System.out.println( x.getTime().until(cur,ChronoUnit.MINUTES));
            if(x.getDate().equals(parseLocalDate(LocalDate.now()))){
                if(x.getTime().until(cur,ChronoUnit.MINUTES) >30){
                    //System.out.println("deleting reservation: "+x.getDate()+" "+x.getTime());
                    deleteList.add(x);
                }
            }
        }
        for(reservation d: deleteList){
            ra.deleteReservation(d.getName());
        }
       
    }

    public String parseLocalDate(LocalDate ld){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return ld.format(formatter);
    }

    // public long timeDifference(String s1, String s2){
    //     long diff= 0;
    //     Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse(s1);
    //     Date d2 = new SimpleDateFormat("dd/MM/yyyy").parse(s2);
    //     System.out.println(d1);
    //     System.out.println(d2);
    //     diff = ChronoUnit.MINUTES.between(d1, d2);
    //     System.out.println(diff);
    //     return diff;
    // }

    public boolean compareDates(String s){
        String[] reserve = s.split("/");
        LocalDate ld = LocalDate.now();
        String now = parseLocalDate(ld);
        String[] now2 = now.split("/");
        if(Integer.parseInt(reserve[2])<Integer.parseInt(now2[2]))
            return false;
        else if(Integer.parseInt(reserve[2])==Integer.parseInt(now2[2]))
        {
          
            if(Integer.parseInt(reserve[1])<Integer.parseInt(now2[1]))
                return false;
            else if(Integer.parseInt(reserve[1])==Integer.parseInt(now2[1])){
                if(Integer.parseInt(reserve[0])<Integer.parseInt(now2[0])){
                    return false;
                }                   
            }

        }
        return true;
    }
}