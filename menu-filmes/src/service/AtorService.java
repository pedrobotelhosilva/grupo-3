package service;

import model.Ator;
import model.Filme;
import repository.AtorFileRepository;
import repository.FilmeFileRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AtorService {
    private List<Ator> atores;

    public AtorService() {
        this.atores = AtorFileRepository.buscarTodos();
    }

    public void cadastrarAtor(Ator ator) {
        if (ator != null) {
            AtorFileRepository.adicionarAtor(ator);
            this.atores.add(ator);
        }
    }

    public List<Ator> listarAtores() {
        return (this.atores);
    }

    public Ator buscarAtorPorNome(String nome) {

        for (Ator ator : atores) {

            if (ator.getNome().trim().equalsIgnoreCase(nome.trim())) {
                return ator;
            }
        }

        return null;
    }


}
