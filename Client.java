//Miranda
//include constructor, get, set, variables as needed

import java.util.*;
import java.io.*;

public class Client implements Serializable
{
	private static final long serialVersionUID = 1L;
	private static final String CLIENT_STRING = "C";
	private String id;
	private String name;
	private double amountDue;
	private List<LineItem> cart;
	
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
	
	public void incrementAmountDue(double amount)
	{
		
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
