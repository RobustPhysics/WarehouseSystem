//Chris

import java.io.*;

public class Invoice implements Serializable {
	//Contains information on date, product, quantity purchased, description of purchase, and a cost

	private String date;
	private Product product;
	private String description;
	private double totalCost;
	
	public Invoice(String d, Product p, String desc, double cost)
	{
		date = d;
		product = p;
		description = desc;
		totalCost = cost;
	}
	
	public String getDate() {
		return date;
	}

	public Product getProduct() {
		return product;
	}

	public String getDescription() {
		return description;
	}

	public double getTotalCost() {
		return totalCost;
	}
	
	public String toString()
	{
		String str = date + "\t" + product + "\t" + description + "\t$" + totalCost;
		return str;
	}
}
