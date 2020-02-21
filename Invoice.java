//???

public class Invoice {
	//Contains information on date, product, quantity purchased, description of purchase, and a cost

	private String date;
	private ProductList products;
	private String description;
	private double totalCost;

	public void setDate(String d) {
		date = d;
	}
	public void setProducts(ProductList p) {
		products = p;
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

	public ProductList getProducts() {
		return products;
	}

	public String getDescription() {
		return description;
	}

	public double getTotalCost() {
		return totalCost;
	}
}
