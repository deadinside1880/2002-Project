package orderAndStaff;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedWriter; 
import java.io.FileWriter; 
import java.io.PrintWriter;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order{
	
	Staff staff=new Staff();
 	private boolean membership=false;
 	ArrayList<String> neworder = new ArrayList<String>();
	
 	public Staff getStaff() throws IOException
 	{
 		String empid;
 		String s;
 		String[] words=null;
 		
 		Scanner sc=new Scanner(System.in);
 		System.out.println("Enter the employeeID of the staff making this order");
		empid=sc.nextLine();
 		this.staff.setEmployeeID(empid);

		FileReader fr=new FileReader( "C:\\Users\\sasha\\eclipse-workspace\\OOPProject\\src\\orderAndStaff\\Staff.txt");   
 		BufferedReader br=new BufferedReader(fr);   
 		StringBuffer sb=new StringBuffer();   
 		while((s=br.readLine())!=null)
 		{
 			words=s.split(",");
			
 			if(words[0].equals(this.staff.getEmployeeID()))
 			{
 				this.staff.setName(words[1]);;
 				this.staff.setGender(words[2]);
 				this.staff.setJobTitle(words[3]);
 			}
				
 		}
		fr.close();
		return this.staff;
 	}
 	
 	public void setStaff() throws IOException
 	{
 		Scanner sc=new Scanner(System.in);
 		System.out.println("Enter the name of the new staff");
 		staff.setName(sc.nextLine());
 		System.out.println("Enter the EmployeeID of the new staff");
 		staff.setEmployeeID(sc.nextLine());
 		System.out.println("Enter the gender of the new staff");
 		staff.setGender(sc.nextLine());;
 		System.out.println("Enter the jobTitle of the new staff");
 		staff.setJobTitle(sc.nextLine());
	
 		FileWriter fw = new FileWriter("C:\\\\Users\\\\sasha\\\\eclipse-workspace\\\\OOPProject\\\\src\\\\orderAndStaff\\\\Staff.txt", true);
 		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
 		pw.println(this.staff.getEmployeeID()+','+this.staff.getName()+','+this.staff.getGender()+','+this.staff.getJobTitle());
 		System.out.println("Data Successfully appended into file");
		pw.flush();
 		pw.close();
	    bw.close();
	    fw.close();
 		
 	}


 	
 	public boolean getMembership()
 	{
 		return membership;
 	}
 	
 	public void setMembership(boolean member)
 	{
 		if(member == true)
 		{
 			this.membership=true;
 		}
 		else
 		{
 			
 			this.membership=false;
 		}
 	}
 	
 	public void createOrder() throws IOException
 	{
 		LocalDateTime createdDateTime = LocalDateTime.now();
 		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
 		String formattedCreateDate = createdDateTime.format(myFormatObj);
 		Scanner sc=new Scanner(System.in);
 		String item;
 		String price=null;
 		int i=0;
 		neworder.add(i,"Order Created At"+formattedCreateDate);

 		i+=1;
 	
 		FileReader fr=new FileReader( "C:\\Users\\sasha\\eclipse-workspace\\OOPProject\\src\\orderAndStaff\\Menu.txt");   
 		BufferedReader br=new BufferedReader(fr);   
 		StringBuffer sb=new StringBuffer();   
 		String s;
 		String[] words=null;
 		do
 		{
 			System.out.println("Enter the item you would like to order(enter f to finish order)");
 			item=sc.nextLine();
 			while((s=br.readLine())!=null)
 	 		{
 	 			words=s.split(",");
 				
 	 			if(words[0].equals(item))
 	 			{
 	 				price=words[1];
 	 				
 	 			}
 	 		}
 			if(price==null)
 			{
 				System.out.println("Item ordered is not in the menu!!! Please try again");
 				continue;
 			}
 			if(!item.equals("f"))
 			{
 				neworder.add(i,item+','+price);
 				i+=1;
 			}
 		}while(!item.equals("f"));
 		fr.close();
 		LocalDateTime finishedDateTime = LocalDateTime.now();
 		DateTimeFormatter myFormatOb = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
 		String formattedFinishedDate =finishedDateTime.format(myFormatOb);
 		neworder.add(i,"Order Finished At"+formattedFinishedDate);
 		
 		FileWriter f=new FileWriter( "C:\\Users\\sasha\\eclipse-workspace\\OOPProject\\src\\orderAndStaff\\currentOrder.txt");   
 		BufferedWriter b=new BufferedWriter(f);   
 		PrintWriter pw = new PrintWriter(b);  
 		for(int j=0;j<neworder.size();j++)
 		{
 			pw.println(neworder.get(j));
 		}
 		pw.flush();
 		pw.close();
 		b.close();
 		f.close();
 		}
 	public static void main(String[] args) throws IOException {
 	
 		Order order=new Order();
 		order.createOrder();
 	
 	}
 	
}
	
