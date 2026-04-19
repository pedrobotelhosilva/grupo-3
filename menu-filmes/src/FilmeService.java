import java.util.ArrayList;
import java.util.List;

public class FilmeService {
    private List<Filme> filmes;

    public FilmeService() {
        this.filmes = new ArrayList<>();
    }

    public void cadastrarFilme(Filme filme) {
        if (filme != null) {
            filmes.add(filme);
        }
    }

    public List<Filme> listarFilmes() {
        return filmes;
    }

    public Filme pesquisarFilmePorNome(String nome) {
        for (Filme filme : filmes) {
            if (filme.getNome().equalsIgnoreCase(nome)) {
                return filme;
            }
        }
        throw new NaoEncontradoException("Filme não encontrado: " + nome);
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