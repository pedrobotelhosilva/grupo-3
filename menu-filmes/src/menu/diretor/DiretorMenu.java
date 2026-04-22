package menu.diretor;

import java.util.List;
import java.util.Scanner;

import model.Diretor;
import service.DiretorService;
import utils.general.MenuUtils;
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
        int opcao;

        opcao = -1;
        while (opcao != 0)
        {
            MenuUtils.limparTela();
            MenuUtils.titulo("MENU DE DIRETORES");
            System.out.println("1 - Cadastrar diretor");
            System.out.println("2 - Listar diretores");
            System.out.println("3 - Buscar diretor por nome");
            System.out.println("0 - Voltar");
            MenuUtils.separador();

            opcao = MenuUtils.lerOpcao(this.scanner, "Escolha uma opção: ");
            MenuUtils.limparTela();

            switch (opcao)
            {
                case 1:
                    this.cadastrarDiretor();
                    MenuUtils.pausar(this.scanner);
                    break;
                case 2:
                    this.listarDiretores();
                    MenuUtils.pausar(this.scanner);
                    break;
                case 3:
                    this.buscarDiretor();
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

    public boolean cadastrarDiretor()
    {
        String nome;
        String data;
        String nacionalidade;

        MenuUtils.titulo("CADASTRAR DIRETOR");

        nome = MenuUtils.lerTexto(this.scanner, "Nome: ");
        if (!DiretorValidation.validateNome(nome))
        {
            System.out.println("Nome inválido.");
            return (false);
        }

        if (!DiretorValidation.validationNomeExiste(nome, this.diretorService.listarDiretores()))
        {
            System.out.println("Já existe um diretor com esse nome cadastrado.");
            return (false);
        }

        data = MenuUtils.lerTexto(this.scanner, "Data de nascimento (xx/xx/xxxx): ");
        if (!DiretorValidation.validateDataNascimento(data))
        {
            System.out.println("Data de nascimento inválida.");
            return (false);
        }

        nacionalidade = MenuUtils.lerTexto(this.scanner, "Nacionalidade (Por Extenso): ");
        if (!DiretorValidation.validateNacionalidade(nacionalidade))
        {
            System.out.println("Nacionalidade inválida.");
            return (false);
        }

        Diretor diretor = new Diretor(nome, data, nacionalidade);
        this.diretorService.cadastrarDiretor(diretor);

        System.out.println("Diretor cadastrado com sucesso.");
        return (true);
    }

    public void listarDiretores()
    {
        List<Diretor> diretores = this.diretorService.listarDiretores();

        MenuUtils.titulo("LISTA DE DIRETORES");

        if (diretores.isEmpty())
        {
            System.out.println("Nenhum diretor cadastrado.");
            return;
        }

        for (int i = 0; i < diretores.size(); i++)
        {
            System.out.println((i + 1) + " - " + diretores.get(i).exibirInformacoes());
            MenuUtils.separador();
        }
    }

    public void buscarDiretor()
    {
        String nome;
        Diretor diretor;

        MenuUtils.titulo("BUSCA DE DIRETOR");

        nome = MenuUtils.lerTexto(this.scanner, "Nome do diretor: ");
        diretor = this.diretorService.buscarDiretorPorNome(nome);

        if (diretor == null)
        {
            System.out.println("Diretor não encontrado.");
            return;
        }

        System.out.println(diretor.exibirInformacoes());
    }
}
