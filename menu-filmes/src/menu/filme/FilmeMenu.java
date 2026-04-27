package menu.filme;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exceptions.AtorNaoEncontradoException;
import exceptions.DiretorNaoEncontradoException;
import exceptions.FilmeNaoEncontradoException;
import model.Ator;
import model.Diretor;
import model.Filme;
import service.AtorService;
import service.DiretorService;
import service.FilmeService;
import utils.general.MenuUtils;

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
        int opcao;

        opcao = -1;
        while (opcao != 0)
        {
            MenuUtils.limparTela();
            MenuUtils.titulo("MENU DE FILMES");
            System.out.println("1 - Cadastrar filme");
            System.out.println("2 - Buscar filme por nome");
            System.out.println("3 - Listar filmes");
            System.out.println("4 - Associar diretor a filme");
            System.out.println("5 - Associar ator a filme");
            System.out.println("0 - Voltar");
            MenuUtils.separador();

            opcao = MenuUtils.lerOpcao(this.scanner, "Escolha uma opção: ");
            MenuUtils.limparTela();

            switch (opcao)
            {
                case 1:
                    this.cadastrarFilme();
                    MenuUtils.pausar(this.scanner);
                    break;
                case 2:
                    this.buscarFilme();
                    MenuUtils.pausar(this.scanner);
                    break;
                case 3:
                    this.listarFilmes();
                    MenuUtils.pausar(this.scanner);
                    break;
                case 4:
                    this.associarDiretorAFilme();
                    MenuUtils.pausar(this.scanner);
                    break;
                case 5:
                    this.associarAtorAFilme();
                    MenuUtils.pausar(this.scanner);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
                    MenuUtils.pausar(this.scanner);
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

        MenuUtils.titulo("CADASTRAR FILME");

        nome = MenuUtils.lerTexto(this.scanner, "Nome do filme: ");
        dataLancamento = MenuUtils.lerTexto(this.scanner, "Data de lançamento (xx/xx/xxxx): ");
        orcamento = MenuUtils.lerDouble(this.scanner, "Orçamento: ");
        descricao = MenuUtils.lerTexto(this.scanner, "Descrição: ");

        if (this.perguntarSeDesejaListar("Deseja listar os diretores cadastrados antes de escolher? (s/n): "))
            this.listarDiretoresDisponiveis();

        nomeDiretor = MenuUtils.lerTexto(this.scanner, "Nome do diretor: ");

        diretor = null;
        if (!nomeDiretor.isBlank())
        {
            try
            {
                diretor = this.diretorService.buscarDiretorPorNome(nomeDiretor);
            }
            catch (DiretorNaoEncontradoException e)
            {
                System.out.println(e.getMessage());
                return;
            }
        }

        if (this.perguntarSeDesejaListar("Deseja listar os atores cadastrados antes de escolher? (s/n): "))
            this.listarAtoresDisponiveis();

        entradaAtores = MenuUtils.lerTexto(this.scanner, "Digite os nomes dos atores (separados por vírgula): ");
        atores = new ArrayList<>();

        if (!entradaAtores.isBlank())
        {
            String[] nomes = entradaAtores.split(",");

            for (String nomeAtor : nomes)
            {
                try
                {
                    Ator ator = this.atorService.buscarAtorPorNome(nomeAtor.trim());
                    atores.add(ator);
                }
                catch (AtorNaoEncontradoException e)
                {
                    System.out.println(e.getMessage() + " Nome informado: " + nomeAtor.trim());
                    return;
                }
            }
        }

        filme = new Filme(nome, dataLancamento, orcamento, descricao, diretor, atores);
        this.filmeService.cadastrarFilme(filme);

        System.out.println("Filme cadastrado com sucesso.");
    }

    public void buscarFilme()
    {
        String nome;
        Filme filme;

        MenuUtils.titulo("BUSCA DE FILME");

        if (this.perguntarSeDesejaListar("Deseja listar os filmes cadastrados antes da busca? (s/n): "))
            this.listarFilmes();

        nome = MenuUtils.lerTexto(this.scanner, "Nome do filme: ");

        try
        {
            filme = this.filmeService.buscarFilmePorNome(nome);
            System.out.println(filme.exibirDetalhes());
        }
        catch (FilmeNaoEncontradoException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void listarFilmes()
    {
        List<Filme> filmes = this.filmeService.listarFilmes();

        MenuUtils.titulo("LISTA DE FILMES");

        if (filmes.isEmpty())
        {
            System.out.println("Nenhum filme cadastrado.");
            return;
        }

        for (int i = 0; i < filmes.size(); i++)
        {
            System.out.println((i + 1) + " - " + filmes.get(i).getNome());
            MenuUtils.separador();
            System.out.println(filmes.get(i).exibirDetalhes());
            MenuUtils.separador();
        }
    }

    public void associarDiretorAFilme()
    {
        String nomeFilme;
        String nomeDiretor;
        Filme filme;
        Diretor diretor;

        MenuUtils.titulo("ASSOCIAR DIRETOR A FILME");

        if (this.perguntarSeDesejaListar("Deseja listar os filmes cadastrados antes de escolher? (s/n): "))
            this.listarFilmes();

        nomeFilme = MenuUtils.lerTexto(this.scanner, "Nome do filme: ");

        if (this.perguntarSeDesejaListar("Deseja listar os diretores cadastrados antes de escolher? (s/n): "))
            this.listarDiretoresDisponiveis();

        nomeDiretor = MenuUtils.lerTexto(this.scanner, "Nome do diretor: ");

        try
        {
            filme = this.filmeService.buscarFilmePorNome(nomeFilme);
            diretor = this.diretorService.buscarDiretorPorNome(nomeDiretor);
            this.filmeService.associarDiretor(filme, diretor);
        }
        catch (FilmeNaoEncontradoException | DiretorNaoEncontradoException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void associarAtorAFilme()
    {
        String nomeFilme;
        String nomeAtor;
        Filme filme;
        Ator ator;

        MenuUtils.titulo("ASSOCIAR ATOR A FILME");

        if (this.perguntarSeDesejaListar("Deseja listar os filmes cadastrados antes de escolher? (s/n): "))
            this.listarFilmes();

        nomeFilme = MenuUtils.lerTexto(this.scanner, "Nome do filme: ");

        if (this.perguntarSeDesejaListar("Deseja listar os atores cadastrados antes de escolher? (s/n): "))
            this.listarAtoresDisponiveis();

        nomeAtor = MenuUtils.lerTexto(this.scanner, "Nome do ator: ");

        try
        {
            filme = this.filmeService.buscarFilmePorNome(nomeFilme);
            ator = this.atorService.buscarAtorPorNome(nomeAtor);
            this.filmeService.associarAtor(filme, ator);
        }
        catch (FilmeNaoEncontradoException | AtorNaoEncontradoException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private boolean perguntarSeDesejaListar(String mensagem)
    {
        String resposta;

        resposta = MenuUtils.lerTexto(this.scanner, mensagem);
        return (resposta.equalsIgnoreCase("s"));
    }

    private void listarAtoresDisponiveis()
    {
        List<Ator> atores = this.atorService.listarAtores();

        MenuUtils.titulo("ATORES CADASTRADOS");

        if (atores.isEmpty())
        {
            System.out.println("Nenhum ator cadastrado.");
            return;
        }

        for (int i = 0; i < atores.size(); i++)
        {
            System.out.println((i + 1) + " - " + atores.get(i).getNome());
        }

        MenuUtils.separador();
    }

    private void listarDiretoresDisponiveis()
    {
        List<Diretor> diretores = this.diretorService.listarDiretores();

        MenuUtils.titulo("DIRETORES CADASTRADOS");

        if (diretores.isEmpty())
        {
            System.out.println("Nenhum diretor cadastrado.");
            return;
        }

        for (int i = 0; i < diretores.size(); i++)
        {
            System.out.println((i + 1) + " - " + diretores.get(i).getNome());
        }

        MenuUtils.separador();
    }
}
