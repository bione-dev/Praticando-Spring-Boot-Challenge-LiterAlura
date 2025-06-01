package com.projeto.ChallengeLiterAlura;

import com.projeto.ChallengeLiterAlura.client.GutendexClient;
import com.projeto.ChallengeLiterAlura.model.Autor;
import com.projeto.ChallengeLiterAlura.model.Livro;
import com.projeto.ChallengeLiterAlura.response.BooksResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private final GutendexClient client = new GutendexClient();

    private final List<Livro> livrosBuscados = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1": // Buscar livro por título
                    System.out.print("Digite o título do livro para buscar: ");
                    String titulo = scanner.nextLine();

                    try {
                        BooksResponse resposta = client.buscarLivrosPorTituloMapeado(titulo);
                        List<Livro> livros = resposta.getResultados();
                        if (livros != null) {
                            livrosBuscados.addAll(livros);  // Salva os livros buscados
                        }
                        imprimirLivros(livros);
                    } catch (Exception e) {
                        System.err.println("Erro ao buscar livros por título:");
                        e.printStackTrace();
                    }
                    break;

                case "2": // Listar todos os livros buscados
                    if (livrosBuscados.isEmpty()) {
                        System.out.println("Nenhum livro foi buscado ainda.");
                    } else {
                        imprimirLivros(livrosBuscados);
                    }
                    break;

                case "3": // Listar autores únicos dos livros buscados
                    listarAutoresUnicos();
                    break;

//                case "4": // Listar autores vivos em determinado ano
//                    System.out.print("Digite o ano para verificar autores vivos: ");
//                    try {
//                        int ano = Integer.parseInt(scanner.nextLine());
//                        List<Autor> vivos = buscarAutoresVivosEmAno(livrosBuscados, ano);
//                        if (vivos.isEmpty()) {
//                            System.out.println("Nenhum autor vivo encontrado para o ano " + ano);
//                        } else {
//                            System.out.println("Autores vivos no ano " + ano + ":");
//                            vivos.forEach(System.out::println);
//                        }
//                    } catch (NumberFormatException ex) {
//                        System.out.println("Ano inválido.");
//                    }
//                    break;

                case "0":
                    continuar = false;
                    System.out.println("Saindo da aplicação.");
                    break;

                default:
                    System.out.println("Opção inválida, tente novamente.");
                    break;
            }

            System.out.println();
        }

        scanner.close();
    }

    private void exibirMenu() {
        System.out.println("=== Menu ===");
        System.out.println("1 - Buscar livro por título");
        System.out.println("2 - Listar todos os livros buscados");
        System.out.println("3 - Listar autores dos livros buscados");
        System.out.println("4 - Listar autores vivos em determinado ano");
        System.out.println("0 - Sair");
    }

    private void imprimirLivros(List<Livro> livros) {
        if (livros == null || livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
            return;
        }

        System.out.println("\nTotal de livros: " + livros.size());
        for (Livro livro : livros) {
            System.out.println(livro);
        }
    }

    private void listarAutoresUnicos() {
        if (livrosBuscados.isEmpty()) {
            System.out.println("Nenhum livro foi buscado ainda.");
            return;
        }

        Set<String> nomesAutores = new HashSet<>();
        for (Livro livro : livrosBuscados) {
            List<Autor> autores = livro.getAutores();
            if (autores != null && !autores.isEmpty()) {
                nomesAutores.add(autores.get(0).getNome());  // Considera apenas o primeiro autor
            }
        }

        if (nomesAutores.isEmpty()) {
            System.out.println("Nenhum autor encontrado.");
        } else {
            System.out.println("Autores dos livros buscados:");
            nomesAutores.forEach(System.out::println);
        }
    }

//    public List<Autor> buscarAutoresVivosEmAno(List<Livro> livros, int ano) {
//        Set<Autor> autoresUnicos = new HashSet<>();
//        List<Autor> vivos = new ArrayList<>();
//
//        for (Livro livro : livros) {
//            List<Autor> autores = livro.getAutores();
//            if (autores != null && !autores.isEmpty()) {
//                Autor autor = autores.get(0);
//                autoresUnicos.add(autor);
//            }
//        }
//
//        for (Autor autor : autoresUnicos) {
//            Integer nascimento = autor.getAnoNascimento();
//            Integer falecimento = autor.getAnoFalecimento();
//            if (nascimento != null && nascimento <= ano && (falecimento == null || falecimento > ano)) {
//                vivos.add(autor);
//            }
//        }
//        return vivos;
// }
    }
