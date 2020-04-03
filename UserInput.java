import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class UserInput {
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public static String getToken(String prompt)
	{
		do
		{
			try
			{
				System.out.println(prompt);
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
				if (tokenizer.hasMoreTokens())
				{
					return tokenizer.nextToken();
				}
			}
			catch (IOException ioe)
			{
				System.exit(0);
			}
		} while (true);
	}
	
	public static boolean promptYesNo(String prompt)
	{
		prompt += " (Y/y) for YES, anything else for NO.";
		char result = getToken(prompt).charAt(0);
		if (result == 'Y' || result == 'y')
		{
			return true;
		}
		return false;
	}
}
