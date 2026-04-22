package menu.filme;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Ator;
import model.Diretor;
import model.Filme;
import service.AtorService;
import service.DiretorService;
import service.FilmeService;

public class FilmeMenu
{
    private Scanner scanner;
    private FilmeService filmeService;
    private AtorService atorService;
    private DiretorService diretorService;

    public FilmeMenu(Scanner scanner, FilmeService filmeService, AtorService atorService, DiretorService diretorService)
    {
        this.scanner = scanner;
        this.filmeService = filmeService;
        this.atorService = atorService;
        this.diretorService = diretorService;
    }

    public void exibirMenu()
    {
        int opcao = -1;

        while (opcao != 0)
        {
            System.out.println("\n=== MENU DE FILMES ===");
            System.out.println("1 - Cadastrar Filme");
            System.out.println("2 - Buscar Filme por Nome");
            System.out.println("3 - Listar Filmes");
            System.out.println("4 - Associar Diretor a Filme");
            System.out.println("5 - Associar Ator a Filme");
            System.out.println("0 - Voltar");
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
                    cadastrarFilme();
                    break;
                case 2:
                    buscarFilme();
                    break;
                case 3:
                    listarFilmes();
                    break;
                case 4:
                    associarDiretorAFilme();
                    break;
                case 5:
                    associarAtorAFilme();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public void cadastrarFilme()
    {
        String nome;
        String dataLancamento;
        double orcamento;
        String descricao;
        String nomeDiretor;
        String entradaAtores;
        Diretor diretor;
        List<Ator> atores;
        Filme filme;

        System.out.print("Nome do filme: ");
        nome = scanner.nextLine();

        if (nome.isBlank())
        {
            System.out.println("Nome inválido.");
            return;
        }

        if (filmeService.buscarFilmePorNome(nome) != null)
        {
            System.out.println("Já existe um filme com esse nome.");
            return;
        }

        System.out.print("Data de lançamento: ");
        dataLancamento = scanner.nextLine();

        System.out.print("Orçamento: ");
        if (!scanner.hasNextDouble())
        {
            System.out.println("Orçamento inválido.");
            scanner.nextLine();
            return;
        }
        orcamento = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Descrição: ");
        descricao = scanner.nextLine();

        System.out.print("Nome do diretor: ");
        nomeDiretor = scanner.nextLine();

        diretor = null;
        if (!nomeDiretor.isBlank())
        {
            diretor = diretorService.buscarDiretorPorNome(nomeDiretor);

            if (diretor == null)
            {
                System.out.println("Diretor não encontrado. Cadastre o diretor antes.");
                return;
            }
        }

        System.out.print("Digite os nomes dos atores (separados por vírgula): ");
        entradaAtores = scanner.nextLine();

        atores = new ArrayList<>();
        if (!entradaAtores.isBlank())
        {
            String[] nomes = entradaAtores.split(",");

            for (String nomeAtor : nomes)
            {
                Ator ator = atorService.buscarAtorPorNome(nomeAtor.trim());

                if (ator == null)
                {
                    System.out.println("Ator não encontrado: " + nomeAtor.trim());
                    return;
                }
                atores.add(ator);
            }
        }

        filme = new Filme(nome, dataLancamento, orcamento, descricao, diretor, atores);
        filmeService.cadastrarFilme(filme);

        System.out.println("Filme cadastrado com sucesso!");
    }

    public void buscarFilme()
    {
        System.out.print("Nome do filme: ");
        String nome = scanner.nextLine();

        Filme filme = filmeService.buscarFilmePorNome(nome);

        if (filme == null)
        {
            System.out.println("Filme não encontrado.");
            return;
        }

        System.out.println("\n=== FILME ENCONTRADO ===");
        System.out.println(filme.exibirDetalhes());
    }

    public void listarFilmes()
    {
        List<Filme> filmes = filmeService.listarFilmes();

        System.out.println("\n=== LISTA DE FILMES ===");

        if (filmes.isEmpty())
        {
            System.out.println("Nenhum filme cadastrado.");
            return;
        }

        for (Filme filme : filmes)
        {
            System.out.println(filme.exibirDetalhes());
            System.out.println("----------------------------------");
        }
    }

    public void associarDiretorAFilme()
    {
        System.out.print("Nome do filme: ");
        String nomeFilme = scanner.nextLine();

        Filme filme = filmeService.buscarFilmePorNome(nomeFilme);

        if (filme == null)
        {
            System.out.println("Filme não encontrado.");
            return;
        }

        System.out.print("Nome do diretor: ");
        String nomeDiretor = scanner.nextLine();

        Diretor diretor = diretorService.buscarDiretorPorNome(nomeDiretor);

        if (diretor == null)
        {
            System.out.println("Diretor não encontrado.");
            return;
        }

        filmeService.associarDiretor(filme, diretor);
    }

    public void associarAtorAFilme()
    {
        System.out.print("Nome do filme: ");
        String nomeFilme = scanner.nextLine();

        Filme filme = filmeService.buscarFilmePorNome(nomeFilme);

        if (filme == null)
        {
            System.out.println("Filme não encontrado.");
            return;
        }

        System.out.print("Nome do ator: ");
        String nomeAtor = scanner.nextLine();

        Ator ator = atorService.buscarAtorPorNome(nomeAtor);

        if (ator == null)
        {
            System.out.println("Ator não encontrado.");
            return;
        }

        filmeService.associarAtor(filme, ator);
    }
}
