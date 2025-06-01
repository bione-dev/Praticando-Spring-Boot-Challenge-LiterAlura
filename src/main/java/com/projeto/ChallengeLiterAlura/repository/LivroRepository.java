package com.projeto.ChallengeLiterAlura.repository;


import com.projeto.ChallengeLiterAlura.entity.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<LivroEntity, Long> {
    List<LivroEntity> findByIdioma(String idioma);
}

