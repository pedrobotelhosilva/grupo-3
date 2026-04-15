package validation.general;

import enums.Months;
import utils.general.GeneralUtils;
import utils.general.SplitUtils;

public final class DateValidation
{
	private static final String DATE_FORMAT = "00/00/0000";
	private static final int MIN_YEAR = 1800;

	private DateValidation()
	{
	}

	public static boolean validate(String date)
	{
		if (!hasValidFormat(date))
			return (false);
		if (!hasValidDate(date))
			return (false);
		return (true);
	}

	private static boolean hasValidFormat(String date)
	{
		if (GeneralUtils.isNullOrBlank(date))
			return (false);
		if (date.length() != DATE_FORMAT.length())
			return (false);
		if (date.charAt(2) != '/' || date.charAt(5) != '/')
			return (false);
		if (GeneralUtils.countSeparator(date, '/') != 2)
			return (false);
		if (!containsOnlyDigitsInDate(date))
			return (false);
		return (true);
	}

	private static boolean containsOnlyDigitsInDate(String date)
	{
		for (int i = 0; i < date.length(); i++)
		{
			if (i == 2 || i == 5)
				continue;
			if (!Character.isDigit(date.charAt(i)))
				return (false);
		}
		return (true);
	}

	private static boolean hasValidDate(String date)
	{
		int[] parts;
		int day;
		int month;
		int year;
		int maxDays;

		parts = SplitUtils.splitStringToInt(date, '/');
		
    if (parts.length != 3) // De acordo com o formato "00/00/0000" o (split by /) deve retornar um array de String com tamanho 3.
			return (false);

		day = parts[0];
		month = parts[1];
		year = parts[2];

		if (year < MIN_YEAR)
			return (false);

		maxDays = getDaysInMonth(month);
		
    if (maxDays == 0) // se for zero então o mes passado não existe
			return (false);

		return (day >= 1 && day <= maxDays); // verifica se abas são afirmações verdadeiras se ao menos uma for falsa então retorna falso.
	}

	private static int getDaysInMonth(int monthNumber)
	{
		for (Months currentMonth : Months.values())
		{
			if (currentMonth.getNumber() == monthNumber)
				return (currentMonth.getDays());
		}
		return (0);
	}
}


// Depois eu comento o resto do código para explicação.
//
// Vou criar uma task paralela para isso.
