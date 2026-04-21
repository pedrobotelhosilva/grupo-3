package service;

import model.Diretor;
import model.Filme;
import repository.AtorFileRepository;
import repository.DiretorFileRepository;
import repository.FilmeFileRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DiretorService {
    private List<Diretor> diretores;

    public DiretorService() {
        this.diretores = new ArrayList<>();
    }

    public static void cadastrarDiretor(Diretor diretor) {
        if (diretor != null) {
            DiretorFileRepository.adicionarDiretor(diretor);

        }
    }

    public List<Diretor> listarDiretores() {
        return DiretorFileRepository.buscarTodos();
    }

    public Diretor buscarDiretorPorNome(String nome) {

        for (Diretor diretor : listarDiretores()) {

            if (diretor.getNome().trim().equalsIgnoreCase(nome.trim())) {
                return diretor;
            }
        }

        return null;
    }
}