package menu;

import java.util.Scanner;

import menu.ator.AtorMenu;
import menu.diretor.DiretorMenu;
import menu.filme.FilmeMenu;
import service.AtorService;
import service.DiretorService;
import service.FilmeService;
import utils.general.MenuUtils;

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
        int opcao;

        opcao = -1;
        while (opcao != 0)
        {
            MenuUtils.limparTela();
            MenuUtils.titulo("MENU PRINCIPAL");
            System.out.println("1 - Atores");
            System.out.println("2 - Diretores");
            System.out.println("3 - Filmes");
            System.out.println("0 - Sair");
            MenuUtils.separador();

            opcao = MenuUtils.lerOpcao(this.scanner, "Escolha uma opção: ");
            MenuUtils.limparTela();

            switch (opcao)
            {
                case 1:
                    this.atorMenu.exibirMenu();
                    break;
                case 2:
                    this.diretorMenu.exibirMenu();
                    break;
                case 3:
                    this.filmeMenu.exibirMenu();
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    MenuUtils.pausar(this.scanner);
            }
        }
    }
}
