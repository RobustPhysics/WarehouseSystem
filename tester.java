import java.util.*;
import java.io.*;

class tester
{
	public static void main(String[] args)
	{
		//UserInterface userInterface = UserInterface.getInstance();
		Warehouse warehouse = Warehouse.getInstance();
		
		warehouse.addClient("M1","Clare");
		warehouse.addClient("M2","Chris");
		warehouse.addClient("M3","Miranda");
		warehouse.addClient("M4","Barrett");
		warehouse.addClient("M5","whatsgoingon");
		warehouse.addClient("M6","isaid");
		warehouse.addClient("M7","hey");
		warehouse.addClient("M8","hey");
		warehouse.addClient("M9","hey");
		warehouse.addClient("M10","hey");
		
		System.out.println("EXPECTED OUTPUT");
		System.out.println(" id M1 name Clare");
		System.out.println(" id M2 name Chris");
		System.out.println(" id M3 name Miranda");
		System.out.println(" id M4 name Barrett");
		System.out.println(" id M5 name whatsgoingon");
		System.out.println(" id M6 name isaid");
		System.out.println(" id M7 name hey");
		System.out.println(" id M8 name hey");
		System.out.println(" id M9 name hey");
		System.out.println(" id M10 name hey");
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
		
		warehouse.addProduct("P1","Gameboy Advance",199.99,30);
		warehouse.addProduct("P2","Pokemon Silver",35.99,344);
		warehouse.addProduct("P3","Pokemon Gold",35.99,435);
		warehouse.addProduct("P4","Pokemon Crystal",45.99,234);
		warehouse.addProduct("P5","Pokemon Red",24.99,342);
		warehouse.addProduct("P6","Pokemon Blue",24.99,123);
		warehouse.addProduct("P7","Nintendo 64",349.99,86);
		warehouse.addProduct("P8","Super Mario 64",30.00,54);
		warehouse.addProduct("P9","Paper Mario",20.00,243);
		warehouse.addProduct("P10","Donkey Kong 64",25.00,163);
		
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
		
		warehouse.addSupplier("S1", "Medtronic","Address 1");
		warehouse.addSupplier("S2", "Aldrin Labs","Address 2");
		warehouse.addSupplier("S3", "Ariake Tech","Address 3");
		warehouse.addSupplier("S4", "Armali Council","Address 4");
		warehouse.addSupplier("S5", "Devlon Industries","Address 5");
		warehouse.addSupplier("S6", "Kassa Fabrication","Address 6");
		warehouse.addSupplier("S7", "Rosenkov Materials","Address 7");
		warehouse.addSupplier("S8", "Hahne-Kedar","Address 8");
		warehouse.addSupplier("S9", "Serrice Council","Address 9");
		warehouse.addSupplier("S10", "Sirta Foundation","Address 10");
		
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
