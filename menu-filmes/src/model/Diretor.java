package model;

public class Diretor extends Pessoa {

    public Diretor(String nome) {
        super(nome);
    }


    public Diretor(String nome, String dataNascimento, String nacionalidade) {
        super(nome, dataNascimento, nacionalidade);
    }

    @Override
    public String exibirInformacoes() {
        return "model.Diretor: " + getNome() +
                " | Data de nascimento: " + getDataNascimento() +
                " | Nacionalidade: " + getNacionalidade();
    }

    @Override
    public String toString() {
        return getNome();
    }
}
