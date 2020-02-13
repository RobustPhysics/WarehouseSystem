import java.util.*;
import java.io.*;

// Chris Leach
// Add, Serch
public class ProductList implements Serializable
{
	private static final long serialVersionUID = 1L;
	private static ProductList productList;
	List<Product> products = new LinkedList<Product>();                 // List of all products
	
	private ProductList()
	{
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
	
	public boolean insertProduct(Product p)
	{
		return products.add(p);
	}
}