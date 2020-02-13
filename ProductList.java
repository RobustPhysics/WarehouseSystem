import java.util.*;
import java.io.*;

// Chris Leach
// Add, Serch
public class ProductList implements Serializable
{
	private static final long serialVersionUID = 1L;
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
	
	public Iterator getProducts()
	{
		return products.iterator();
	}

	public Product searchProduct(String id) {
		for (int i = 0; i < products.size(); i++)
		{
			Product p = products.get(i);
			if (p.getProductID() == id)
			{
				return p;
			}
		}
		return null;
	}
	
	// Returns true if the product is in the product list,
	// returns false if it is not in the list
	public boolean isInList(Product p) {
		if (products.contains(p) == true)
			return true;
		else
			return false;
	}
	
	public boolean insertProduct(Product p)
	{
		return products.add(p);
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