package service;

import model.Diretor;

import java.util.ArrayList;
import java.util.List;

public class DiretorService {
    private List<Diretor> diretores;

    public DiretorService() {
        this.diretores = new ArrayList<>();
    }

    public void cadastrarDiretor(Diretor diretor) {
        if (diretor != null) {
            diretores.add(diretor);
        }
    }

    public List<Diretor> listarDiretores() {
        return diretores;
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