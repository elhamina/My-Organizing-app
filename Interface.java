package calendar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Interface
{
	
	public static void main(String[] args) throws Exception
	{
		Scanner manage = new Scanner(System.in);
		DBmanager database = new DBmanager();
		Controller ctrl = new Controller();
		//creates an account data type and makes sure the account is within the dbmanager
		String x = "";
		boolean loggedIn = false;
		Account thisUser = new Account();
		List<Task> tasks = new ArrayList<Task>();
		while(loggedIn==false)
		{
			System.out.println("enter n for new account or l to log in");
			x=manage.next();
			//System.out.println(x);
			if(x.equals("n"))
			{
				thisUser = database.createAccount();
				loggedIn=true;
			}
			else if (x.equals("l"))
			{
				thisUser =  database.logIn();
				if(thisUser!=null)
					loggedIn=true;
			}
		}
		
		if(loggedIn) {
			tasks=database.tasks;
		}
		
		//prints the caledar, if the user already has one
		for (int i=0; i<tasks.size();i++)
		{
			System.out.println("\n<Task list>\n");
			ctrl.print(tasks.get(i));
			System.out.println();
		}
		do{
			System.out.println("Enter the character for the command: ");
			System.out.println("c -create task\ne -edit (index) task\n"
					+ "d -delete (index) task\ns -sort\np -print");
			System.out.println("enter x to finish");
			System.out.println("----------------------------------------");
			System.out.println();
			x=manage.next();
			switch(x)
			{
				case "c":
					ctrl.createTask(tasks);
					break;
				case "e":
					x="";
					System.out.println("Index of the task you want to edit: ");
					Scanner sc = new Scanner(System.in);
					ctrl.editTask(Integer.parseInt(sc.nextLine()), tasks);
					break;
				case "d":
					System.out.println("Index of the task you want to remove: ");
					Scanner sc2 = new Scanner(System.in);
					ctrl.deleteTask(Integer.parseInt(sc2.nextLine()), tasks);
					break;
				case "s":
					ctrl.sortBy(tasks);
					break;
				case "p":
					//System.out.println(thisUser.myCalendar.size());
					for (int i=0; i<tasks.size();i++)
					{
						System.out.println("Task index: "+i);
						ctrl.print(tasks.get(i));
						System.out.println();
					}
			}
			//copies the controller calendar to the account
			database.update(thisUser, tasks);
			//tasks = thisUser.ctrl.calendar;
		}while(!x.equals("x"));
		manage.close();
	}
}
