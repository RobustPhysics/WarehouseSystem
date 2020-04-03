public class LoginMenuState extends WarehouseState {
	private WarehouseContext context;
	private static LoginMenuState instance;
	
	private enum Option
	{
		CLIENT_LOGIN("Log into warehouse as a client"),
		CLERK_LOGIN("Log into warehouse as a clerk"),
		MANAGER_LOGIN("Log into warehouse as a manager"),
		
		HELP("Displays the help menu"),
		EXIT("Exits the program");
		
		private String description;
		private static int LENGTH = Option.values().length;
		
		private Option(String str)
		{
			description = str;
		}
		
		public String getDescription()
		{
			return description;
		}
	}
	
	private LoginMenuState()
	{
		super();
		//context = WarehouseContext.instance();
	}
	
	public static LoginMenuState getInstance()
	{
		if (instance == null)
		{
			instance = new LoginMenuState();
		}
		return instance;
	}
	
	public Option getCommand()
	{
		do
		{
			try
			{
				String token = UserInput.getToken("Enter a command. Use " + Option.HELP.ordinal() + " to display the menu.");
				int value = Integer.parseInt(token);
				if (value >= 0 && value <= Option.LENGTH)
				{
					System.out.println("IN THE getCommand() METHOD");
					return Option.values()[value];
				}
				else
				{
					System.out.println("Input command out of range!");
				}
			}
			catch (NumberFormatException nfe)
			{
				System.out.println("Invalid input - Please enter a valid number!");
			}
		} while (true);
	}
	
	private void displayHelp()
	{
		System.out.println("Enter a number associated with a command seen below");
		System.out.println("---------------------");
		Option options[] = Option.values();
		
		for (Option opt : options)
		{
			System.out.println(opt.ordinal() + " - " + opt.getDescription());
		}
	}
	
	private void loginClient()
	{
		String userID = UserInput.getToken("Please input the client id to log into: ");
		if (Warehouse.getInstance().getClient(userID) != null)
		{
			WarehouseContext wContext = WarehouseContext.instance();
			wContext.setLogin(WarehouseContext.IsUser);
			wContext.setUser(userID);
			wContext.changeState(0); //switching to client state
		}
		else
		{
			System.out.println("Invalid user id.");
		}
	}
	
	private void loginClerk()
	{
		(WarehouseContext.instance()).setLogin(WarehouseContext.IsClerk);
	    (WarehouseContext.instance()).changeState(1); //switch to clerk state
	}
	
	private void loginManager()
	{
		(WarehouseContext.instance()).setLogin(WarehouseContext.IsClerk);
	    (WarehouseContext.instance()).changeState(2); //switch to manager state
	}
	
	public void process()
	{
		Option command;
		displayHelp();
		do
		{
			command = getCommand();
			switch (command)
			{
			case CLIENT_LOGIN:
				loginClient();
				break;
			case CLERK_LOGIN:
				loginClerk();
				break;
			case MANAGER_LOGIN:
				loginManager();
				break;
			
			case HELP:
				displayHelp();
				break;
			}
		} while (command != Option.EXIT);
		(WarehouseContext.instance()).changeState(3); //switch to logging out state
	}
	
	public void run()
	{
		process();
	}
}
