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
		
		//Show list of products for a supplier, with purchase prices
		
		//Add a supplier for a product. Actor provides productID, supplierID and purchase price
		
		//Modify purchase price for a particular product from a particular supplier. Actor provides productID, supplierID and purchase price
		
		//Become a salesclerk
		
		//Logout.

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
	
	public void process()
	{
		ManagerOption command;
		displayHelp();
		
		do
		{
			command = getPersonCommand();
			switch (command)
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
				/* Show client details. The state invokes a method on Facade to get the Client object and then gets the client details. Note that the ClientID is available in the Context. */
			}
		} while (command != Option.EXIT);
	}
	
	
	
}