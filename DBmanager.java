package calendar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class DBmanager
{
	List<Account> accounts = new ArrayList<Account>();
	List<Task> tasks = new ArrayList<Task>();
	//List<Task> tasks=new ArrayList<>();
	
	public DBmanager() throws IOException {
		try {
			BufferedReader br = new BufferedReader(new FileReader("accounts.txt"));
			String line;
			while((line=br.readLine())!=null) {
				String array[]=line.split("/");
				Account account=new Account();
				account.setUsername(array[0]);
				account.setPassword(array[1]);
				accounts.add(account);
			}
			br.close();
		}
		catch(Exception e) {
			File file = new File("accounts.txt");
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
            output.write("");
            output.close();
		}
	}

	public Account createAccount() throws IOException
	{
		String temp;
		Scanner input = new Scanner(System.in);
		Account created = new Account();
		boolean valid = true;
		System.out.println("Enter username: ");
		do
		{
			valid=true;
			temp = input.next();
			for(int i=0;i<accounts.size();i++)
			{
				if (temp.equals(accounts.get(i).getUsername()))
				{
					valid = false;
				}
			}
			if (valid==false)
			{
				System.out.println("Invalid. Enter username: ");
			}
		}while(valid==false);
		created.setUsername(temp);
		System.out.println("Enter password: "); //Set the password length
		created.setPassword(input.next());
		accounts.add(created);
		FileWriter fw = new FileWriter("accounts.txt",true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter out = new PrintWriter(bw);
	    out.println(created.getUsername()+"/"+created.getPassword());
	    out.close();
	    
	    String fileName=temp+".txt";
	    File file = new File(fileName);
		BufferedWriter output = new BufferedWriter(new FileWriter(file));
        output.write("");
        output.close();
		//add the account info to the file.
		//collectTasks(created.getUsername());
		return created;
	}
	
	public Account logIn() throws Exception
	{
		String id;
		Scanner input = new Scanner(System.in);
		Account myAccount = new Account();
		boolean valid = false;
		System.out.println("Enter username: ");
		do
		{
			id = input.next();

			for(int i=0;i<accounts.size();i++)
			{
				if (id.equals(accounts.get(i).getUsername()))
				{
					valid = true;
					myAccount = accounts.get(i);
				}
			}
			if (valid==false)
			{
				System.out.println("Invalid. Enter username: ");
			}
		}while(valid==false);
		System.out.println("Enter password: ");
		String password=input.next();
		if(password.equals(myAccount.getPassword())) {
			String fileName=id+".txt";
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			while((line=br.readLine())!=null) {
				String array[]=line.split("/");
				Task task=new Task();
				task.setName(array[0]);
				task.setType(array[1]);
				Date parsed=format.parse(array[2]);
				task.setStart(parsed);
				parsed=format.parse(array[3]);
				task.setEnd(parsed);
				task.setPriority(array[4]);
				task.setAlarm(array[5]);
				if(array[5].equals("false"))
					task.setAlarmDate(null);
				else {
					parsed=format2.parse(array[6]);
					task.setAlarmDate(parsed);
				}
				tasks.add(task);
				/*String array[]=line.split("/");
				Account account=new Account();
				account.setUsername(array[0]);
				account.setPassword(array[1]);
				accounts.add(account);*/
			}
			br.close();
			return myAccount;
		}
		else {
			System.out.println("Wrong password.");
			return null;
		}
		
		/*while (temp!=myAccount.getPassword())
		{
			System.out.println("Invalid. Enter password: ");
			temp=input.next();
		}
		return myAccount;*/
//if someone does not have the password or no users exist and this is selected, it will go on infinitely. needs validation
	}
	
	public void update(Account account, List<Task> tasks) throws Exception {
		String fileName=account.getUsername()+".txt";
		FileWriter fw = new FileWriter(fileName,false);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter out = new PrintWriter(bw);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for(int i=0;i<tasks.size();i++) {
			Task t=tasks.get(i);
			String start=format.format(t.getStart());
			String end=format.format(t.getEnd());
			out.print(t.getName()+"/"+t.getType()+"/"+start+"/"+end+"/"+t.getPriority()+"/"
			+t.getAlarm()+"/");
			if(t.getAlarm()==true) {
				String alarmDate=format2.format(t.getAlarmDate());
				out.println(alarmDate);
			}
			else
				out.println("null");
		}
	    out.close();
	}
	/*public void collectTasks(String un) {
		String fileName=un+".txt";
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line;
			while((line=br.readLine())!=null) {
				String array[]=line.split("/");
				Task task=new Task();
				task.setName(array[0]);
				task.setType(array[1]);
				task.setStart(array[2]);
				task.setEnd(array[3]);
				task.setPriority(array[4]);
				task.setAlarm(array[5]);
				if(array[5].equals("false"))
					task.setAlarmDate(null);
				else
					task.setAlarmDate(array[6]);
				tasks.add(task);
			}
			br.close();
		}
		catch(Exception e) {
			File file = new File(fileName);
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
            output.write("");
            output.close();
		}
	}*/
}
