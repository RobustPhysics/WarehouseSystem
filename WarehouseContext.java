import java.util.*;
import java.text.*;
import java.io.*;

public class WarehouseContext {
	
	private int currentState;
	private static Warehouse warehouse;
	private static WarehouseContext context;
	
	private int currentUserType;
	private String currentClientId;
	
	public static final int IsClient = 0;
	public static final int IsClerk = 1;
	public static final int IsManager = 2;
	private WarehouseState[] states;
	private int[][] nextState;

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

	//Used to set whether current user is client, clerk, or manager
	public void setUserType(int code)
	{
		currentUserType = code;
	}
	
	//Used to log in as a specified client
	public void setClientId(String id)
	{
		currentClientId = id;
	}

	public int getUserType()
	{
		return currentUserType;
	}

	public String getClientId()
	{
		return currentClientId;
	}

	private WarehouseContext() 
	{	//constructor
		System.out.println("In WarehouseContext constructor");
		if (UserInput.promptYesNo("Look for saved data and use it?"))
		{
			retrieve();
		}
		else
		{
			warehouse = Warehouse.getInstance();
		}
		// set up the FSM and transition table;
		states = new WarehouseState[4];
		states[0] = ClientMenuState.getInstance(); 
		states[1] = ClerkMenuState.getInstance();
		states[2] = ManagerMenuState.getInstance();
		states[3]=	LoginMenuState.getInstance();
		nextState = new int[4][4];
		
		//Transitions for client
		nextState[0][0] = 3;nextState[0][1] = 1;nextState[0][2] = -2;nextState[0][3] = -2;
		
		//Transitions for clerk
		nextState[1][0] = 3;nextState[1][1] = 2;nextState[1][2] = 0;nextState[1][3] = -2;
		
		//Transitions for manager
		nextState[2][0] = 3;nextState[2][1] = 1;nextState[2][2] = -2;nextState[2][3] = -2;
		
		//Transitions for login
		nextState[3][0] = 0;nextState[3][1] = 1;nextState[3][2] = 2;nextState[3][3] = -1;
		
		
		currentState = 3;
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
		if (UserInput.promptYesNo("Save data?"))
		{
			if (warehouse.save())
			{
				System.out.println(" The library has been successfully saved in the file LibraryData \n" );
			}
			else
			{
				System.out.println(" There has been an error in saving \n" );
			}
		}
		System.out.println(" Goodbye \n "); 
		System.exit(0);
	}

	public static WarehouseContext getInstance()
	{
		if (context == null)
		{
			System.out.println("calling constructor");
			context = new WarehouseContext();
		}
		
		return context;
	}

	public void process()
	{
		warehouse.addClient("Clare");
		warehouse.addClient("Chris");
		warehouse.addClient("Miranda");
		warehouse.addClient("Dexter");
		warehouse.addClient("Barrett");
		warehouse.addClient("Joe");
		warehouse.addClient("Barbara");
		
		warehouse.addProduct("Gameboy Advance",199.99,30);
		warehouse.addProduct("Gameboy Platinum",199.99,30);
		warehouse.addProduct("Pokemon Silver",35.99,344);
		warehouse.addProduct("Pokemon Lead",15.99,222);
		warehouse.addProduct("Pokemon Gold",35.99,1);
		warehouse.addProduct("Pokemon Malachite",32.99,223);
		warehouse.addProduct("Pokemon Crystal",45.99,234);
		warehouse.addSupplier("Medtronic","Address 1");
		warehouse.addSupplier("Aldrin Labs","Address 2");
		warehouse.addSupplier("Ariake Tech","Address 3");
		warehouse.addSupplier("Armali Council","Address 4");
		warehouse.addSuppliedProduct("P1", "S1", 60);
		warehouse.addSuppliedProduct("P2", "S1", 15);
		warehouse.addSuppliedProduct("P3", "S1", 15);
		warehouse.addSuppliedProduct("P4", "S1", 15);
		warehouse.addSuppliedProduct("P1", "S2", 45);
		warehouse.addSuppliedProduct("P2", "S3", 10);
		warehouse.addSuppliedProduct("P3", "S3", 10);
		warehouse.addSuppliedProduct("P4", "S3", 10);
		states[currentState].run();
	}
	
	public static void main (String[] args)
	{
		WarehouseContext.getInstance().process(); 
	}


}
