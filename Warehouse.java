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
		IdServer.instance();
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
	
	public Client addClient(String name)
	{
		Client client = new Client(name);
		if (clientList.insertClient(client))
		{
			return client;
		}
		return null;
	}
	
	public Product addProduct(String name, double price, int quantity)
	{
		Product product = productList.searchProductByName(name);
		if (product != null)
		{
			product.setQuantity(product.getQuantity() + quantity);
			return product;
		}
		else
		{
			product = new Product(name, price);
			product.setQuantity(quantity);
			if (productList.insertProduct(product))
			{
				return product;
			}
			return null;
		}
	}
	
	public Supplier addSupplier(String name, String address)
	{
		Supplier supplier = new Supplier(name, address);
		if (supplierList.insertSupplier(supplier))
		{
			return supplier;
		}
		return null;
	}
	
	public Client getClient(String id)
	{
		return clientList.getClient(id);
	}
	
	public Product getProduct(String id)
	{
		return productList.searchProductByID(id);
	}
	
	public Supplier getSupplier(String id)
	{
		return supplierList.searchSupplier(id);
	}
	
	public Iterator getProductWaitlist(String productId)
	{
		Product product = getProduct(productId);
		if (product != null)
		{
			return product.getWaitlist();
		}
		
		return null;
	}
	
	public Iterator getSuppliedProductsFromProduct(String productId)
	{
		Product product = getProduct(productId);
		if (product != null)
		{
			return product.getSuppliedProducts();
		}
		
		return null;
	}
	
	public Iterator getSuppliedProductsFromSupplier(String supplierId)
	{
		Supplier supplier = getSupplier(supplierId);
		if (supplier != null)
		{
			return supplier.getSuppliedProducts();
		}
		
		return null;
	}
	
	public SuppliedProduct getSuppliedProduct(String supplierId, String productId)
	{
		Supplier supplier = getSupplier(supplierId);
		if (supplier.canShipProduct(productId))
		{
			SuppliedProduct sp = null;
			Iterator suppliedProducts = supplier.getSuppliedProducts();
			while (suppliedProducts.hasNext() && sp != null)
			{
				sp = (SuppliedProduct) suppliedProducts.next();
				Product product = sp.getProduct();
				
				if (product != null && product.getProductID() != productId)
				{
					product = null; //reset it back to null if not the right product
					sp = null;
				}
			}
			
			return sp;
		}
		
		return null;
	}
	
	public boolean addSuppliedProduct(String productId, String supplierId, double purchasePrice)
	{
		Product product = getProduct(productId);
		Supplier supplier = getSupplier(supplierId);
		
		if (product == null)
		{
			System.out.println("Unable to find product " + productId);
			return false;
		}
		if (supplier == null)
		{
			System.out.println("Unable to find supplier " + supplierId);
			return false;
		}
		SuppliedProduct sp = new SuppliedProduct(product, supplier, purchasePrice);
		
		boolean r1 = product.addSuppliedProduct(sp);
		boolean r2 = supplier.addSuppliedProduct(sp);
		
		return r1 && r2;
	}
	
	public Iterator getClientTransactions(String clientId)
	{
		Client client = getClient(clientId);
		if (client == null)
		{
			return null;
		}
		
		return client.getTransactions();
	}
	
	public Iterator getClientCart(String clientId)
	{
		Client client = getClient(clientId);
		if (client == null)
		{
			return null;
		}
		
		return client.getCart();
	}
	
	public boolean canShipProduct(String supplierId, String productId)
	{
		Supplier supplier = getSupplier(supplierId);
		//TODO: Check if product exists too? In case supplier has productId, but product
		//doesn't actually exist
		if (supplier != null)
		{
			return supplier.canShipProduct(productId);
		}
		
		return false;
	}
	
	public boolean shipProduct(String supplierId, String productId, int quantity)
	{
		Supplier supplier = getSupplier(supplierId);
		if (supplier != null && supplier.canShipProduct(productId))
		{
			//TODO: Should supplier ship product instead of warehouse?
			//supplier.shipProduct(productId);
			
			Product product = null;
			SuppliedProduct sp = null;
			Iterator suppliedProducts = supplier.getSuppliedProducts();
			while (suppliedProducts.hasNext() && product == null && sp == null)
			{
				sp = (SuppliedProduct) suppliedProducts.next();
				product = sp.getProduct();
				if (product != null && !product.getProductID().equals(productId))
				{
					product = null; //reset it back to null if not the right product
					sp = null;
				}
				
				//TODO: Should supplier have an invoice for products purchased?
				//TODO: Should warehouse have a variable for total funds?
			}
			if (product != null && sp != null)
			{
				product.setQuantity(product.getQuantity() + quantity);
				product.processWaitlist();
			}
		}
		else
		{
			return false;
		}
		
		return true;
	}
	
	public boolean addToCart(String clientId, String productId, int quantity)
	{
		Product product = getProduct(productId);
		Client client = getClient(clientId);
		if (product == null)
		{
			System.out.println("Product " + productId + " not found!");
			return false;
		}
		else if (client == null)
		{
			System.out.println("Client " + clientId + " not found!");
			return false;
		}
		
		//TODO: We do not need to worry if product is out of stock
		//That's only a concern for processOrder()
		double price = product.getSalePrice();
		LineItem item = new LineItem(product, price, quantity);
		
		return client.addToCart(item);
	}
	
	
	public boolean removeFromCart(String clientId, String productId)
	{
		Client client = clientList.getClient(clientId);
		if (client == null)
		{
			System.out.println("Client " + clientId + " not found!");
			return false;
		}
		
		return client.removeFromCart(productId);
	}
	
	public boolean updateProductInCart(String clientId, String productId, int newQuantity)
	{
		Client client = clientList.getClient(clientId);
		if (client == null)
		{
			System.out.println("Client " + clientId + " not found!");
			return false;
		}
		
		return client.updateProductInCart(productId, newQuantity);
		
		/*
		LineItem item = client.getLineItemInCart(productId);
		if (item == null)
		{
			System.out.println("Product " + productId + " not found in cart for client " + client);
			return false;
		}
		
		item.setProductQuantity(newQuantity);
		
		return true;
		*/
	}
	
	
	public boolean processOrder(String clientId)
	{
		Client client = getClient(clientId);
		if (client != null)
		{
			return client.processOrder();
		}
		else
		{
			return false;
		}
	}
	
	public Iterator getOutstandingClients()
	{
		List<Client> outstandingClients = new ArrayList<Client>();
		Iterator clients = clientList.getClients();
		while (clients.hasNext())
		{
			Client client = (Client) clients.next();
			System.out.println("Client " + client.getId() + " owes " + client.getAmountDue());
			if (client.getAmountDue() > 0)
			{
				outstandingClients.add(client);
			}
		}
		
		return outstandingClients.iterator();
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
			IdServer.retrieve(input);
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
	
	public boolean save()
	{
		try
		{
			FileOutputStream file = new FileOutputStream(new File("WarehouseData.txt"));
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(warehouse);
			output.writeObject(IdServer.instance());
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