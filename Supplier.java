// Barrett
// Supplier.java
import java.util.*;
import java.io.*;

public class Supplier implements Serializable {

  private static final long serialVersionUID = 1L;
  private String supplierID;
  private String name;
  private String address;
  private static final String SUPPLIER_STRING = "M";
  private List<SuppliedProduct> suppliedproducts = new LinkedList<SuppliedProduct>();
  
  public Supplier (String name, String address)
  {
    // ID server??
    //supplierID = SUPPLIER_STRING + ManufacturerIDServer.instance().getID();
    this.name = name;
    this.address = address;
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

  public boolean assignProduct(Supplier supplier)
  {
    return suppliedProducts.add(supplies);
  }

  public boolean unassignProduct(String productID, String manufacturerID)
  {
    Iterator suppliesIterator = suppliedProducts.iterator();

    while (suppliesIterator.hasNext())
    {
      Supplies supplies = (Supplies)(suppliesIterator.next());
      Manufacturer manufacturer = supplies.getManufacturer();
      Product product = supplies.getProduct();
      
      if (product.getProductID().equals(productID) && manufacturer.getManufacturerID().equals(manufacturerID))
      {
        return suppliedProducts.remove(supplies);
      }
    }
    
    return false; //Couldn't find Supplies object to unassign
   
   }

  
  public String toString() {
    String string = "Supplier ID: " + supplierID + " \nName: " + name + "\nAddress: " + address;
    return string;
  }

}
