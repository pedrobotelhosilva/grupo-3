package validation.ator;

import utils.general.GeneralUtils;
import validation.general.DateValidation;

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

	public static boolean validateDataNascimento(String dataNascimento)
	{
		return (DateValidation.validate(dataNascimento));
	}

	public static boolean validateNacionalidade(String nacionalidade)
	{
		if (GeneralUtils.isNullOrBlank(nacionalidade))
			return (false);
		
    return (nacionalidade.trim().length() >= 2);
	}
}
