package menu;

import java.util.Scanner;

import menu.ator.AtorMenu;
import menu.diretor.DiretorMenu;
import menu.filme.FilmeMenu;
import service.AtorService;
import service.DiretorService;
import service.FilmeService;

public class Menu
{
    private Scanner scanner;
    private AtorMenu atorMenu;
    private DiretorMenu diretorMenu;
    private FilmeMenu filmeMenu;

    public Menu(AtorService atorService, DiretorService diretorService, FilmeService filmeService)
    {
        this.scanner = new Scanner(System.in);
        this.atorMenu = new AtorMenu(this.scanner, atorService);
        this.diretorMenu = new DiretorMenu(this.scanner, diretorService);
        this.filmeMenu = new FilmeMenu(this.scanner, filmeService, atorService, diretorService);
    }

    public void exibirMenu()
    {
        int opcao = -1;

        while (opcao != 0)
        {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Menu de Atores");
            System.out.println("2 - Menu de Diretores");
            System.out.println("3 - Menu de Filmes");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            if (!scanner.hasNextInt())
            {
                System.out.println("Digite um número válido.");
                scanner.nextLine();
                continue;
            }

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao)
            {
                case 1:
                    atorMenu.exibirMenu();
                    break;
                case 2:
                    diretorMenu.exibirMenu();
                    break;
                case 3:
                    filmeMenu.exibirMenu();
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
