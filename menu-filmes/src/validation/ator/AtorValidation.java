package validation.ator;

import model.Ator;
import enums.Nationality;
import utils.general.GeneralUtils;
import validation.general.DateValidation;

import java.util.List;

public final class AtorValidation
{
	private AtorValidation()
	{
	}

	public static boolean validateNome(String nome)
	{
		if (GeneralUtils.isNullOrBlank(nome))
			return (false);
		
    return (nome.trim().length() >= 2);
	}

  public static boolean validationNomeExiste(String nome, List<Ator> atores)
  {
    for (Ator ator: atores)
    {
      if (ator.getNome().equalsIgnoreCase(nome))
        return (false);
    }
    return (true);
  }

	public static boolean validateDataNascimento(String dataNascimento)
	{
		if (GeneralUtils.isNullOrBlank(dataNascimento))
			return (false);
    return (DateValidation.validate(dataNascimento));
	}

	public static boolean validateNacionalidade(String nacionalidade)
	{
		if (GeneralUtils.isNullOrBlank(nacionalidade))
			return (false);
	  for (Nationality nationality: Nationality.values())
    {
      if (nacionalidade.equalsIgnoreCase(nationality.getNationality()))
        return (true);
    }
    return (false);
	}
}
