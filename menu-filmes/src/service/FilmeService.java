package service;

import java.util.List;

import model.Ator;
import model.Diretor;
import model.Filme;
import repository.FilmeFileRepository;

public class FilmeService
{
    private List<Filme> filmes;
    private AtorService atorService;
    private DiretorService diretorService;

    public FilmeService(AtorService atorService, DiretorService diretorService)
    {
        this.atorService = atorService;
        this.diretorService = diretorService;
        filmes = FilmeFileRepository.buscarTodos(atorService, diretorService);
    }

    public void cadastrarFilme(Filme filme)
    {
        if (filme == null)
        {
            System.out.println("Filme inválido.");
            return;
        }

        if (buscarFilmePorNome(filme.getNome()) != null)
        {
            System.out.println("Já existe um filme com esse nome.");
            return;
        }

        FilmeFileRepository.adicionarFilme(filme);
        filmes.add(filme);
    }

    public List<Filme> listarFilmes()
    {
        return (filmes);
    }

    public Filme buscarFilmePorNome(String nome)
    {
        if (nome == null || nome.isBlank())
            return (null);

        for (Filme filme : filmes)
        {
            if (filme.getNome().equalsIgnoreCase(nome))
                return (filme);
        }
        return (null);
    }

    public void associarDiretor(Filme filme, Diretor diretor)
    {
        if (filme == null)
        {
            System.out.println("Filme não encontrado.");
            return;
        }

        if (diretor == null)
        {
            System.out.println("Diretor não encontrado.");
            return;
        }

        filme.setDiretor(diretor);
        FilmeFileRepository.atualizarFilme(filme, atorService, diretorService);

        System.out.println("Diretor associado com sucesso!");
    }

    public void associarAtor(Filme filme, Ator ator)
    {
        if (filme == null)
        {
            System.out.println("Filme não encontrado.");
            return;
        }

        if (ator == null)
        {
            System.out.println("Ator não encontrado.");
            return;
        }

        filme.adicionarAtor(ator);
        FilmeFileRepository.atualizarFilme(filme, atorService, diretorService);

        System.out.println("Ator associado com sucesso!");
    }
}
