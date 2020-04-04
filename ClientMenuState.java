import java.util.Iterator;

public class ClientMenuState extends WarehouseState {
	private WarehouseContext context;
	private static Warehouse warehouse;
	private static ClientMenuState instance;
	
	private enum Option
	{
		
		SHOW_CLIENT_DETAILS("Show client details."), //TODO
		SHOW_PRODUCTS("Shows products in database"),
		SHOW_CLIENT_TRANSACTIONS("Displays a list of transactions for specified client"),
		UPDATE_PRODUCT_IN_CART("Updates the quantity of a specified product in a specified clients shopping cart"),
		ADD_TO_CART("Adds a specified product to a clients shopping cart"),
		PROCESS_ORDER("Processes an order for all items currently in shopping cart."),
		SHOW_CLIENT_CART("Shows all line items in the shopping cart"),
		SHOW_CLIENT_WAITLIST("Shows all items on the waitlist"),
		HELP("Displays the help menu"),
		
		EXIT("Exits the program"); //TODO
		
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
	
	private ClientMenuState()
	{
		warehouse = Warehouse.getInstance(); //get the facade
		//context = WarehouseContext.getInstance();
	}
	
	public static ClientMenuState getInstance()
	{
		if (instance == null)
		{
			instance = new ClientMenuState();
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
		} while (true);
	}
	
	private void displayHelp()
	{
		System.out.println("Enter a number associated with a command seen below");
		System.out.println("---------------------");
		Option options[] = Option.values();
		
		for (Option opt : options)
		{
			System.out.println(opt.ordinal() + " - " + opt.getDescription());
		}
	}
	
	public void showClientDetails()
	{
		String clientId = WarehouseContext.getInstance().getClientId();
		Client client = warehouse.getClient(clientId);
		
		System.out.println(client);
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
	
	public void showClientTransactions()
	{
		String clientId = WarehouseContext.getInstance().getClientId();
		Client client = warehouse.getClient(clientId);
		
		Iterator invoiceList = warehouse.getClientTransactions(clientId);
		if (invoiceList == null || client == null)
		{
			return;
		}
		
		System.out.println("Transactions for client " + client.getName() + " (" + clientId + "): ");
		while (invoiceList.hasNext())
		{
			Invoice invoice = (Invoice) invoiceList.next();
			System.out.println("\t" + invoice);
		}
	}
	
	public void updateProductInCart()
	{
		//TODO: possibly remove, and simply make 'addProductToCart()' method
		//automatically add quantity to product if it's already in cart?
		//otherwise, if there are multiple items with the same ID in the cart...
		String clientId = WarehouseContext.getInstance().getClientId();
		Client client = warehouse.getClient(clientId);
		
		if (client != null)
		{
			String productId = UserInput.getToken("Enter ID of product to update in cart");
			String quantityStr = UserInput.getToken("Enter new quantity of product " + productId + " in cart");
			int quantity = Integer.parseInt(quantityStr);
			
			boolean result = warehouse.updateProductInCart(clientId, productId, quantity);
			if (result)
			{
				System.out.println("Successfully set product " + productId + " to quantity " + quantity + " in for client " + clientId);
			}
			else
			{
				System.out.println("Encountered error trying to update product in cart!");
			}
		}
		else
		{
			System.out.println("Error! Unable to find client with id " + clientId);
		}
	}
	
	public void addToCart()
	{
		String clientId = WarehouseContext.getInstance().getClientId();
		Client client = warehouse.getClient(clientId);
		//TODO: How to prevent UserInterface from modifying client???
		//Maybe instead use a method that returns true if client found, or false if not?
		if (client != null)
		{
			String productId = UserInput.getToken("Enter ID of product to add to cart");
			String quantityStr = UserInput.getToken("Enter quantity of product " + productId + " to add to cart");
			int quantity = Integer.parseInt(quantityStr);
			
			boolean result = warehouse.addToCart(clientId, productId, quantity);
			if (result)
			{
				System.out.println("Successfully added product " + productId + " ("+ quantity + ") to cart for client " + clientId);
			}
			else
			{
				System.out.println("Encountered error trying to add product!");
			}
		}
		else
		{
			System.out.println("Error! Unable to find client with id " + clientId);
		}
	}
	
	public void processOrder()
	{
		String clientId = WarehouseContext.getInstance().getClientId();
		
		boolean result = warehouse.processOrder(clientId);
		if (result)
		{
			System.out.println("Order processed successfully!");
		}
		else
		{
			System.out.println("Failed to process client order!");
		}
	}
	
	public void showClientCart()
	{
		String clientId = WarehouseContext.getInstance().getClientId();
		
		Iterator cart = warehouse.getClientCart(clientId);
		System.out.println("Cart for client " + clientId);
		while (cart.hasNext())
		{
			LineItem item = (LineItem) cart.next();
			System.out.println("\t" + item);
		}
	}
	
	public void showWaitlist()
	{
		//TODO
		System.out.println("Unimplemented");
	}
	
	public void logout()
	{
		int wLogin = WarehouseContext.getInstance().getUserType();
		if (wLogin == WarehouseContext.IsClerk || wLogin == WarehouseContext.IsManager)
		{
			//switch to clerk if actor logged in as clerk OR as manager
			//(manager because it can only get to client by first switching to clerk)
			WarehouseContext.getInstance().changeState(1); //switch to clerk state
		}
		else if (wLogin == WarehouseContext.IsClient)
		{
			WarehouseContext.getInstance().changeState(0); //switch to login state
		}
		else
		{
			//note this could occur as an error if manager somehow entered client
			//without going through clerk first
			WarehouseContext.getInstance().changeState(3); //switch to error state
		}
	}
	
	public void process()
	{
		Option command;
		displayHelp();
		do
		{
			command = getCommand();
			switch (command)
			{
				case SHOW_CLIENT_DETAILS:
					showClientDetails();
					break;
				case SHOW_PRODUCTS:
					showProducts();
					break;
				case SHOW_CLIENT_TRANSACTIONS:
					showClientTransactions();
					break;
				case UPDATE_PRODUCT_IN_CART:
					updateProductInCart();
					break;
				case ADD_TO_CART:
					addToCart();
					break;
				case PROCESS_ORDER:
					processOrder();
					break;
				case SHOW_CLIENT_CART:
					showClientCart();
					break;
				case SHOW_CLIENT_WAITLIST:
					showWaitlist();
					break;
				case HELP:
					displayHelp();
					break;
			}
		} while (command != Option.EXIT);
		logout();
	}
	
	public void run()
	{
		process();
	}
}
