package com.projeto.ChallengeLiterAlura.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "autores")
public class AutorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(name = "ano_nascimento")
    private Integer anoNascimento;

    @Column(name = "ano_falecimento")
    private Integer anoFalecimento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private Set<LivroEntity> livros;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoFalecimento(Integer anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }

    public Set<LivroEntity> getLivros() {
        return livros;
    }

    public void setLivros(Set<LivroEntity> livros) {
        this.livros = livros;
    }

    @Override
    public String toString() {
        return "AutorEntity{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", anoNascimento=" + anoNascimento +
                ", anoFalecimento=" + anoFalecimento +
                '}';
    }

}
