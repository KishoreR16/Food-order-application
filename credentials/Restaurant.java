//Restaurant side
package credentials;
import java.util.*;
import java.io.*;

public class Restaurant 
{
	static String name,password,type,status,owner;
	String s,hotelName,hotelPlace;
	static Scanner sc;
	static ArrayList<Hotels> globalHotels;
	ArrayList<Items> itemsList;
	public Restaurant() throws Exception
	{
		globalHotels=new ArrayList<>();
		itemsList=new ArrayList<>();
		
		sc=new Scanner(System.in);
		FileInputStream in=new FileInputStream("D:/Courses/zoho incubation/Java/Main Application/credentials/Hotel_Credentials.txt");				//deserialize
		ObjectInputStream ois=null;
		try
		{
			ois=new ObjectInputStream(in);  
		}
		catch(EOFException e)
		{
			;
		}
		Hotels s1;
		while(in.available()!=0)
		{
			s1=(Hotels)ois.readObject();
			globalHotels.add(s1);  
		}
		in.close();
	}
	public void setCredentials(String name,String password)
	{
		this.name=name;
		this.password=password;
	}
	
	void addStock() throws Exception
	{
		String name;
		int quantity,price;
		
		
		while(true)
		{
			int flag1=0;
			System.out.println("ENTER THE NAME OF THE DISH");
			name=sc.nextLine().toLowerCase();
			System.out.println("ENTER THE QUANTITY OF THE DISH");
			quantity=sc.nextInt();
			System.out.println("ENTER THE PRICE OF THE DISH");
			price=sc.nextInt();
			Items items=new Items(name,quantity,price);
			for (Items list2: itemsList)
			{
				if(list2.name.equals(name))
				{
					list2.quantity=list2.quantity+quantity;
					list2.price=price;
					System.out.println("DATA CHANGED SUCCESSFULLY FOR THIS DISH...\n");
					flag1=1;
				}
			}
			if(flag1==0)
			{
				itemsList.add(items);
				System.out.println("New dish "+name+" has been added Successfully in the restaurant "+hotelName+"\n");
			}
			System.out.printf("%s\n","1.ADD ANOTHER DISH");
			System.out.printf("%s\n","2.GO BACK");
			System.out.printf("%s","ENTER YOUR CHOICE:");
			int n2=sc.nextInt();
			sc.nextLine();
			System.out.println();
			if(n2==1)
			{
				continue;
			}
			if(n2==2)
			{
				break;
			}
		}
		FileOutputStream file1=new FileOutputStream("D:/Courses/zoho incubation/Java/Main Application/credentials/data/"+hotelPlace+"/"+hotelName+".txt");		//Serialization
		ObjectOutputStream out1=new ObjectOutputStream(file1);
		for (Items list: itemsList)
		{
			out1.writeObject(list);	
		}
		out1.close();
		file1.close();			
	}
	void printStock()
	{
		System.out.println("HOTEL NAME:"+hotelName+"    PLACE:"+hotelPlace+"    TYPE:"+type+"    STATUS:"+status+"\n");
		System.out.printf("%20s%15s%15s\n","DISH NAME","QUANTITY","PRICE"); 
		System.out.format("--------------------------------------------------------------------------------------------------------------------\n");  
		for(Items s1:itemsList)
		{
			System.out.format("%20s%15d%15d\n" ,s1.name,s1.quantity,s1.price);  
		}
	}
		
	
	public void access() throws Exception
	{
		int choice;
		do
		{
			System.out.println();
			System.out.printf("%s\n","1.ADD STOCKS IN THE HOTEL");
			System.out.printf("%s\n","2.PRINT THE STOCKS IN A HOTEL");
			System.out.printf("%s\n","3.LOG OUT");
			System.out.println();
			System.out.printf("%s","ENTER YOUR CHOICE:");
			choice=sc.nextInt();
			sc.nextLine();
			System.out.println();
			switch(choice)
			{
				case 1:System.out.format("%55s","ADD STOCKS IN THE HOTEL");
					   System.out.println();
					   System.out.format("%60s\n","===============================");
					   addStock();
					   break;
				case 2:System.out.format("%60s","PRINT THE STOCKS IN A HOTEL");
					   System.out.println();
					   System.out.format("%60s\n","===============================");
					   printStock();
					   break;
				
			    case 3:System.out.format("%56s","SUCCESSFULLY LOGGED OUT....");
					   System.out.println();
					   System.out.format("%60s\n","===============================");
					   itemsList.clear();
					   break;

						
			}
		}while(choice!=3);
	}
	public static void forgotPassword() throws Exception
	{
		int flag=0;
		System.out.println("ENTER THE USERNAME OF THE RESTAURANT:");
		name=sc.nextLine();
		
		for (Hotels credentials: globalHotels)
		{
		
			if(credentials.username.equals(name))
			{
				flag=1;
				Random random=new Random();
				int otp = random.nextInt(1000);   
				FileWriter w = new FileWriter("D:/Courses/zoho incubation/Java/Main Application/credentials/otp.txt");
				PrintWriter p=new PrintWriter(w);
				p.print(otp);
				p.close();
				w.close();
				int check;
				System.out.println("enter otp");
				check=sc.nextInt();
				sc.nextLine();
				if(check==otp)
				{
					while(true)
					{
						String regex = "^(?=.*[0-9])"+ "(?=.*[a-z])(?=.*[A-Z])"+ "(?=.*[@#$%^&+=])"+ "(?=\\S+$).{8,16}$";
						System.out.println("enter new password");
						password=sc.nextLine();
						if(password.matches(regex))
						{
							break;
						}
						else
						{
							System.out.println("Password must contain One Uppercase,One Lowercase,One number and a special character and must of length 8 to 16...try again!!!");
						}
					}
					credentials.password=password;
					System.out.println("THE PASSWORD HAS BEEN SUCCESSFULLY CHANGED");
					System.out.println();
					FileOutputStream file2=new FileOutputStream("D:/Courses/zoho incubation/Java/Main Application/credentials/Hotel_Credentials.txt");		//Serialization
					ObjectOutputStream out2=new ObjectOutputStream(file2);
					for (int i=0;i< globalHotels.size();i++)
					{
						out2.writeObject(globalHotels.get(i));	
					}
					out2.close();
					file2.close();
					FileOutputStream file3=new FileOutputStream("D:/Courses/zoho incubation/Java/Main Application/credentials/data/"+credentials.place+"/hotels.txt");		//Serialization
					ObjectOutputStream out3=new ObjectOutputStream(file3);
					for (int i=0;i< globalHotels.size();i++)
					{
						out3.writeObject(globalHotels.get(i));	
					}
					out3.close();
					file3.close();
					break;
							
				}
				else
				{
					System.out.println("THE OTP IS NO LONGER VALID...TRY AGAIN!!!");
					continue;
				}

			}
		}
			if(flag==0)
			{
				System.out.println("THERE IS NO USERNAME FOUND FOR THIS RESTAURANT");
			
			}
		
	}
	public boolean checkDetails() throws Exception
	{
		for(Hotels list2:globalHotels)  
		{  
			
			if(list2.username.equals(name))
			{
				if(list2.password.equals(password))
				{
					hotelPlace=list2.place;
					hotelName=list2.hotelName;
					type=list2.type;
					status=list2.status;
					owner=list2.hotelOwner;
					FileInputStream in1=new FileInputStream("D:/Courses/zoho incubation/Java/Main Application/credentials/data/"+hotelPlace+"/"+hotelName+".txt");				//deserialize
					ObjectInputStream ois1=null;
					try
					{
						ois1=new ObjectInputStream(in1);  
					}
					catch(EOFException e)
					{
						;
					}
					Items i1;
					while(in1.available()!=0)
					{
						i1=(Items)ois1.readObject();
						itemsList.add(i1);  
					}
					in1.close();
					return true;
				}
				else
				{
					System.out.printf("%s\n","PASSWORD IS WRONG!!!");
					return false;
				}
			}
		}
		System.out.printf("%s\n","RESTAURANT USER NAME DOESN'T EXISTS...SORRY ):"); 
		return false;
	}
}