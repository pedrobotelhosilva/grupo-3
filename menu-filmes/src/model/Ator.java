package model;

public class Ator extends Pessoa {
    private String generoArtistico;


    public Ator(String nome) {
        super(nome);
    }

    public Ator(String nome, String dataNascimento, String nacionalidade, String generoArtistico) {
        super(nome, dataNascimento, nacionalidade);
        this.generoArtistico = generoArtistico;
    }


    public String getGeneroArtistico() {
        return generoArtistico;
    }

    public void setGeneroArtistico(String generoArtistico) {
        this.generoArtistico = generoArtistico;
    }

    @Override
    public String exibirInformacoes() {
        return "model.Ator: " + getNome() +
                " | Data de nascimento: " + getDataNascimento() +
                " | Nacionalidade: " + getNacionalidade() +
                " | Gênero artístico: " + generoArtistico;
    }

    @Override
    public String toString() {
        return getNome();
    }
}