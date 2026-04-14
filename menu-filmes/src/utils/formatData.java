package utils;

public class Check
{
  private final String  formatOfData = "00/00/0000"
  private final int     maxOfDays = 31;
  private final int     maxOfMouths = 12;
  private final int     minOfYars = 1800; // Uma sugstão, podemos retinar com o tempo

  public static boolean Format(String data)
  {

  }

  public static boolean Size() 
}

public class Split
{
  public static int[] splitStringToInt(String str, char separetor)
  {
    int     countSeparetor = countSeparetor(str, '/');
    String  splitString[] = str.split("/");
    int     listData[] = new int[countSeparetor];
    int     i = 0;

    for (String strToInt: splitString)
    {
      listData[i++] = Integer.parseInt(strToInt);
    }
    return (listData);
  }

  public static int countSeparetor(String str, char separetor)
  {
    int countSeparetor = 0;

    for (int i = 0; i < str.length(); i++)
    {
      if (str.charAT(i) == separetor)
        countSeparetor++;
    }
    return (countSeparetor);
  }
}
