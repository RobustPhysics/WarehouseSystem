// Barrett Carlson
// SupplierList.java
import java.io.*;
import java.util.*;

public class SupplierList implements Serializable{

  private static final long serialVersionUID = 1L;
  private List<Supplier> suppliers = new LinkedList<Supplier>();
  private static SupplierList supplierlist;

  private SupplierList() {}

  public static SupplierList getInstance()
  {
    if (supplierlist == null) 
    {
      return (supplierlist = new SupplierList());
    } 
    else 
    {
      return supplierlist;
    }
  }

  public boolean insertSupplier(Supplier supplier) {
    return suppliers.add(supplier);
  }

  public Supplier searchSuppler(String supplierID)
  {
    Iterator supplierIterator = suppliers.iterator();
    
    while (supplierIterator.hasNext())
    {
        Supplier supplier = (Supplier)(supplierIterator.next());
        if (supplier.getSupplierID().equals(supplierID))
        {
            return supplier;
        }
    }
    return null;
  }
  
  public Iterator getSuppliers(){
    return suppliers.iterator();
  }
  
  /*
  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(supplierlist);
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }
  
  private void readObject(java.io.ObjectInputStream input) {
    try {
      if (supplierlist != null) {
        return;
      } else {
        input.defaultReadObject();
        if (supplierlist == null) {
          supplierlist = (SupplierList) input.readObject();
        } else {
          input.readObject();
        }
      }
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
    }
  }
  */
  
  public String toString() {
    return suppliers.toString();
  }

}
