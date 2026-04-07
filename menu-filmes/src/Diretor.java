public class Diretor extends Pessoa {
    private String estiloDirecao;

    public Diretor(String nome, String dataNascimento, String nacionalidade, String estiloDirecao) {
        super(nome, dataNascimento, nacionalidade);
        this.estiloDirecao = estiloDirecao;
    }

    public String getEstiloDirecao() {
        return estiloDirecao;
    }

    public void setEstiloDirecao(String estiloDirecao) {
        this.estiloDirecao = estiloDirecao;
    }

    @Override
    public String exibirInformacoes() {
        return "Diretor: " + getNome() +
                " | Data de nascimento: " + getDataNascimento() +
                " | Nacionalidade: " + getNacionalidade() +
                " | Estilo de direção: " + estiloDirecao;
    }

    @Override
    public String toString() {
        return getNome();
    }
}