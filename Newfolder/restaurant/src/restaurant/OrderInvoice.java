package restaurant;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.StringBuilder;;
/*
Pseudocode:
    Read in the current order
    Print bill
    Create order invoice
    Store order invoices in a file 
    Generate sales revenue from file
*/

public class OrderInvoice {
    static ArrayList<String> allOrders = new ArrayList<>();

    public void initInvoices()throws IOException{
        readFromFile("orders.csv");
    }

    public void readFromFile(String path)throws IOException{
        String s;
        URL url = getClass().getResource("orders.csv");
        File file = new File(url.getPath());
        FileReader fr=new FileReader( file.getAbsolutePath());   
        BufferedReader br=new BufferedReader(fr);   
        while((s=br.readLine())!=null)
        {
            allOrders.add(s);
            
        }
    }

    public void writeInvoices(String Order)throws FileNotFoundException, IOException{
        URL url = getClass().getResource("orders.csv");
 		File file = new File(url.getPath());
 		FileWriter f=new FileWriter(file.getAbsolutePath(), true);   
 		BufferedWriter b=new BufferedWriter(f);   
 		PrintWriter pw = new PrintWriter(b);
        pw.print(Order);
        //System.out.println("write invoice: "+ Order);
        pw.println();
 		pw.flush();
 		pw.close();
 		b.close();
 		f.close();
        //System.out.println(Order);
    }

    public void generateInovice(String TID) throws IOException{
        // String s;
        // URL url = getClass().getResource("CurrentOrder.txt");
        // File file = new File(url.getPath());
        // FileReader fr=new FileReader( file.getAbsolutePath());   
        // BufferedReader br=new BufferedReader(fr);   
        // while((s=br.readLine())!=null)
        // {
        //     allOrders.add(s);
        // }
        // System.out.println("gen Invoice");
        // initInvoices();
        // writeInvoices();
        // printInvoice(allOrders.get(allOrders.size()-1));
        Order o = findOrder(TID);
        if(o!=null){
            OrderManager.currentOrders.remove(o);
            OrderManager om = new OrderManager();
            om.writeToFile();
            String s ="";
            s+=(o.getTOCreation()+","+o.getTOCompletion()+","+o.getOrderStaff()+",");
            for(int i =0;i<o.getOrderItems().size();i++){
                s+=(o.getOrderItems().get(i)+":"+String.valueOf(o.getItemQuantity().get(i))+":"+String.valueOf(o.getItemCost().get(i))+";");
            }
            s+=(","+String.valueOf(getTotal(o.getTotal(), o.getMember()))+","+String.valueOf(o.getMember())+","+String.valueOf(o.getTableID())+","+o.getOrderID());
            allOrders.add(s);
            writeInvoices(s);
            printInvoice(o);
            TableManager tm = new TableManager();
            tm.freeTable(TID);
        }
    }

    public void printInvoice(Order order){
        //String[] arr = order.split(",");
        // for(String x: arr)
        //     System.out.println(x);
    	String a = String.format("%-20s %5s %10s\n", "Item", "Qty", "Price");
    	String a1 = String.format("%-20s %5s %10s\n", "----", "---", "-----");
        System.out.printf("%-20s %5s\n","Order Created at:",	order.getTOCreation());
        System.out.printf("%-20s %5s\n","Order Completed at:",	order.getTOCompletion());
        System.out.printf("%-20s %5s\n","Server: ",		order.getOrderStaff());
        System.out.printf("%-20s %5s\n","Table ID:",order.getTableID());
        ArrayList<String> items = order.getOrderItems();
        ArrayList<Integer> itemquantity = order.getItemQuantity();
        ArrayList<Double> itemcosts = order.getItemCost(); 
        System.out.print(a);
        System.out.print(a1);
        for(int i =0; i<items.size(); i++){
            System.out.printf("%-20s %5s %10.2f\n",items.get(i),itemquantity.get(i),itemcosts.get(i));
        }
        System.out.print(a1);
        double tot = order.getTotal();
        System.out.printf("%-20s %5.2f\n","Raw total:",	tot);
        System.out.printf("%-20s %5.2f\n","GST (7%):",	(tot*0.07));
        System.out.printf("%-20s %5.2f\n","Service(10%):",	(tot*0.1));
        double cost =getTotal(tot, order.getMember());
        System.out.printf("%-20s %5.2f\n","Total:", cost);
        System.out.printf("%-20s %5s\n","Order ID:", order.getOrderID());
    }

    public Double getTotal(Double total, boolean mem){
        total = total*1.07*1.1;
        if(mem){
            System.out.printf("%-20s %5.2f\n","Member Discount(5%):",	(total*0.05));
            return total*0.95;
        }
        return total;
    }

    public Order findOrder(String TID){
        for(Order x: OrderManager.currentOrders){
            if(x.getTableID()==(Integer.parseInt(TID))){
                return x;
            }
        }
        System.out.println("Order Not Found!!");
        return null;
    }
}
