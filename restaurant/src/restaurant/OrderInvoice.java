package restaurant;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
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

    public void writeInvoices()throws FileNotFoundException, IOException{
        URL url = getClass().getResource("orders.csv");
        File file = new File(url.getPath());
        FileWriter fw = new FileWriter(file);
        StringBuilder sb = new StringBuilder();


        for(String num: allOrders){
            sb.append(num);
            sb.append("\n");
        }
        System.out.println("write invoice");
        fw.write(sb.toString());
        fw.close();
    }

    public void generateInovice() throws IOException{
        String s;
        URL url = getClass().getResource("CurrentOrder.txt");
        File file = new File(url.getPath());
        FileReader fr=new FileReader( file.getAbsolutePath());   
        BufferedReader br=new BufferedReader(fr);   
        while((s=br.readLine())!=null)
        {
            allOrders.add(s);
        }
        System.out.println("gen Invoice");
        initInvoices();
        writeInvoices();
        printInvoice(allOrders.get(0));
    }

    public void printInvoice(String order){
        String[] arr = order.split(",");
        System.out.println("Order Created at \t" + arr[0]);
        System.out.println("Order Completed at \t" + arr[1]);
        System.out.println("Server: \t" + arr[2]);
        String[] items = arr[3].split(";");
        System.out.println("Item: \t Quantity: \t Cost:");
        for(String x: items){
            String [] details = x.split(":");
            System.out.println(details[0]+"\t"+details[1]+"\t"+details[2]);
        }
        System.out.println("GST: \t 7%");
        System.out.println("Service: \t 10%");
        double cost =getTotal(arr[4], arr[5]);
        System.out.println("Total: \t"+ cost);
    }

    public Double getTotal(String raw, String mem){
        Double total = Double.parseDouble(raw);
        total = total*1.07*1.1;
        boolean member = Boolean.parseBoolean(mem);
        if(member){
            System.out.println("Member Discount \t 5%" );
            return total*0.95;
        }
        return total;
    }
}