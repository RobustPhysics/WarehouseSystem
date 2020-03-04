//Miranda
//methods to add, remove, and search
import java.util.*;
import java.io.*;
public class ClientList implements Serializable
{
	private static final long serialVersionUID=1L;
	private List<Client> clients = new LinkedList<Client>();
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
	
	public Client getClient(String id)
	{
		for (int i = 0; i < clients.size(); i++)
		{
			Client c = clients.get(i);
			if (c.getId().equals(id))
			{
				return c;
			}
		}
		return null;
	}
	
	public boolean insertClient(Client client)
	{
		clients.add(client);
		return true;
	}
	
	public Iterator getClients()
	{
		return clients.iterator();
	}
	
	public String toString()
	{
		return clients.toString();
	}
}

