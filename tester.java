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
		warehouse.addClient("whatsgoingon");
		warehouse.addClient("isaid");
		warehouse.addClient("hey");
		warehouse.addClient("hey");
		warehouse.addClient("hey");
		warehouse.addClient("hey");
		
		System.out.println("EXPECTED OUTPUT");
		System.out.println(" id C1 name Clare");
		System.out.println(" id C2 name Chris");
		System.out.println(" id C3 name Miranda");
		System.out.println(" id C4 name Barrett");
		System.out.println(" id C5 name whatsgoingon");
		System.out.println(" id C6 name isaid");
		System.out.println(" id C7 name hey");
		System.out.println(" id C8 name hey");
		System.out.println(" id C9 name hey");
		System.out.println(" id C10 name hey");
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
		warehouse.addProduct("Pokemon Red",24.99,342);
		warehouse.addProduct("Pokemon Blue",24.99,123);
		warehouse.addProduct("Nintendo 64",349.99,86);
		warehouse.addProduct("Super Mario 64",30.00,54);
		warehouse.addProduct("Paper Mario",20.00,243);
		warehouse.addProduct("Donkey Kong 64",25.00,163);
		
		System.out.println("EXPECTED OUTPUT");
		System.out.println("id P1 name Gameboy Advance ($199.99 x 30)");
		System.out.println("id P2 name Pokemon Silver ($35.99 x 344)");
		System.out.println("id P3 name Pokemon Gold ($35.99 x 435)");
		System.out.println("id P4 name Pokemon Crystal ($45.99 x 234)");
		System.out.println("id P5 name Pokemon Red ($24.99 x 342)");
		System.out.println("id P6 name Pokemon Blue ($24.99 x 123)");
		System.out.println("id P7 name Nintendo 64 ($349.99 x 86)");
		System.out.println("id P8 name Super Mario 64 ($30.00 x 54)");
		System.out.println("id P9 name Paper Mario ($20.00 x 243)");
		System.out.println("id P10 name Donkey Kong 64 ($25.00 x 163)");
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
		warehouse.addSupplier("Devlon Industries","Address 5");
		warehouse.addSupplier("Kassa Fabrication","Address 6");
		warehouse.addSupplier("Rosenkov Materials","Address 7");
		warehouse.addSupplier("Hahne-Kedar","Address 8");
		warehouse.addSupplier("Serrice Council","Address 9");
		warehouse.addSupplier("Sirta Foundation","Address 10");
		
		System.out.println("Expected output: ");
		System.out.println(" id S1 name Medtronic (Address 1)");
		System.out.println(" id S2 name Aldrin Labs (Address 2)");
		System.out.println(" id S3 name Ariake Tech (Address 3)");
		System.out.println(" id S4 name Armali Council (Address 4)");
		System.out.println(" id S5 name Devlon Industries (Address 5)");
		System.out.println(" id S6 name Kassa Fabrication (Address 6)");
		System.out.println(" id S7 name Rosenkov Materials (Address 7)");
		System.out.println(" id S8 name Hahne-Kedar (Address 8)");
		System.out.println(" id S9 name Serrice Council (Address 9)");
		System.out.println(" id S10 name Sirta Foundation (Address 10)");
		System.out.println();
		
		System.out.println("REAL OUTPUT");
		
		Iterator suppliers = warehouse.getSuppliers();
		while (suppliers.hasNext())
		{
			Supplier supplier = (Supplier) (suppliers.next());
			System.out.println(supplier);
		}
		
		//////////////////////////////////////////////////////
	}
}
