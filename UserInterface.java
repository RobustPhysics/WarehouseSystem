//Clare Miller

import java.util.*;
import java.text.*;
import java.io.*;

public class UserInterface
{
	private static UserInterface userInterface;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static Warehouse warehouse;
	
	private enum Option
	{
		
		ADD_CLIENT("Adds client to system"),
		ADD_PRODUCT("Adds product to system"),
		ADD_SUPPLIER("Adds supplier to system"),
		ADD_TO_CART("Adds a specified product to a clients shopping cart"),
		REMOVE_FROM_CART("Unimplemented"),
		UPDATE_PRODUCT_IN_CART("Unimplemented"),
		PROCESS_ORDER("When client shopping cart is full, use this to process their order"),
		
		SHOW_CLIENT_TRANSACTIONS("Unimplemented"),
		GET_PRODUCT_INFO("Shows information on a specified product and whom supplies it"),
		GET_SUPPLIER_INFO("Shows a list of all products supplied by the specified supplier"),
		SHOW_OUTSTANDING_CLIENTS("Shows a list of all clients with an outstanding balance due"),
		SHOW_WAIT_LIST_PRODUCTS("Shows a list of every product and the waitlists for that product."),
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
	
	public void addProduct()
	{
		String name = getToken("Enter name of product");
		String priceStr = getToken("Enter product price");
		String quantityStr = getToken("Enter quantity of product");
		
		double price = Double.parseDouble(priceStr);
		int quantity = Integer.parseInt(quantityStr);
		
		Product product;
		product = warehouse.addProduct(name, price, quantity);
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
		//NOTE: How to prevent UserInterface from modifying client???
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
		System.out.println("Dummy action.");
		/*
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
		*/
	}
	
	public void updateProductInCart()
	{
		System.out.println("Dummy action.");
		/*
		//NOTE: possibly remove, and simply make 'addProductToCart()' method
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
		*/
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
		//NOTE: Method not currently in business process
		System.out.println("Dummy action.");
	}
	
	public void showClientTransactions()
	{
		System.out.println("Dummy action.");
	}
	
	public void getProductInfo()
	{
		//NOTE: Perhaps this should only display waitlist for product? Or waitlist AND suppliers?
		String id = getToken("Enter product ID to view a list of suppliers that supply this product");
		Product product = warehouse.getProduct(id);
		//NOTE: Perhaps a warehouse method to get an Iterator for each SuppliedProduct instead of the product itself?
		Iterator suppliedProducts = product.getSuppliedProducts();
		
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
		//NOTE: Perhaps a warehouse method to get an Iterator for each SuppliedProduct instead of the supplier itself?
		Iterator suppliedProducts = supplier.getSuppliedProducts();
		
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
			if (client != null)
			{
				System.out.println("\t" + client);
				System.out.println("\t\tAmount due: " + client.getAmountDue());
			}
			else
			{
				//What to do if it's null??
			}
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
			//NOTE: Should we get waitlist directly from product p?
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
				case ADD_PRODUCT:
					addProduct();
					break;
				case ADD_SUPPLIER:
					addSupplier();
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
				///////
				///////
				case REMOVE_FROM_CART:
					removeFromCart();
					break;
				case UPDATE_PRODUCT_IN_CART:
					updateProductInCart();
					break;
				case SHOW_CLIENT_TRANSACTIONS:
					showClientTransactions();
					break;
				///////
				///////
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
		} while (command != Option.EXIT);
	}
	
	public static void main(String[] s)
	{
		UserInterface.getInstance().process();
	}
}