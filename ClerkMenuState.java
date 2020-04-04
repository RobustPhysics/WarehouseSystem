// ClerkMenuState.java

import java.util.*;
import java.text.*;
import java.io.*;

public class ClerkMenuState extends WarehouseState
{
	private static Warehouse warehouse;
	public static ClerkMenuState instance;
	
	private enum Option
	{
		// Add a client. Gets details of new client; calls method on Facade.
		ADD_CLIENT("Adds client to system"),
		// Show list of products with quantities and sale prices. The state invokes a method on Facade to get an iterator, and then extracts the needed information.
		SHOW_PRODUCTS("Shows products in database"),
		// Show list of clients. The state invokes a method on Facade to get an iterator, and then extracts the needed information.
		SHOW_CLIENTS("Show list of clients"),
		// Show list of clients with outstanding balance. The state invokes a method on Facade to get an iterator, and then extracts the needed information.
		SHOW_OUTSTANDING_CLIENTS("Shows a list of all clients with an outstanding balance due"),
		
		// Display the waitlist for a product. The state asks the actor for productid; calls method on Façade to get an iterator.
		SHOW_WAIT_LIST_PRODUCTS("Shows a list of every product and the waitlists for that product."),
		// Receive a shipment. The state asks the actor for productid and quantity; calls method on Façade to get an iterator. Displays each waitlisted order and performs operation requested by actor (skip or fill).
		RECIEVE_SHIPMENT("recieve a shipment"), //TODO
		// Record a payment from a client. State asks the actor for ID and amount; calls method on Façade to credit the amount to the client’s account.
		RECORD_PAYMENT("record payment from a client"), //TODO
		// Become a client. The actor will be asked to input a ClientID; if valid, this ID will be stored in Context, and the system transitions to the ClientMenuState.
		BECOME_CLIENT("become a client"), //TODO
		HELP("display the help menu"),
		// Logout. System transitions to the previous state, which has to be remembered in the context. (If previous state was the OpeningState, it goes there; otherwise it goes to ManagerMenuState.)
		LOGOUT("logout"); //TODO
		
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
		warehouse = Warehouse.getInstance(); //get the facade
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
	
	public Option getCommand()
	{
		do
		{
			try
			{
				String token = UserInput.getToken("Enter a command. Use " + Option.HELP.ordinal() + " to display the menu.");
				int value = Integer.parseInt(token);
				if (value >= 0 && value <= Option.LENGTH)
				{
					return Option.values()[value];
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
	
	//Display Menu
	public void displayHelp()
	{
		System.out.println("Enter a number associated with a command seen below");
		System.out.println("---------------------");
		Option options[] = Option.values();

		for (Option opt : options)
		{
			System.out.println(opt.ordinal() + " - " + opt.getDescription());
		}
	}
	
	public void addClient()
	{
		String name = UserInput.getToken("Enter client name");
		Client client;
		client = warehouse.addClient(name);
		if (client == null)
		{
			System.out.println("Error! Failed to add client to warehouse!");
		}
		System.out.println("Client: " + client);
	}
	
	public void showProducts()
	{
		Iterator products = warehouse.getProducts();
		while (products.hasNext())
		{
			Product product = (Product) (products.next());
			System.out.println(product);
		}
	}
	
	public void showClients()
	{
		Iterator clients = warehouse.getClients();
		while (clients.hasNext())
		{
			Client client = (Client) (clients.next());
			System.out.println(client);
		}
	}
	
	public void showOutstandingClients()
	{
		Iterator outstandingClients = warehouse.getOutstandingClients();
		
		System.out.println("The following clients have an outstanding balance.");
		if (!outstandingClients.hasNext())
		{
			System.out.println("\tNone");
		}
		while (outstandingClients.hasNext())
		{
			Client client = (Client) outstandingClients.next();
			System.out.println("\t" + client);
			System.out.println("\t\tAmount due: " + client.getAmountDue());
		}
	}
	
	public void showProductWaitlist()
	{
		String id = UserInput.getToken("Enter the product ID whose waitlist will be shown");
		Product product = warehouse.getProduct(id);
		Iterator waitlist = warehouse.getProductWaitlist(id);
		
		if (product != null)
		{
			System.out.println("Waitlist for product " + product.getProductName() + " (" + id + ") is...");
			while (waitlist.hasNext())
			{
				WaitlistItem item = (WaitlistItem) waitlist.next();
				System.out.println("\t" + item);
			}
		}
	}
	
	public void shipProduct()
	{
		String productId = UserInput.getToken("Enter ID of product to be shipped");
		String supplierId = UserInput.getToken("Enter ID of supplier to ship product");

		//TODO: Should this be an int with error codes? i.e. 0 = can ship, 1 = product not found, 2 = supplier not found, etc
		boolean canShip = warehouse.canShipProduct(supplierId, productId);

		if (canShip)
		{
			String quantityStr = UserInput.getToken("How many products should be shipped to warehouse?");
			int quantity = Integer.parseInt(quantityStr);
			warehouse.shipProduct(supplierId, productId, quantity);
		}
		else
		{
			System.out.println("Unable to ship product " + productId + " from supplier " + supplierId + "!");
		}
	}
	
	public void recordPayment()
	{
		//TODO
		System.out.println("Unimplemented");
	}
	
	public void clientMenu()
	{
		WarehouseContext.getInstance().changeState(2); //switch to client state
	}
	
	public void logout()
	{
		int wLogin = WarehouseContext.getInstance().getUserType();
		if (wLogin == WarehouseContext.IsManager)
		{
			WarehouseContext.getInstance().changeState(1); //switch to manager state
		}
		else if (wLogin == WarehouseContext.IsClerk)
		{
			WarehouseContext.getInstance().changeState(0); //switch to login state
		}
		else
		{
			//note this would occur as an error if client somehow became a clerk
			WarehouseContext.getInstance().changeState(3); //switch to error state
		}
	}
	
	//need to add methods for process commands
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
					addClient();
					break;
				case SHOW_PRODUCTS:
					showProducts();
					break;
				case SHOW_CLIENTS:
					showClients();
					break;
				case SHOW_OUTSTANDING_CLIENTS:
					showOutstandingClients();
					break;
				case SHOW_WAIT_LIST_PRODUCTS:
					showProductWaitlist();
					break;
				case RECIEVE_SHIPMENT:
					shipProduct();
					break;
				case RECORD_PAYMENT:
					recordPayment();
					break;
				case BECOME_CLIENT:
					clientMenu();
					break;
				case HELP:
					displayHelp();
					break;
				case LOGOUT:
					break;
			}
		} while (command != Option.LOGOUT);
		logout();
	}
	
	public void run()
	{
		process();
	}
		
}