package com.projeto.ChallengeLiterAlura.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.projeto.ChallengeLiterAlura.model.Livro;

import java.util.List;



@JsonIgnoreProperties(ignoreUnknown = true)
public class BooksResponse {

    @JsonAlias("count")
    private Integer total;

    @JsonAlias("next")
    private String proximo;

    @JsonAlias("previous")
    private String anterior;

    @JsonAlias("results")
    private List<Livro> resultados;

    // Getters e setters

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getProximo() {
        return proximo;
    }

    public void setProximo(String proximo) {
        this.proximo = proximo;
    }

    public String getAnterior() {
        return anterior;
    }

    public void setAnterior(String anterior) {
        this.anterior = anterior;
    }

    public List<Livro> getResultados() {
        return resultados;
    }

    public void setResultados(List<Livro> resultados) {
        this.resultados = resultados;
    }
}
