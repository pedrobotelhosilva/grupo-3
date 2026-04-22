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
        nomeDiretor = MenuUtils.lerTexto(this.scanner, "Nome do diretor: ");

        diretor = null;
        if (!nomeDiretor.isBlank())
        {
            diretor = this.diretorService.buscarDiretorPorNome(nomeDiretor);

            if (diretor == null)
            {
                System.out.println("Diretor não encontrado. Cadastre o diretor antes.");
                return;
            }
        }

        entradaAtores = MenuUtils.lerTexto(this.scanner, "Digite os nomes dos atores (separados por vírgula): ");
        atores = new ArrayList<>();

        if (!entradaAtores.isBlank())
        {
            String[] nomes = entradaAtores.split(",");

            for (String nomeAtor : nomes)
            {
                Ator ator = this.atorService.buscarAtorPorNome(nomeAtor.trim());

                if (ator == null)
                {
                    System.out.println("Ator não encontrado: " + nomeAtor.trim());
                    return;
                }
                atores.add(ator);
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

        nome = MenuUtils.lerTexto(this.scanner, "Nome do filme: ");
        filme = this.filmeService.buscarFilmePorNome(nome);

        if (filme == null)
        {
            System.out.println("Filme não encontrado.");
            return;
        }

        System.out.println(filme.exibirDetalhes());
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

        nomeFilme = MenuUtils.lerTexto(this.scanner, "Nome do filme: ");
        filme = this.filmeService.buscarFilmePorNome(nomeFilme);

        if (filme == null)
        {
            System.out.println("Filme não encontrado.");
            return;
        }

        nomeDiretor = MenuUtils.lerTexto(this.scanner, "Nome do diretor: ");
        diretor = this.diretorService.buscarDiretorPorNome(nomeDiretor);

        if (diretor == null)
        {
            System.out.println("Diretor não encontrado.");
            return;
        }

        this.filmeService.associarDiretor(filme, diretor);
    }

    public void associarAtorAFilme()
    {
        String nomeFilme;
        String nomeAtor;
        Filme filme;
        Ator ator;

        MenuUtils.titulo("ASSOCIAR ATOR A FILME");

        nomeFilme = MenuUtils.lerTexto(this.scanner, "Nome do filme: ");
        filme = this.filmeService.buscarFilmePorNome(nomeFilme);

        if (filme == null)
        {
            System.out.println("Filme não encontrado.");
            return;
        }

        nomeAtor = MenuUtils.lerTexto(this.scanner, "Nome do ator: ");
        ator = this.atorService.buscarAtorPorNome(nomeAtor);

        if (ator == null)
        {
            System.out.println("Ator não encontrado.");
            return;
        }

        this.filmeService.associarAtor(filme, ator);
    }
}
