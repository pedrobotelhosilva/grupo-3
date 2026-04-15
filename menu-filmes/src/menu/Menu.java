package menu;

import model.Ator;
import model.Diretor;
import model.Filme;
import repository.FilmeFileRepository;
import service.AtorService;
import service.DiretorService;
import service.FilmeService;
import utils.Check;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private Scanner scanner;
    private AtorService atorService;
    private DiretorService diretorService;
    private FilmeService filmeService;

    public Menu(AtorService atorService, DiretorService diretorService, FilmeService filmeService) {
        this.scanner = new Scanner(System.in);
        this.atorService = atorService;
        this.diretorService = diretorService;
        this.filmeService = filmeService;
    }

    public void exibirMenu() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== MENU ===");
            System.out.println("1 - Cadastrar Ator");
            System.out.println("2 - Cadastrar Diretor");
            System.out.println("3 - Cadastrar Filme");
            System.out.println("4 - Associar Diretor a model.Filme");
            System.out.println("5 - Associar Ator a model.Filme");
            System.out.println("6 - Buscar Filme por Nome");
            System.out.println("7 - Listar todos os Filmes Cadastrados");
            System.out.println("0 - Sair");

            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    cadastrarAtor();
                    break;
                case 2:
                    cadastrarDiretor();
                    break;
                case 3:
                    cadastrarFilme();
                    break;
                case 4:
                    associarDiretor();
                    break;
                case 5:
                    associarAtor();
                    break;
                case 6:
                    buscarFilme();
                    break;
                case 7:
                    listarFilmes();
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarAtor() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Data de nascimento: ");
        String data = scanner.nextLine();

////////////////////////////////////////////////////////////////////////
/////***********************TESTE*********************************/////
//////////////////////////////////////////////////////////////////////
////////if (!Check.validate(data))///////////////////////////////////
//////////System.out.println("Errado ai meu chef");/////////////////
///////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////

        System.out.print("Nacionalidade: ");
        String nacionalidade = scanner.nextLine();

        System.out.print("Gênero artístico: ");
        String genero = scanner.nextLine();

        Ator ator = new Ator(nome, data, nacionalidade, genero);
        atorService.cadastrarAtor(ator);

        System.out.println("model.Ator cadastrado com sucesso!");
    }

    private void cadastrarDiretor() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Data de nascimento: ");
        String data = scanner.nextLine();

        System.out.print("Nacionalidade: ");
        String nacionalidade = scanner.nextLine();

        System.out.print("Estilo de direção: ");
        String estilo = scanner.nextLine();

        Diretor diretor = new Diretor(nome, data, nacionalidade, estilo);
        diretorService.cadastrarDiretor(diretor);

        System.out.println("model.Diretor cadastrado com sucesso!");
    }

    private void cadastrarFilme() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Data de lançamento: ");
        String data = scanner.nextLine();

        System.out.print("Orçamento: ");
        double orcamento = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        Filme filme = new Filme(nome, data, orcamento, descricao);
        filmeService.cadastrarFilme(filme);

        System.out.println("model.Filme cadastrado com sucesso!");
    }

    private void associarDiretor() {
        System.out.print("Nome do filme: ");
        String nomeFilme = scanner.nextLine();

        Filme filme = filmeService.pesquisarFilmePorNome(nomeFilme);

        if (filme == null) {
            System.out.println("model.Filme não encontrado!");
            return;
        }

        System.out.print("Nome do diretor: ");
        String nomeDiretor = scanner.nextLine();

        Diretor diretor = diretorService.buscarDiretorPorNome(nomeDiretor);

        if (diretor == null) {
            System.out.println("model.Diretor não encontrado!");
            return;
        }

        filmeService.associarDiretorAoFilme(filme, diretor);
        System.out.println("model.Diretor associado com sucesso!");
    }

    private void associarAtor() {
        System.out.print("Nome do filme: ");
        String nomeFilme = scanner.nextLine();

        Filme filme = filmeService.pesquisarFilmePorNome(nomeFilme);

        if (filme == null) {
            System.out.println("model.Filme não encontrado!");
            return;
        }

        System.out.print("Nome do ator: ");
        String nomeAtor = scanner.nextLine();

        Ator ator = atorService.buscarAtorPorNome(nomeAtor);

        if (ator == null) {
            System.out.println("model.Ator não encontrado!");
            return;
        }

        filmeService.associarAtorAoFilme(filme, ator);
        System.out.println("model.Ator associado com sucesso!");
    }

    private void buscarFilme() {
        System.out.print("Nome do filme: ");
        String nome = scanner.nextLine();

        Filme filme = filmeService.pesquisarFilmePorNome(nome);

        if (filme != null) {
            System.out.println("\n=== FILME ENCONTRADO ===");
            System.out.println(filme.exibirDetalhes());
        } else {
            System.out.println("model.Filme não encontrado!");
        }
    }
//    private void listarFilmes() {
//        System.out.println("\n=== LISTA DE FILMES ===");
//        List<Filme> filmes = FilmeFileRepository.buscarTodos();
//
//        for (Filme f : filmes) {
//            System.out.println(f.getNome());
//        }
        private void listarFilmes() {
            System.out.println("\n=== LISTA DE FILMES ===");

            List<Filme> filmes = filmeService.listarFilmes();

            for (Filme f : filmes) {
                System.out.println(f.getNome());
            }
        }

    }

