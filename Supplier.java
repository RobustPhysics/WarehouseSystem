// Barrett
// Supplier.java
import java.util.*;
import java.io.*;

public class Supplier implements Serializable {

	private static final long serialVersionUID = 1L;
	private String supplierID;
	private String name;
	private String address;
	private static final String SUPPLIER_STRING = "S";
	private List<SuppliedProduct> suppliedProducts = new LinkedList<SuppliedProduct>();
	
	public Supplier (String name, String address)
	{
		this.name = name;
		this.address = address;
		supplierID = SUPPLIER_STRING + IdServer.instance().getSupplierId();
	}
	
	public boolean addSuppliedProduct(SuppliedProduct sp)
	{
		return suppliedProducts.add(sp);
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public String getSupplierID()
	{
		return supplierID;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public Iterator getSuppliedProducts()
	{
		return suppliedProducts.iterator();
	}
	
	public boolean canShipProduct(String productId)
	{
		//TODO
		Product product = null;
		Iterator suppliesIterator = suppliedProducts.iterator();

		while (suppliesIterator.hasNext())
		{
			SuppliedProduct sp = (SuppliedProduct)(suppliesIterator.next());
			Product p = sp.getProduct();
			if (p != null && p.getProductID() == productId)
			{
				product = p;
			}
		}
		
		return product != null;
	}
 
	public boolean assignProduct(SuppliedProduct sp)
	{
		return suppliedProducts.add(sp);
	}

/*
	public boolean unassignProduct(String productID, String manufacturerID)
	{
		Iterator suppliesIterator = suppliedProducts.iterator();

		while (suppliesIterator.hasNext())
		{
			SuppliedProduct sp = (SuppliedProduct)(suppliesIterator.next());
			Manufacturer manufacturer = sp.getManufacturer();
			Product product = sp.getProduct();
			
			if (product.getProductID().equals(productID) && manufacturer.getManufacturerID().equals(manufacturerID))
			{
				return suppliedProducts.remove(supplier);
			}
		}
		
		return false; //Couldn't find Supplies object to unassign
	 
	 }
*/

	public String toString() {
		//String str = "Supplier ID: " + supplierID + " \nName: " + name + "\nAddress: " + address;
		String str = " id " + supplierID + " name " + name + " (" + address + ")";
		return str;
	}

}
