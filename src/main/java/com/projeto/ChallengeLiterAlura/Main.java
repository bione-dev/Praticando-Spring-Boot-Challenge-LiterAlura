package com.projeto.ChallengeLiterAlura;

import com.projeto.ChallengeLiterAlura.client.GutendexClient;
import com.projeto.ChallengeLiterAlura.entity.AutorEntity;
import com.projeto.ChallengeLiterAlura.entity.LivroEntity;
import com.projeto.ChallengeLiterAlura.model.Autor;
import com.projeto.ChallengeLiterAlura.model.Livro;
import com.projeto.ChallengeLiterAlura.response.BooksResponse;
import com.projeto.ChallengeLiterAlura.repository.AutorRepository;
import com.projeto.ChallengeLiterAlura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    private final GutendexClient client = new GutendexClient();

    private final List<Livro> livrosBuscados = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    private LivroEntity converterLivroModelParaEntity(Livro livroModel) {
        LivroEntity livroEntity = new LivroEntity();
        livroEntity.setTitulo(livroModel.getTitulo());
        livroEntity.setIdioma(livroModel.getIdiomaPrincipal());
        livroEntity.setDownloads(livroModel.getDownloads());

        if (livroModel.getAutores() != null && !livroModel.getAutores().isEmpty()) {
            Autor autorModel = livroModel.getAutores().get(0); // pega primeiro autor
            AutorEntity autorEntity = new AutorEntity();
            autorEntity.setNome(autorModel.getNome());
            autorEntity.setAnoNascimento(autorModel.getAnoNascimento());
            autorEntity.setAnoFalecimento(autorModel.getAnoFalecimento());

            livroEntity.setAutor(autorEntity);
        }

        return livroEntity;
    }

    private void salvarLivrosNoBanco(List<Livro> livros) {
        for (Livro livro : livros) {
            LivroEntity livroEntity = converterLivroModelParaEntity(livro);

            AutorEntity autorEntity = livroEntity.getAutor();
            if (autorEntity != null) {
                Optional<AutorEntity> autorExistente = autorRepository.findByNome(autorEntity.getNome());
                if (autorExistente.isPresent()) {
                    livroEntity.setAutor(autorExistente.get());
                } else {
                    autorRepository.save(autorEntity);
                }
            }

            livroRepository.save(livroEntity);
        }
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            try {
                switch (opcao) {
                    case "1": // Buscar livro por título
                        System.out.print("Digite o título do livro para buscar: ");
                        String titulo = scanner.nextLine();

                        BooksResponse resposta = client.buscarLivrosPorTituloMapeado(titulo);
                        List<Livro> livros = resposta.getResultados();
                        if (livros != null) {
                            livrosBuscados.addAll(livros);  // Salva os livros buscados na lista local
                            salvarLivrosNoBanco(livros);   // Persiste no banco
                        }
                        imprimirLivros(livros);
                        break;

                    case "2": // Listar todos os livros no banco
                        List<LivroEntity> livrosNoBanco = livroRepository.findAll();
                        if (livrosNoBanco.isEmpty()) {
                            System.out.println("Nenhum livro encontrado no banco.");
                        } else {
                            for (LivroEntity livroEntity : livrosNoBanco) {
                                System.out.println("Título: " + livroEntity.getTitulo() +
                                        ", Idioma: " + livroEntity.getIdioma() +
                                        ", Downloads: " + livroEntity.getDownloads() +
                                        ", Autor: " + (livroEntity.getAutor() != null ? livroEntity.getAutor().getNome() : "N/A"));
                            }
                        }
                        break;


                    case "3": // Listar autores únicos no banco
                        List<AutorEntity> autoresNoBanco = autorRepository.findAll();
                        if (autoresNoBanco.isEmpty()) {
                            System.out.println("Nenhum autor encontrado no banco.");
                        } else {
                            System.out.println("Autores cadastrados no banco:");
                            for (AutorEntity autor : autoresNoBanco) {
                                System.out.println("- " + autor.getNome());
                            }
                        }
                        break;


                    case "4": // Listar autores vivos em determinado ano
                        System.out.print("Digite o ano para verificar autores vivos: ");
                        int ano = Integer.parseInt(scanner.nextLine());
                        List<AutorEntity> vivos = autorRepository.findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanOrAnoFalecimentoIsNull(ano, ano);
                        if (vivos.isEmpty()) {
                            System.out.println("Nenhum autor vivo encontrado para o ano " + ano);
                        } else {
                            System.out.println("Autores vivos no ano " + ano + ":");
                            vivos.forEach(System.out::println);
                        }
                        break;

                    case "0":
                        continuar = false;
                        System.out.println("Saindo da aplicação.");
                        break;

                    default:
                        System.out.println("Opção inválida, tente novamente.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida: por favor, digite um número válido.");
            } catch (Exception e) {
                System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
                e.printStackTrace();
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
}
