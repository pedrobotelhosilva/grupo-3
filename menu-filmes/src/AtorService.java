import java.util.ArrayList;
import java.util.List;

public class AtorService {
    private List<Ator> atores;

    public AtorService() {
        this.atores = new ArrayList<>();
    }

    public void cadastrarAtor(Ator ator) {
        if (ator != null) {
            atores.add(ator);
        }
    }

    public List<Ator> listarAtores() {
        return atores;
    }

    public Ator buscarAtorPorNome(String nome) {
        for (Ator ator : atores) {
            if (ator.getNome().equalsIgnoreCase(nome)) {
                return ator;
            }
        }
        return null;
    }
}