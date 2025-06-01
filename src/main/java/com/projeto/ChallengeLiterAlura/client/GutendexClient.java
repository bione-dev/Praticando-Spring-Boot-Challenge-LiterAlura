package com.projeto.ChallengeLiterAlura.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.io.IOException;

public class GutendexClient {

    private static final String BASE_URL = "https://gutendex.com/books/";

    private final HttpClient httpClient;

    public GutendexClient() {
        this.httpClient = HttpClient.newHttpClient();
    }

    /**
     * Busca livros pela API Gutendex por título.
     * @param titulo O título do livro para busca.
     * @return String JSON bruto da resposta.
     * @throws IOException
     * @throws InterruptedException
     */
    public String buscarLivroPorTitulo(String titulo) throws IOException, InterruptedException {
        // Monta a URL de busca, substituindo espaços por +
        String url = BASE_URL + "?search=" + titulo.replace(" ", "+");

        // Cria a requisição GET
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();


        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());


        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new RuntimeException("Erro na requisição HTTP: código " + response.statusCode());
        }
    }
}
