package menu.ator;

//import fora 
import java.util.Scanner;

//import projeto
import model.Ator;
import service.AtorService;
import utils.ator.AtorUtils;
import validation.ator.AtorValidation;

import java.util.List;

public final class AtorMenu
{
  private AtorService atorService;
  private Scanner     scanner;

  public AtorMenu(Scanner scanner, AtorService atorService)
  {
	  this.scanner = scanner;
	  this.atorService = atorService;
  }

  public boolean cadastrarAtor()
  {
    String nome;
    String data;
    String nacionalidade;
    String genero;

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
    for (Ator a : atores)
    {
      System.out.println(a.getNome());
    }
  }
}
