package restaurant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter; 
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class OrderManager{
  
   Staff staff=new Staff();
   Order neworder = new Order();
  
   public Staff getStaff() throws IOException
   {
     String empid;
     String s;
     String[] words=null;
     int flag=0;
     
     Scanner sc=new Scanner(System.in);
     System.out.println("Enter the employeeID of the staff making this order");
    empid=sc.nextLine();
     this.staff.setEmployeeID(empid);
     URL url = getClass().getResource("Staff.csv");
     File file = new File(url.getPath());
    FileReader fr=new FileReader( file.getAbsolutePath());   
     BufferedReader br=new BufferedReader(fr);   
     while((s=br.readLine())!=null)
     {
       words=s.split(",");
      
       if(words[0].trim().equals(this.staff.getEmployeeID().trim()))
       {
         this.staff.setName(words[1]);;
         this.staff.setGender(words[2]);
         this.staff.setJobTitle(words[3]);
         flag=1;
         break;
       }
        
     }
     if(flag==0)
     {
       char mem=' ';
       int flag1=0;
       System.out.println("Staff not found in the database!!!");
       do
       {
       System.out.println("Do you want to add this staff to the database?(y/n)");
       mem=sc.next().charAt(0);
       switch(mem)
       {
       case 'y':
       case 'Y':
         setStaff(empid);
         flag1=1;
         break;
       case 'n':
       case 'N':
         flag1=1;
         break;
       default:
         System.out.println("Please enter y/n");
         break;
       }
     }while(flag1==0);
    }
    fr.close();
    return this.staff;
   }
   
   public void setStaff(String empid) throws IOException
   {
     Scanner sc=new Scanner(System.in);
     System.out.println("Enter the name of the new staff");
     this.staff.setName(sc.nextLine());
     this.staff.setEmployeeID(empid);
     System.out.println("Enter the gender of the new staff");
     this.staff.setGender(sc.nextLine());;
     System.out.println("Enter the jobTitle of the new staff");
     this.staff.setJobTitle(sc.nextLine());
     
     URL url = getClass().getResource("Staff.csv");
     File file = new File(url.getPath());
     FileWriter fw = new FileWriter(file.getAbsolutePath(), true);
     BufferedWriter bw = new BufferedWriter(fw);
    PrintWriter pw = new PrintWriter(bw);
     pw.println(this.staff.getEmployeeID()+','+this.staff.getName()+','+this.staff.getGender()+','+this.staff.getJobTitle());
     System.out.println("Data Successfully appended into file");
    pw.flush();
     pw.close();
      bw.close();
      fw.close();
   }
    public void createOrder() throws IOException
   {
     Scanner sc=new Scanner(System.in);
     String startDate;
     
     int i=0,choice=-1;
     startDate=getTimeAndDate();
     neworder.setTOCreation(LocalDateTime.now());
     staff=getStaff();
     while(staff.getName()==null)
     {
       staff=getStaff();
     }
     neworder.setStaff(staff.getName());
     while(choice!=6) 
     {
        System.out.println("Please select what you would like to do");
           System.out.println("(1) Add an ala carte item to the order");
           System.out.println("(2) Add a promotion item to the order");
           System.out.println("(3) Remove an item from the order");
           System.out.println("(4) Cancel the order");
           System.out.println("(5) View the current Order");
           System.out.println("(6) Finish the current Order");
           choice=sc.nextInt();
           
           switch(choice)
           {
             case 1:
               i=addItem(i);
               break;
             case 2:
               i=addPromo(i);
               break;
             case 3:
               if(removeItem()==1)
               {
                 i=i-1;
               }
               break;
             case 4:
               cancelOrder();
               choice=6;
               break;
             case 5:
               viewOrder();
               break;
             case 6:
               finishOrder(i);
               break;
           }
     }
   }
   
   public int addItem(int i)throws IOException
   {
     String item,s,price=null;
     String[] words=null;
     int count=0;
     Scanner sc=new Scanner(System.in);

     System.out.println("Enter the Item you want to add to the order");
     item=sc.nextLine();
     item=item.toLowerCase();
     URL url = getClass().getResource("MenuItems.csv");
     File file = new File(url.getPath());
     FileReader fr=new FileReader(file);   
    BufferedReader br=new BufferedReader(fr);  
    while((s=br.readLine())!=null)
     {
         words=s.split(",");
        
         if(words[0].toLowerCase().trim().equals(item.trim()))
         {
           price=words[2];
           count=1;
           break;
           
         }
     }
    if(count==0)
      {
        System.out.println("Item ordered is not in the menu!!! Please try again");
      }
    if(count==1)
      {
        neworder.addItems(item);
        neworder.setItemQuantity(1);
        neworder.setItemCost(Double.parseDouble(price));
        i+=1;
        System.out.println("Item has been added to the order!!!");
      }
    fr.close();
    return i;
     
   }
   
   public int addPromo(int i)throws IOException
   {
     String item,s,price=null;
     String[] words=null;
     int count=0;
     Scanner sc=new Scanner(System.in);
     System.out.println("Enter the promotion meal you want to add to the order");
     item=sc.nextLine();
     item=item.toLowerCase();
     
     URL url = getClass().getResource("Promotions.csv");
     File file = new File(url.getPath());
     FileReader fr=new FileReader(file);   
    BufferedReader br=new BufferedReader(fr);  
    
    while((s=br.readLine())!=null)
     {
         words=s.split(",");
        System.out.println(words[0].toLowerCase());
         if(words[0].toLowerCase().trim().equals(item.trim()))
         {
           price=words[2];
           count=1;
           break;
           
         }
     }
    if(count==0)
    {
      System.out.println("Promotion meal ordered is not in the menu!!! Please try again");
    }
    if(count==1)
    {
      neworder.addItems(item);
      neworder.setItemCost(Double.parseDouble(price));
      neworder.setItemQuantity(1);
      i+=1;
      System.out.println("Promotion meal has been added to the order!!!");
    }
      fr.close();
     return i;
   }
   
   public int removeItem()
   {
     String item;
     int j=0,flag=0;
     Scanner sc=new Scanner(System.in);

     System.out.println("Enter the Item you want to remove from the order");
     item=sc.nextLine();
     
     for(String element: neworder.getOrderItems())
      {
        if(element.contains(item))
        {
          neworder.removeItem(item);
          flag=1;
          System.out.println("Item has been removed from the order!!!");
          return 1;
        }
        j+=1;
      }
      if(flag==0)
      {
        System.out.println("Item to remove is not in the current order");
      }
      return 0;
  }
   
   public void cancelOrder()
   {
     neworder.init();
     System.out.println("The order has been canceled!");
     
   }
   
   public void viewOrder()
   {
     String[] words=null;
     if(neworder.getOrderItems().size()==0)
     {
       System.out.println("The order is empty!!");
       return;
     }
     System.out.println("The current order is:");
     System.out.printf("%-20s%-20s\n","Item","Price(S$)");
     System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ ");
    int count =0;
     for(String x : neworder.getOrderItems()){
      System.out.printf("%-20s%-20s\n",x,neworder.getItemCost().get(count++));
     }
     System.out.printf("%-20s%-20s\n","Total:",neworder.getTotal());
   }
   
   public void finishOrder(int i) throws IOException
   {

     String endDate=getTimeAndDate();
     char mem=' ';
     int flag=0;
     neworder.setTOCompletion(LocalDateTime.now());
     Scanner sc=new Scanner(System.in);
     do
       {
       System.out.println("Are you a member?(y/n)");
       mem=sc.next().charAt(0);
       switch(mem)
       {
       case 'y':
       case 'Y':
         neworder.setMember(true);
         flag=1;
         break;
       case 'n':
       case 'N':
         neworder.setMember(false);
         flag=1;
         break;
       default:
         System.out.println("Please enter y/n");
         break;
       }
       }while(flag==0);
     if(neworder.getOrderItems().size()==0)
     {
       cancelOrder();
     }

     URL url = getClass().getResource("currentOrder.txt");
     File file = new File(url.getPath());
     FileWriter f=new FileWriter(file.getAbsolutePath());   
     BufferedWriter b=new BufferedWriter(f);   
     PrintWriter pw = new PrintWriter(b);
    pw.print(neworder.getTOCreation()+","+neworder.getTOCompletion()+","+neworder.getOrderStaff()+",");  
     for(int j=0;j<neworder.getOrderItems().size();j++)
     {
       pw.print(neworder.getOrderItems().get(j)+":"+neworder.getItemQuantity().get(j)+":"+neworder.getItemCost().get(j)+";");
     }
     pw.print(","+neworder.getTotal());
     pw.println();
     pw.flush();
     pw.close();
     b.close();
     f.close();
     System.out.println("Your order has been created!!");
     System.out.println("Here is a summary of your order");
     //viewOrder();
     OrderInvoice obj = new OrderInvoice();
     System.out.println("calling gen invoice");
     obj.generateInovice();
    
   }
   
   public String getTimeAndDate()
   {
     LocalDateTime createdDateTime = LocalDateTime.now();
     DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
     String formattedCreateDate = createdDateTime.format(myFormatObj);
     return formattedCreateDate;
   }
   
   public static void main(String[] args) throws IOException {
   
     OrderManager order=new OrderManager();
     order.createOrder();
   
   }
   
}