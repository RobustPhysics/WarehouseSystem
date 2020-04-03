//Entire Group

import java.io.*;

class SuppliedProduct implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Product product;
	private Supplier supplier;
	private double purchasedPrice;
	
	public SuppliedProduct(Product p, Supplier s, double price)
	{
		product = p;
		supplier = s;
		purchasedPrice = price;
	}
	
	public Product getProduct()
	{
		return product;
	}
	
	public Supplier getSupplier()
	{
		return supplier;
	}
	
	public double getPrice()
	{
		return purchasedPrice;
	}
	
	public void setPrice(double newPrice)
	{
		purchasedPrice = newPrice;
	}
	
	public String toString()
	{
		return  supplier.toString() +  "\n" + product.toString() + "\nPrice " + String.format("%.2f", purchasedPrice);
	}
}