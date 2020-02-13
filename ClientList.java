//Miranda
//methods to add, remove, and search
import java.util.*;
import java.io.*;
public class ClientList implements Serializable
{
	private static final long serialVersionUID=1L;
	private List clients = new LinkedList();
	private static ClientList clientList;
	private ClientList(){
	}
	public static ClientList instance(){
		if (clientList==null)
		{
			return (clientList=new ClientList());
		}
		else
		{
			return clientList;
		}
	}
	public boolean insertClient(Client client)
	{
		client.add(client);
		return true;
	}
	
	public Iterator getClients()
	{
		return clients.iterator();
	}
	
	/*
	private void writeObject(java.io.ObjectOutputStream output)
	{
		try
		{
			output.defaultWriteObject();
			output.writeObject(clientList);
		}catch(IOException ioe)
		{
		ioe.printStackTrace();
		}
	}
	private void readObject (java.io.ObjectInputStream input)
	{
		try
		{
			if(clientList !=null)
			{
				return;
			}
			else
			{
				input.defaultReadObject();
				if(clientList == null)
				{
					clientList=(ClientList) input.readObject();
				}
				else
				{
					input.readObject();
				}
			}
		}catch(IOException ioe)
		{
		ioe.printStackTrace();
		}catch(ClassNotFoundException crne)
		{
			crne.printStackTrace();
		}
	}
	*/
	
	public String toString()
	{
		return clients.toString();
	}
}
	//************Clare initial stuff below***************
//	private static ClientList singleton;
//	List<Client> clients;
	
//	private ClientList()
//	{
//	}
	
//	public static ClientList getInstance()
//	{
//		return singleton;
//	}
	
//	public Iterator GetClients()
//	{
//		return clients.iterator();
//	}
//}
