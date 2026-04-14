package utils;

public class Split
{
	public static int[] splitStringToInt(String str, char separator)
	{
		String[] splitParts;
		int[] numbers;
		int i;

		splitParts = str.split(String.valueOf(separator));
		numbers = new int[splitParts.length];
		i = 0;
		for (String part : splitParts)
		{
			numbers[i] = Integer.parseInt(part);
			i++;
		}
		return (numbers);
	}
}
