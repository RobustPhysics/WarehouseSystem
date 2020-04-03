// ClerkMenuState.java

import java.util.*;
import java.text.*;
import java.io.*;

public class ClerkMenuState extends WarehouseState
{
	private static ClerkMenuState ClerkMenuState;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static Warehouse warehouse;
		
		private enum Option
	{
		// Add a client. Gets details of new client; calls method on Facade.
		ADD_CLIENT("Adds client to system"),
		// Show list of products with quantities and sale prices. The state invokes a method on Facade to get an iterator, and then extracts the needed information.
		SHOW_PRODUCTS("Shows products in database"),
		// Show list of clients. The state invokes a method on Facade to get an iterator, and then extracts the needed information.
		SHOW_CLIENT_TRANSACTIONS("Displays a list of transactions for specified client"),
		// Show list of clients with outstanding balance. The state invokes a method on Facade to get an iterator, and then extracts the needed information.
		SHOW_OUTSTANDING_CLIENTS("Shows a list of all clients with an outstanding balance due"),
		// Become a client. The actor will be asked to input a ClientID; if valid, this ID will be stored in Context, and the system transitions to the ClientMenuState.
			//TODO
		// Display the waitlist for a product. The state asks the actor for productid; calls method on Façade to get an iterator.
		SHOW_WAIT_LIST_PRODUCTS("Shows a list of every product and the waitlists for that product."),
		// Receive a shipment. The state asks the actor for productid and quantity; calls method on Façade to get an iterator. Displays each waitlisted order and performs operation requested by actor (skip or fill).
			//TODO
		// Record a payment from a client. State asks the actor for ID and amount; calls method on Façade to credit the amount to the client’s account.
			//TODO
		// Logout. System transitions to the previous state, which has to be remembered in the context. (If previous state was the OpeningState, it goes there; otherwise it goes to ManagerMenuState.)
			//TODO
		
		private String description;
		private static int LENGTH = Option.values().length;
		
		private Option(String str)
		{
			description = str;
		}
		
		public String getDescription()
		{
			return description;
		}
	}
	
	private ClerkMenuState()
	{
		warehouse = Warehouse.instance(); //get the facade
		//context = WarehouseContext.instance();
	}
	
	public static ClerkMenuState getInstance()
	{
		if (instance == null)
		{
			instance = new ClerkMenuState();
		}
		return instance;
	}
	
	//Display Menu
	public void displayHelp()
	{
		System.out.println("Enter a number associated with a command seen below");
		System.out.println("---------------------");
		ManagerOption options[] = ManagerOption.values();

		for (ManagerOption opt : options)
		{
			System.out.println(opt.ordinal() + " - " + opt.getDescription());
		}
	}
	
	public Option getCommand()
	{
		do
		{
			try
			{
				String token = getToken("Enter a command. Use " + ClerkOption.HELP.ordinal() + " to display the menu.");
				int value = Integer.parseInt(token);
				if (value >= 0 && value <= ClerkOption.LENGTH)
				{
					System.out.println("IN THE getCommand() METHOD");
					return ClerkOption.values()[value];
				}
				else
				{
					System.out.println("Input command out of range!");
				}
			}
			catch (NumberFormatException nfe)
			{
				System.out.println("Invalid input - Please enter a valid number!");
			}
		} while(true);
	}
	///need to add methods for process commands
	public void process()
	{
		Option command;
		displayHelp();
		do
		{
			command = getCommand();
			switch (command)
			{
				case ADD_CLIENT:
					showClientDetails();
					break;
				case SHOW_PRODUCTS:
					showProducts();
					break;
				case SHOW_CLIENT_TRANSACTIONS:
					showClientTransactions();
					break;
				case SHOW_OUTSTANDING_CLIENTS:
					updateProductInCart();
					System.out.println("Product updated")
					break;
				//become client	
				case BECOME CLIENT:
					System.out.println("getting client info");
					becomeClient();
					break;
				case SHOW_WAIT_LIST_PRODUCTS:
					showWaitListProducts();
					System.out.println("waitlist products retrieved");
					break;
				//record payment from client
				case RECORD CLIENT PAYMENT:
					recordClientPayment();
					System.out.println("recording cleint's payment");
					break;
				case HELP:
					displayHelp();
					break;
			}
		} while (command != ClientOption.EXIT);
		logout();
	}
	
	public void run()
	{
		process();
	}
		
}