package service;

import exceptions.AtorNaoEncontradoException;
import model.Ator;
import repository.AtorFileRepository;

import java.util.List;

public class AtorService
{
    private List<Ator> atores;

    public AtorService()
    {
        this.atores = AtorFileRepository.buscarTodos();
    }

    public void cadastrarAtor(Ator ator)
    {
        if (ator != null)
        {
            AtorFileRepository.adicionarAtor(ator);
            this.atores.add(ator);
        }
    }

    public List<Ator> listarAtores()
    {
        return (this.atores);
    }

    public Ator buscarAtorPorNome(String nome)
    {
        for (Ator ator : this.atores)
        {
            if (ator.getNome().trim().equalsIgnoreCase(nome.trim()))
                return (ator);
        }

        throw new AtorNaoEncontradoException("Ator não encontrado.");
    }
}
