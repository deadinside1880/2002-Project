package restaurant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_TIME;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.temporal.ChronoUnit;

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

    public table getEmptyTable(table[] arr){
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
                    	System.out.println(r.getDate() + " vs " + date);
                    	System.out.println(r.getTime() + " vs " + time);
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
        System.out.println("findEmptyTable returns null");
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
        while((s=br.readLine())!=null)
        {
            words=s.split(",");
            reservationApp.allres.add(new reservation(Integer.parseInt(words[0]), words[1], words[2], words[3], LocalTime.parse(words[4])));
            
        }
    }   

    public void trimReservation()throws IOException{
        reservationApp ra = new reservationApp();
        String current =(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)).format(ISO_LOCAL_TIME);
        LocalTime cur=LocalTime.parse(current);
        for(reservation x: reservationApp.allres){
            if(x.getTime().until(cur,ChronoUnit.MINUTES) > 30){
                ra.deleteReservation(x.getName());
            }
        }
    }
}