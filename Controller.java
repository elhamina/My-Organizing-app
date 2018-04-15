package calendar;
import java.text.SimpleDateFormat;
import java.util.*;

public class Controller
{
	//scanner is how user enters all data and chooses what to change
	//calendar is the list of tasks we are working with
	Scanner manage = new Scanner(System.in);
	//List<Task> calendar = new ArrayList<Task>();
	
	public void print(Task x)
	{
		System.out.println("Name: "+x.getName());
		System.out.println("Type: "+x.getType());
		System.out.println("Start date: "+x.getStart());
		System.out.println("End date: "+x.getEnd());
		System.out.println("Priority: "+x.getPriority());
		System.out.println("Alarm setting: "+x.getAlarm());
		if(x.getAlarm()==true) {
			System.out.println("Alarm date and time: "+x.getAlarmDate());
		}
	}
	
	public void sortBy(List<Task> tasks)
	{
		String x="";
		System.out.println("Enter the character for the field you want to sort by: ");
		System.out.println("n -name\ne -end date\np -priority\n");
		System.out.println("enter x to finish");
		x = manage.nextLine();
		switch(x)
		{
		case "n":
			Task temp=new Task();
			for(int i=0;i<tasks.size();i++){
				int min=i;
				for(int j=i+1;j<tasks.size();j++){
					if(tasks.get(j).getName().compareTo(tasks.get(min).
							getName())<0){
						min=j;
					}
				}
				temp.copy(tasks.get(i));
				tasks.get(i).copy(tasks.get(min));
				tasks.get(min).copy(temp);
			}
			break;
		case "e":
			temp=new Task();
			try {
				for(int i=0;i<tasks.size();i++){
					int min=i;
					for(int j=i+1;j<tasks.size();j++){
						if(tasks.get(j).getEnd().compareTo(tasks.get(min).
								getEnd())<0){
							min=j;
						}
					}
					temp.copy(tasks.get(i));
					tasks.get(i).copy(tasks.get(min));
					tasks.get(min).copy(temp);
				}
			}
			catch(Exception e) {
				System.out.println("Exception occured.");
			}
			break;
		case "p":
			temp=new Task();
			for(int i=0;i<tasks.size();i++){
				int min=i;
				for(int j=i+1;j<tasks.size();j++){
					if(tasks.get(j).getPriority().compareTo(tasks.get(min).
							getPriority())<0){
						min=j;
					}
				}
				temp.copy(tasks.get(i));
				tasks.get(i).copy(tasks.get(min));
				tasks.get(min).copy(temp);
			}
			break;
		default:
			System.out.println("Invalid input.");
		}
	}
	
	public void createTask(List<Task> tasks)
	{
		System.out.println("Enter the data for this task in this order: ");
		Task x=new Task();
		System.out.println("name: ");
		x.setName(manage.nextLine());
		System.out.println("type: ");
		x.setType(manage.nextLine());
		try {
			System.out.println("start date (yyyy-MM-dd): ");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String date=manage.nextLine();
			Date parsed=format.parse(date);
			x.setStart(parsed);
		}
		catch(Exception e) {
			x.setStart(new Date(0,0,0));
			System.out.println("Exception occured.");
		}
		try {
			System.out.println("end date (yyyy-MM-dd): ");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String date=manage.nextLine();
			Date parsed=format.parse(date);
			x.setEnd(parsed);
		}
		catch(Exception e) {
			x.setEnd(new Date(0,0,0));
			System.out.println("Exception occured.");
		}
		System.out.println("priority(A/B/C): ");
		x.setPriority(manage.nextLine());
		System.out.println("alarm(T/F): ");
		x.setAlarm(manage.nextLine());
		if(x.getAlarm()==true) {
			try {
				System.out.println("alarm date (yyyy-MM-dd): ");
				String date=manage.nextLine();
				System.out.println("alarm time (HH:mm): ");
				String time=manage.nextLine();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String result=date+" "+time;
				Date parsed=format.parse(result);
				x.setAlarmDate(parsed);
			}
			catch(Exception e) {
				x.setAlarmDate(new Date(0,0,0));
				System.out.println("Exception occured.");
			}
		}
		tasks.add(x);
	}
	
