package utils;

public class Check
{
	private static final String DATE_FORMAT = "00/00/0000";
	private static final int MIN_YEAR = 1800;

	public static boolean validate(String data)
	{
		if (!hasValidFormat(data))
			return (false);
		if (!hasValidDate(data))
			return (false);
		return (true);
	}

	private static boolean hasValidFormat(String data)
	{
		if (data == null)
			return (false);
		if (data.length() != DATE_FORMAT.length())
			return (false);
		if (data.charAt(2) != '/' || data.charAt(5) != '/')
			return (false);
		if (Utils.countSeparator(data, '/') != 2)
			return (false);
		if (!onlyDigitsInDate(data))
			return (false);
		return (true);
	}

	private static boolean onlyDigitsInDate(String data)
	{
		for (int i = 0; i < data.length(); i++)
		{
			if (i == 2 || i == 5)
				continue;
			if (!Character.isDigit(data.charAt(i)))
				return (false);
		}
		return (true);
	}

	private static boolean hasValidDate(String data)
	{
		int[] parts;
		int day;
		int month;
		int year;

		parts = Split.splitStringToInt(data, '/');
		if (parts.length != 3)
			return (false);
		day = parts[0];
		month = parts[1];
		year = parts[2];
		if (year < MIN_YEAR)
			return (false);
		for (Months currentMonth : Months.values())
		{
			if (currentMonth.getNumber() == month)
			{
				if (day >= 1 && day <= currentMonth.getDays())
					return (true);
				return (false);
			}
		}
		return (false);
	}
}
