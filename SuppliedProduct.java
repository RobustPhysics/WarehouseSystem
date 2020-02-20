//Entire Group

import java.io.*;

class SuppliedProduct implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String productId;
	private String supplierId;
	private double purchasedPrice;
	
	public SuppliedProduct(String pId, String sId, double price)
	{
		productId = pId;
		supplierId = sId;
		purchasedPrice = price;
	}
	
	public String getProductId()
	{
		return productId;
	}
	
	public String getSupplierId()
	{
		return supplierId;
	}
	
	public double getPrice()
	{
		return purchasedPrice;
	}
}