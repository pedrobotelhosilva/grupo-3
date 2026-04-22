package utils.general;

import java.io.IOException;
import java.util.Scanner;

public final class MenuUtils
{
    private MenuUtils()
    {
    }

    public static void limparTela()
    {
        try
        {
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                new ProcessBuilder("clear").inheritIO().start().waitFor();
        }
        catch (IOException | InterruptedException e)
        {
            for (int i = 0; i < 50; i++)
                System.out.println();
        }
    }

    public static void titulo(String titulo)
    {
        System.out.println("==========================================");
        System.out.println(" " + titulo);
        System.out.println("==========================================");
    }

    public static void separador()
    {
        System.out.println("------------------------------------------");
    }

    public static int lerOpcao(Scanner scanner, String mensagem)
    {
        int opcao;

        while (true)
        {
            System.out.print(mensagem);

            if (scanner.hasNextInt())
            {
                opcao = scanner.nextInt();
                scanner.nextLine();
                return (opcao);
            }

            System.out.println("Opção inválida. Digite um número.");
            scanner.nextLine();
        }
    }

    public static double lerDouble(Scanner scanner, String mensagem)
    {
        double valor;

        while (true)
        {
            System.out.print(mensagem);

            if (scanner.hasNextDouble())
            {
                valor = scanner.nextDouble();
                scanner.nextLine();
                return (valor);
            }

            System.out.println("Valor inválido. Digite um número.");
            scanner.nextLine();
        }
    }

    public static String lerTexto(Scanner scanner, String mensagem)
    {
        System.out.print(mensagem);
        return (scanner.nextLine());
    }

    public static void pausar(Scanner scanner)
    {
        System.out.println();
        System.out.print("Pressione ENTER para continuar...");
        scanner.nextLine();
    }
}
