import java.util.*;
import java.io.*;

// Chris Leach
// Add, Serch
public class ProductList implements Serializable
{
	private static ProductList productList;
	List<Product> products;                 // List of all products
	
	private ProductList()
	{
		products = null;                    // Initilizing an empty list for the products
	}
	
	public static ProductList getInstance()
	{
		if (productList == null)
		{
			productList = new ProductList();
			return productList;
		}
		else
		{
			return productList;
		}
	}
	
	public Iterator GetProducts()
	{
		return products.iterator();
	}

	public Product searchProduct(Product p) {
		if (products.contains(p)) {

		}
		return p;
	}
	
	// Returns true if the product is in the product list,
	// returns false if it is not in the list
	public boolean isInList(Product p) {
		if (products.contains(p) == true)
			return true;
		else
			return false;
	}

	// Adds one instance of a product into the system
	public void addProduct(Product p) {
		products.add(p);
		p.setQuantity(p.getQuantity() + 1);   // Increasing product quantity by one
	}

	// Adds multiple instances of a product into the system
	public void addProduct(Product p, int quantity) {
		products.add(p);
		p.setQuantity(p.getQuantity() + quantity); // Increase quantity
	}
}