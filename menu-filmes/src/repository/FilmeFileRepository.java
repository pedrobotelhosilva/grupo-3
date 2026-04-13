package repository;

import model.Ator;
import model.Diretor;
import model.Filme;

import java.io.*;
import java.util.*;

public class FilmeFileRepository {

    private static final String FILE_NAME = "filmes.txt";

    public static List<Filme> buscarTodos() {
        List<Filme> filmes = new ArrayList<>();

        InputStream is = FilmeFileRepository.class
                .getClassLoader()
                .getResourceAsStream(FILE_NAME);

        if (is == null) {
            System.out.println("Arquivo filmes.txt não encontrado!");
            return filmes;
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.isBlank()) continue;
                String[] partes = linha.split(";");

                String nome = partes[0];
                String dataLancamento = partes[1];
                double orcamento = Double.parseDouble(partes[2]);
                String descricao = partes[3];
                Diretor diretor = new Diretor(partes[4]);
                String[] nomesAtores = partes[5].split(",");
                List<Ator> atores = new ArrayList<>();

                for (String nomeAtor : nomesAtores) {
                    atores.add(new Ator(nomeAtor.trim()));
                }

                // puxar os dados do filme e criar o objeto
                Filme filme = new Filme(nome, dataLancamento, orcamento, descricao);
                filme.setDiretor(diretor);
                filme.setAtores(atores);

                filmes.add(filme);
            }


        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo");
        }

        return filmes;
    }
}