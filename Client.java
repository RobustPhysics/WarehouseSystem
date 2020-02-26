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
	//TODO: Software class diagram uses a separate class as a cart
	private List<LineItem> cart;
	private List<Invoice> invoiceList;
	
	public Client (String name)
	{
		this.name=name;
		id = CLIENT_STRING + (IdServer.instance()).getClientId();
		cart = new LinkedList<LineItem>();
		invoiceList = new LinkedList<Invoice>();
	}
	
	public boolean addToCart(LineItem item)
	{
		return cart.add(item);
	}
	
	public boolean removeFromCart(String productId)
	{
		//TODO
		return true;
	}
	
	public boolean updateProductInCart(String productId, int newQuantity)
	{
		//TODO
		return true;
	}
	
	public double getAmountDue()
	{
		return amountDue;
	}
	
	public boolean processOrder()
	{
		//TODO: Sequence diagram should show the client object processing the order, not warehouse
		
		//TODO: Is this a long method? Should parts be broken up into private methods?
		//i.e. method to generate invoice, to add to waitlist, etc?
		Iterator shoppingCart = getCart();
		if (!shoppingCart.hasNext())
		{
			return false; //return that client had no items in cart to process
		}
		while (shoppingCart.hasNext())
		{
			LineItem item = (LineItem) shoppingCart.next();
			Product product = item.getProduct();
			if (product != null)
			{
				if (product.getQuantity() >= item.getProductQuantity())
				{
					double amountDue = item.getProductPrice() * item.getProductQuantity();
					Date d = new Date();
					String desc="Bought "+item.getProductQuantity()+ " of " +product.getProductName();
					Invoice invoice=new Invoice(d.toString(), product, desc, amountDue);
					invoiceList.add(invoice);
					incrementAmountDue(amountDue);
					product.setQuantity(product.getQuantity()-1);
				}
				else
				{
					System.out.println("Adding " + product + " to waitlist!");
					Date d = new Date();
					product.addToWaitList(this,item.getProductQuantity(),d.toString());
				}
			}
			else
			{
				//If product doesn't exist, do nothing.
				//It will be removed later.
				//TODO: Should we inform user that it wasn't processed?
			}
		}
		
		//Clear cart now that it was processed
		while (!cart.isEmpty())
		{
			cart.remove(0);
		}
		
		return true;
	}
	
	public Iterator getTransactions()
	{
		return invoiceList.iterator();
	}
	
	public void incrementAmountDue(double amount)
	{
		amountDue=amountDue+amount;
	}
	
	public Iterator getCart()
	{
		return cart.iterator();
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
		String string="id: "+id + "\tname: "+ name;
		return string; 
	}
}
