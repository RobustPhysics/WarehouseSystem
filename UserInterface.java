public class UserInterface
{
	private static UserInterface singleton;
	
	private UserInterface()
	{
		
	}
	
	public static UserInterface getInstance()
	{
		return singleton;
	}
}