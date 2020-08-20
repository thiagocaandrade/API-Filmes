package io.github.thiagocaandrade.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface FilmeRepository extends JpaRepository<Filme, Long> {


    Optional<Filme> findById(Long id);

    List<Filme> findByTipo(String tipo);

    List<Filme> findByAvaliacao(String avaliacao);

}
