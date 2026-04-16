package service;

import model.Diretor;
import repository.DiretorFileRepository;
import repository.FilmeFileRepository;

import java.util.ArrayList;
import java.util.List;

public class DiretorService {
    private List<Diretor> diretores;

    public DiretorService() {
        this.diretores = new ArrayList<>();
    }

    public void cadastrarDiretor(Diretor diretor) {
        if (diretor != null) {
            DiretorFileRepository.adicionarDiretor(diretor);
            //diretores.add(diretor);
        }
    }

    public List<Diretor> listarDiretores() {
        return DiretorFileRepository.buscarTodos();
    }

    public Diretor buscarDiretorPorNome(String nome) {
        for (Diretor diretor : diretores) {
            if (diretor.getNome().equalsIgnoreCase(nome)) {
                return diretor;
            }
        }
        return null;
    }
}