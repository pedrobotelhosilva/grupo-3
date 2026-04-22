package menu.ator;

import java.util.List;
import java.util.Scanner;

import model.Ator;
import service.AtorService;
import utils.general.MenuUtils;
import validation.ator.AtorValidation;

public final class AtorMenu
{
    private AtorService atorService;
    private Scanner scanner;

    public AtorMenu(Scanner scanner, AtorService atorService)
    {
        this.scanner = scanner;
        this.atorService = atorService;
    }

    public void exibirMenu()
    {
        int opcao;

        opcao = -1;
        while (opcao != 0)
        {
            MenuUtils.limparTela();
            MenuUtils.titulo("MENU DE ATORES");
            System.out.println("1 - Cadastrar ator");
            System.out.println("2 - Listar atores");
            System.out.println("3 - Buscar ator por nome");
            System.out.println("0 - Voltar");
            MenuUtils.separador();

            opcao = MenuUtils.lerOpcao(this.scanner, "Escolha uma opção: ");
            MenuUtils.limparTela();

            switch (opcao)
            {
                case 1:
                    this.cadastrarAtor();
                    MenuUtils.pausar(this.scanner);
                    break;
                case 2:
                    this.listarAtores();
                    MenuUtils.pausar(this.scanner);
                    break;
                case 3:
                    this.buscarAtor();
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

    public boolean cadastrarAtor()
    {
        String nome;
        String data;
        String nacionalidade;

        MenuUtils.titulo("CADASTRAR ATOR");

        nome = MenuUtils.lerTexto(this.scanner, "Nome: ");
        if (!AtorValidation.validateNome(nome))
        {
            System.out.println("Nome inválido.");
            return (false);
        }

        if (!AtorValidation.validationNomeExiste(nome, this.atorService.listarAtores()))
        {
            System.out.println("Já existe um ator com esse nome cadastrado.");
            return (false);
        }

        data = MenuUtils.lerTexto(this.scanner, "Data de nascimento (xx/xx/xxxx): ");
        if (!AtorValidation.validateDataNascimento(data))
        {
            System.out.println("Data de nascimento inválida.");
            return (false);
        }

        nacionalidade = MenuUtils.lerTexto(this.scanner, "Nacionalidade (Por Extenso): ");
        if (!AtorValidation.validateNacionalidade(nacionalidade))
        {
            System.out.println("Nacionalidade inválida.");
            return (false);
        }

        Ator ator = new Ator(nome, data, nacionalidade);
        this.atorService.cadastrarAtor(ator);

        System.out.println("Ator cadastrado com sucesso.");
        return (true);
    }

    public void listarAtores()
    {
        List<Ator> atores = this.atorService.listarAtores();

        MenuUtils.titulo("LISTA DE ATORES");

        if (atores.isEmpty())
        {
            System.out.println("Nenhum ator cadastrado.");
            return;
        }

        for (int i = 0; i < atores.size(); i++)
        {
            System.out.println((i + 1) + " - " + atores.get(i).exibirInformacoes());
            MenuUtils.separador();
        }
    }

    public void buscarAtor()
    {
        String nome;
        Ator ator;

        MenuUtils.titulo("BUSCA DE ATOR");

        nome = MenuUtils.lerTexto(this.scanner, "Nome do ator: ");
        ator = this.atorService.buscarAtorPorNome(nome);

        if (ator == null)
        {
            System.out.println("Ator não encontrado.");
            return;
        }

        System.out.println(ator.exibirInformacoes());
    }
}
