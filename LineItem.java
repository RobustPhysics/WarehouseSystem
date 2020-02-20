//Clare

import java.util.*;
import java.io.*;

public class LineItem implements Serializable {
	//Purpose of class - Stores unique data for items in a
	//clients cart. 
	
	private static final long serialVersionUID = 1L;
	private String productId;
	private double productPrice;
	private int productQuantity;
	
	public LineItem(String id, double price, int quantity)
	{
		productId = id;
		productPrice = price;
		productQuantity = quantity;
	}
	
	public String getProductId()
	{
		return productId;
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
}
