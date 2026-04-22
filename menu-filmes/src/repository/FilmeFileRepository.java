package repository;

import model.Ator;
import model.Diretor;
import model.Filme;
import service.AtorService;
import service.DiretorService;

import java.io.*;
import java.util.*;

public class FilmeFileRepository {

  public static List<Filme> buscarTodos(AtorService atorService, DiretorService diretorService) {
    List<Filme> filmes = new ArrayList<>();

    try {
      File file = new File("src/data/filmes.txt");

      BufferedReader br = new BufferedReader(new FileReader(file));
      String linha;

      while ((linha = br.readLine()) != null) {
        if (linha.isBlank()) continue;

        String[] partes = linha.split(";");

        if (partes.length < 6) {
          System.out.println("Linha incompleta: " + linha);
          continue;
        }

        String nome = partes[0];
        String dataLancamento = partes[1];
        double orcamento = Double.parseDouble(partes[2]);
        String descricao = partes[3];

        Diretor diretor = null;
        if (!partes[4].isBlank()
        && !partes[4].equalsIgnoreCase("Não informado")
        && !partes[4].equalsIgnoreCase("null")) {
          diretor = diretorService.buscarDiretorPorNome(partes[4]);
        }

        String[] nomesAtores = partes[5].split(",");
        List<Ator> atores = new ArrayList<>();

        for (String nomeAtor : nomesAtores) {
          String nomeTratado = nomeAtor.trim();

          if (nomeTratado.isBlank() || nomeTratado.equalsIgnoreCase("Nenhum ator"))
            continue;

          Ator ator = atorService.buscarAtorPorNome(nomeTratado);

          if (ator != null)
            atores.add(ator);
        }

        Filme filme = new Filme(nome, dataLancamento, orcamento, descricao);
        filme.setDiretor(diretor);
        filme.setAtores(atores);

        filmes.add(filme);
      }

      br.close();

    } catch (Exception e) {
      e.printStackTrace();
    }

    return filmes;
  }

  public static Filme adicionarFilme(Filme filme) {
    File file = new File("src/data/filmes.txt");

    try {
      FileWriter writer = new FileWriter(file, true);
      BufferedWriter bw = new BufferedWriter(writer);

      StringBuilder sb = new StringBuilder();
      sb.append(filme.getNome()).append(";");
      sb.append(filme.getDataLancamento()).append(";");
      sb.append(filme.getOrcamento()).append(";");
      sb.append(filme.getDescricao()).append(";");

      String nomeDiretor = (filme.getDiretor() != null) ? filme.getDiretor().getNome() : "Não informado";

      sb.append(nomeDiretor).append(";");

      List<Ator> atores = filme.getAtores();

      if (atores != null && !atores.isEmpty()) {
        for (int i = 0; i < atores.size(); i++) {
          sb.append(atores.get(i).getNome());

          if (i < atores.size() - 1) {
            sb.append(",");
          }
        }
      } else {
        sb.append("Nenhum ator");
      }
      sb.append("\n");

      bw.write(sb.toString());
      bw.flush();
      System.out.println("Conteúdo a gravar: " + sb.toString());
      bw.close();

    } catch (IOException e) {
      System.out.println("Erro ao escrever no arquivo");
      e.printStackTrace();
    }

    return filme;
  }

  public static Filme atualizarFilme(Filme filme, AtorService atorService, DiretorService diretorService) {
    List<Filme> filmes = buscarTodos(atorService, diretorService);

    try {
      File file = new File("src/data/filmes.txt");
      BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));

      for (Filme f : filmes) {
        if (f.getNome().equalsIgnoreCase(filme.getNome())) {
          f.setDataLancamento(filme.getDataLancamento());
          f.setOrcamento(filme.getOrcamento());
          f.setDescricao(filme.getDescricao());
          f.setDiretor(filme.getDiretor());
          f.setAtores(filme.getAtores());
        }

        StringBuilder sb = new StringBuilder();
        sb.append(f.getNome()).append(";");
        sb.append(f.getDataLancamento()).append(";");
        sb.append(f.getOrcamento()).append(";");
        sb.append(f.getDescricao()).append(";");

        String nomeDiretor = (f.getDiretor() != null)
        ? f.getDiretor().getNome()
        : "Não informado";
        sb.append(nomeDiretor).append(";");

        List<Ator> atores = f.getAtores();

        if (atores != null && !atores.isEmpty()) {
          for (int i = 0; i < atores.size(); i++) {
            sb.append(atores.get(i).getNome());

            if (i < atores.size() - 1) {
              sb.append(",");
            }
          }
        } else {
          sb.append("Nenhum ator");
        }
        sb.append("\n");

        bw.write(sb.toString());
      }

      bw.flush();
      bw.close();

    } catch (IOException e) {
      System.out.println("Erro ao atualizar o arquivo");
      e.printStackTrace();
    }
    return filme;
  }

}
