package restaurant;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/*
Pseudocode:
    Read in the current order
    Print bill
    Create order invoice
    Store order invoices in a file 
    Generate sales revenue from file
*/

public class OrderInvoice {
    final static double GST = 0.07;
    final static double SERVICE_CHARGE = 0.10;
    final static double MEMBERSHIP_DISCOUNT = 0.05;

    static ArrayList <String> orderInvoices = new ArrayList<>(); //to store all the invoices
    static ArrayList <String> invoiceDates = new ArrayList<>();

    public void createOrderInvoice(boolean isMember) throws IOException
    {
        LocalDateTime createdDateTime = LocalDateTime.now();
 		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
 		String formattedCreateDate = createdDateTime.format(myFormatObj);
        int i = 0;
        float total = 0;
        orderInvoices.add("Invoice created at " + formattedCreateDate);

        
        FileReader fr = new FileReader("/Users/smolquail/Downloads/Y2S1/CZ2002/Project/currentOrder.txt");   
 		BufferedReader br = new BufferedReader(fr);
        String s;
        String[] words = null;

        //writing for sales revenue report
        FileWriter writer = new FileWriter("allOrderInvoices.txt");
        BufferedWriter bw = new BufferedWriter(writer);
        
        ArrayList <String[]> orders = new ArrayList<>();
        System.out.println("Order Invoice created at " + formattedCreateDate);
        System.out.println("------------------------------------------------");
        System.out.println("Items \t\t\tPrices");
        System.out.println("________________________________________________");
        
        while ((s = br.readLine()) != null) {
            words = s.split(",");            
            orders.add(words);

            for (i=1; i<words.length; i++){
                System.out.print(words[0] + "\t\t" + words[i]);
                orderInvoices.add(words[0]);
                orderInvoices.add(words[1]); 
                bw.write(words[0]);
                //bw.write(words[1]);
                bw.newLine();
                String newStr = words[i].replace("$", "");
                total += Float.parseFloat(newStr);
            }
            System.out.println();
        }

        System.out.println("------------------------------------------------");
        System.out.println("GST: \t\t\t$" + GST*total);
        System.out.println("Service Charge: \t$" + SERVICE_CHARGE*total);
        if (isMember == true){
            System.out.println("Membership Discount: \t$" + MEMBERSHIP_DISCOUNT*total);
        }
        System.out.println("SubTotal: \t\t$" + total);
        if (isMember == true){
            System.out.println("Total: \t\t\t$" + (total+GST*total+SERVICE_CHARGE*total-MEMBERSHIP_DISCOUNT*total));
            bw.write(String.valueOf((total+GST*total+SERVICE_CHARGE*total-MEMBERSHIP_DISCOUNT*total)));
            orderInvoices.add(String.valueOf((total+GST*total+SERVICE_CHARGE*total-MEMBERSHIP_DISCOUNT*total)));
            bw.newLine();
            System.out.println();
        }
        else{
            System.out.println("Total: \t\t\t$" + (total+GST*total+SERVICE_CHARGE*total));
            bw.write(String.valueOf((total+GST*total+SERVICE_CHARGE*total)));
            orderInvoices.add(String.valueOf((total+GST*total+SERVICE_CHARGE*total)));
            bw.newLine();
            System.out.println();
        }
        
        invoiceDates.add(orderInvoices.get(0));
        
        fr.close();
        bw.flush();
        bw.close();


    }

    public void printAllInvoices(ArrayList <String> orderInvoices){
        System.out.println("Printing all invoices...");
        System.out.println();
        int set = 1;
        for (int j=0; j<orderInvoices.size(); j++){
            System.out.print(orderInvoices.get(j) + " ");
            set++;
            if (set % 2 == 0){
                System.out.println();
            }
        }
    }

    public ArrayList<String> getOrderInvoices(){
        return invoiceDates;
    }

    public static void main(String[] args) throws IOException{
        OrderInvoice invoice = new OrderInvoice();
        Order order = new Order();
        invoice.createOrderInvoice(order.getMembership());
        invoice.printAllInvoices(orderInvoices);
        
    }
}
