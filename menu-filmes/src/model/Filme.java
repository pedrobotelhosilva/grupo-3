package model;

import java.util.ArrayList;
import java.util.List;

public class Filme {
    private String nome;
    private String dataLancamento;
    private double orcamento;
    private String descricao;
    private Diretor diretor;
    private List<Ator> atores;

    public Filme(String nome, String dataLancamento, double orcamento, String descricao) {
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.orcamento = orcamento;
        this.descricao = descricao;
        this.atores = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public double getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(double orcamento) {
        this.orcamento = orcamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Diretor getDiretor() {
        return diretor;
    }

    public void setDiretor(Diretor diretor) {
        this.diretor = diretor;
    }

    public List<Ator> getAtores() {
        return atores;
    }

    public void setAtores(List<Ator> atores) {this.atores = atores;}

    public void adicionarAtor(Ator ator) {
        if (ator != null) {
            atores.add(ator);
        }
    }

    public void removerAtor(Ator ator) {
        atores.remove(ator);
    }

    public String exibirDetalhes() {
        String nomeDiretor = (diretor != null) ? diretor.getNome() : "Não informado";

        String nomesAtores = "";
        if (atores.isEmpty()) {
            nomesAtores = "Nenhum ator cadastrado";
        } else {
            for (int i = 0; i < atores.size(); i++) {
                nomesAtores += atores.get(i).getNome();
                if (i < atores.size() - 1) {
                    nomesAtores += ", ";
                }
            }
        }

        return "model.Filme: " + nome +
                "\nData de lançamento: " + dataLancamento +
                "\nOrçamento: " + orcamento +
                "\nDescrição: " + descricao +
                "\nmodel.Diretor: " + nomeDiretor +
                "\nAtores: " + nomesAtores;
    }

    @Override
    public String toString() {
        return "model.Filme: " + nome + " | Lançamento: " + dataLancamento;
    }


}