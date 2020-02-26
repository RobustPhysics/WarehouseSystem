// Chris
import java.util.*;
import java.io.*;

public class WaitlistItem implements Serializable{
	// Stored in the product, contains information on
	// client and quantity. Maybe price too? And date added to list?
	private static final long serialVersionUID = 1L;
	private Client client;
	private int quantity;
	private double price;
	private String date;      // Date added to the list
	
	public WaitlistItem(Client client, int quantity, String date)
	{
		this.client = client;
		this.quantity = quantity;
		this.date = date;
		
	}
	
	public Client getClient(){
		return client;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public String getDate(){
		return date;
	}
	
	
	public String toString() {
		return "Waitlist Item: \tClient ID: " + client.getId() + "\tQuantity: " + quantity;
		
	}
}
