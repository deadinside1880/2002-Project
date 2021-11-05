package restaurant;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SalesRevenueReport {
    /*Period of sales
    Individual sale items - either ala carte or promotional items
    Total Revenue*/

    public static ArrayList<String> period;

    public void generateReport()throws IOException{
        //OrderInvoice invoice = new OrderInvoice();
        Date today = new GregorianCalendar().getTime();
        double revenue = 0;
        
        FileReader fr = new FileReader("/Users/smolquail/Downloads/Y2S1/CZ2002/Project/allOrderInvoices.txt");   
        BufferedReader br = new BufferedReader(fr);
        String s;
        String[] words = null;
        int friesQuantity = 0;
        int chickenQuantity = 0;
        int lemonadeQuantity = 0;

        
        while ((s = br.readLine()) != null){
            System.out.println(br.readLine());
            words = s.split("\n");

            for (int i=1; i<words.length; i++){
                if (words[0] == "Fries"){
                    friesQuantity++;
                }
                if (words[0] == "Chicken Steak"){
                    chickenQuantity++;
                }
                if (words[0] == "Lemonade"){
                    lemonadeQuantity++;
                }
            }
        }

        /*period = invoice.getOrderInvoices();
        int last = period.size();*/

        System.out.println("Sales Revenue Report created at " + today);
        //System.out.println("Report of Sales from " + period.get(0) + " to " + period.get(last-1));
        System.out.println("------------------------------------------------");
        System.out.println("Items \t\t Quantity \t\t Prices");
        System.out.println("Fries \t\t\t" + friesQuantity + "");
        System.out.println("Chicken Steak \t\t" + chickenQuantity + "");
        System.out.println("Lemonade \t\t" + lemonadeQuantity + "");
        System.out.println("------------------------------------------------");
        System.out.println("Total Revenue Generated: " + revenue);
        
        fr.close();
    }    

    public static void main(String[] args) throws IOException{
        SalesRevenueReport report = new SalesRevenueReport();
        report.generateReport();
    }
}
