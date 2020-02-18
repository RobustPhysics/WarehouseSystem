//Clare Miller

import java.util.*;
import java.io.*;

public class Warehouse implements Serializable
{
	private static final long serialVersionUID = 1L;
	private static Warehouse warehouse;
	private ClientList clientList;
	private ProductList productList;
	private SupplierList supplierList;
	
	private Warehouse()
	{
		clientList = ClientList.instance();
		productList = ProductList.getInstance();
		supplierList = SupplierList.getInstance();
	}
	
	public static Warehouse getInstance()
	{
		if (warehouse == null)
		{
			warehouse = new Warehouse();
			return warehouse;
		}
		else
		{
			return warehouse;
		}
	}
	
	public Client addClient(String id, String name)
	{
		Client client = new Client(id, name);
		if (clientList.insertClient(client))
		{
			return client;
		}
		return null;
	}
	
	public Product addProduct(String id, String name, double price, int quantity)
	{
		Product product = productList.searchProduct(id);
		if (product != null)
		{
			//productList.addProduct(product, quantity);
			product.setQuantity(product.getQuantity() + quantity);
			return product;
		}
		else
		{
			product = new Product(id, name, price);
			product.setQuantity(quantity);
			if (productList.insertProduct(product))
			{
				return product;
			}
			return null;
		}
	}
	
	public Supplier addSupplier(String id, String name, String address)
	{
		Supplier supplier = new Supplier(id, name, address);
		if (supplierList.insertSupplier(supplier))
		{
			return supplier;
		}
		return null;
	}
	
	public Iterator getClients()
	{
		return clientList.getClients();
	}
	
	public Iterator getProducts()
	{
		return productList.getProducts();
	}
	
	public Iterator getSuppliers()
	{
		return supplierList.getSuppliers();
	}
	
	public static Warehouse retrieve()
	{
		try
		{
			FileInputStream file = new FileInputStream(new File("WarehouseData.txt"));
			ObjectInputStream input = new ObjectInputStream(file);
			input.readObject();
			
			return warehouse;
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
			return null;
		}
		catch (ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace();
			return null;
		}
	}
	
	public static boolean save()
	{
		try
		{
			FileOutputStream file = new FileOutputStream(new File("WarehouseData.txt"));
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(warehouse);
			output.close();
			
			return true;
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
			return false;
		}
	}
	
	private void writeObject(java.io.ObjectOutputStream output)
	{
		try
		{
			output.defaultWriteObject();
			output.writeObject(warehouse);
		}
		catch (IOException ioe)
		{
			System.out.println(ioe);
		}
	}
	
	private void readObject(java.io.ObjectInputStream input)
	{
		try
		{
			input.defaultReadObject();
			if (warehouse == null)
			{
				warehouse = (Warehouse) input.readObject();
			}
			else
			{
				input.readObject();
			}
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String toString()
	{
		return clientList + "\n" + productList + "\n" + supplierList;
	}
}