	public void editTask(int y, List<Task> tasks)
	{
		Task z=tasks.get(y);
		String x;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("Enter the character for the field you want to edit: ");
			System.out.println("n -name: "+z.getName()+"\nt -type: "+z.getType()+
					"\ns -start date: "+z.getStart()+
					"\ne -end date: "+z.getEnd()+"\np -priority: "+z.getPriority()+
					"\na -alarm: "+z.getAlarm());
			if(z.getAlarm()==true) {
				System.out.println("d -alarm date and time: "+z.getAlarmDate());
			}
			System.out.println("enter x to finish");
			x = manage.nextLine();
			switch(x)
			{
				case "n":
					System.out.println("Enter the name.");
					//Scanner sc = new Scanner(System.in);				
					z.setName(sc.nextLine());
					//calendar.set(y,z);
					break;
				case "t":
					System.out.println("Enter the type.");
					//Scanner sc2 = new Scanner(System.in);
					z.setType(sc.nextLine());
					//calendar.set(y,z);
					break;
				case "s":
					try {
					//Scanner sc4 = new Scanner(System.in);
					System.out.println("Enter the start date (yyyy-MM-dd): ");
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String date=sc.nextLine();
					Date parsed=format.parse(date);
					z.setStart(parsed);
					//sc4.close();
					//calendar.set(y,z);
					}
					catch(Exception e) {
						z.setStart(new Date(0,0,0));
						System.out.println("Exception occured.");
					}
					break;
				case "e":
					try {
					//Scanner sc5 = new Scanner(System.in);
					System.out.println("Enter the end date (yyyy-MM-dd): ");
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String date=sc.nextLine();
					Date parsed=format.parse(date);
					z.setEnd(parsed);
					//sc5.close();
					//calendar.set(y,z);
					}
					catch(Exception e) {
						z.setEnd(new Date(0,0,0));
						System.out.println("Exception occured.");
					}
					break;
				case "p":
					System.out.println("Enter the priority.");
					//Scanner sc6 = new Scanner(System.in);
					z.setPriority(sc.nextLine());
					//calendar.set(y,z);
					break;
				case "a":
					System.out.println("Enter the alarm setting.");
					//Scanner sc7 = new Scanner(System.in);
					z.setAlarm(sc.nextLine());
					//calendar.set(y,z);
					break;
				case "d":
					if(z.getAlarm()==false) {
						System.out.println("Alarm is not on.");
						break;
					}
					else {
						try {
							//Scanner sc8 = new Scanner(System.in);
							System.out.println("Enter the alarm date (yyyy-MM-dd): ");
							String date=sc.nextLine();
							System.out.println("Enter the alarm time (HH:mm): ");
							String time=sc.nextLine();
							SimpleDateFormat format = 
									new SimpleDateFormat("yyyy-MM-dd HH:mm");
							String result=date+" "+time;
							Date parsed=format.parse(result);
							z.setAlarmDate(parsed);
							//sc8.close();
							//calendar.set(y,z);
						}
						catch(Exception e) {
							z.setAlarmDate(new Date(0,0,0));
							System.out.println("Exception occured.");
						}
						break;
					}
			}
		}while(!x.equals("x"));
	}
	
	public void deleteTask(int x, List<Task> tasks)
	{
		tasks.remove(x);
	}
	
	/*public void updateDatabase() throws IOException {
		String fileName=username+".txt";
		FileWriter fw = new FileWriter(fileName,false);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter out = new PrintWriter(bw);
		for(int i=0;i<calendar.size();i++) {
			Task t=calendar.get(i);
		    out.println(t.getName()+"/"+t.getType()+"/"+t.getStart()+"/"+
			t.getEnd()+"/"+t.getPriority()+"/"+t.getAlarm()+"/"+t.getAlarmDate());
		}
		out.close();
	}*/
}
