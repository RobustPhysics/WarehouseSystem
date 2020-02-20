//Miranda
//include constructor, get, set, variables as needed

import java.util.*;
import java.io.*;

public class Client implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	
	private static final String CLIENT_STRING = "C";
	
	public Client (String id, String name)
	{
		this.id=id;
		this.name=name;
		id = CLIENT_STRING + (ClientIdServer.instance()).getId();
	}
	
	public String getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String toString()
	{
		String string="id: "+id + "\nname: "+ name;
		return string; 
	}
}
