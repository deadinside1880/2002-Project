package restaurant;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.IOException;
import java.util.ArrayList;
import java.io.*;
import java.net.URL;

public class SalesRevenueReport {

    static ArrayList<String> allOrder = new ArrayList<>();

    public SalesRevenueReport()throws IOException{
        readAllOrders();
    }
    
    public void readAllOrders()throws IOException{
        String s;
        URL url = getClass().getResource("orders.csv");
        File file = new File(url.getPath());
        FileReader fr=new FileReader( file.getAbsolutePath());   
        BufferedReader br=new BufferedReader(fr);   
        while((s=br.readLine())!=null)
        {
            allOrder.add(s);
            
        }
    }

    public void generateRevenue(){

        System.out.println("Total Number of orders: " + allOrder.size());
        System.out.println("Total revenue: " + getTotalRevenue());    
    }

    public Double getTotalRevenue() {
        Double total =0.0;
        for(String x: allOrder){
            String[] num = x.split(",");
            total += Double.parseDouble(num[4]);
        }
        return total;
    }

    public void printAllOrders(){
        for(String integer : allOrder){
                    String[] num = integer.split(",");
                    System.out.println("---------------------------------");
                    System.out.println("Order Created at : " + num[0]);
                    System.out.println("Order paid at : " + num[1]);
                    System.out.println("Order Items : " + num[2]);
                    System.out.println("Total Due : " + num[3]);
                    System.out.println("---------------------------------");
                }
    }
    
}