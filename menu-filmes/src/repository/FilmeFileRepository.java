package repository;

import model.Ator;
import model.Diretor;
import model.Filme;
import service.AtorService;
import service.DiretorService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilmeFileRepository
{
    private static final String FILE_NAME = "src/data/filmes.txt";

    public static List<Filme> buscarTodos(AtorService atorService, DiretorService diretorService)
    {
        List<Filme> filmes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME)))
        {
            String linha;

            while ((linha = br.readLine()) != null)
            {
                if (linha.isBlank())
                    continue;

                String[] partes = linha.split(";", -1);

                if (partes.length < 6)
                {
                    System.out.println("Linha incompleta: " + linha);
                    continue;
                }

                String nome = partes[0];
                String dataLancamento = partes[1];
                double orcamento = Double.parseDouble(partes[2]);
                String descricao = partes[3];
                String nomeDiretor = partes[4];
                String nomesAtores = partes[5];

                Filme filme = new Filme(nome, dataLancamento, orcamento, descricao);

                if (!nomeDiretor.isBlank()
                        && !nomeDiretor.equalsIgnoreCase("Não informado")
                        && !nomeDiretor.equalsIgnoreCase("null"))
                {
                    Diretor diretor = diretorService.buscarDiretorPorNome(nomeDiretor);

                    if (diretor != null)
                        filme.setDiretor(diretor);
                }

                if (!nomesAtores.isBlank()
                        && !nomesAtores.equalsIgnoreCase("Nenhum ator"))
                {
                    String[] nomes = nomesAtores.split(",");

                    for (String nomeAtor : nomes)
                    {
                        Ator ator = atorService.buscarAtorPorNome(nomeAtor.trim());

                        if (ator != null)
                            filme.adicionarAtor(ator);
                    }
                }

                filmes.add(filme);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Arquivo não encontrado: " + FILE_NAME);
        }
        catch (IOException e)
        {
            System.out.println("Erro ao ler arquivo de filmes");
            e.printStackTrace();
        }

        return (filmes);
    }

    public static Filme adicionarFilme(Filme filme)
    {
        File file = new File(FILE_NAME);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true)))
        {
            bw.write(toLine(filme));
            bw.newLine();
        }
        catch (IOException e)
        {
            System.out.println("Erro ao escrever no arquivo");
            e.printStackTrace();
        }

        return (filme);
    }

    public static Filme atualizarFilme(Filme filmeAtualizado, AtorService atorService, DiretorService diretorService)
    {
        List<Filme> filmes = buscarTodos(atorService, diretorService);

        for (int i = 0; i < filmes.size(); i++)
        {
            if (filmes.get(i).getNome().equalsIgnoreCase(filmeAtualizado.getNome()))
            {
                filmes.set(i, filmeAtualizado);
                break;
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, false)))
        {
            for (Filme filme : filmes)
            {
                bw.write(toLine(filme));
                bw.newLine();
            }
        }
        catch (IOException e)
        {
            System.out.println("Erro ao atualizar o arquivo");
            e.printStackTrace();
        }

        return (filmeAtualizado);
    }

    private static String toLine(Filme filme)
    {
        StringBuilder sb = new StringBuilder();

        sb.append(filme.getNome()).append(";");
        sb.append(filme.getDataLancamento()).append(";");
        sb.append(filme.getOrcamento()).append(";");
        sb.append(filme.getDescricao()).append(";");

        if (filme.getDiretor() != null)
            sb.append(filme.getDiretor().getNome());

        sb.append(";");

        List<Ator> atores = filme.getAtores();

        if (atores != null && !atores.isEmpty())
        {
            for (int i = 0; i < atores.size(); i++)
            {
                sb.append(atores.get(i).getNome());

                if (i < atores.size() - 1)
                    sb.append(",");
            }
        }
        else
        {
            sb.append("Nenhum ator");
        }

        return (sb.toString());
    }
}
