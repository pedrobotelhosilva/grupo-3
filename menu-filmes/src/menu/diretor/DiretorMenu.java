package menu.diretor;

import java.util.Scanner;

import model.Diretor;
import service.DiretorService;
import utils.diretor.DiretorUtils;
import validation.diretor.DiretorValidation;

import java.util.List;

public final class DiretorMenu
{

  private DiretorService diretorService;
  private Scanner     scanner;

  public DiretorMenu(Scanner scanner, DiretorService diretorService)
  {
	  this.scanner = scanner;
	  this.diretorService = diretorService;
  }

  public boolean cadastrarDiretor()
  {
    String nome;
    String data;
    String nacionalidade;
    String genero;

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
    for (Diretor d : diretores)
    {
      System.out.println(d.getNome());
    }
  }
}
