// Chris Leach
import java.util.*;
import java.io.*;

public class Product implements Serializable
{
	private static final long serialVersionUID = 1L;
    private String productID;       // Unique product ID
    private String productName;     // Name of product
    private double salePrice;       // Sale price of product
    private int productQuantity;    // Avaliable quantity of product
    private List<SuppliedProduct> suppliedProducts = new LinkedList<SuppliedProduct>();
    private List<WaitlistItem> waitlist = new LinkedList<WaitlistItem>();
    
    private static final String PRODUCT_STRING = "P";
    
    // Constructor
    public Product(String name, double price) {
    	productID = PRODUCT_STRING + (IdServer.instance()).getProductId();
        productName = name;
        salePrice = price;
        productQuantity = 0;
    }
    
    public boolean addToWaitList(Client client, double price, int quantity, String date)
    {
        WaitlistItem item = new WaitlistItem(client, price, quantity, date);
    	return waitlist.add(item);
    }
    
    public boolean removeFromWaitlist(WaitlistItem item)
    {
    	return waitlist.remove(item);
    }
    
    public void processWaitlist()
    {
    	List<WaitlistItem> itemsToRemove = new LinkedList<WaitlistItem>();
    	for (WaitlistItem item : waitlist)
    	{
    		if (productQuantity >= item.getQuantity())
    		{
    			LineItem item2 = new LineItem(this, item.getPrice(), item.getQuantity());
    			Client client = item.getClient();
    			boolean success = client.processItem(item2);
    			if (success)
    			{
    				itemsToRemove.add(item);
    			}
    			else
    			{
    				System.out.println("ERROR! Failed to process item " + item2 + " for client " + client);
    			}
    		}
    	}
    	
    	for (WaitlistItem item : itemsToRemove)
    	{
    		waitlist.remove(item);
    	}
    }
    
    public Iterator getWaitlist()
    {
    	return waitlist.iterator();
    }
    
    public boolean addSuppliedProduct(SuppliedProduct sp)
    {
    	return suppliedProducts.add(sp);
    }
    
    public Iterator getSuppliedProducts()
    {
      return suppliedProducts.iterator();
    }
    
    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public int getQuantity() {
        return productQuantity;
    }

    public void setProductID(String id) {
        productID = id;
    }

    public void setProductName(String name) {
        productName = name;
    }

    public void setSalePrice(double price) {
        salePrice = price;
    }

    public void setQuantity(int quantity) {
        productQuantity = quantity;
    }

    public String toString() {
        String string = "id " + productID + " name " + productName + "($" + salePrice + " x " + productQuantity + ")";
        return string;
      }
}