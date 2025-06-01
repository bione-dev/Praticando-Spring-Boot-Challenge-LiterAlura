package com.projeto.ChallengeLiterAlura;

import com.projeto.ChallengeLiterAlura.client.GutendexClient;

public class Main {
    public static void main(String[] args) {
        GutendexClient client = new GutendexClient();
        try {
            String resultadoJson = client.buscarLivroPorTitulo("Pride and Prejudice");
            System.out.println("Resposta da API Gutendex:");
            System.out.println(resultadoJson);
        } catch (Exception e) {
            System.err.println("Erro ao buscar livro:");
            e.printStackTrace();
        }
    }
}
