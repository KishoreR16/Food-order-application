//Create a food order application as like Swiggy. 
//User and restaurant side
import java.util.*;
import credentials.*;
public class Home1
{
   
	public static void main(String args[])  throws Exception
	{
		int choice,n;
		String userName,password;
		System.out.println();
		Jni.Clean();
		System.out.println();
		System.out.println();
		
		System.out.format("%60s","---FOOD ORDERING APPLICATION---");
		System.out.println();
		System.out.format("%60s","===============================\n");
		System.out.format("%50s","LOADING...");
		System.out.println();
		Thread.sleep(500);
		boolean answer;
		Restaurant restaurant=new Restaurant();
		User user=new User();
		do
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("1.LOG IN AS PARTNER");
			System.out.println("2.LOG IN AS USER");
			System.out.println("3.EXIT");
			System.out.println("ENTER YOUR CHOICE");
			choice=sc.nextInt();									//getting user's choice
			sc.nextLine();
			switch(choice)
			{
				case 1:System.out.format("%50s","RESTAURANT PAGE");
					   System.out.println();
					   while(true)
						{
							System.out.println("1.SIGN IN");
							System.out.println("2.FORGOT PASSWORD");
							System.out.println("3.GO BACK");
							System.out.println("ENTER YOUR CHOICE:");
							n=sc.nextInt();
							sc.nextLine();
							if(n==1)
							{
								System.out.format("%50s","SIGN IN");
								System.out.println();
								System.out.println("ENTER USER NAME:");
								userName=sc.nextLine();
								System.out.println("ENTER PASSWORD");
								password=sc.nextLine();
								restaurant.setCredentials(userName,password);
								answer=restaurant.checkDetails();
								if(answer==true)
								{
									restaurant.access();
								}	
								break;
							}
							else if(n==2)
							{
								System.out.format("%50s","FORGOT PASSWORD");
								System.out.println();
								Restaurant.forgotPassword();
								break;
							}
							else if(n==3)
							{
								break;
							}
					   }
					   break;
				case 2:System.out.format("%50s","USER PAGE");
					   System.out.println();
					   while(true)
					   {
						   System.out.println("1.SIGN IN");
						   System.out.println("2.SIGN UP");
						   System.out.println("3.GO BACK");
						   System.out.println("ENTER YOUR CHOICE:");
						   n=sc.nextInt();
						   sc.nextLine();
						   if(n==1)
							{
								System.out.format("%50s","SIGN IN");
								System.out.println();
								System.out.println("ENTER USER NAME:");
								userName=sc.nextLine();
								System.out.println("ENTER PASSWORD");
								password=sc.nextLine();
								user.setCredentials(userName,password);
								answer=user.checkDetails();
								if(answer==true)
								{
									user.access();
								}	
								break;
								
							}
							else if(n==2)
							{
								System.out.format("%50s","SIGN UP");
								System.out.println();
								user.createAccount();
								break;
							}
							else if(n==3)
							{
								break;
							}
					   }
					   break;
					   
				case 3:System.out.format("%50s","EXIT");
					   System.out.println();
					   System.out.println("THANK YOU FOR USING OUR APPLICATION...PLEASE VISIT AGAIN");
					   break;
					   
			}
			
		}while(choice!=3);
	}
}