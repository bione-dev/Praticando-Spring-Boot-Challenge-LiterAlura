package com.projeto.ChallengeLiterAlura.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.ChallengeLiterAlura.model.Autor;
import com.projeto.ChallengeLiterAlura.model.Livro;
import com.projeto.ChallengeLiterAlura.response.BooksResponse;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GutendexClient {

    private static final String BASE_URL = "https://gutendex.com/books/";

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public GutendexClient() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

//    /**
//     * Busca livros pela API Gutendex por título e retorna JSON bruto.
//     */
//    public String buscarLivroPorTitulo(String titulo) throws IOException, InterruptedException {
//        String url = BASE_URL + "?search=" + titulo.replace(" ", "+");
//
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(url))
//                .GET()
//                .build();
//
//        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//
//        if (response.statusCode() == 200) {
//            return response.body();
//        } else {
//            throw new RuntimeException("Erro na requisição HTTP: código " + response.statusCode());
//        }


    /**
     * Busca livros pela API Gutendex por título e retorna objeto mapeado BooksResponse.
     */
    public BooksResponse buscarLivrosPorTituloMapeado(String titulo) throws IOException, InterruptedException {
        String url = BASE_URL + "?search=" + titulo.replace(" ", "+");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return objectMapper.readValue(response.body(), BooksResponse.class);
        } else {
            throw new RuntimeException("Erro na requisição HTTP: código " + response.statusCode());
        }
    }
    /**
     * Busca livros pela API Gutendex por autor e retorna objeto mapeado BooksResponse.
     */

    public BooksResponse buscarLivrosPorAutorMapeado(String autor) throws IOException, InterruptedException {
        String url = BASE_URL + "?search=" + autor.replace(" ", "+");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return objectMapper.readValue(response.body(), BooksResponse.class);
        } else {
            throw new RuntimeException("Erro na requisição HTTP: código " + response.statusCode());
        }
    }


}
