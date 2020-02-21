// Chris Leach
import java.util.*;
import java.io.*;

public class Product implements Serializable
{
	private static final long serialVersionUID = 1L;
    private String productID;          // Unique product ID
    private String productName;     // Name of product
    private double salePrice;       // Sale price of product
    private int productQuantity;    // Avaliable quantity of product
    private List<SuppliedProduct> suppliedProducts = new LinkedList<SuppliedProduct>();
    //private List<Waitlist> waitlist = new LinkedList<Waitlist>();
    
    private static final String PRODUCT_STRING = "M";
    
    // Constructor
    public Product(String name, double price) {
    	productID = PRODUCT_STRING + (IdServer.instance()).getProductId();
        productName = name;
        salePrice = price;
        productQuantity = 0;
    }
    
    public boolean addToWaitList(Client client, int quantity)
    {
    	return true;
    }
    
    public Iterator getWaitlist()
    {
    	return null;
    }
    
    public boolean addSuppliedProduct(SuppliedProduct sp)
    {
    	return true;
    }
    
    public Iterator getSuppliedProducts()
    {
  	  return null;
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