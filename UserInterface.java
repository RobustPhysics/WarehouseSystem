//Clare Miller

import java.util.*;
import java.text.*;
import java.io.*;

public class UserInterface
{
	private static UserInterface userInterface;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static Warehouse warehouse;
	
	/*
	private enum Option
	{
		
		ADD_CLIENT("Adds client to system"),
		ADD_PRODUCT("Adds product to system"),
		ADD_SUPPLIER("Adds supplier to system"),
		ADD_SUPPLIED_PRODUCT("Adds supplied-product to product/supplier"),
		ADD_TO_CART("Adds a specified product to a clients shopping cart"),
		SHIP_PRODUCT("Ships a specified product from a specific supplier to the warehouse"),
		REMOVE_FROM_CART("Removes a specified product from a specified clients shopping cart"),
		UPDATE_PRODUCT_IN_CART("Updates the quantity of a specified product in a specified clients shopping cart"),
		PROCESS_ORDER("When client shopping cart is full, use this to process their order"),
		
		SHOW_CLIENT_TRANSACTIONS("Displays a list of transactions for specified client"),
		GET_PRODUCT_INFO("Shows information on a specified product and whom supplies it"),
		GET_SUPPLIER_INFO("Shows a list of all products supplied by the specified supplier"),
		SHOW_OUTSTANDING_CLIENTS("Shows a list of all clients with an outstanding balance due"),
		SHOW_WAIT_LIST_PRODUCTS("Shows a list of every product and the waitlists for that product."),
		SHOW_CLIENT_CART("Shows all line items in a specified clients cart"),
		SHOW_CLIENTS("Shows clients in database"),
		SHOW_PRODUCTS("Shows products in database"),
		SHOW_SUPPLIERS("Shows suppliers in database"),
		
		SAVE("Saves data to file"),
		RETRIEVE("Loads data from file"),
		HELP("Displays the help menu"),
		EXIT("Exits the program");
		
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
	*/
	
	private enum ClientOption
	{
		// Show client details. The state invokes a method on Facade to get the Client object and then gets the client details. Note that the ClientID is available in the Context.
		SHOW_CLIENT_DETAILS("Show client details."), //TODO
		// Show list of products with sale prices. The state invokes a method on Facade to get an iterator, and then extracts the needed information.
		SHOW_PRODUCTS("Shows products in database"),
		// Show client transactions. The state invokes a method on Facade to get the Client object and then gets the transaction details for the client. Note that the ClientID is available in the Context.
		SHOW_CLIENT_TRANSACTIONS("Displays a list of transactions for specified client"),
		// Edit client’s shopping cart. Change quantities of products in the shopping cart. Facade provides the iterator.
		UPDATE_PRODUCT_IN_CART("Updates the quantity of a specified product in a specified clients shopping cart"),
		// Add to client’s shopping cart. Actor provides the product id and quantity; invoke method on Facade.
		ADD_TO_CART("Adds a specified product to a clients shopping cart"),
		// Display client’s waitlist.
		SHOW_CLIENT_CART("Shows all line items in a specified clients cart"),
		// Logout. System transitions to the previous state, which has to be remembered in the context. (If previous state was the OpeningState, it goes there; otherwise it goes to ClerkMenuState.)
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
	
	private enum ClerkOption
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
		
		// Display the waitlist for a product. The state asks the actor for productid; calls method on Façade to get an iterator.
		SHOW_WAIT_LIST_PRODUCTS("Shows a list of every product and the waitlists for that product."),
		// Receive a shipment. The state asks the actor for productid and quantity; calls method on Façade to get an iterator. Displays each waitlisted order and performs operation requested by actor (skip or fill).
		
		// Record a payment from a client. State asks the actor for ID and amount; calls method on Façade to credit the amount to the client’s account.
		
		// Logout. System transitions to the previous state, which has to be remembered in the context. (If previous state was the OpeningState, it goes there; otherwise it goes to ManagerMenuState.)
		
		// private String description;
		
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
	
