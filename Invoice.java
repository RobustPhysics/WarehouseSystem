//Chris

import java.io.*;

public class Invoice implements Serializable {
	//Contains information on date, product, quantity purchased, description of purchase, and a cost

	private String date;
	private Product product;
	private String description;
	private double totalCost;

	public void setDate(String d) {
		date = d;
	}
	public void setProducts(Product p) {
		product = p;
	}
	
	public void setDescription(String d) {
		description = d;
	}
	
	public void setTotalCost(double c) {
		totalCost = c;
	}
	
	public String getDate() {
		return date;
	}

	public Product getProducts() {
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
		return "";
	}
}
