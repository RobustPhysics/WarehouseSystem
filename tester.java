import java.util.*;
import java.io.*;

class tester
{
	public static void main(String[] args)
	{
		//UserInterface userInterface = UserInterface.getInstance();
		Warehouse warehouse = Warehouse.getInstance();
		
		warehouse.addClient("Clare");
		warehouse.addClient("Chris");
		warehouse.addClient("Miranda");
		warehouse.addClient("Barrett");
		
		System.out.println("EXPECTED OUTPUT");
		System.out.println(" id C1 name Clare");
		System.out.println(" id C2 name Chris");
		System.out.println(" id C3 name Miranda");
		System.out.println(" id C4 name Barrett");
		System.out.println();
		
		System.out.println("REAL OUTPUT");
		
		Iterator clients = warehouse.getClients();
		while (clients.hasNext())
		{
			Client client = (Client) (clients.next());
			System.out.println(client);
		}
		
		System.out.println("\n");
		
		//////////////////////////////////////////////////////
		
		warehouse.addProduct("Gameboy Advance",199.99,30);
		warehouse.addProduct("Pokemon Silver",35.99,344);
		warehouse.addProduct("Pokemon Gold",35.99,435);
		warehouse.addProduct("Pokemon Crystal",45.99,234);
		
		System.out.println("EXPECTED OUTPUT");
		System.out.println("id P1 name Gameboy Advance ($199.99 x 30)");
		System.out.println("id P2 name Pokemon Silver ($35.99 x 344)");
		System.out.println("id P3 name Pokemon Gold ($35.99 x 435)");
		System.out.println("id P4 name Pokemon Crystal ($45.99 x 234)");
		System.out.println();
		System.out.println("REAL OUTPUT");
		
		Iterator products = warehouse.getProducts();
		while (products.hasNext())
		{
			Product product = (Product) (products.next());
			System.out.println(product);
		}
		
		System.out.println("\n");
		
		//////////////////////////////////////////////////////
		
		warehouse.addSupplier("Medtronic","Address 1");
		warehouse.addSupplier("Aldrin Labs","Address 2");
		warehouse.addSupplier("Ariake Tech","Address 3");
		warehouse.addSupplier("Armali Council","Address 4");
		
		System.out.println("Expected output: ");
		System.out.println(" id S1 name Medtronic (Address 1)");
		System.out.println(" id S2 name Aldrin Labs (Address 2)");
		System.out.println(" id S3 name Ariake Tech (Address 3)");
		System.out.println(" id S4 name Armali Council (Address 4)");
		System.out.println();
		
		System.out.println("REAL OUTPUT");
		
		Iterator suppliers = warehouse.getSuppliers();
		while (suppliers.hasNext())
		{
			Supplier supplier = (Supplier) (suppliers.next());
			System.out.println(supplier);
		}
		
		//////////////////////////////////////////////////////
		
		warehouse.addSuppliedProduct("P1", "S1", 60);
		warehouse.addSuppliedProduct("P2", "S1", 15);
		warehouse.addSuppliedProduct("P3", "S1", 15);
		warehouse.addSuppliedProduct("P4", "S1", 15);
		warehouse.addSuppliedProduct("P1", "S2", 45);
		warehouse.addSuppliedProduct("P2", "S3", 10);
		warehouse.addSuppliedProduct("P3", "S3", 10);
		warehouse.addSuppliedProduct("P4", "S3", 10);
		
		System.out.println("Expected output: ");
		System.out.println();
		
		System.out.println("REAL OUTPUT");
		
		products = warehouse.getProducts();
		suppliers = warehouse.getSuppliers();
		while (suppliers.hasNext())
		{
			Supplier supplier = (Supplier) (suppliers.next());
			Iterator suppliedProducts = warehouse.getSuppliedProductsFromSupplier(supplier.getSupplierID());
			System.out.println("Supplier " + supplier.getName() + " (" + supplier.getSupplierID() + ") supplies...");
			while (suppliedProducts.hasNext())
			{
				SuppliedProduct sp = (SuppliedProduct) suppliedProducts.next();
				Product product = sp.getProduct();
				System.out.println("\t" + product);
			}
		}
		while (products.hasNext())
		{
			Product product = (Product) products.next();
			Iterator suppliedProducts = warehouse.getSuppliedProductsFromProduct(product.getProductID());
			System.out.println("Product " + product.getProductName() + " (" + product.getProductID() + ") is supplied by...");
			while (suppliedProducts.hasNext())
			{
				SuppliedProduct sp = (SuppliedProduct) suppliedProducts.next();
				Supplier supplier = sp.getSupplier();
				System.out.println("\t" + supplier);
			}
		}
		
		//////////////////////////////////////////////////////
		
		warehouse.addToCart("C1", "P1", 1);
		warehouse.addToCart("C2", "P1", 1);
		warehouse.addToCart("C3", "P1", 1);
		warehouse.addToCart("C4", "P1", 1);
		warehouse.addToCart("C1", "P4", 1);
		warehouse.addToCart("C2", "P2", 1);
		warehouse.addToCart("C3", "P3", 1);
		warehouse.addToCart("C4", "P2", 1);
		warehouse.addToCart("C4", "P3", 1);
		warehouse.addToCart("C4", "P4", 1);
		
		System.out.println("Expected output: ");
		System.out.println();
		
		System.out.println("REAL OUTPUT");
		
		clients = warehouse.getClients();
		while (clients.hasNext())
		{
			Client client = (Client) clients.next();
			Iterator cart = warehouse.getClientCart(client.getId());
			System.out.println("Cart for client " + client.getId());
			while (cart.hasNext())
			{
				LineItem item = (LineItem) cart.next();
				System.out.println("\t" + item);
			}
		}
	}
}
