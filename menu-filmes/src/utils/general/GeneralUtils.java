package utils.general;

public final class GeneralUtils
{
	private GeneralUtils()
	{
	}

	public static boolean isNullOrBlank(String str)
	{
		return (str == null || str.trim().isEmpty());
	}

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