	private enum ManagerOption
	{
		//Add a product
		ADD_PRODUCT("Adds product to system"),
		//Add a supplier
		ADD_SUPPLIER("Adds supplier to system"),
		//Show list of suppliers
		SHOW_SUPPLIERS("Shows suppliers in database"),
		//Show list of suppliers for a product, with purchase prices
		//Show list of products for a supplier, with purchase prices
		//Add a supplier for a product. Actor provides productID, supplierID and purchase price
		//Modify purchase price for a particular product from a particular supplier. Actor provides productID, supplierID and purchase price
		//Become a salesclerk
		//Logout.

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
	
	private enum PersonOption
	{
		CLIENT("Client"),
		CLERK("Clerk"),
		MANAGER("Manager"),
		HELP("Displays the help menu"),
		EXIT("Exits program");
		
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
	
	private UserInterface()
	{
		boolean option = promptYesNo("Do you wish to load user data?");
		if (option)
		{
			retrieve();
		}
		else
		{
			warehouse = warehouse.getInstance();
		}
	}
	
	public static UserInterface getInstance()
	{
		if (userInterface == null)
		{
			userInterface = new UserInterface();
			return userInterface;
		}
		else
		{
			return userInterface;
		}
	}
	
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
	
	public Option getCommand()
	{
		do
		{
			try
			{
				String token = getToken("Enter a command. Use " + Option.HELP.ordinal() + " to display the menu.");
				int value = Integer.parseInt(token);
				if (value >= 0 && value <= Option.LENGTH)
				{
					System.out.println("IN THE getCommand() METHOD");
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
	
	public PersonOption getPersonCommand()
	{
		do
		{
			try
			{
				String token = getToken("Enter a command. Use " + PersonOption.HELP.ordinal() + " to display the menu.");
				int value = Integer.parseInt(token);
				if (value >= 0 && value <= PersonOption.LENGTH)
				{
					System.out.println("IN THE getCommand() METHOD");
					return PersonOption.values()[value];
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
	
	
	// displays menu
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
		String name = getToken("Enter client name");
		Client client;
		client = warehouse.addClient(name);
		if (client == null)
		{
			System.out.println("Error! Failed to add client to warehouse!");
		}
		System.out.println("Client: " + client);
	}
	
	public void shipProduct()
	{
		String productId = getToken("Enter ID of product to be shipped");
		String supplierId = getToken("Enter ID of supplier to ship product");
		
		//TODO: Should this be an int with error codes? i.e. 0 = can ship, 1 = product not found, 2 = supplier not found, etc
		boolean canShip = warehouse.canShipProduct(supplierId, productId);
		
		if (canShip)
		{
			String quantityStr = getToken("How many products should be shipped to warehouse?");
			int quantity = Integer.parseInt(quantityStr);
			warehouse.shipProduct(supplierId, productId, quantity);
		}
		else
		{
			System.out.println("Unable to ship product " + productId + " from supplier " + supplierId + "!");
		}
	}
	
	public void addProduct()
	{
		String name = getToken("Enter name of product");
		String priceStr = getToken("Enter product price");
		//String quantityStr = getToken("Enter quantity of product");
		
		double price = Double.parseDouble(priceStr);
		//int quantity = Integer.parseInt(quantityStr);
		
		Product product;
		product = warehouse.addProduct(name, price, 0);
		if (product == null)
		{
			System.out.println("Error! Failed to add product to warehouse!");
		}
		System.out.println("Product: " + product);
	}
	
	public void addSupplier()
	{
		String name = getToken("Enter name of supplier");
		String address = getToken("Enter address of supplier");
		Supplier supplier;
		supplier = warehouse.addSupplier(name, address);
		if (supplier == null)
		{
			System.out.println("Error! Failed to add supplier to warehouse!");
		}
		System.out.println("Supplier: " + supplier);
	}
	
	public void addToCart()
	{
		String clientId = getToken("Enter client ID whose cart will be added to");
		Client client = warehouse.getClient(clientId);
		//TODO: How to prevent UserInterface from modifying client???
		//Maybe instead use a method that returns true if client found, or false if not?
		if (client != null)
		{
			String productId = getToken("Enter ID of product to add to cart");
			String quantityStr = getToken("Enter quantity of product " + productId + " to add to cart");
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
	
	public void removeFromCart()
	{
		String clientId = getToken("Enter client ID whose cart will be added to");
		Client client = warehouse.getClient(clientId);
		
		if (client != null)
		{
			String productId = getToken("Enter ID of product to remove from cart");
			
			boolean result = warehouse.removeFromCart(clientId, productId);
			if (result)
			{
				System.out.println("Successfully removed product " + productId + " from cart for client " + clientId);
			}
			else
			{
				System.out.println("Encountered an error removing product from cart.");
			}
			
		}
		else
		{
			System.out.println("Error! Unable to find client with id " + clientId);
		}
	}
	
	public void updateProductInCart()
	{
		//TODO: possibly remove, and simply make 'addProductToCart()' method
		//automatically add quantity to product if it's already in cart?
		//otherwise, if there are multiple items with the same ID in the cart...
		String clientId = getToken("Enter client ID whose cart will be added to");
		Client client = warehouse.getClient(clientId);
		
		if (client != null)
		{
			String productId = getToken("Enter ID of product to update in cart");
			String quantityStr = getToken("Enter new quantity of product " + productId + " in cart");
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
	
	public void processOrder()
	{
		String clientId = getToken("Enter client ID whose cart will be added to");
		
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
	
	public void addSuppliedProduct()
	{
		String supplierId = getToken("Enter supplier ID");
		String productId = getToken("Enter product ID");
		String priceStr = getToken("Enter purchase price for product");
		
		double price = Double.parseDouble(priceStr);
		
		boolean result = warehouse.addSuppliedProduct(productId, supplierId, price);
		if (result)
		{
			System.out.println("Supplier-Product relationship added for supplier " + supplierId + " and product " + productId);
		}
		else
		{
			System.out.println("Failed to add supplier-product relationship");
		}
	}
	
	public void showClientTransactions()
	{
		String id = getToken("Enter client ID to view their transactions (invoices)");
		Client client = warehouse.getClient(id);
		
		Iterator invoiceList = warehouse.getClientTransactions(id);
		if (invoiceList == null || client == null)
		{
			return;
		}
		
		System.out.println("Transactions for client " + client.getName() + " (" + id + "): ");
		while (invoiceList.hasNext())
		{
			Invoice invoice = (Invoice) invoiceList.next();
			System.out.println("\t" + invoice);
		}
	}
	
	public void getProductInfo()
	{
		//TODO: Perhaps this should only display waitlist for product? Or waitlist AND suppliers?
		String id = getToken("Enter product ID to view a list of suppliers that supply this product");
		Product product = warehouse.getProduct(id);
		//TODO: Perhaps a warehouse method to get an Iterator for each SuppliedProduct instead of the product itself?
		Iterator suppliedProducts = warehouse.getSuppliedProductsFromProduct(id);
		
		System.out.println("Product " + product.getProductName() + " (" + id + ") is supplied by...");
		while (suppliedProducts.hasNext())
		{
			SuppliedProduct sp = (SuppliedProduct) suppliedProducts.next();
			Supplier supplier = sp.getSupplier();
			System.out.println("\t" + supplier);
		}
	}
	
	public void getSupplierInfo()
	{
		String id = getToken("Enter supplier ID to view a list of suppliers that supply this product");
		Supplier supplier = warehouse.getSupplier(id);
		//TODO: Perhaps a warehouse method to get an Iterator for each SuppliedProduct instead of the supplier itself?
		Iterator suppliedProducts = warehouse.getSuppliedProductsFromSupplier(id);
		
		System.out.println("Supplier " + supplier.getName() + " (" + id + ") supplies...");
		while (suppliedProducts.hasNext())
		{
			SuppliedProduct sp = (SuppliedProduct) suppliedProducts.next();
			Product product = sp.getProduct();
			System.out.println("\t" + product);
		}
	}
	
	public void showOutstandingClients()
	{
		Iterator outstandingClients = warehouse.getOutstandingClients();
		
		System.out.println("The following clients have an outstanding balance.");
		while (outstandingClients.hasNext())
		{
			Client client = (Client) outstandingClients.next();
			System.out.println("\t" + client);
			System.out.println("\t\tAmount due: " + client.getAmountDue());
		}
	}
	
	public void showWaitlistProducts()
	{
		Iterator products = warehouse.getProducts();
		int productIndex = 1;
		while (products.hasNext())
		{
			Product p = (Product) products.next();
			Iterator waitlist = p.getWaitlist();
			int itemIndex = 1;
			//TODO: Should we get waitlist directly from product p?
			//Or have warehouse get waitlist from product ID?
			//Iterator waitlist = warehouse.getProductWaitlist(p.getProductID());
			
			System.out.println(productIndex + ". The following is the waitlist for Product " + p.getProductName());
			while (waitlist.hasNext())
			{
				WaitlistItem item = (WaitlistItem) waitlist.next();
				System.out.println("\t" + itemIndex + "." + item);
				itemIndex++;
			}
			
			productIndex++;
		}
	}
	
	public void showClientCart()
	{
		String clientId = getToken("Enter client ID whose cart will be added to");
		
		Iterator cart = warehouse.getClientCart(clientId);
		System.out.println("Cart for client " + clientId);
		while (cart.hasNext())
		{
			LineItem item = (LineItem) cart.next();
			System.out.println("\t" + item);
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
	
	public void showProducts()
	{
		Iterator products = warehouse.getProducts();
		while (products.hasNext())
		{
			Product product = (Product) (products.next());
			System.out.println(product);
		}
		
	}
	
	public void showSuppliers()
	{
		Iterator suppliers = warehouse.getSuppliers();
		while (suppliers.hasNext())
		{
			Supplier supplier = (Supplier) (suppliers.next());
			System.out.println(supplier);
		}
	}
	
	private void save()
	{
		if (warehouse.save())
		{
			System.out.println("The warehouse data has been saved to the file WarehouseData.\n");
		}
		else
		{
			System.out.println("Failed to save warehouse data.");
		}
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
	/*
	Barrett Changes: nest another 3 switch statements which will determine
	*/
	public void process()
	{
		PersonOption command1;
		Option command2;
		displayHelp();
		do
		{
			command1 = getPersonCommand();
			switch (command1)
			{
				case CLIENT:
				{
					switch (command2)
					{
							/* Show client details. The state invokes a method 
							on Facade to get the Client object and then gets the
							client details. Note that the ClientID is available 
							in the Context. */
							case SHOW_CLIENT_DETAILS:
							{
								System.out.println("showing client details");
								break;
							}
							
							/* Show list of products with sale prices. The state
							invokes a method on Facade to get an iterator, and 
							then extracts the needed information. */
							case SHOW_PRODUCTS:
							{
								System.out.println("showing list of products with sales prices");
								break;
							}
							// Show client transactions. The state invokes a method on Facade to get the Client object and then gets the transaction details for the client. Note that the ClientID is available in the Context.
							SHOW_CLIENT_TRANSACTIONS("Displays a list of transactions for specified client"),
							// Edit client’s shopping cart. Change quantities of products in the shopping cart. Facade provides the iterator.
							UPDATE_PRODUCT_IN_CART("Updates the quantity of a specified product in a specified clients shopping cart"),
							// Add to client’s shopping cart. Actor provides the product id and quantity; invoke method on Facade.
							ADD_TO_CART("Adds a specified product to a clients shopping cart"),
							// Display client’s waitlist.
							SHOW_CLIENT_CART("Shows all line items in a specified clients cart"),
							// Logout. System transitions to the previous state, which has to be remembered in the context. (If previous state was the OpeningState, it goes there; otherwise it goes to ClerkMenuState.)
							EXIT("Exits the program"); //TODO
						case ADD_CLIENT:
							addClient();
							break;
						case ADD_PRODUCT:
							addProduct();
							break;
						case ADD_SUPPLIER:
							addSupplier();
							break;
						case ADD_SUPPLIED_PRODUCT:
							addSuppliedProduct();
							break;
						case ADD_TO_CART:
							addToCart();
							break;
						case PROCESS_ORDER:
							processOrder();
							break;
						case GET_PRODUCT_INFO:
							getProductInfo();
							break;
						case GET_SUPPLIER_INFO:
							getSupplierInfo();
							break;
						case SHOW_OUTSTANDING_CLIENTS:
							showOutstandingClients();
							break;
						case SHOW_WAIT_LIST_PRODUCTS:
							showWaitlistProducts();
							break;
						case SHOW_CLIENT_CART:
							showClientCart();
							break;
						case SHIP_PRODUCT:
							shipProduct();
							break;
						case REMOVE_FROM_CART:
							removeFromCart();
							break;
						case UPDATE_PRODUCT_IN_CART:
							updateProductInCart();
							break;
						case SHOW_CLIENT_TRANSACTIONS:
							showClientTransactions();
							break;
						case SHOW_CLIENTS:
							showClients();
							break;
						case SHOW_PRODUCTS:
							showProducts();
							break;
						case SHOW_SUPPLIERS:
							showSuppliers();
							break;
						case SAVE:
							save();
							break;
						case RETRIEVE:
							retrieve();
							break;
						case HELP:
							displayHelp();
							break;
					}
					break;
				}
				
				case CLERK:
				{
					switch (command2)
					{
						case ADD_CLIENT:
							addClient();
							break;
						case ADD_PRODUCT:
							addProduct();
							break;
						case ADD_SUPPLIER:
							addSupplier();
							break;
						case ADD_SUPPLIED_PRODUCT:
							addSuppliedProduct();
							break;
						case ADD_TO_CART:
							addToCart();
							break;
						case PROCESS_ORDER:
							processOrder();
							break;
						case GET_PRODUCT_INFO:
							getProductInfo();
							break;
						case GET_SUPPLIER_INFO:
							getSupplierInfo();
							break;
						case SHOW_OUTSTANDING_CLIENTS:
							showOutstandingClients();
							break;
						case SHOW_WAIT_LIST_PRODUCTS:
							showWaitlistProducts();
							break;
						case SHOW_CLIENT_CART:
							showClientCart();
							break;
						case SHIP_PRODUCT:
							shipProduct();
							break;
						case REMOVE_FROM_CART:
							removeFromCart();
							break;
						case UPDATE_PRODUCT_IN_CART:
							updateProductInCart();
							break;
						case SHOW_CLIENT_TRANSACTIONS:
							showClientTransactions();
							break;
						case SHOW_CLIENTS:
							showClients();
							break;
						case SHOW_PRODUCTS:
							showProducts();
							break;
						case SHOW_SUPPLIERS:
							showSuppliers();
							break;
						case SAVE:
							save();
							break;
						case RETRIEVE:
							retrieve();
							break;
						case HELP:
							displayHelp();
							break;
					}
					break;
				}
				case MANAGER:
				{
					switch (command2)
					{
						case ADD_CLIENT:
							addClient();
							break;
						case ADD_PRODUCT:
							addProduct();
							break;
						case ADD_SUPPLIER:
							addSupplier();
							break;
						case ADD_SUPPLIED_PRODUCT:
							addSuppliedProduct();
							break;
						case ADD_TO_CART:
							addToCart();
							break;
						case PROCESS_ORDER:
							processOrder();
							break;
						case GET_PRODUCT_INFO:
							getProductInfo();
							break;
						case GET_SUPPLIER_INFO:
							getSupplierInfo();
							break;
						case SHOW_OUTSTANDING_CLIENTS:
							showOutstandingClients();
							break;
						case SHOW_WAIT_LIST_PRODUCTS:
							showWaitlistProducts();
							break;
						case SHOW_CLIENT_CART:
							showClientCart();
							break;
						case SHIP_PRODUCT:
							shipProduct();
							break;
						case REMOVE_FROM_CART:
							removeFromCart();
							break;
						case UPDATE_PRODUCT_IN_CART:
							updateProductInCart();
							break;
						case SHOW_CLIENT_TRANSACTIONS:
							showClientTransactions();
							break;
						case SHOW_CLIENTS:
							showClients();
							break;
						case SHOW_PRODUCTS:
							showProducts();
							break;
						case SHOW_SUPPLIERS:
							showSuppliers();
							break;
						case SAVE:
							save();
							break;
						case RETRIEVE:
							retrieve();
							break;
						case HELP:
							displayHelp();
							break;
					}
					break;
				}
			}
				
				
			}
		} while (command1 != Option.EXIT);
	}
	
	public static void main(String[] s)
	{
		UserInterface.getInstance().process();
	}
}