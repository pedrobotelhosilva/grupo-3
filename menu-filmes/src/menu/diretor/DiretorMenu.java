package menu.diretor;

import java.util.List;
import java.util.Scanner;

import model.Diretor;
import service.DiretorService;
import validation.diretor.DiretorValidation;

public final class DiretorMenu
{
    private DiretorService diretorService;
    private Scanner scanner;

    public DiretorMenu(Scanner scanner, DiretorService diretorService)
    {
        this.scanner = scanner;
        this.diretorService = diretorService;
    }

    public void exibirMenu()
    {
        int opcao = -1;

        while (opcao != 0)
        {
            System.out.println("\n=== MENU DE DIRETORES ===");
            System.out.println("1 - Cadastrar Diretor");
            System.out.println("2 - Listar Diretores");
            System.out.println("3 - Buscar Diretor por Nome");
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
                    cadastrarDiretor();
                    break;
                case 2:
                    listarDiretores();
                    break;
                case 3:
                    buscarDiretor();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public boolean cadastrarDiretor()
    {
        String nome;
        String data;
        String nacionalidade;

        System.out.print("Nome: ");
        nome = scanner.nextLine();
        if (!DiretorValidation.validateNome(nome))
        {
            System.out.println("Nome inválido!");
            return (false);
        }

        if (!DiretorValidation.validationNomeExiste(nome, diretorService.listarDiretores()))
        {
            System.out.println("Já existe um diretor com esse nome cadastrado!");
            return (false);
        }

        System.out.print("Data de nascimento: ");
        data = scanner.nextLine();
        if (!DiretorValidation.validateDataNascimento(data))
        {
            System.out.println("Data de nascimento inválida!");
            return (false);
        }

        System.out.print("Nacionalidade: ");
        nacionalidade = scanner.nextLine();
        if (!DiretorValidation.validateNacionalidade(nacionalidade))
        {
            System.out.println("Nacionalidade inválida!");
            return (false);
        }

        Diretor diretor = new Diretor(nome, data, nacionalidade);
        diretorService.cadastrarDiretor(diretor);

        System.out.println("Diretor cadastrado com sucesso!");
        return (true);
    }

    public void listarDiretores()
    {
        List<Diretor> diretores = diretorService.listarDiretores();

        System.out.println("\n=== LISTA DE DIRETORES ===");

        if (diretores.isEmpty())
        {
            System.out.println("Nenhum diretor cadastrado.");
            return;
        }

        for (Diretor diretor : diretores)
            System.out.println(diretor.exibirInformacoes());
    }

    public void buscarDiretor()
    {
        System.out.print("Nome do diretor: ");
        String nome = scanner.nextLine();

        Diretor diretor = diretorService.buscarDiretorPorNome(nome);

        if (diretor == null)
        {
            System.out.println("Diretor não encontrado.");
            return;
        }

        System.out.println("\n=== DIRETOR ENCONTRADO ===");
        System.out.println(diretor.exibirInformacoes());
    }
}
