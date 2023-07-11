///USER PAGE
package credentials;
import java.util.*;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

class End 
{
	String name,hotel,place;
	int quantity,price;
	End(String name,int quantity,int price,String place,String hotel)
	{
		this.name=name;
		this.quantity=quantity;
		this.price=price;
		this.place=place;
		this.hotel=hotel;
		
	}
} 
public class User 
{	
	
	ArrayList<String> list1;
	ArrayList<String> list2;
	LinkedList<Hotels> listOfHotels;
	LinkedList<Items> listOfItems;
	LinkedList<End> purchase;
	LinkedList<Items> adding1;
	LinkedList<End> adding2;
	String s; 
	int n;
	LocalDateTime now;
	DateTimeFormatter dtf;
	static int first,flag=0;
	String userName,userPassword,phoneNo,name,address;
	Scanner sc;
	
	public User() throws Exception
	{
		list1=new ArrayList<String>();
		list2=new ArrayList<String>();
		listOfHotels=new LinkedList<>();
		listOfItems=new LinkedList<>();
		purchase=new LinkedList<>();
		adding1=new LinkedList<>();
		adding2=new LinkedList<>();
		sc=new Scanner(System.in);	
		
		FileReader fr=new FileReader("D:/Courses/zoho incubation/Java/Main Application/credentials/User_Credentials.txt");    
		BufferedReader br=new BufferedReader(fr);  
		int flag=0;
		while((s=br.readLine())!=null)
		{
			if(flag==0)
			{
				list1.add(s);
				flag=1;
			}
			else
			{
				flag=0;
				list2.add(s);
			}
		}	
		br.close(); 
		
		fr.close();   
	}
	public void setCredentials(String userName,String userPassword)
	{
		this.userName=userName;
		this.userPassword=userPassword;
	}
	public void addToCart() throws Exception
	{
		String chosen,chosen1;
		int select,select1;
		list1.clear();
		FileReader fr=new FileReader("D:/Courses/zoho incubation/Java/Main Application/credentials/data/places.txt");    
		BufferedReader br=new BufferedReader(fr);  
		while((s=br.readLine())!=null)
		{
			list1.add(s);
		}
		br.close();
		fr.close();
		
		while(true)
		{
			
			int n2;
			System.out.println("THE PLACES WHERE OUR SERVICES AVAILABLE ARE");
			System.out.println("1.CONTINUE");
			System.out.println("2.GO BACK");
			n2=sc.nextInt();
			sc.nextLine();
			if(n2==2)
			{
				break;
			}
			System.out.println("THE PLACES WHERE OUR SERVICES AVAILABLE ARE");
			for(int i=0;i<list1.size();i++)
			{
				System.out.println((i+1)+"."+list1.get(i));
			}
			System.out.println();
			System.out.println("ENTER THE PLACE NUMBER YOU LIKE TO SELECT");
			select=sc.nextInt();
			if(flag==0)
			{
				first=select;
				flag=1;
			}
			if(first==select)
			{
				;
			}
			else
			{ 
				for(End adding:purchase)
				{
					FileInputStream in2=new FileInputStream("D:/Courses/zoho incubation/Java/Main Application/credentials/data/"+adding.place+"/"+adding.hotel+".txt");				//deserialize
					ObjectInputStream ois2=null;
					try{
					ois2=new ObjectInputStream(in2);  
					}
					catch(EOFException e)
					{
						;
					}	 
					Items s3;
					while(in2.available()!=0)
					{
						s3=(Items)ois2.readObject();
						adding1.add(s3);  
					}
					in2.close();
					
					for(Items l1:adding1)
					{
	
						
						if(l1.name.equals(adding.name))
						{
							l1.quantity=l1.quantity+adding.quantity;
						}
					}
					FileOutputStream file2=new FileOutputStream("D:/Courses/zoho incubation/Java/Main Application/credentials/data/"+adding.place+"/"+adding.hotel+".txt");		//Serialization
					ObjectOutputStream out2=new ObjectOutputStream(file2);
					
					for (Items list3: adding1)
					{
						out2.writeObject(list3);	
					}
					out2.close();
					file2.close();
					adding1.clear();
					listOfItems.clear();
				}
				first=select;
			
				purchase.clear();
				
			}
				
			sc.nextLine();
			if(select>=list1.size()+1)
			{
				System.out.println("SELECT A GIVEN PLACE NUMBER:");
				int n1;
				System.out.println("1.CONTINUE");
				System.out.println("2.GO BACK");
				n1=sc.nextInt();
				sc.nextLine();
				if(n1==2)
				{
					break;
				}
				continue;
			}
			chosen=list1.get(select-1);
			FileInputStream in=new FileInputStream("D:/Courses/zoho incubation/Java/Main Application/credentials/data/"+chosen+"/hotels.txt");				//deserialize
			ObjectInputStream ois=null;
			
			try{
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
				listOfHotels.add(s1);  
			}
			in.close();
			
			while(true)
			{
				System.out.println("THE LIST OF HOTELS AVAILABLE IN THIS PLACE ARE:");
				
				System.out.println("1.CONTINUE");
				System.out.println("2.GO BACK");
				n2=sc.nextInt();
				sc.nextLine();
				if(n2==2)
				{
					break;
				}
				System.out.println("THE LIST OF HOTELS AVAILABLE IN THIS PLACE ARE:");
				System.out.printf("%10s%15s%16s%10s\n","HOTEL ID","HOTEL NAME","VEG/NON VEG","STATUS"); 
				System.out.format("--------------------------------------------------------------------------------------------------------------------\n");  
				int count=1;
				for (Hotels list: listOfHotels)
				{
					System.out.format("%10d%15s%16s%10s\n" ,count++,list.hotelName,list.type,list.status);  
				}
				System.out.println();
				System.out.println("ENTER THE HOTEL NUMBER YOU LIKE TO SELECT");
				select1=sc.nextInt();
				sc.nextLine();
				if(select1>=listOfHotels.size()+1)
				{
					System.out.println("SELECT ONE OF THE GIVEN HOTEL NUMBERS:");
					int n1;
					System.out.println("1.CONTINUE");
					System.out.println("2.GO BACK");
					n1=sc.nextInt();
					sc.nextLine();
					if(n1==2)
					{
						break;
					}
					continue;
				}
				chosen1=listOfHotels.get(select1-1).hotelName;
				
				
				while(true){
					FileInputStream in1=new FileInputStream("D:/Courses/zoho incubation/Java/Main Application/credentials/data/"+chosen+"/"+chosen1+".txt");				//deserialize
				ObjectInputStream ois1=null;
				try{
				ois=new ObjectInputStream(in1);  
				}
				catch(EOFException e)
				{
					;
				} 
				Items s2;
				listOfItems.clear();
				while(in1.available()!=0)
				{
					s2=(Items)ois.readObject();
					listOfItems.add(s2);  
				}
				in1.close();
					int count2=1;
				System.out.printf("%20s%20s%20s%20s","DISH ID","DISH NAME","QUANTITY","PRICE\n"); 
				System.out.format("--------------------------------------------------------------------------------------------------------------------\n");
				for (Items list1: listOfItems)
				{
					System.out.format("%20d%20s%20d%20d\n" ,count2++,list1.name,list1.quantity,list1.price);  
				}
				System.out.println("1.CONTINUE");
				System.out.println("2.GO BACK");
				n=sc.nextInt();
				sc.nextLine();
				if(n==2)
				{
					break;
				}
				System.out.println("ENTER THE DISH NAME YOU LIKE TO ADD IN THE CART:");
				String cart;
				cart=sc.nextLine().toLowerCase();
				for(Items l:listOfItems)
				{
					if(l.name.equals(cart))
					{
						int getter;
						
						while(true)
						{
							System.out.println("ENTER THE QUANTITY:");
							getter=sc.nextInt();
							sc.nextLine();
							if(l.quantity<getter)
							{
								System.out.println("YOUR REQUEST IS HIGHER THAN THE STOCK....THE AVAILABLE QUANTITY IS "+l.quantity);
							}
							else
							{
								l.quantity=l.quantity-getter;
								End end=new End(l.name,getter,l.price*getter,chosen,chosen1);
								
								purchase.add(end);
								FileOutputStream file=new FileOutputStream("D:/Courses/zoho incubation/Java/Main Application/credentials/data/"+chosen+"/"+chosen1+".txt");		//Serialization
								ObjectOutputStream out=new ObjectOutputStream(file);
								System.out.println("listof items:");
								for (Items list3: listOfItems)
								{
									out.writeObject(list3);	
								}
								out.close();
								file.close();
								
								//purchase.get((purchase.size()-1)).quantity=getter;                         //getting recently added index reference and changing the value
								break;
							}
						}
						break;
					}
				}
				listOfItems.clear();
				
				}				
			}
			listOfHotels.clear();	
			listOfItems.clear();
		}
		
	}
	public void buy() throws Exception
	{
		
		int total=0;
		for (End list1: purchase)
		{
			total=total+list1.price;			
		}
		if(total>0){
		System.out.println("ENTER YOUR NAME");
		name=sc.nextLine();
		System.out.println("ENTER YOUR PHONE NUMBER:");
		phoneNo=sc.nextLine();
		System.out.println("ENTER YOUR ADDRESS");
		address=sc.nextLine();
		System.out.println("ORDERED SUCCESSFULLY..");
		System.out.println("ORDER DETAILS");
		dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		now = LocalDateTime.now();
		System.out.println("");
		System.out.println("NAME:"+name);	
		System.out.println("MOBILE NUMBER:"+phoneNo);
		System.out.println("ADDRESS:"+address);
		System.out.println("DATE & TIME:"+dtf.format(now));
		System.out.println("");
		System.out.printf("%20s%20s%20s%20s%20s\n","NAME","QUANTITY","PRICE","HOTEL","PLACE"); 
		System.out.format("--------------------------------------------------------------------------------------------------------------------\n");
		for (End list1: purchase)
		{
			System.out.format("%20s%20d%20d%20s%20s\n" ,list1.name,list1.quantity,list1.price,list1.hotel,list1.place); 
					
		}
		System.out.format("%60s%10d\n","TOTAL",total);
		//
		Thread thread = new Thread( new Runnable()
		{
			public void run()
			{
				try{
				int total=0;
				FileOutputStream fout=new FileOutputStream("D:/Courses/zoho incubation/Java/Main Application/credentials/purchase.txt",true);    
				PrintStream out=new PrintStream(fout); 
				out.println("NAME:"+name);
				out.println("MOBILE NUMBER:"+phoneNo);
				out.println("ADDRESS:"+address);
				out.println("DATE & TIME:"+dtf.format(now));
				out.println("");
				out.printf("%20s%20s%20s%20s%20s\n","NAME","QUANTITY","PRICE","HOTEL","PLACE"); 
				out.println("--------------------------------------------------------------------------------------------------------------------\n");
				for (End list1: purchase)
				{
					out.format("%20s%20d%20d%20s%20s\n" ,list1.name,list1.quantity,list1.price,list1.hotel,list1.place); 
					total=total+list1.price;			
				}
				out.format("%60s%10d\n","TOTAL",total);
				out.println();
				out.close();
				fout.close();
				}
				catch(Exception e)
				{
					;
				}
			}
		});
		thread.start();
		}
		else
		{
			System.out.println("THERE IS NO ITEMS IN THE CART");
		}
        
	}
	void change() 
	{
		try{
		for(End adding:adding2)
		{
			FileInputStream in2=new FileInputStream("D:/Courses/zoho incubation/Java/Main Application/credentials/data/"+adding.place+"/"+adding.hotel+".txt");				//deserialize
			ObjectInputStream ois2=null;
			try
			{
				ois2=new ObjectInputStream(in2);  
			}
			catch(EOFException e)
			{
				;
			}	 
			Items s3;
			while(in2.available()!=0)
			{
				s3=(Items)ois2.readObject();
				adding1.add(s3);  
			}
			in2.close();
			for(Items l1:adding1)
			{
				if(l1.name.equals(adding.name))
				{
					l1.quantity=l1.quantity+adding.quantity;
				}
			}
			FileOutputStream file2=new FileOutputStream("D:/Courses/zoho incubation/Java/Main Application/credentials/data/"+adding.place+"/"+adding.hotel+".txt");		//Serialization
			ObjectOutputStream out2=new ObjectOutputStream(file2);
			for (Items list3: adding1)
			{
				out2.writeObject(list3);	
			}
			out2.close();
			file2.close();
			adding1.clear();
			listOfItems.clear();
		}
		}
		catch(Exception e)
		{
			;
		}
	}
	public void cancel()
	{
		int total=0;
		System.out.printf("%20s%20s%20s%20s%20s\n","DISH NAME","QUANTITY","PRICE","HOTEL NAME","HOTEL PLACE"); 
		System.out.format("--------------------------------------------------------------------------------------------------------------------\n");
		
		for (End list1: purchase)
		{
			System.out.format("%20s%20d%20d%20s%20s\n" ,list1.name,list1.quantity,list1.price,list1.hotel,list1.place); 
			total=total+list1.price;			
		}
		System.out.format("%60s%10d\n","TOTAL",total);
		String name;
		System.out.println("ENTER THE NAME OF THE DISH YOU WOULD LIKE TO CANCEL:");
		name=sc.nextLine().toLowerCase();
		int index=0;
		int removing=-1;
		for(End list1:purchase)
		{
			if(list1.name.equals(name))
			{
				adding2.clear();
				adding2.add(list1);
				change();
				removing=index;
				break;
			}
			index++;
		}
		if(removing!=-1)
		{
			purchase.remove(index);
		}
	}
	public void reduce()
	{
		int total=0;
		System.out.println("THE AVAILABLE ITEMS ARE:");
		System.out.printf("%20s%20s%20s%20s%20s\n","DISH NAME","QUANTITY","PRICE","HOTEL NAME","HOTEL PLACE"); 
		System.out.format("--------------------------------------------------------------------------------------------------------------------\n");
		
		for (End list1: purchase)
		{
			System.out.format("%20s%20d%20d%20s%20s\n" ,list1.name,list1.quantity,list1.price,list1.hotel,list1.place); 
			total=total+list1.price;			
		}
		System.out.format("%60s%10d\n","TOTAL",total);
	
		String name;
		System.out.println("ENTER THE NAME OF THE DISH YOU WOULD LIKE TO REDUCE:");
		name=sc.nextLine().toLowerCase();
		int removing=-1;
		int index=0;
		int flag=0;
		for(End list1:purchase)
		{
			if(list1.name.equals(name))
			{
				removing=index;
				System.out.println("ENTER THE QUANTITY LESS THAN OR EQUAL TO "+list1.quantity);
				int get1,price1;
				get1=sc.nextInt();
				sc.nextLine();
				if(get1==list1.quantity)
				{
					adding2.clear();
					adding2.add(list1);
					change();
					removing=index;
					flag=1;
					break;
				}
				if(get1<list1.quantity)
				{
					price1=get1*(list1.price/list1.quantity);
					list1.quantity=list1.quantity-get1;
					list1.price=list1.price-price1;
					adding2.clear();
					adding2.add(list1);
					change();
					
					break;
				}
				
			}
			index++;
		}
		if(removing!=-1 && flag==1)
		{
			purchase.remove(index);
		}
	}
	public void displayCart() throws Exception
	{
		int total=0;
		System.out.printf("%20s%20s%20s%20s%20s\n","DISH NAME","QUANTITY","PRICE","HOTEL NAME","HOTEL PLACE"); 
		System.out.format("--------------------------------------------------------------------------------------------------------------------\n");
		
		for (End list1: purchase)
		{
			System.out.format("%20s%20d%20d%20s%20s\n" ,list1.name,list1.quantity,list1.price,list1.hotel,list1.place); 
			total=total+list1.price;			
		}
		System.out.format("%60s%10d\n","TOTAL",total);
		int end;
		do
		{
			System.out.println("1.BUY");
			System.out.println("2.CLOSE");
			System.out.println("3.CANCEL THE ITEMS");
			System.out.println("4.REDUCE THE QUANTITY");
			System.out.println("ENTER YOUR CHOICE:");
			end=sc.nextInt();
			sc.nextLine();
			switch(end)
			{
				case 1:System.out.printf("%50s\n","BUY");
					   buy();
					   break;
				case 2:
					   break;
				case 3:System.out.printf("%50s\n","CANCEL THE ITEMS");
					   cancel();
					   break;
				case 4:System.out.printf("%50s\n","REDUCE THE QUANTITY");
					   reduce();
					   break;
					   
			}			
		}while((end!=2)&&(end!=1));
		
		
	}
	public void access() throws Exception
	{
		int choice;
		do
		{
			System.out.format("%50s\n","1.ADD TO CART");
			System.out.format("%50s\n","2.DISPLAY CART");
			System.out.format("%50s\n","3.LOG OUT");
			
			System.out.format("%60s\n","===============================");
			System.out.println("\nENTER YOUR CHOICE:");
			choice=sc.nextInt();
			sc.nextLine();
			switch(choice)
			{
				case 1:System.out.format("%50s","ADD TO CART");
					   System.out.println();
					   addToCart();
					   break;
				case 2:System.out.format("%50s","DISPLAY CART");
					   System.out.println();
					   displayCart();
					   break;
			    case 3:System.out.format("%50s","SUCCESSFULLY LOGGED OUT");
					   System.out.println();
					   
					   break;

						
			}
		}while(choice!=3);
	}
	public void createAccount()
	{
			while(true)
			{
				System.out.println("ENTER USER NAME:");
				userName=sc.nextLine();
				if(list1.contains(userName))
				{
					System.out.println("USER NAME ALREADY EXISTS...TRY ANOTHER USER NAME");
					System.out.println("1.CREATE ACCOUNT");
					System.out.println("2.GO BACK");
					System.out.println("ENTER YOUR CHOICE:");
					n=sc.nextInt();
					sc.nextLine();
					if(n==2)
					{
						break;
					}
				}
				else
				{
					System.out.println("ENTER PASSWORD:");
					userPassword=sc.nextLine();
					String regex = "^(?=.*[0-9])"+ "(?=.*[a-z])(?=.*[A-Z])"+ "(?=.*[@#$%^&+=])"+ "(?=\\S+$).{8,16}$";
					if(userPassword.matches(regex))
					{
						System.out.println("YOUR ACCOUNT HAS BEEN CREATED SUCCESSFULLY");
						list1.add(userName);
						list2.add(userPassword);
						try{
						FileWriter fWriter = new FileWriter("D:/Courses/zoho incubation/Java/Main Application/credentials/User_Credentials.txt");
					    for(int j=0;j<list1.size();j++)
						{
							s=list1.get(j);
							fWriter.write(s);
							fWriter.write("\n");
							s=list2.get(j);
							fWriter.write(s);
							fWriter.write("\n");	
						}
						fWriter.close();
						}
						catch(IOException e)
						{
							System.out.println(e);
						}
						break;
					}
					else
					{
						System.out.println("PASSWORD MUST CONTAIN A UPPERCAE,A LOWERCASE,A NUMBER,A SPECIAL CHARACTER AND MUST OF LENGTH 8 TO 16...TRY AGAIN!!!!");
					}
					System.out.println("1.GO BACK");
					System.out.println("2.CONTINUE");
					System.out.println("ENTER YOUR CHOICE:");
					n=sc.nextInt();
					sc.nextLine();
					if(n==1)
					{
						break;
					}
				}
			}
       
	}
	public boolean checkDetails() throws Exception
	{  
		char choice;
		for(int i=0;i<list1.size();i++)
		{
			if((list1.get(i).compareTo(userName))==0)
			{
				if((list2.get(i).compareTo(userPassword))==0)
				{
					return true;
				}
				else
				{
					System.out.println("PASSWORD IS WRONG");
					System.out.println("1.FORGOT PASSWORD");
					System.out.println("2.GO BACK");
					System.out.println("ENTER YOUR CHOICE:");
					n=sc.nextInt();
					sc.nextLine();
					if(n==1)
					{
						Random random=new Random();
						int otp = random.nextInt(1000);   
						FileWriter w = new FileWriter("D:/Courses/zoho incubation/Java/Main Application/credentials/otp.txt");
						PrintWriter p=new PrintWriter(w);
						p.print(otp);
						p.close();
						w.close();
						int check;
						System.out.println("ENTER THE OTP SENT TO YOU:");
						check=sc.nextInt();
						sc.nextLine();
						if(check==otp)
						{
							while(true)
							{
								String regex = "^(?=.*[0-9])"+ "(?=.*[a-z])(?=.*[A-Z])"+ "(?=.*[@#$%^&+=])"+ "(?=\\S+$).{8,16}$";        					//password must match these conditions
								System.out.println("ENTER NEW PASSWORD");
								userPassword=sc.nextLine();
								if(userPassword.matches(regex))
								{
									break;
								}
								else
								{
									System.out.println("PASSWORD MUST CONTAIN A UPPERCAE,A LOWERCASE,A NUMBER,A SPECIAL CHARACTER AND MUST OF LENGTH 8 TO 16...TRY AGAIN!!!!");
								}
							}
							
							list2.set(i,userPassword);
							System.out.println("YOUR PASSWORD HAS BEEN SUCCESSFULLY CHANGED");
							FileWriter fWriter = new FileWriter("D:/Courses/zoho incubation/Java/Main Application/credentials/User_Credentials.txt");
							for(int j=0;j<list1.size();j++)
							{
								s=list1.get(j);
								fWriter.write(s);
								fWriter.write("\n");
								s=list2.get(j);
								fWriter.write(s);
								fWriter.write("\n");	
							}
							fWriter.close();
							
						}
						else
						{
							System.out.println("OTP IS NO LONGER VALID...TRY AGAIN!!!");
						}
					}
				}
				  
				return false;
			}
		}
		System.out.println("USER DOESN'T EXISTS");
		System.out.println("DO YOU WANT TO CREATE A NEW ACCOUNT:");
		System.out.println("If YES press 'y'....If NO press 'n'....");
		choice=sc.next().charAt(0);
		if(choice=='y'||choice=='Y')
		{
			sc.nextLine();
			createAccount();
		}
		
		
		
		return false;
	}
}