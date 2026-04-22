package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Ator;
import model.Diretor;
import model.Filme;
import repository.FilmeFileRepository;

public class FilmeService {

 public void cadastrarFilme(Filme filme) {
        if (filme != null) {
            FilmeFileRepository.adicionarFilme(filme);

        }
    }
  
    public void adicionarFilme() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Nome do filme: ");
        String nome = scanner.nextLine();

        System.out.print("Data de lançamento: ");
        String dataLancamento = scanner.nextLine();

        System.out.print("Orçamento: ");
        double orcamento = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();


        System.out.print("Nome do diretor: ");
        String nomeDiretor = scanner.nextLine();

        Diretor diretor = null;

        if (!nomeDiretor.isBlank()) {
            diretor = new Diretor(nomeDiretor);
        }


        System.out.print("Digite os nomes dos atores (separados por vírgula): ");
        String entradaAtores = scanner.nextLine();

        List<Ator> atores = new ArrayList<>();

        if (!entradaAtores.isBlank()) {
            String[] nomes = entradaAtores.split(",");

            for (String n : nomes) {
                atores.add(new Ator(n.trim()));
            }
        }


        Filme filme = new Filme(nome, dataLancamento, orcamento, descricao, diretor, atores);

        FilmeFileRepository.adicionarFilme(filme);

        System.out.println("Filme cadastrado com sucesso!");
    }

    public List<Filme> listarFilmes() {
        return FilmeFileRepository.buscarTodos();
    }

    public Filme buscarFilmePorNome(String nome) {
        for (Filme filme : FilmeFileRepository.buscarTodos()) {
            if (filme.getNome().equalsIgnoreCase(nome)) {
                return filme;
            }
        }
        return null;
    }

    private void buscarFilme(Scanner scanner) {
        System.out.print("Nome do filme: ");
        String nome = scanner.nextLine();


        Filme filme = buscarFilmePorNome(nome);


        if (filme != null) {
            System.out.println("\n=== FILME ENCONTRADO ===");
            System.out.println(filme.exibirDetalhes());
        } else {
            System.out.println("Filme não encontrado!");
        }
    }

    public void associarDiretor(Filme filme, Diretor diretor) {
        if (filme == null) {
            System.out.println("Filme não pode ser nulo.");
            return;
        }

        if (diretor == null) {
            System.out.println("Diretor não pode ser nulo.");
            return;
        }


        filme.setDiretor(diretor);

        FilmeFileRepository.atualizarFilme(filme);

        System.out.println("Diretor associado e salvo com sucesso!");
    }

    public void associarAtor(Filme filme, Ator ator) {

        if (filme == null) {
            System.out.println("Filme não encontrado.");
            return;
        }

        if (ator == null) {
            System.out.println("Ator não encontrado.");
            return;
        }

        System.out.println("Filme encontrado: " + filme.getNome());

        List<Ator> atores = filme.getAtores();

        if (atores == null) {
            atores = new ArrayList<>();
        }


        boolean jaExiste = false;
        for (Ator a : atores) {
            if (a.getNome().equalsIgnoreCase(ator.getNome())) {
                jaExiste = true;
                break;
            }
        }

        if (!jaExiste) {
            atores.add(ator);
        } else {
            System.out.println("Ator já está associado ao filme.");
        }

        filme.setAtores(atores);


        FilmeFileRepository.atualizarFilme(filme);

        System.out.println("Ator associado com sucesso!");
    }

}
