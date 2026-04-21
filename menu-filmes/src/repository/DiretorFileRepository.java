package repository;

import model.Ator;
import model.Diretor;
import model.Filme;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DiretorFileRepository {

    private static final String FILE_NAME = "src/data/diretores.txt";

    public static List<Diretor> buscarTodos() {
        List<Diretor> diretores = new ArrayList<>();

        try {
            File file = new File(FILE_NAME);
            BufferedReader br = new BufferedReader(new FileReader(file));

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
                String estiloDirecao = partes[3];

                Diretor diretor = new Diretor(nome, dataNascimento, nacionalidade, estiloDirecao);

                diretores.add(diretor);
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return diretores;
    }



    public static Diretor adicionarDiretor(Diretor diretor) {
        File file = new File(FILE_NAME);

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));

            StringBuilder sb = new StringBuilder();
            sb.append(diretor.getNome()).append(";");
            sb.append(diretor.getDataNascimento()).append(";");
            sb.append(diretor.getNacionalidade()).append(";");
            sb.append(diretor.getEstiloDirecao()).append(";");
            sb.append("\n");

            bw.write(sb.toString());
            bw.flush();

            //System.out.println("Caminho absoluto: " + file.getAbsolutePath());
            System.out.println("Conteúdo gravado: " + sb.toString());

            bw.close();

        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo");
            e.printStackTrace();
        }

        return diretor;
       }
}
