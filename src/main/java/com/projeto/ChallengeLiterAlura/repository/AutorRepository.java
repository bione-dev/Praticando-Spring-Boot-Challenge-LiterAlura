package com.projeto.ChallengeLiterAlura.repository;

import com.projeto.ChallengeLiterAlura.entity.AutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<AutorEntity, Long>
{     Optional<AutorEntity> findByNome(String nome);

    List<AutorEntity> findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanOrAnoFalecimentoIsNull(int ano1, int ano2);
}
