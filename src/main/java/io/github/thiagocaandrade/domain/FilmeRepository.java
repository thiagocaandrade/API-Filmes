package io.github.thiagocaandrade.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface FilmeRepository extends CrudRepository<Filme, Long> {


    Optional<Filme> findById(Long id);

    Iterable<Filme> findByTipo(String tipo);

    Iterable<Filme> findByAvaliacao(String avaliacao);

}
