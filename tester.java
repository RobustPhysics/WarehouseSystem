import java.util.*;
import java.io.*;

class tester
{
	public static void main(String[] args)
	{
		//UserInterface userInterface = UserInterface.getInstance();
		Warehouse warehouse = Warehouse.getInstance();
		
		warehouse.addClient();
		warehouse.addClient();
		warehouse.addClient();
		warehouse.addClient();
		warehouse.addClient();
		warehouse.addClient();
		warehouse.addClient();
		warehouse.addClient();
		warehouse.addClient();
		warehouse.addClient();
		
		Iterator clients = warehouse.getClients();
		while (clients.hasNext())
		{
			Client client = (Client) (clients.next());
			System.out.println(client);
		}
		
		System.out.println("Expected output: ");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println();
		
		warehouse.addProduct();
		warehouse.addProduct();
		warehouse.addProduct();
		warehouse.addProduct();
		warehouse.addProduct();
		warehouse.addProduct();
		warehouse.addProduct();
		warehouse.addProduct();
		warehouse.addProduct();
		warehouse.addProduct();
		
		Iterator products = warehouse.getProducts();
		while (products.hasNext())
		{
			Product product = (Product) (products.next());
			System.out.println(product);
		}
		
		System.out.println("Expected output: ");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println();
		
		warehouse.addSupplier();
		warehouse.addSupplier();
		warehouse.addSupplier();
		warehouse.addSupplier();
		warehouse.addSupplier();
		warehouse.addSupplier();
		warehouse.addSupplier();
		warehouse.addSupplier();
		warehouse.addSupplier();
		warehouse.addSupplier();
		
		Iterator suppliers = warehouse.getSuppliers();
		while (suppliers.hasNext())
		{
			Supplier supplier = (Supplier) (suppliers.next());
			System.out.println(supplier);
		}
		
		System.out.println("Expected output: ");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println();
	}
}