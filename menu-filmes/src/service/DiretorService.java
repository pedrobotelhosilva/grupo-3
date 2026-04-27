package service;

import exceptions.DiretorNaoEncontradoException;
import model.Diretor;
import repository.DiretorFileRepository;

import java.util.List;

public class DiretorService
{
    private List<Diretor> diretores;

    public DiretorService()
    {
        this.diretores = DiretorFileRepository.buscarTodos();
    }

    public void cadastrarDiretor(Diretor diretor)
    {
        if (diretor != null)
        {
            DiretorFileRepository.adicionarDiretor(diretor);
            this.diretores.add(diretor);
        }
    }

    public List<Diretor> listarDiretores()
    {
        return (this.diretores);
    }

    public Diretor buscarDiretorPorNome(String nome)
    {
        for (Diretor diretor : this.diretores)
        {
            if (diretor.getNome().trim().equalsIgnoreCase(nome.trim()))
                return (diretor);
        }

        throw new DiretorNaoEncontradoException("Diretor não encontrado.");
    }
}
