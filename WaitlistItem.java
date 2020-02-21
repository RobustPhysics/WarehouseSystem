// Chris

public class WaitlistItem {
	//Stored in the product, contains information on
	//client and quantity. Maybe price too? And date added to list?

	private int quantity;
	private Client client;
	private double price;
	private String date;      // Date added to the list
	
	public void setQuantity(int q) {
		quantity = q;
	}

	public void setClient(Client c) {
		client = c;
	}

	public void setPrice(double p) {
		price = p;
	}

	public void setDate(String d) {
		date = d;
	}
	
	public WaitlistItem()
	{
		
	}
 
	
	public String toString()
	{
		return "";
	}
}
