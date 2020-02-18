import java.util.*;
import java.io.*;

class tester
{
	public static void main(String[] args)
	{
		//UserInterface userInterface = UserInterface.getInstance();
		Warehouse warehouse = Warehouse.getInstance();
		
		warehouse.addClient("M1","Bob Sagat");
		warehouse.addClient("M2","Hello Padre");
		warehouse.addClient("M3","goodness gracious");
		warehouse.addClient("M4","hihowareyou");
		warehouse.addClient("M5","whatsgoingon");
		warehouse.addClient("M6","isaid");
		warehouse.addClient("M7","hey");
		warehouse.addClient("M8","hey");
		warehouse.addClient("M9","hey");
		warehouse.addClient("M10","hey");
		warehouse.addClient("M11","isaidhey");
		warehouse.addClient("M12","whats going on");
		warehouse.addClient("M13","clare");
		warehouse.addClient("M14","chris");
		warehouse.addClient("M15","MIRANDANANA");

		Iterator clients = warehouse.getClients();
		while (clients.hasNext())
		{
			Client client = (Client) (clients.next());
			System.out.println(client);
		}
		
		System.out.println("Expected output: ");
		System.out.println("");
		System.out.println();
		
		warehouse.addProduct("P1","bay food",45.00,78);
		warehouse.addProduct("P2","babyfood",45.00,78);
		warehouse.addProduct("P3","baby ood",45.00,78);
		warehouse.addProduct("P4","baby o",45.00,78);
		warehouse.addProduct("P5","bab ood",45.00,78);
		warehouse.addProduct("P6","bay food",45.00,78);
		warehouse.addProduct("P7","bby food",45.00,78);
		warehouse.addProduct("P8","bb food",45.00,78);
		warehouse.addProduct("P9","ba food",45.00,78);
		warehouse.addProduct("P10","baod",45.00,78);
		warehouse.addProduct("P11","bod",45.00,78);
		warehouse.addProduct("P12","bab foo",45.00,78);
		warehouse.addProduct("P13","baby fod",45.00,78);
		warehouse.addProduct("P14","baby food",45.00,78);

		Iterator products = warehouse.getProducts();
		while (products.hasNext())
		{
			Product product = (Product) (products.next());
			System.out.println(product);
		}
		
		System.out.println("Expected output: ");
		System.out.println("");
		System.out.println();
		
		warehouse.addSupplier("S1", "MEDTRONIC","666 Satans Ave.");
		warehouse.addSupplier("S2", "MEDTRONIC","666 Satans Ave.");
		warehouse.addSupplier("S3", "MEDTRONIC","666 Satans Ave.");
		warehouse.addSupplier("S4", "MEDTRONIC","666 Satans Ave.");
		warehouse.addSupplier("S5", "MEDTRONIC","666 Satans Ave.");
		warehouse.addSupplier("S6", "MEDTRONIC","666 Satans Ave.");
		warehouse.addSupplier("S7", "MEDTRONIC","666 Santa Ave.");
		warehouse.addSupplier("S8", "MEDTRONIC","666 Satanaas Ave.");
		warehouse.addSupplier("S9", "ME22323kDTRONIC","666 Satans Ave.");
		warehouse.addSupplier("S10", "MEDTRONIC","666 Satans Ave.");
		warehouse.addSupplier("S11", "MEDTRONIC","666 Satans Ave.");
		warehouse.addSupplier("S12", "MEDTRONIC","666 Satans Ave.");
		
		Iterator suppliers = warehouse.getSuppliers();
		while (suppliers.hasNext())
		{
			Supplier supplier = (Supplier) (suppliers.next());
			System.out.println(supplier);
		}
		
		System.out.println("Expected output: ");
		System.out.println("");
		System.out.println();
	}
}
