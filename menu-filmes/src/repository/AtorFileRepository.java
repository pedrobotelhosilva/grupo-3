package repository;

import model.Ator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AtorFileRepository {

    private static final String FILE_NAME = "atores.txt";

    public static List<Ator> buscarTodos() {
        List<Ator> atores = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {

            String linha;

            while ((linha = br.readLine()) != null) {
                if (linha.isBlank()) continue;

                String[] partes = linha.split(";");

                if (partes.length < 4) {
                    System.out.println("Dados incompletos: " + linha);
                    continue;
                }

                String nome = partes[0];
                String dataNascimento = partes[1];
                String nacionalidade = partes[2];
                String generoArtistico = partes[3];

                Ator ator = new Ator(nome, dataNascimento, nacionalidade, generoArtistico);

                atores.add(ator);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo");
            e.printStackTrace();
        }

        return atores;
    }

    public static Ator adicionarAtor(Ator ator) {
        File file = new File(FILE_NAME);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {

            StringBuilder sb = new StringBuilder();
            sb.append(ator.getNome()).append(";");
            sb.append(ator.getDataNascimento()).append(";");
            sb.append(ator.getNacionalidade()).append(";");
            sb.append(ator.getGeneroArtistico()).append(";");
            sb.append("\n");

            bw.write(sb.toString());

            //System.out.println("Caminho absoluto: " + file.getAbsolutePath());
            System.out.println("Conteúdo gravado: " + sb.toString());

        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo");
            e.printStackTrace();
        }

        return ator;
    }

}