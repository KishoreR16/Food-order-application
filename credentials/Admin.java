////ADMIN PAGE
package credentials;
import java.util.*;
import java.io.*;


class Items implements Serializable
{
	String name;
	int quantity,price;
	Items(String name,int quantity,int price)
	{
		this.name=name;
		this.quantity=quantity;
		this.price=price;
	}
}
class Hotels implements Serializable
{
	String hotelName,address,hotelOwner,type,status,username,password,place;
	Hotels(String hotelName,String address,String hotelOwner,String type,String status,String username,String password,String place)
	{
		this.hotelName=hotelName;
		this.address=address;
		this.hotelOwner=hotelOwner;
		this.type=type;
		this.status=status;
		this.username=username;
		this.password=password;
		this.place=place;
	}
}
public class Admin 
{
	
	String adminName,adminPassword,s;
	static List<String> list1;
	Map<String,String> credentials;
	Scanner sc;
	FileWriter fr;
	static ArrayList<Hotels> hotelsList;
	static ArrayList<Items> itemsList;
	static ArrayList<Hotels> globalHotels;
	static
	{
		list1=new LinkedList<>();
		hotelsList=new ArrayList<>();
	}
	/*public Admin() 
	{
		;		
	}*/
	public Admin() throws Exception
	{
		globalHotels=new ArrayList<>();
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
	public void setCredentials(String adminName,String adminPassword)
	{
		this.adminName=adminName;
		this.adminPassword=adminPassword;
	}
	void addPlace()
	{
		try
		{
			System.out.println("enter the place you like to add:");
			s=sc.nextLine().toLowerCase();
			File file = new File("D:/Courses/zoho incubation/Java/Main Application/credentials/data/"+s+"");
			if (file.mkdir()) 
			{				
				file = new File("D:/Courses/zoho incubation/Java/Main Application/credentials/data/"+s+"/"+"hotels.txt");
				file.createNewFile();
				System.out.println("Place "+s+" added Successfully");
				fr=new FileWriter("D:/Courses/zoho incubation/Java/Main Application/credentials/data/places.txt",true);
				fr.write(s);
				fr.write("\n");
				fr.close();  
			}
			else
			{
				System.out.println ("Place "+s+" already exists\n");
			}
		}
		catch(IOException e)
		{
			System.out.println("Exception in adding hotel");
		}
		
	}
	void addHotel() throws Exception
	{
		FileReader fr1=new FileReader("D:/Courses/zoho incubation/Java/Main Application/credentials/data/places.txt");    
		BufferedReader br1=new BufferedReader(fr1);
		list1.clear();
		while((s=br1.readLine())!=null)
		{
			list1.add(s);
		}
		br1.close();
		fr1.close();
		String place;
		while(true)
		{
			System.out.println("Enter the place you like to add the restaurant");
			place=sc.nextLine().toLowerCase();
			if(list1.contains(place))
			{
				hotelsList.clear();
				FileInputStream in=new FileInputStream("D:/Courses/zoho incubation/Java/Main Application/credentials/data/"+place+"/"+"hotels.txt");				//deserialize
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
					hotelsList.add(s1);  
					//in.skip(4);				//while appending int is also appended...so we are skipping
				}
				in.close();
				while(true)
				{
					String hotelName,address,hotelOwner,type,status,username,password;
					System.out.println("Enter restaurant name:");
					hotelName=sc.nextLine().toLowerCase();
					System.out.println("Enter restaurant address:");
					address=sc.nextLine().toLowerCase();
					System.out.println("Enter restaurant owner's name:");
					hotelOwner=sc.nextLine().toLowerCase();
					System.out.println("Enter restaurant type(VEG/NON VEG:)");
					type=sc.nextLine().toLowerCase();
					System.out.println("Enter restaurant status:");
					status=sc.nextLine().toLowerCase();
					System.out.println("create username for this restaurant:");
					username=sc.nextLine();
					System.out.println("create password for this restaurant");
					password=sc.nextLine();
					Hotels hotels=new Hotels(hotelName,address,hotelOwner,type,status,username,password,place);
					int flag=0;
					for (Hotels list1: hotelsList)
					{
						if(list1.hotelName.equals(hotelName) && (list1.address.equals(address)))
						{
							list1.hotelOwner=hotelOwner;
							list1.type=type;
							list1.status=status;
							flag=1;
							System.out.println("Data Changed Successfully for this restaurant....\n");
							
							for(Hotels list2:globalHotels)
							{
								if(list2.hotelName.equals(hotelName) && (list2.address.equals(address)))
								{
									list2.hotelOwner=hotelOwner;
									list2.type=type;
									list2.status=status;
								}
							}
							break;
							
						}
						else if(list1.address.equals(address))
						{
							System.out.println("There is another restaurant in this address...Try again!!!\n");
							flag=1;
							break;
						}
					}
					if(flag==0)
					{
						hotelsList.add(hotels);
						globalHotels.add(hotels);
						System.out.println("New restaurant "+hotelName+" added Successfully in the place "+place);
						File file1=new File("D:/Courses/zoho incubation/Java/Main Application/credentials/data/"+place+"/"+hotelName+".txt");
						file1.createNewFile();
						
					}
					
					FileOutputStream file=new FileOutputStream("D:/Courses/zoho incubation/Java/Main Application/credentials/data/"+place+"/"+"hotels.txt");		//Serialization
					ObjectOutputStream out=new ObjectOutputStream(file);
					for (Hotels list1: hotelsList)
					{
						out.writeObject(list1);	
					}
					out.close();
					file.close();
				
					int n2;
					System.out.printf("%s\n","1.ADD ANOTHER HOTEL");
					System.out.printf("%s\n","2.GO BACK");
					System.out.printf("%s","ENTER YOUR CHOICE:");
					n2=sc.nextInt();
					sc.nextLine();
					System.out.println();
					if(n2==1)
					{
						continue;
					}
					break;
				}
			}
			else
			{
				System.out.println("THERE IS NO SERVICE IN THIS PLACE\n");
				System.out.printf("%s\n","1.TRY AGAIN");
				System.out.printf("%s\n","2.GO BACK");
				int n;
				System.out.printf("%s","ENTER YOUR CHOICE:");
				n=sc.nextInt();
				sc.nextLine();
				System.out.println();
				if(n==1)
				{
					continue;
				}
				else if(n==2)
				{
					break;
				}
				else
				{
					while(true)
					{
						System.out.println("PLEASE CHOOSE THE GIVEN OPTIONS:");
						System.out.printf("%s\n","1.TRY AGAIN");
						System.out.printf("%s\n","2.GO BACK");
						System.out.printf("%s","ENTER YOUR CHOICE:");
						n=sc.nextInt();
						sc.nextLine();
						System.out.println();
						if(n==1)
						{
							break;
						}
						else if(n==2)
						{
							break;
						}
					
					}
					if(n==1)
					{
						continue;
					}
				}
				break;
			}
			break;
		}
		FileOutputStream file2=new FileOutputStream("D:/Courses/zoho incubation/Java/Main Application/credentials/Hotel_Credentials.txt");		//Serialization
						ObjectOutputStream out2=new ObjectOutputStream(file2);
						for (Hotels list2: globalHotels)
						{
							out2.writeObject(list2);	
						}
						out2.close();
						file2.close();
	}
	/*void addStock() throws Exception
	{
		FileReader fr1=new FileReader("D:/Courses/zoho incubation/Java/Main Application/credentials/data/places.txt");    
		BufferedReader br1=new BufferedReader(fr1);
		list1.clear();
		while((s=br1.readLine())!=null)
		{
			list1.add(s);
		}
		br1.close();
		fr1.close();
		String place,hotel,name;
		int quantity,price;
		char s2='r';
		while(true){
		System.out.println("Enter the place you like to add the stock in the hotel");
		place=sc.nextLine().toLowerCase();
		if(list1.contains(place))
		{
			
			hotelsList.clear();
			FileInputStream in=new FileInputStream("D:/Courses/zoho incubation/Java/Main Application/credentials/data/"+place+"/"+"hotels.txt");				//deserialize
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
				hotelsList.add(s1);  
				//in.skip(4);				//while appending int is also appended...so we are skipping
			}
			in.close();
			int flag=0;	
			while(true)
			{
				int n2;
				System.out.printf("%s\n","DO YOU WANT TO CONTINUE:");
				System.out.printf("%s\n","1.YES");
				System.out.printf("%s\n","2.NO");
				System.out.printf("%s","ENTER YOUR CHOICE:");
				n2=sc.nextInt();
				sc.nextLine();
				System.out.println();
				if(n2==2)
				{
					break;
				}
				System.out.println("Enter the restaurant name you like to add the stock");
				hotel=sc.nextLine().toLowerCase();
					
				for(Hotels list1:hotelsList)
				{
					if(list1.hotelName.equals(hotel))
					{
						itemsList=new ArrayList<>();
						
						flag=1;
						FileInputStream in1=new FileInputStream("D:/Courses/zoho incubation/Java/Main Application/credentials/data/"+place+"/"+hotel+".txt");				//deserialize
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
							//in.skip(4);				//while appending int is also appended...so we are skipping
						}
						in1.close();
						
						while(true)
						{
							System.out.println("enter the name of the dish");
							name=sc.nextLine().toLowerCase();
							System.out.println("enter the  of quantity of the dish");
							quantity=sc.nextInt();
							System.out.println("enter the price of the dish");
							price=sc.nextInt();
							Items items=new Items(name,quantity,price);
							int flag1=0;
							for (Items list2: itemsList)
							{
								if(list2.name.equals(name))
								{
									list2.quantity=list2.quantity+quantity;
									list2.price=list2.price;
									System.out.println("Data Changed Successfully for this dish....\n");
									flag1=1;
									break;
								}
							}
							if(flag1==0)
							{
								itemsList.add(items);
								System.out.println("New dish "+name+" has been added Successfully in the restaurant "+hotel+"\n");
							}
							FileOutputStream file1=new FileOutputStream("D:/Courses/zoho incubation/Java/Main Application/credentials/data/"+place+"/"+hotel+".txt");		//Serialization
							ObjectOutputStream out1=new ObjectOutputStream(file1);
							for (Items list: itemsList)
							{
								out1.writeObject(list);	
							}
							out1.close();
							file1.close();
							
							System.out.printf("%s\n","1.ADD ANOTHER DISH");
							System.out.printf("%s\n","2.GO BACK");
							System.out.printf("%s","ENTER YOUR CHOICE:");
							n2=sc.nextInt();
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
					
					}
				}
				if(flag==0)
				{
					System.out.println("THE RESTAURANT IS NOT FOUND IN THIS REGION \n");
					System.out.println("DO YOU WANT TO TRY ANOTHER RESTAURANT");
					System.out.println("If 'YES' press 'y'....if 'NO' press 'n'");
					s2=sc.next().charAt(0);
					if(s2=='y'||s2=='Y')
					{
						sc.nextLine();
						continue;
					}
					flag=-1;
					break;
				}
				
			}
			if((flag==-1)&&(s2=='y'||s2=='Y'))
			{
				sc.nextLine();
				continue;
			}
			else if((flag==-1)&&(s2=='N'||s2=='n'))
			{
				break;
			}	
				
		}
				
		else
		{
			System.out.println("THERE IS NO SERVICE IN THIS PLACE");
			System.out.printf("%s\n","1.TRY AGAIN");
			System.out.printf("%s\n","2.GO BACK");
			int n;
			System.out.printf("%s","ENTER YOUR CHOICE:");
			n=sc.nextInt();
			sc.nextLine();
			System.out.println();
			if(n==1)
			{
				continue;
			}
			else if(n==2)
			{
				break;
			}
			else
			{
				while(true)
				{
					System.out.println("PLEASE CHOOSE THE GIVEN OPTIONS:");
					System.out.printf("%s\n","1.TRY AGAIN");
					System.out.printf("%s\n","2.GO BACK");
					System.out.printf("%s","ENTER YOUR CHOICE:");
					n=sc.nextInt();
					sc.nextLine();
					System.out.println();
					if(n==1)
					{
						break;
					}
					else if(n==2)
					{
						break;
					}
					
				}
				if(n==1)
				{
					continue;
				}
			}
			break;
		}
		
			System.out.println("PLEASE CHOOSE THE GIVEN OPTIONS:");
			System.out.printf("%s\n","1.TRY AGAIN");
			System.out.printf("%s\n","2.GO BACK");
			int n;
			System.out.printf("%s","ENTER YOUR CHOICE:");
			n=sc.nextInt();
			sc.nextLine();
			System.out.println();
			if(n==1)
			{
				continue;
			}
			else if(n==2)
			{
				break;
			}
		
	}
			
}
*/
	void printPlaces() throws Exception
	{
		FileReader fr=new FileReader("D:/Courses/zoho incubation/Java/Main Application/credentials/data/places.txt");    
        BufferedReader br=new BufferedReader(fr);    
		System.out.println("The places where our services available are:");
		String read;
		int count=1;
		while((read=br.readLine())!=null)
		{
			System.out.println(count+"."+read);
			count++;
		}	
		System.out.println();
		br.close(); 
		fr.close();   
		
	}
				
			
	void printHotel() throws Exception
	{
		int select;
		String place;
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
			System.out.println("The places available are:");
			for(int i=0;i<list1.size();i++)
			{
				System.out.println((i+1)+"."+list1.get(i));
			}
			System.out.println();
			System.out.println("enter the place number you like to select");
			select=sc.nextInt();
			sc.nextLine();
			if(select>=list1.size()+1)
			{
				System.out.printf("%s\n","PLEASE SELECT A PROPER CATEGORY");
				int n1;
				System.out.printf("%s\n","1.CONTINUE");
				System.out.printf("%s\n","2.GO BACK");
				System.out.printf("%s","ENTER YOUR CHOICE:");
				n1=sc.nextInt();
				sc.nextLine();
				System.out.println();
				if(n1==2)
				{
					break;
				}
				continue;
			}
		
			place=list1.get(select-1);
			FileInputStream in=new FileInputStream("D:/Courses/zoho incubation/Java/Main Application/credentials/data/"+place+"/hotels.txt");				//deserialize
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
			System.out.printf("%15s%20s%15s%16s%10s%20s%17s\n","HOTEL NAME","HOTEL ADDRESS","HOTEL OWNER","VEG/NON VEG","STATUS","USERNAME","PASSWORD"); 
			System.out.format("--------------------------------------------------------------------------------------------------------------------\n");  

			while(in.available()!=0)
			{
				s1=(Hotels)ois.readObject();
				System.out.format("%15s%20s%15s%16s%10s%20s%17s\n" ,s1.hotelName,s1.address,s1.hotelOwner,s1.type,s1.status,s1.username,s1.password);  
			}
			System.out.println();
			in.close();
			int n2;
			System.out.printf("%s\n","1.VIEW");
			System.out.printf("%s\n","2.GO BACK");
			System.out.printf("%s","ENTER YOUR CHOICE:");
			n2=sc.nextInt();
			sc.nextLine();
			System.out.println();
			if(n2==2)
			{
				break;
			}
		}	
	}
	void printStock() throws Exception
	{
		list1.clear();
		int breaking;
		int select;
		String place,hotel;
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
			System.out.printf("%s\n","PLACES");
			System.out.printf("%s\n","1.CONTINUE");
			System.out.printf("%s\n","2.GO BACK");
			System.out.printf("%s","ENTER YOUR CHOICE:");
			breaking=sc.nextInt();
			sc.nextLine();
			System.out.println();
			if(breaking==2)
			{
				break;
			}
			System.out.println("The places available are:");
			for(int i=0;i<list1.size();i++)
			{
				System.out.println((i+1)+"."+list1.get(i));
			}
			System.out.println();
			System.out.println("enter the place number you like to select");
			select=sc.nextInt();
			sc.nextLine();
			if(select>=list1.size()+1)
			{
				System.out.printf("%s\n","PLEASE SELECT A PROPER CATEGORY");
				int n1;
				System.out.printf("%s\n","1.CONTINUE");
				System.out.printf("%s\n","2.GO BACK");
				System.out.printf("%s","ENTER YOUR CHOICE:");
				n1=sc.nextInt();
				sc.nextLine();
				System.out.println();
				if(n1==2)
				{
					break;
				}
				continue;
			}
		
			place=list1.get(select-1);
			LinkedList<String> list2=new LinkedList<>();
			FileInputStream in1=new FileInputStream("D:/Courses/zoho incubation/Java/Main Application/credentials/data/"+place+"/hotels.txt");	
			ObjectInputStream ois1=null;
			try
			{
				ois1=new ObjectInputStream(in1);  
			}
			catch(EOFException e)
			{
				;
			}
			Hotels h;
			while(in1.available()!=0)
			{
				h=(Hotels)ois1.readObject();
				list2.add(h.hotelName) ;
			}
			in1.close();
			while(true)
			{
				System.out.println("The hotels available in the place "+place+" are:");
				for(int i=0;i<list2.size();i++)
				{
					System.out.println((i+1)+"."+list2.get(i));
				}
				System.out.println();
				System.out.println("enter the hotel number you like to select");
				select=sc.nextInt();
				sc.nextLine();
				if(select>=list2.size()+1)
				{
					System.out.printf("%s\n","PLEASE SELECT A PROPER CATEGORY");
					int n1;
					System.out.printf("%s\n","1.CONTINUE");
					System.out.printf("%s\n","2.GO BACK");
					System.out.printf("%s","ENTER YOUR CHOICE:");
					n1=sc.nextInt();
					sc.nextLine();
					System.out.println();
					if(n1==2)
					{
						break;
					}
					continue;
				}
				hotel=list2.get(select-1);
				FileInputStream in=new FileInputStream("D:/Courses/zoho incubation/Java/Main Application/credentials/data/"+place+"/"+hotel+".txt");	
				ObjectInputStream ois=null;
				try
				{
					ois=new ObjectInputStream(in);  
				}
				catch(EOFException e)
				{
					;
				}
				Items s1;
				System.out.printf("%20s%20s%20s\n","DISH NAME","QUANTITY","PRICE"); 
				System.out.format("--------------------------------------------------------------------------------------------------------------------\n");  
				while(in.available()!=0)
				{
					s1=(Items)ois.readObject();
					System.out.format("%20s%20d%20d\n" ,s1.name,s1.quantity,s1.price); 
				}
				System.out.println();
				in.close();
				int n2;
				System.out.printf("%s\n","1.CONTINUE");
				System.out.printf("%s\n","2.GO BACK");
				System.out.printf("%s","ENTER YOUR CHOICE:");
				n2=sc.nextInt();
				System.out.println();
				sc.nextLine();
				if(n2==2)
				{
					break;
				}

			}
			
		}
	}
	void purchaseDetails() throws Exception
	{
		FileReader fr1=new FileReader("D:/Courses/zoho incubation/Java/Main Application/credentials/purchase.txt");    
        BufferedReader br1=new BufferedReader(fr1);  
		while((s=br1.readLine())!=null)
		{
			System.out.println(s);
		}
		br1.close();
		fr1.close();
	}
	public void access() throws Exception
	{
		int choice;
		do
		{
		
			System.out.printf("%s\n","1.ADD PLACE");
			System.out.printf("%s\n","2.ADD/MODIFY HOTEL");
			//System.out.printf("%s\n","3.ADD STOCKS IN THE HOTEL");
			System.out.printf("%s\n","3.DISPLAY ALL THE PLACES");
			System.out.printf("%s\n","4.DISPLAY THE HOTELS IN A PLACE ");
			System.out.printf("%s\n","5.PRINT THE STOCKS IN A HOTEL");
			System.out.printf("%s\n","6.PRINT CUSTOMER PURCHASE DETAILS");
			System.out.printf("%s\n","7.LOG OUT");
			System.out.println();
			System.out.printf("%s","ENTER YOUR CHOICE:");
			choice=sc.nextInt();
			sc.nextLine();
			System.out.println();
			switch(choice)
			{
				case 1:System.out.format("%50s","ADD PLACE");
					   System.out.println();
					   System.out.format("%60s\n","===============================");
					   addPlace();
					   
					   break;
				case 2:System.out.format("%53s","ADD/MODIFY HOTEL");
					   System.out.println();
					   System.out.format("%60s\n","===============================");
					   addHotel();
					   break;
				/*case 3:System.out.format("%55s","ADD STOCKS IN THE HOTEL");
					   System.out.println();
					   System.out.format("%60s\n","===============================");
					   //addStock();
					   break;*/
				case 3:System.out.format("%55s","DISPLAY ALL THE PLACES");
					   System.out.println();
					   System.out.format("%60s\n","===============================");
					   printPlaces();
					   break;
				case 4:System.out.format("%60s","DISPLAY THE HOTELS IN A PLACE");
					   System.out.println();
					   System.out.format("%60s\n","===============================");
					   printHotel();
					   break;
				case 5:System.out.format("%60s","PRINT THE STOCKS IN A HOTEL");
					   System.out.println();
					   System.out.format("%60s\n","===============================");
					   printStock();
					   break;
				case 6:System.out.format("%60s","PRINT CUSTOMER PURCHASE DETAILS");
					   System.out.println();
					   System.out.format("%60s\n","===============================");
					   purchaseDetails();
					   break;
			    case 7:System.out.format("%56s","SUCCESSFULLY LOGGED OUT....");
					   System.out.println();
					   System.out.format("%60s\n","===============================");
						
					   break;

						
			}
		}while(choice!=7);
	}
	public boolean checkDetails() throws Exception
	{
		
		FileReader fr=new FileReader("D:/Courses/zoho incubation/Java/Main Application/credentials/Admin_Credentials.txt");    
        BufferedReader br=new BufferedReader(fr);    
        String s1="",s2="";    
		credentials=new HashMap<>();
		sc=new Scanner(System.in);
		int flag=0;
        while((s=br.readLine())!=null)
		{
			if(flag==0)
			{
				s1=s;
				flag=1;
			}
			else
			{
				flag=0;
				s2=s;
				credentials.put(s1,s2);
			}
        }  
		
		if(credentials.containsKey(adminName))
		{
			if(credentials.get(adminName).equals(adminPassword))
			{
				return true;
			}
			else
			{
				System.out.printf("%50s\n","PASSWORD IS WRONG!!!");
				br.close(); 
				fr.close();    
				return false;
			}
			
		}
		System.out.printf("%50s\n","ADMIN DOESN'T EXISTS...SORRY ):");
		br.close(); 
		fr.close();    
		return false;
		
	}
}