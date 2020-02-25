//Clare

import java.util.*;
import java.io.*;

public class LineItem implements Serializable {
	//Purpose of class - Stores unique data for items in a
	//clients cart. 
	
	private static final long serialVersionUID = 1L;
	private Product product;
	private double productPrice;
	private int productQuantity;
	
	public LineItem(Product p, double price, int quantity)
	{
		product = p;
		productPrice = price;
		productQuantity = quantity;
	}
	
	public Product getProduct()
	{
		return product;
	}
	
	public double getProductPrice()
	{
		return productPrice;
	}
	
	public int getProductQuantity()
	{
		return productQuantity;
	}
	
	public void setProductQuantity(int quantity)
	{
		productQuantity = quantity;
	}
	
	public String toString()
	{
		double total = productPrice*productQuantity;
		String str = "LineItem: " + productQuantity + "x " + product.getProductID() + " at $" + productPrice + " each (total $" + total +")";
		return str;
	}
}
