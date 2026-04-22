package menu.ator;

import java.util.List;
import java.util.Scanner;

import model.Ator;
import service.AtorService;
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
        int opcao = -1;

        while (opcao != 0)
        {
            System.out.println("\n=== MENU DE ATORES ===");
            System.out.println("1 - Cadastrar Ator");
            System.out.println("2 - Listar Atores");
            System.out.println("3 - Buscar Ator por Nome");
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
                    cadastrarAtor();
                    break;
                case 2:
                    listarAtores();
                    break;
                case 3:
                    buscarAtor();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public boolean cadastrarAtor()
    {
        String nome;
        String data;
        String nacionalidade;

        System.out.print("Nome: ");
        nome = scanner.nextLine();
        if (!AtorValidation.validateNome(nome))
        {
            System.out.println("Nome inválido!");
            return (false);
        }

        if (!AtorValidation.validationNomeExiste(nome, atorService.listarAtores()))
        {
            System.out.println("Já existe um ator com esse nome cadastrado!");
            return (false);
        }

        System.out.print("Data de nascimento: ");
        data = scanner.nextLine();
        if (!AtorValidation.validateDataNascimento(data))
        {
            System.out.println("Data de nascimento inválida!");
            return (false);
        }

        System.out.print("Nacionalidade: ");
        nacionalidade = scanner.nextLine();
        if (!AtorValidation.validateNacionalidade(nacionalidade))
        {
            System.out.println("Nacionalidade inválida!");
            return (false);
        }

        Ator ator = new Ator(nome, data, nacionalidade);
        atorService.cadastrarAtor(ator);

        System.out.println("Ator cadastrado com sucesso!");
        return (true);
    }

    public void listarAtores()
    {
        List<Ator> atores = atorService.listarAtores();

        System.out.println("\n=== LISTA DE ATORES ===");

        if (atores.isEmpty())
        {
            System.out.println("Nenhum ator cadastrado.");
            return;
        }

        for (Ator ator : atores)
            System.out.println(ator.exibirInformacoes());
    }

    public void buscarAtor()
    {
        System.out.print("Nome do ator: ");
        String nome = scanner.nextLine();

        Ator ator = atorService.buscarAtorPorNome(nome);

        if (ator == null)
        {
            System.out.println("Ator não encontrado.");
            return;
        }

        System.out.println("\n=== ATOR ENCONTRADO ===");
        System.out.println(ator.exibirInformacoes());
    }
}
