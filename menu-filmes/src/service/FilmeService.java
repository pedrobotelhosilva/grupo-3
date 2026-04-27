package service;

import exceptions.FilmeNaoEncontradoException;
import model.Ator;
import model.Diretor;
import model.Filme;
import repository.FilmeFileRepository;

import java.util.List;

public class FilmeService
{
    private List<Filme> filmes;
    private AtorService atorService;
    private DiretorService diretorService;

    public FilmeService(AtorService atorService, DiretorService diretorService)
    {
        this.atorService = atorService;
        this.diretorService = diretorService;
        this.filmes = FilmeFileRepository.buscarTodos(atorService, diretorService);
    }

    public void cadastrarFilme(Filme filme)
    {
        if (filme == null)
        {
            System.out.println("Filme inválido.");
            return;
        }

        if (this.existeFilmeComNome(filme.getNome()))
        {
            System.out.println("Já existe um filme com esse nome.");
            return;
        }

        FilmeFileRepository.adicionarFilme(filme);
        this.filmes.add(filme);
    }

    public List<Filme> listarFilmes()
    {
        return (this.filmes);
    }

    public Filme buscarFilmePorNome(String nome)
    {
        if (nome == null || nome.isBlank())
            throw new FilmeNaoEncontradoException("Filme não encontrado.");

        for (Filme filme : this.filmes)
        {
            if (filme.getNome().equalsIgnoreCase(nome))
                return (filme);
        }

        throw new FilmeNaoEncontradoException("Filme não encontrado.");
    }

    public void associarDiretor(Filme filme, Diretor diretor)
    {
        if (diretor == null)
        {
            System.out.println("Diretor não encontrado.");
            return;
        }

        filme.setDiretor(diretor);
        FilmeFileRepository.atualizarFilme(filme, this.atorService, this.diretorService);

        System.out.println("Diretor associado com sucesso!");
    }

    public void associarAtor(Filme filme, Ator ator)
    {
        if (ator == null)
        {
            System.out.println("Ator não encontrado.");
            return;
        }

        filme.adicionarAtor(ator);
        FilmeFileRepository.atualizarFilme(filme, this.atorService, this.diretorService);

        System.out.println("Ator associado com sucesso!");
    }

    private boolean existeFilmeComNome(String nome)
    {
        for (Filme filme : this.filmes)
        {
            if (filme.getNome().equalsIgnoreCase(nome))
                return (true);
        }
        return (false);
    }
}
