// ManagerMenuState.java

import java.util.*;
import java.text.*;
import java.io.*;

public class ManagerMenuState extends WarehouseState
{
	private static Warehouse warehouse;
	private static ManagerMenuState instance;

	private enum Option
	{
		//Add a product
		ADD_PRODUCT("Adds product to warehouse database"),
		//Add a supplier
		ADD_SUPPLIER("Adds supplier to system"),
		//Show list of suppliers
		SHOW_SUPPLIERS("Shows suppliers in database"),
		//Show list of suppliers for a product, with purchase prices
		GET_PRODUCT_INFO("Shows information on a specified product and whom supplies it"),
		//Show list of products for a supplier, with purchase prices
		GET_SUPPLIER_INFO("Shows a list of all products supplied by the specified supplier"),
		//Add a supplier for a product. Actor provides productID, supplierID and purchase price
		ADD_SUPPLIED_PRODUCT("Adds supplied-product to product/supplier"),
		//Modify purchase price for a particular product from a particular supplier. Actor provides productID, supplierID and purchase price
		MODIFY_SUPPLIED_PRODUCT("Modifies purchase price for a particular product from a particular supplier"), //TODO
		//Become a salesclerk
		BECOME_SALES_CLERK("Become a salesclerk"), //TODO
		//HELP
		HELP("display the help menu"),
		//Logout.
		LOGOUT("Logs out of the Manager state"); //TODO

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
	
	private ManagerMenuState()
	{
		warehouse = Warehouse.getInstance(); //get the facade
		//context = WarehouseContext.instance();
	}
	
	public static ManagerMenuState getInstance()
	{
		if (instance == null)
		{
			instance = new ManagerMenuState();
		}
		return instance;
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
		} while(true);
	}

	public void addProduct()
	{
		String name = UserInput.getToken("Enter name of product");
		String priceStr = UserInput.getToken("Enter product price");
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
		String name = UserInput.getToken("Enter name of supplier");
		String address = UserInput.getToken("Enter address of supplier");
		Supplier supplier;
		supplier = warehouse.addSupplier(name, address);
		if (supplier == null)
		{
			System.out.println("Error! Failed to add supplier to warehouse!");
		}
		System.out.println("Supplier: " + supplier);
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

	public void getProductInfo()
	{
		//TODO: Perhaps this should only display waitlist for product? Or waitlist AND suppliers?
		String id = UserInput.getToken("Enter product ID to view a list of suppliers that supply this product");
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
		String id = UserInput.getToken("Enter supplier ID to view a list of suppliers that supply this product");
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

	public void addSuppliedProduct()
	{
		String supplierId = UserInput.getToken("Enter supplier ID");
		String productId = UserInput.getToken("Enter product ID");
		String priceStr = UserInput.getToken("Enter purchase price for product");

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

	public void modifySuppliedProduct()
	{
		//TODO
		String supplierId = UserInput.getToken("Enter supplier ID");
		String productId = UserInput.getToken("Enter product ID");
		
		SuppliedProduct suppliedProduct = warehouse.getSuppliedProduct(supplierId, productId);
		if (suppliedProduct != null)
		{
			double currentPrice = suppliedProduct.getPrice();
			String priceStr = UserInput.getToken("Enter the new purchase price (current is " + currentPrice + ")");

			double newPrice = Double.parseDouble(priceStr);
			suppliedProduct.setPrice(newPrice);
		}
		else
		{
			System.out.println("Error! Supplier " + supplierId + " does not have product " + productId);
		}
	}

	public void clerkMenu()
	{
		//WarehouseContext.getInstance().setUserType(WarehouseContext.IsClerk);
		WarehouseContext.getInstance().changeState(1);
	}

	public void logout()
	{
		WarehouseContext.getInstance().changeState(0);
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
				case ADD_PRODUCT:
					addProduct();
					break;
				case ADD_SUPPLIER:
					addSupplier();
					break;
				case SHOW_SUPPLIERS:
					showSuppliers();
					break;
				case GET_PRODUCT_INFO:
					getProductInfo();
					break;
				case GET_SUPPLIER_INFO:
					getSupplierInfo();
					break;
				case ADD_SUPPLIED_PRODUCT:
					addSuppliedProduct();
					break;
				case MODIFY_SUPPLIED_PRODUCT:
					modifySuppliedProduct();
					break;
				case BECOME_SALES_CLERK:
					clerkMenu();
					break;
				case HELP:
					displayHelp();
					break;
				case LOGOUT:
					System.out.println("logging out");
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
