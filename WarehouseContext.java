import java.util.*;
import java.text.*;
import java.io.*;

public class WarehouseContext {
	
	private int currentState;
	private static Warehouse warehouse;
	private static WarehouseContext context;
	
	private int currentUser;
	private String userID;
	
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public static final int IsClerk = 0;
	public static final int IsUser = 1;
	private WarehouseState[] states;
	private int[][] nextState;

	private String getToken(String prompt)
	{
		do
		{
			try
			{
				System.out.println(prompt);
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
				if (tokenizer.hasMoreTokens())
				{
					return tokenizer.nextToken();
				}
			}
			catch (IOException ioe)
			{
				System.exit(0);
			}
		} while (true);
	}
	
	private boolean promptYesNo(String prompt)
	{
		prompt += " (Y/y) for YES, anything else for NO.";
		char result = getToken(prompt).charAt(0);
		if (result == 'Y' || result == 'y')
		{
			return true;
		}
		return false;
	}

	private void retrieve()
	{
		try
		{
			Warehouse tempWarehouse = Warehouse.retrieve();
			if (tempWarehouse != null)
			{
				System.out.println("The warehouse data has been retrieved from file WarehouseData.\n");
				warehouse = tempWarehouse;
			}
			else
			{
				System.out.println("Warehouse file not found. Creating new warehouse data.");
				warehouse = Warehouse.getInstance();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void setLogin(int code)
	{currentUser = code;}

	public void setUser(String uID)
	{userID = uID;}

	public int getLogin()
	{ return currentUser;}

	public String getUser()
	{ return userID;}

	private WarehouseContext() 
	{	//constructor
		System.out.println("In WarehouseContext constructor");
		if (yesOrNo("Look for saved data and use it?")) {
			retrieve();
		} else {
			Warehouse = Warehouse.getInstance();
		}
		// set up the FSM and transition table;
		states = new WarehouseState[3];
		states[0] = ClerkState.instance();
		states[1] = ClientState.instance(); 
		states[2]=	LoginState.instance();
		nextState = new int[3][3];
		nextState[0][0] = 2;nextState[0][1] = 1;nextState[0][2] = -2;
		nextState[1][0] = 2;nextState[1][1] = 0;nextState[1][2] = -2;
		nextState[2][0] = 0;nextState[2][1] = 1;nextState[2][2] = -1;
		currentState = 2;
	}

	public void changeState(int transition)
	{
		//System.out.println("current state " + currentState + " \n \n ");
		currentState = nextState[currentState][transition];
		if (currentState == -2)
		{
			System.out.println("Error has occurred");
			terminate();
		}
		
		if (currentState == -1)
		{
			terminate();
		}
		
		//System.out.println("current state " + currentState + " \n \n ");
		states[currentState].run();
	}

	private void terminate()
	{
		if (yesOrNo("Save data?"))
		{
			if (warehouse.save())
			{
				System.out.println(" The library has been successfully saved in the file LibraryData \n" );
			}
			
			else {
				System.out.println(" There has been an error in saving \n" );
			}
		}
	
		System.out.println(" Goodbye \n "); 
		System.exit(0);
	}

	public static LibContext instance()
	{
		if (context == null)
		{
			System.out.println("calling constructor");
			context = new LibContext();
		}
		
		return context;
	}

	public void process()
	{
		states[currentState].run();
	}
	
	public static void main (String[] args)
	{
		WarehouseContext.instance().process(); 
	}


}
