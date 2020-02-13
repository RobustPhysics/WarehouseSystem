import java.util.*;
import java.io.*;

class tester
{
	public static void main(String[] args)
	{
		//UserInterface userInterface = UserInterface.getInstance();
		Warehouse warehouse = Warehouse.getInstance();
		
		warehouse.addClient("237487","Bob Sagat");
		warehouse.addClient("1234656","Hello Padre");
		warehouse.addClient("1234123","goodness gracious");
		warehouse.addClient("12341234","hihowareyou");
		warehouse.addClient("4234","whatsgoingon");
		warehouse.addClient("856787","isaid");
		warehouse.addClient("2341","hey");
		warehouse.addClient("44324","hey");
		warehouse.addClient("9484","hey");
		warehouse.addClient("93402","hey");
		warehouse.addClient("2394923","isaidhey");
		warehouse.addClient("239023","whats going on");
		warehouse.addClient("92034","clare");
		warehouse.addClient("67565","chris");
		warehouse.addClient("2342341","MIRANDANANA");

		Iterator clients = warehouse.getClients();
		while (clients.hasNext())
		{
			Client client = (Client) (clients.next());
			System.out.println(client);
		}
		
		System.out.println("Expected output: ");
		System.out.println("");
		System.out.println();
		
		warehouse.addProduct("982734","bay food",45.00,78);
		warehouse.addProduct("98273","babyfood",45.00,78);
		warehouse.addProduct("9827","baby ood",45.00,78);
		warehouse.addProduct("9824","baby o",45.00,78);
		warehouse.addProduct("98734","bab ood",45.00,78);
		warehouse.addProduct("82734","bay food",45.00,78);
		warehouse.addProduct("9834","bby food",45.00,78);
		warehouse.addProduct("982734","bb food",45.00,78);
		warehouse.addProduct("8274","ba food",45.00,78);
		warehouse.addProduct("973","baod",45.00,78);
		warehouse.addProduct("98273","bod",45.00,78);
		warehouse.addProduct("94434","bab foo",45.00,78);
		warehouse.addProduct("87","baby fod",45.00,78);
		warehouse.addProduct("94","baby food",45.00,78);

		Iterator products = warehouse.getProducts();
		while (products.hasNext())
		{
			Product product = (Product) (products.next());
			System.out.println(product);
		}
		
		System.out.println("Expected output: ");
		System.out.println("");
		System.out.println();
		
		warehouse.addSupplier("MEDTRONIC","666 Satans Ave.");
		warehouse.addSupplier("MEDTRONIC","666 Satans Ave.");
		warehouse.addSupplier("MEDTRONIC","666 Satans Ave.");
		warehouse.addSupplier("MEDTRONIC","666 Satans Ave.");
		warehouse.addSupplier("MEDTRONIC","666 Satans Ave.");
		warehouse.addSupplier("MEDTRONIC","666 Satans Ave.");
		warehouse.addSupplier("MEDTRONIC","666 Santa Ave.");
		warehouse.addSupplier("MEDTRONIC","666 Satanaas Ave.");
		warehouse.addSupplier("ME22323kDTRONIC","666 Satans Ave.");
		warehouse.addSupplier("MEDTRONIC","666 Satans Ave.");
		warehouse.addSupplier("MEDTRONIC","666 Satans Ave.");
		warehouse.addSupplier("MEDTRONIC","666 Satans Ave.");
		
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
