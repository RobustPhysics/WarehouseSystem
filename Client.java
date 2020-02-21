//Miranda

import java.util.*;
import java.io.*;

public class Client implements Serializable
{
	private static final long serialVersionUID = 1L;
	private static final String CLIENT_STRING = "C";
	private String id;
	private String name;
	private double amountDue;
	//NOTE: Software class diagram uses a separate class as a cart
	private List<LineItem> cart;
	//private List<Invoice> invoices;
	
	public Client (String name)
	{
		//this.id=id;
		this.name=name;
		id = CLIENT_STRING + (IdServer.instance()).getClientId();
		cart = new LinkedList<LineItem>();
	}
	
	public boolean addToCart(LineItem item)
	{
		return cart.add(item);
	}
	
	public double getAmountDue()
	{
		return amountDue;
	}
	
	public boolean processOrder()
	{
		//NOTE: Sequence diagram should show the client object processing the order, not warehouse
		
		//NOTE: Is this a long method? Should parts be broken up into private methods?
		//i.e. method to generate invoice, to add to waitlist, etc?
		Iterator cart = client.getCart();
		if (!cart.hasNext())
		{
			return false; //return that client had no items in cart to process
		}
		while (cart.hasNext())
		{
			LineItem item = (LineItem) cart.next();
			Product product = getProduct(item.getProductId());
			if (product != null)
			{
				if (product.getQuantity() >= item.getProductQuantity())
				{
					//NOTE: Generate invoice
					double amountDue = item.getProductPrice() * item.getProductQuantity();
					client.incrementAmountDue(amountDue);
				}
				else
				{
					//NOTE: Add item to waitlist
					WaitListItem item2 = new WaitListItem(productId, price, quantity);
				}
			}
			else
			{
				//NOTE: product doesn't exist, what do we do?
				//Remove line item from cart?
				client.removeFromCart(item);
			}
		}
		
		//return true;
		
		
		return true;
	}
	
	public void incrementAmountDue(double amount)
	{
		amountDue=amountDue+amount;
	}
	
	public Iterator getCart()
	{
		return null;
	}
	
	public String getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String toString()
	{
		String string="id: "+id + "\nname: "+ name;
		return string; 
	}
}
