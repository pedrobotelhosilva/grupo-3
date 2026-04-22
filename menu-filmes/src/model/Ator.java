package model;

import java.util.ArrayList;
import java.util.List;

public class Ator extends Pessoa
{
    private List<Filme> filmes;

    public Ator(String nome)
    {
        super(nome);
        this.filmes = new ArrayList<>();
    }

    public Ator(String nome, String dataNascimento, String nacionalidade)
    {
        super(nome, dataNascimento, nacionalidade);
        this.filmes = new ArrayList<>();
    }

    public List<Filme> getFilmes()
    {
        return (this.filmes);
    }

    public void setFilmes(List<Filme> filmes)
    {
        if (filmes == null)
            this.filmes = new ArrayList<>();
        else
            this.filmes = filmes;
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

    public void removerFilme(Filme filme)
    {
        this.filmes.remove(filme);
    }

    public String listarFilmes()
    {
        String nomesFilmes;

        nomesFilmes = "";
        if (this.filmes.isEmpty())
            return ("Nenhum filme cadastrado");
        for (int i = 0; i < this.filmes.size(); i++)
        {
            nomesFilmes += this.filmes.get(i).getNome();
            if (i < this.filmes.size() - 1)
                nomesFilmes += ", ";
        }
        return (nomesFilmes);
    }

    @Override
    public String exibirInformacoes()
    {
        return ("Ator: " + this.getNome()
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
