// ManagerMenuState.java

import java.util.*;
import java.text.*;
import java.io.*;

public class ManagerMenuState extends WarehouseState
{
	private static ManagerMenuState ManagerMenuState;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static Warehouse warehouse;

	private enum ManagerOption
	{
		//Add a product
		ADD_PRODUCT("Adds product to system"),
		//Add a supplier
		ADD_SUPPLIER("Adds supplier to system"),
		//Show list of suppliers
		SHOW_SUPPLIERS("Shows suppliers in database"),
		//Show list of suppliers for a product, with purchase prices
		GET_PRODUCT_INFO("Shows information on a specified product and whom supplies it"),
		//Show list of products for a supplier, with purchase prices
		GET_SUPPLIER_INFO("Shows a list of all products supplied by the specified supplier"),
		//Add a supplier for a product. Actor provides productID, supplierID and purchase price
		ADD_SUPPLIER("Adds supplier to system"),
		//Modify purchase price for a particular product from a particular supplier. Actor provides productID, supplierID and purchase price
		MODIFY_PRODUCT("Modifies purchase price for a particular product from a particular supplier"); //TODO
		//Become a salesclerk
		BECOME_SALES_CLERK("Become a salesclerk"); //TODO
		//HELP
		HELP("display the help menu")
		//Logout.
		LOGOUT("Logs out of the Manager state"); //TODO

		private String description;
		private static int LENGTH = ManagerOption.values().length;

		private ManagerOption(String str)
		{
			description = str;
		}

		public String getDescription()
		{
			return description;
		}
	}

	// displays menu
	public void displayManagerHelp()
	{
		System.out.println("Enter a number associated with a command seen below");
		System.out.println("---------------------");
		ManagerOption options[] = ManagerOption.values();

		for (ManagerOption opt : options)
		{
			System.out.println(opt.ordinal() + " - " + opt.getDescription());
		}
	}

	public ManagerOption getCommand()
	{
		do
		{
			try
			{
				String token = getToken("Enter a command. Use " + ManagerOption.HELP.ordinal() + " to display the menu.");
				int value = Integer.parseInt(token);
				if (value >= 0 && value <= ManagerOption.LENGTH)
				{
					System.out.println("IN THE getCommand() METHOD");
					return ManagerOption.values()[value];
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

	public void process()
	{
		ManagerOption command;
		displayManagerHelp();

		do
		{
			command = getCommand();
			switch (command)
			{

				//Add a product
				case ADD_PRODUCT:
				{
					System.out.println("Adding a product");
					break;
				}

				//Add a supplier
				case ADD_SUPPLIER:
				{
					System.out.println("Adding a supplier");
					break;
				}

				//Show list of suppliers
				case SHOW_SUPPLIERS:
				{
					System.out.println("showing a supplier");
					break;
				}

				//Show list of suppliers for a product, with purchase prices
				case GET_PRODUCT_INFO:
				{
					System.out.println("getting product's information");
					break;
				}

				//Show list of products for a supplier, with purchase prices
				case GET_SUPPLIER_INFO:
				{
					System.out.println("getting supplier information");
					break;
				}

				//Add a supplier for a product. Actor provides productID, supplierID and purchase price
				case ADD_SUPPLIER:
				{
					System.out.println("Adding a supplier for a product");
					break;
				}

				//Modify purchase price for a particular product from a particular supplier. Actor provides productID, supplierID and purchase price
				case MODIFY_PRODUCT:
				{
					System.out.println("modifying purchase price for a product");
					break;
				}

				//Become a salesclerk
				case BECOME_SALES_CLERK:
				{
					System.out.println("go from a manager to a salesclerk");
					break;
				}
				
				//Become a salesclerk
				case HELP:
				{
					System.out.println("help menu");
					displayManagerHelp();
					break;
				}

				//Logout.
				case LOGOUT:
				{
					System.out.println("logging out");
					break;
				}
			}

		} while (command != ManagerOption.EXIT);
	}



}