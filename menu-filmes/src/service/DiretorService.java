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
        this.diretores = DiretorFileRepository.buscarTodos();
    }

    public void cadastrarDiretor(Diretor diretor) {
        if (diretor != null) {
            DiretorFileRepository.adicionarDiretor(diretor);
            this.diretores.add(diretor);
        }
    }

    public List<Diretor> listarDiretores() {
        return (this.diretores);
    }

    public Diretor buscarDiretorPorNome(String nome) {

        for (Diretor diretor : this.diretores) {

            if (diretor.getNome().trim().equalsIgnoreCase(nome.trim())) {
                return diretor;
            }
        }

        return null;
    }
}
