// Barrett
// ClientIdServer.java
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class IdServer implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int clientIdCount; // id counter
	private int productIdCount;
	private int supplierIdCount;
	private static IdServer server;

	private IdServer()
	{
		clientIdCount = 1;
		productIdCount = 1;
		supplierIdCount = 1;
	}

	public static IdServer instance()
	{
		if (server == null)
			return (server = new IdServer());
		else
			return server;
	}

	public int getClientId()
	{
		return clientIdCount++;
	}
	
	public int getProductId()
	{
		return productIdCount++;
	}
	
	public int getSupplierId()
	{
		return supplierIdCount++;
	}

	public String toString()
	{
		return ("Client ID " + clientIdCount + " | Product ID " + productIdCount + " | Supplier ID " + supplierIdCount);
	}

	public static void retrieve(ObjectInputStream input)
	{
		try
		{
			server = (IdServer) input.readObject();
		
		}catch(IOException ioe){
		
			ioe.printStackTrace();
		
		}catch(Exception cnfe) {
			cnfe.printStackTrace();
		}
	}

	private void writeObject(java.io.ObjectOutputStream output) throws IOException {
		try
		{
			output.defaultWriteObject();
			output.writeObject(server);
		
		}catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}

	private void readObject(java.io.ObjectInputStream input) throws IOException, ClassNotFoundException {
		try 
		{
			input.defaultReadObject();
			if (server == null)
				server = (IdServer) input.readObject();
			else
				input.readObject();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
}
