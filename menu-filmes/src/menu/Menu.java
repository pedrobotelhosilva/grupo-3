package menu;

import model.Ator;
import model.Diretor;
import model.Filme;
import repository.FilmeFileRepository;
import service.AtorService;
import service.DiretorService;
import service.FilmeService;
import menu.ator.AtorMenu;
import menu.diretor.DiretorMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

  private Scanner scanner;
  private AtorService atorService;
  private DiretorService diretorService;
  private FilmeService filmeService;
  private AtorMenu  atorMenu; // implement-AtorUtils
  private DiretorMenu  diretorMenu; // implement-DiretorUtils

  public Menu(AtorService atorService, DiretorService diretorService, FilmeService filmeService) {
    this.scanner = new Scanner(System.in);
    this.atorService = atorService;
    this.diretorService = diretorService;
    this.filmeService = filmeService;
    this.atorMenu = new AtorMenu(this.scanner, this.atorService); // implement-AtorUtils
    this.diretorMenu = new DiretorMenu(this.scanner, this.diretorService); // implement-AtorUtils
  }

  public void exibirMenu() {
    int opcao = -1;

    while (opcao != 0) {
      System.out.println("\n=== MENU ===");
      System.out.println("1 - Cadastrar Ator");
      System.out.println("2 - Cadastrar Diretor");
      System.out.println("3 - Cadastrar Filme");
      //System.out.println("4 - Associar Diretor a Filme");
      //System.out.println("5 - Associar Ator a Filme");
      System.out.println("6 - Buscar Filme por Nome");
      System.out.println("7 - Listar todos os Filmes Cadastrados");
      System.out.println("8 - Listar todos os Diretores Cadastrados");
      System.out.println("9 - Listar todos os Atores Cadastrados");
      System.out.println("0 - Sair");

      System.out.print("Escolha uma opção: ");
      opcao = scanner.nextInt();
      scanner.nextLine(); // limpar buffer

      switch (opcao) {
        case 1:
          atorMenu.cadastrarAtor();   
          break;
        case 2:
          diretorMenu.cadastrarDiretor();   
          break;
        case 3:
        //    cadastrarFilme();
          break;
       // case 4:
        //    diretorMenu.associarDiretor();
       //   break;
       // case 5:
        //    atorMenu.associarAtor();
       //   break;
        case 6:
        //   buscarFilme();
          break;
        case 7:
        //   listarFilmes();
          break;
        case 8:
          diretorMenu.listarDiretores();
          break;
        case 9:
          atorMenu.listarAtores();
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
