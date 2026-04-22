package model;

import java.util.ArrayList;
import java.util.List;

public class Filme
{
    private String nome;
    private String dataLancamento;
    private double orcamento;
    private String descricao;
    private Diretor diretor;
    private List<Ator> atores;

    public Filme(String nome, String dataLancamento, double orcamento, String descricao)
    {
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.orcamento = orcamento;
        this.descricao = descricao;
        this.atores = new ArrayList<>();
    }

    public Filme(String nome, String data, double orcamento, String descricao, Diretor diretor, List<Ator> atores)
    {
        this(nome, data, orcamento, descricao);
        this.setDiretor(diretor);
        this.setAtores(atores);
    }

    public String getNome()
    {
        return (this.nome);
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getDataLancamento()
    {
        return (this.dataLancamento);
    }

    public void setDataLancamento(String dataLancamento)
    {
        this.dataLancamento = dataLancamento;
    }

    public double getOrcamento()
    {
        return (this.orcamento);
    }

    public void setOrcamento(double orcamento)
    {
        this.orcamento = orcamento;
    }

    public String getDescricao()
    {
        return (this.descricao);
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public Diretor getDiretor()
    {
        return (this.diretor);
    }

    public void setDiretor(Diretor diretor)
    {
        this.diretor = diretor;

        if (diretor != null)
            diretor.adicionarFilme(this);
    }

    public List<Ator> getAtores()
    {
        return (this.atores);
    }

    public void setAtores(List<Ator> atores)
    {
        this.atores = new ArrayList<>();

        if (atores == null)
            return;

        for (Ator ator : atores)
            this.adicionarAtor(ator);
    }

    public void adicionarAtor(Ator ator)
    {
        if (ator == null)
            return;

        for (Ator a : this.atores)
        {
            if (a.getNome().equalsIgnoreCase(ator.getNome()))
                return;
        }

        this.atores.add(ator);
        ator.adicionarFilme(this);
    }

    public void removerAtor(Ator ator)
    {
        if (ator == null)
            return;

        for (int i = 0; i < this.atores.size(); i++)
        {
            if (this.atores.get(i).getNome().equalsIgnoreCase(ator.getNome()))
            {
                this.atores.remove(i);
                return;
            }
        }
    }

    public String exibirDetalhes()
    {
        String nomeDiretor = (this.diretor != null) ? this.diretor.getNome() : "Não informado";
        StringBuilder nomesAtores = new StringBuilder();

        if (this.atores.isEmpty())
        {
            nomesAtores.append("Nenhum ator cadastrado");
        }
        else
        {
            for (int i = 0; i < this.atores.size(); i++)
            {
                nomesAtores.append(this.atores.get(i).getNome());

                if (i < this.atores.size() - 1)
                    nomesAtores.append(", ");
            }
        }

        return ("Filme: " + this.nome
            + "\nData de lançamento: " + this.dataLancamento
            + "\nOrçamento: " + this.orcamento
            + "\nDescrição: " + this.descricao
            + "\nDiretor: " + nomeDiretor
            + "\nAtores: " + nomesAtores);
    }

    @Override
    public String toString()
    {
        return ("Filme: " + this.nome + " | Lançamento: " + this.dataLancamento);
    }
}
