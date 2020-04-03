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
	
}