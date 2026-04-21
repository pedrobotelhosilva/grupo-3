package menu;

import model.Ator;
import model.Diretor;
import model.Filme;
import repository.FilmeFileRepository;
import service.AtorService;
import service.DiretorService;
import service.FilmeService;
import menu.ator.AtorMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private Scanner scanner;
    private AtorService atorService;
    private DiretorService diretorService;
    private FilmeService filmeService;
    private AtorMenu  atorMenu; // implement-AtorUtils

    public Menu(AtorService atorService, DiretorService diretorService, FilmeService filmeService) {
        this.scanner = new Scanner(System.in);
        this.atorService = atorService;
        this.diretorService = diretorService;
        this.filmeService = filmeService;
        this.atorMenu = new AtorMenu(this.scanner, this.atorService); // implement-AtorUtils
    }

    public void exibirMenu() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== MENU ===");
            System.out.println("1 - Cadastrar Ator");
            System.out.println("2 - Cadastrar Diretor");
            System.out.println("3 - Cadastrar Filme");
            System.out.println("4 - Associar Diretor a Filme");
            System.out.println("5 - Associar Ator a Filme");
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
                    cadastrarAtor();
                    break;
                case 2:
                //    cadastrarDiretor();
                    break;
                case 3:
                //    cadastrarFilme();
                    break;
                case 4:
                //    associarDiretor();
                    break;
                case 5:
                //    associarAtor();
                    break;
                case 6:
                //   buscarFilme();
                    break;
                case 7:
                //   listarFilmes();
                    break;
                case 8:
                    List<Diretor> diretores = diretorService.listarDiretores();
                    System.out.println("\n=== LISTA DE DIRETORES ===");
                    for (Diretor d : diretores) {
                        System.out.println(d.getNome());
                    }
                    break;
                case 9:
                    List<Ator> atores = atorService.listarAtores();
                    System.out.println("\n=== LISTA DE ATORES ===");
                    for (Ator a : atores) {
                        System.out.println(a.getNome());
                    }
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
          System.out.println(atorService.listarAtores());
        }
    }

    private void cadastrarAtor() {
      atorMenu.cadastrarAtor();   
    }

/*
                    Ator ator = new Ator(nomeAtor, data, nacionalidade, genero);

                    atorService.cadastrarAtor(ator);

                    break;

                case 2:
                    System.out.print("Nome do diretor: ");
                    String nomeDiretor = scanner.nextLine();

                    System.out.println("Digite a data de nascimento do diretor: ");
                    String dataNascimento = scanner.nextLine();

                    System.out.println("Digite a nacionalidade do diretor: ");
                    String nacionalidade2 = scanner.nextLine();

                    System.out.println("Digite o estilo de direção do diretor: ");
                    String estiloDirecao = scanner.nextLine();


                    Diretor diretor = new Diretor(nomeDiretor, dataNascimento, nacionalidade2, estiloDirecao);
                    diretorService.cadastrarDiretor(diretor);
                    break;

                case 3:
                    filmeService.adicionarFilme();
                    break;

                case 4:
                    System.out.print("Nome do filme: ");
                    String nomeFilme = scanner.nextLine().trim();

                    Filme nomeFilmeVar = filmeService.buscarFilmePorNome(nomeFilme);

                    if (nomeFilmeVar == null) {
                        System.out.println("Filme não encontrado!");
                        break;
                    }

                    System.out.print("Nome do diretor: ");
                    String nomeDiretorVar = scanner.nextLine().trim();

                    Diretor nomeDiretor2 = diretorService.buscarDiretorPorNome(nomeDiretorVar);

                    if (nomeDiretor2 == null) {
                        System.out.println("Diretor não encontrado!");
                        break;
                    }

                    filmeService.associarDiretor(nomeFilmeVar, nomeDiretor2);
                    break;


                case 5:
                    System.out.print("Nome do filme: ");
                    String nomeFilme2 = scanner.nextLine().trim();

                    Filme filme3 = filmeService.buscarFilmePorNome(nomeFilme2);

                    if (filme3 == null) {
                        System.out.println("Filme não encontrado!");
                        break;
                    }

                    System.out.print("Nome do ator: ");
                    String nomeAtor3 = scanner.nextLine().trim();

                    Ator ator3 = atorService.buscarAtorPorNome(nomeAtor3);

                    if (ator3 == null) {
                        System.out.println("Ator não encontrado!");
                        break;
                    }

                    filmeService.associarAtor(filme3, ator3);
                    break;
                case 6:
                    System.out.print("Nome do filme: ");
                    String nome4 = scanner.nextLine();

                    Filme filme4 = filmeService.buscarFilmePorNome(nome4);

                    if (filme4 == null) {
                        System.out.println("Filme não encontrado!");
                    } else {
                        System.out.println("\n=== FILME ENCONTRADO ===");
                        System.out.println(filme4.exibirDetalhes());
                    }
                    break;


                case 7:
                    List<Filme> filmes = filmeService.listarFilmes();

                    System.out.println("\n=== LISTA DE FILMES ===");

                    for (Filme f : filmes) {
                        System.out.println(f);
                    }
                    break;

                case 8:
                    List<Diretor> diretores = diretorService.listarDiretores();

                    System.out.println("\n=== LISTA DE DIRETORES ===");

                    for (Diretor d : diretores) {
                        System.out.println(d.getNome());
                    }
                    break;

                case 9:
                    List<Ator> atores2 = atorService.listarAtores();
                    System.out.println("\n=== LISTA DE ATORES ===");
                    for (Ator a : atores2) {
                        System.out.println(a.getNome());
                    }
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }*/
}

