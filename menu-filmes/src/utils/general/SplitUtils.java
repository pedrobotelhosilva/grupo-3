package utils.general;

public final class SplitUtils
{
	private SplitUtils()
	{
	}

	public static int[] splitStringToInt(String str, char separator)
	{
		String[]  parts;
		int[]     numbers;

		parts = str.split(String.valueOf(separator));
		numbers = new int[parts.length];

		for (int i = 0; i < parts.length; i++)
			numbers[i] = Integer.parseInt(parts[i]);
		
    return (numbers);
	}
}
