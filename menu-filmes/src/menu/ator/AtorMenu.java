package menu.ator;

//import fora 
import java.util.Scanner;

//import projeto
import model.Ator;
import service.AtorService;
import utils.ator.AtorUtils;
import validation.ator.AtorValidation;

public final class AtorMenu
{
  private AtorService atorService;
  private Scanner     scanner;

  public AtorMenu(Scanner scanner, AtorService atorService)
  {
	this.scanner = scanner;
	this.atorService = atorService;
  }

  public void cadastrarAtor()
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
      return;
    }

    System.out.print("Data de nascimento: ");
    data = scanner.nextLine();
    if (!AtorValidation.validateDataNascimento(data))
    {
      System.out.println("Data de nascimento inválida!");
      return;
    }

    System.out.print("Nacionalidade: ");
    nacionalidade = scanner.nextLine();
    if (!AtorValidation.validateNacionalidade(nacionalidade))
    {
      System.out.println("Nacionalidade inválida!");
      return;
    }

    /*
    System.out.print("Gênero artístico: ");
    genero = scanner.nextLine();
    if (!AtorValidation.validateGeneroArtistico(genero))
    {
      System.out.println("Gênero artístico inválido!");
      return;
    }

    */

    Ator ator = new Ator(nome, data, nacionalidade, " "); // genero vazio por enquanto (irei repensar os atributos do ator)
    atorService.cadastrarAtor(ator);

    System.out.println("Ator cadastrado com sucesso!");
  }
}
