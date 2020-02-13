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
  //private List<SuppliedProduct> suppliedProducts = new LinkedList<SuppliedProduct>();
  
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
  /*
  public boolean assignProduct(SuppliedProduct sp)
  {
    return suppliedProducts.add(sp);
  }

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
    String string = "Supplier ID: " + supplierID + " \nName: " + name + "\nAddress: " + address;
    return string;
  }

}
