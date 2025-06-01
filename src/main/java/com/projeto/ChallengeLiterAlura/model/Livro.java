package com.projeto.ChallengeLiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Livro {

    private Long id;

    @JsonAlias("title")
    private String titulo;

    @JsonAlias("authors")
    private List<Autor> autores;

    @JsonAlias("languages")
    private List<String> idiomas;

    @JsonAlias("download_count")
    private Integer downloads;

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    // Retorna o nome do primeiro autor, ou "N/A" caso não tenha autores
    public String getAutorPrincipal() {
        if (autores != null && !autores.isEmpty()) {
            return autores.get(0).getNome();
        }
        return "N/A";
    }

    // Retorna o primeiro idioma, ou "N/A" caso não tenha idiomas
    public String getIdiomaPrincipal() {
        if (idiomas != null && !idiomas.isEmpty()) {
            return idiomas.get(0);
        }
        return "N/A";
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor=" + getAutorPrincipal() +
                ", idioma=" + getIdiomaPrincipal() +
                ", downloads=" + downloads +
                '}';
    }
}
