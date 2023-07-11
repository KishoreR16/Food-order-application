//Admin side
import java.util.*;
import credentials.*;
public class Home2
{
   
	public static void main(String args[])  throws Exception
	{
		int choice;
		String userName,password;
		System.out.println();
		Jni.Clean();
		System.out.println();
		System.out.println();
		
		System.out.format("%60s","---FOOD ORDERING APPLICATION---");
		System.out.println();
		System.out.format("%60s","===============================\n");
		boolean answer;
		Admin admin=new Admin();
		do
		{
			Scanner sc=new Scanner(System.in);
			System.out.format("%50s\n","1.ADMIN SIGN IN");
			System.out.format("%50s\n","2.EXIT");
			System.out.format("%60s\n","===============================");
			System.out.println("ENTER YOUR CHOICE:");
			choice=sc.nextInt();														//getting admin's choice
			sc.nextLine();
			switch(choice)
			{
				case 1:System.out.format("%50s","ADMIN SIGN IN");	
						System.out.println();
						System.out.format("%60s\n","===============================");
						System.out.println();
						System.out.println("ENTER USER NAME:");
						userName=sc.nextLine();
						System.out.println("Enter PASSWORD");
						password= String.valueOf(System.console().readPassword());
						admin.setCredentials(userName,password);
						answer=admin.checkDetails();
						if(answer==true)
						{
							admin.access();
						}
						break;
				case 2:
					    System.out.println();
						System.out.format("%65s\n","Thank you for using our Application...");
						System.out.format("%60s\n","===============================");
					    break;		   
			}
		}while(choice!=2);
	}
}