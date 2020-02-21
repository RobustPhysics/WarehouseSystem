// Chris
import java.util.*;
import java.io.*;

public class WaitlistItem implements Serializable{
	// Stored in the product, contains information on
	// client and quantity. Maybe price too? And date added to list?
	private static final long serialVersionUID = 1L;
	private Client client;
	private Product product;
	private int quantity;
	private double price;
	private String date;      // Date added to the list
	
	public WaitlistItem(Client client, Product product, int quantity)
	{
		this.client = client;
		this.product = product; 
		this.quantity = quantity;
		
	}
	
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
	
	public Client getClient(){
		return client;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public String getDate(){
		return date;
	}
	
	public void decrementQuantity(int quantity) {
		this.quantity -= quantity;
	}

 
	
	public String toString() {
		return "Waitlist Item: \nClient ID: " + client.getId() + "\n" + product.toString() + "\nQuantity: " + quantity;
		
	}
}
