public class ProductList
{
	private static ProductList singleton;
	List<Product> products;
	
	private ProductList()
	{
	}
	
	public static ProductList getInstance()
	{
		return singleton;
	}
	
	public Iterator GetProducts()
	{
		return products.iterator();
	}
}