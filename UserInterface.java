//Clare Miller

import java.util.*;
import java.text.*;
import java.io.*;

public class UserInterface
{
	private static UserInterface userInterface;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static Warehouse warehouse;
	
	private enum Options
	{
		
		ADD_CLIENT("Adds client to system"),
		ADD_PRODUCT("Adds product to system"),
		ADD_SUPPLIER("Adds supplier to system"),
		ADD_TO_CART("Unimplemented"),
		REMOVE_FROM_CART("Unimplemented"),
		UPDATE_PRODUCT_IN_CART("Unimplemented"),
		PROCESS_ORDER("Unimplemented"),
		
		SHOW_CLIENT_TRANSACTIONS("Unimplemented"),
		GET_PRODUCT_INFO("Unimplemented"),
		GET_SUPPLIER_INFO("Unimplemented"),
		SHOW_OUTSTANDING_CLIENTS("Unimplemented"),
		SHOW_WAIT_LIST_PRODUCTS("Unimplemented"),
		SHOW_CLIENTS("Shows clients in database"),
		SHOW_PRODUCTS("Shows products in database"),
		SHOW_SUPPLIERS("Shows suppliers in database"),
		
		SAVE("Saves data to file"),
		RETRIEVE("Loads data from file"),
		HELP("Displays the help menu"),
		EXIT("Exits the program");
		
		private String description;
		private static int LENGTH = Options.values().length;
		
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
		prompt += " (Y/y) for YES, anything else for NO.");
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
		} while (true)
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
		String id = getToken("Enter client ID");
		Client client;
		client = warehouse.addMember(id, name);
		if (client == null)
		{
			System.out.println("Error! Failed to add client to warehouse!");
		}
		System.out.println("Client: " + client);
	}
	
	public void addProduct()
	{
		String name = getToken("Enter name of product");
		String id = getToken("Enter product ID");
		String priceStr = getToken("Enter product price");
		String quantityStr = getToken("Enter quantity of product");
		
		double price = Double.parseDouble(priceStr);
		int quantity = Integer.parseInt(quantityStr);
		
		Product product;
		product = warehouse.addProduct(id, name, price);
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
		System.out.println("Dummy action.");
	}
	
	public void removeFromCart()
	{
		System.out.println("Dummy action.");
	}
	
	public void updateProductInCart()
	{
		System.out.println("Dummy action.");
	}
	
	public void processOrder()
	{
		System.out.println("Dummy action.");
	}
	
	public void showClientTransactions()
	{
		System.out.println("Dummy action.");
	}
	
	public void getProductInfo()
	{
		System.out.println("Dummy action.");
	}
	
	public void getSupplierInfo()
	{
		System.out.println("Dummy action.");
	}
	
	public void showOutstandingClients()
	{
		System.out.println("Dummy action.");
	}
	
	public void showWaitListProducts()
	{
		System.out.println("Dummy action.");
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
			Supplier supplier = (Supplier) (supplier.next());
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
				warehouse = Warehouse.instance();
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
				///////
				///////
				case ADD_TO_CART:
					addToCart();
					break;
				case REMOVE_FROM_CART:
					removeFromCart();
					break;
				case UPDATE_PRODUCT_IN_CART:
					updateProductInCart();
					break;
				case PROCESS_ORDER:
					processOrder();
					break;
				case SHOW_CLIENT_TRANSACTIONS:
					showClientTransactions();
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
					showWaitListProducts();
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
				case HELP:
					displayHelp();
					break;
			}
		} while (command != Option.EXIT);
	}
	
	public static void main(String[] s)
	{
		UserInterface.instance().process();
	}
}