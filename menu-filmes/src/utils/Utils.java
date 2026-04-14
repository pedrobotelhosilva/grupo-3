package utils;

public class Utils
{
	public static int countSeparator(String str, char separator)
	{
		int count;

		count = 0;
		for (int i = 0; i < str.length(); i++)
		{
			if (str.charAt(i) == separator)
				count++;
		}
		return (count);
	}
}
