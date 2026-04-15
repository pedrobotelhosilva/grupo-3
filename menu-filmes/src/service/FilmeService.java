package service;

import java.util.ArrayList;
import java.util.List;

import model.Ator;
import model.Diretor;
import model.Filme;
import repository.FilmeFileRepository;

public class FilmeService {
//    private List<Filme> filmes;
//
//    public FilmeService() {
//        this.filmes = new ArrayList<>();
//    }

    public void cadastrarFilme(Filme filme) {
        if (filme != null) {
            FilmeFileRepository.adicionarFilme(filme);
            //filmes.add(filme);
        }
    }


    public List<Filme> listarFilmes() {
        return FilmeFileRepository.buscarTodos();
    }


    public Filme pesquisarFilmePorNome(String nome) {
        for (Filme filme : FilmeFileRepository.buscarTodos()) {
            if (filme.getNome().equalsIgnoreCase(nome)) {
                return filme;
            }
        }
        return null;
    }


    public void associarDiretorAoFilme(Filme filme, Diretor diretor) {
        if (filme != null && diretor != null) {
            filme.setDiretor(diretor);
        }
    }

    public void associarAtorAoFilme(Filme filme, Ator ator) {
        if (filme != null && ator != null) {
            filme.adicionarAtor(ator);
        }
    }
}