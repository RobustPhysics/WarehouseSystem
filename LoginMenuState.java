import java.util.*;
import java.text.*;
import java.io.*;
public class LoginMenuState extends WarehouseState {
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private WarehouseContext context;
	private static LoginMenuState instance;
	
	private LoginMenuState()
	{
		super();
	}
	
	public static LoginMenuState getInstance()
	{
		if (instance == null)
		{
			instance = new LoginMenuState();
		}
		return instance;
	}
	
	
}
