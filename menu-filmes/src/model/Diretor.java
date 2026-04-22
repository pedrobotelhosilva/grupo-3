package model;

import java.util.ArrayList;
import java.util.List;

public class Diretor extends Pessoa
{
    private List<Filme> filmes;

    public Diretor(String nome)
    {
        super(nome);
        this.filmes = new ArrayList<>();
    }

    public Diretor(String nome, String dataNascimento, String nacionalidade)
    {
        super(nome, dataNascimento, nacionalidade);
        this.filmes = new ArrayList<>();
    }

    public List<Filme> getFilmes()
    {
        return (this.filmes);
    }

    public void adicionarFilme(Filme filme)
    {
        if (filme == null)
            return;

        for (Filme f : this.filmes)
        {
            if (f.getNome().equalsIgnoreCase(filme.getNome()))
                return;
        }
        this.filmes.add(filme);
    }

    public String listarFilmes()
    {
        if (this.filmes.isEmpty())
            return ("Nenhum filme cadastrado");

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.filmes.size(); i++)
        {
            sb.append(this.filmes.get(i).getNome());

            if (i < this.filmes.size() - 1)
                sb.append(", ");
        }
        return (sb.toString());
    }

    @Override
    public String exibirInformacoes()
    {
        return ("Diretor: " + this.getNome()
            + " | Data de nascimento: " + this.getDataNascimento()
            + " | Nacionalidade: " + this.getNacionalidade()
            + " | Filmes: " + this.listarFilmes());
    }

    @Override
    public String toString()
    {
        return (this.getNome());
    }
}
