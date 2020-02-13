//Miranda
//include constructor, get, set, variables as needed
import java.util.*;
import java.io.*;
public class Client implements Serializable
{
	private String id;
	private String name;
	
	public Client (String id,String name){
	this.id=id;
	this.name=name;
	}
	
	public String getId(){
	return id;
	}
	
	public String getName(){
	return name;
	}
	
	public String toString(){
		String string=" id "+id + " name "+ name;
		return string; 
	}
}