package restaurant;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
import java.net.URL;
import java.util.Scanner;

public class SalesRevenue {

    static ArrayList<String> allOrder = new ArrayList<>();
    static ArrayList<String> items = new ArrayList<>();
    static ArrayList<Integer> itemquant = new ArrayList<>();
    static ArrayList<Double> itemtotal = new ArrayList<>();
    
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

    public void generateRevenue()throws IOException{ 
    	allOrder.clear();
        readAllOrders();
        System.out.println("Enter date (mm/yyyy or dd/mm/yyyy) of report desired");
        Scanner AT = new Scanner(System.in);
        String date="";
        if(AT.hasNext())
            date = AT.nextLine();
        System.out.println("Total Number of Orders: " + getTotalOrders(date));
        System.out.println("Item breakdown:" );
        getItemBreakdown(date);
        System.out.printf("%-20s%-20s\n","Item","Quantity");
        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ ");
        for(int i=0;i<items.size();i++)
        {
        	System.out.printf("%-20s%-20d\n",items.get(i),itemquant.get(i));
        }
        System.out.println("Total revernue: "+getTotalRevenue(date));
        //AT.close();
    }

    public int getTotalOrders(String date){
        int count =0;
        String [] checkdate = date.split("/");
        if(checkdate.length==2){
            for(String x: allOrder){
                String [] order = x.split(",");
                String [] orderdate = order[0].substring(0,10).split("-");
                if(checkdate[1].equals(orderdate[0]) && checkdate[0].equals(orderdate[1])){
                    count++;
                }
               
            }
        }
        if(checkdate.length==3){
            for(String x: allOrder){
                String [] order = x.split(",");
                String [] orderdate = order[0].substring(0,10).split("-");
                if(checkdate[2].equals(orderdate[0]) && checkdate[1].equals(orderdate[1]) && checkdate[0].equals(orderdate[2])){
                    count++;
                }
            }
        }
        return count;
    }

    public Double getTotalRevenue(String date) {
        Double total =0.0;
        String [] checkdate = date.split("/");
        if(checkdate.length==2){
        for(String x: allOrder)
        {
            String [] order = x.split(",");
            String [] orderdate = order[0].substring(0,10).split("-");
            if(checkdate[1].equals(orderdate[0]) && checkdate[0].equals(orderdate[1]))
            {
            	  total += Double.parseDouble(order[4]);
            }
            }
        }
        else if(checkdate.length==3){
            for(String x: allOrder){
                String [] order = x.split(",");
                String [] orderdate = order[0].substring(0,10).split("-");   
                if(checkdate[2].equals(orderdate[0]) && checkdate[1].equals(orderdate[1]) && checkdate[0].equals(orderdate[2]))
                {
                	total += Double.parseDouble(order[4]);
                }
                }
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

    public void getItemBreakdown(String date){
    	items.clear();
    	itemquant.clear();	
    	 String [] checkdate = date.split("/");
    	 String item=null;
    	 int flag=0;
    	 int quantity=0;
         if(checkdate.length==2){
             for(String x: allOrder){
                 String [] order = x.split(",");
                 String [] orderdate = order[0].substring(0,10).split("-");
                 if(checkdate[1].equals(orderdate[0]) && checkdate[0].equals(orderdate[1]))
                 {	 	String[] ord=order[3].split(";");
                 		

                 		for(int i=0;i<ord.length;i++)
                		 {
                			 	String[] itemQuant=ord[i].split(":");
                			 	flag=0;	
                			 	item=itemQuant[0];
                			 
                			 	quantity=Integer.parseInt(itemQuant[1]);
                			 	if(items.size()==0)
                			 	{
                			 		items.add(item);
                			 		itemquant.add(quantity);
                			 		continue;
                			 	}
                			 	for(int a=0;a<items.size();a++)
                			 	{
                			 		if(items.get(a).equals(item))
                			 		{
                			 			int temp=0;
                			 			temp=itemquant.get(a);
                			 			temp=temp+quantity;
                			 			itemquant.set(a,temp);
                			 			flag=1;
                			 			
                			 		}
                			 	}
                			 	if(flag==0)
                			 	{
                			 		items.add(item);
                			 		itemquant.add(quantity);
                			 	}
                		}
                		 
                	
                 }
             }
         }
         else if(checkdate.length==3){
             for(String x: allOrder){
                 String [] order = x.split(",");
                 String [] orderdate = order[0].substring(0,10).split("-");   
                 if(checkdate[2].equals(orderdate[0]) && checkdate[1].equals(orderdate[1]) && checkdate[0].equals(orderdate[2]))
                 {
                	 String[] ord=order[3].split(";");
                	 
            		 for(int i=0;i<ord.length;i++)
            		 {		flag=0;
            			 	String[] itemQuant=ord[i].split(":");
            			 	item=itemQuant[0];
            			 	
            			 	quantity=Integer.parseInt(itemQuant[1]);
            			 	if(items.size()==0)
            			 	{
            			 		items.add(item);
            			 		itemquant.add(quantity);
            			 		continue;
            			 	}
            			 	
            			 	for(int a=0;a<items.size();a++)
            			 	{
            			 		if(items.get(a).equals(item))
            			 		{
            			 			int temp=0;
            			 			temp=itemquant.get(a);
            			 			temp=temp+quantity;
            			 			itemquant.set(a,temp);
            			 			flag=1;
            			 		
            			 		}
            			 	}
            			 	if(flag==0)
            			 	{
            			 		items.add(item);
            			 		itemquant.add(quantity);
            			 	}
            		}
                	 
                	 }
                 }
             }
        }
    }
    

