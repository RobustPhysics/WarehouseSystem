//Miranda
public class ClientList
{
	private static ClientList singleton;
	List<Client> clients;
	
	private ClientList()
	{
	}
	
	public static ClientList getInstance()
	{
		return singleton;
	}
	
	public Iterator GetClients()
	{
		return clients.iterator();
	}
}