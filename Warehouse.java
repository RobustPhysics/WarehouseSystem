//Clare Miller

public class Warehouse
{
	private static Warehouse singleton;
	
	private Warehouse()
	{
	}
	
	public static Warehouse getInstance()
	{
		return singleton;
	}
}