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

    public static Filme adicionarFilme(Filme filme) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            StringBuilder sb = new StringBuilder();
            sb.append(filme.getNome()).append(";");
            sb.append(filme.getDataLancamento()).append(";");
            sb.append(filme.getOrcamento()).append(";");
            sb.append(filme.getDescricao()).append(";");
            //sb.append(filme.getDiretor().getNome()).append(";");

//            List<Ator> atores = filme.getAtores();
//            for (int i = 0; i < atores.size(); i++) {
//                sb.append(atores.get(i).getNome());
//                if (i < atores.size() - 1) {
//                    sb.append(",");
//                }
//            }
            sb.append("\n");

            bw.write(sb.toString());
            bw.flush();
            //Teste caminho do arquivo funcionando. Verificar porquê não está gravando os dados
            // System.out.println("Conteúdo a gravar: " + sb.toString());
            bw.write(sb.toString());

        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo");
            e.printStackTrace();
        }

        return filme;
    }
}