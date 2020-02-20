// Barrett
// ClientIdServer.java
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class ClientIdServer implements Serializable
{
	private int idCount; // id counter
	private static ClientIdServer server;

	private ClientIdServer()
	{
		idCount = 1;
	}

	public static ClientIdServer instance()
	{
		if (server == null)
			return (server = new ClientIdServer());
		else
			return server;
	}

	public int getId()
	{
		return idCount++;
	}

	public String toString()
	{
		return ("IdServer:" + idCount);
	}

	public static void retrieve(ObjectInputStream input)
	{
		try
		{
			server = (ClientIdServer) input.readObject();
		
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
				server = (ClientIdServer) input.readObject();
			else
				input.readObject();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
}
