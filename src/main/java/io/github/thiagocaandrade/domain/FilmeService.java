package io.github.thiagocaandrade.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository rep;

    public Iterable<Filme> getFilmes() {
        return rep.findAll();
    }

    public Optional<Filme> getFilmeById(Long id) {
        return rep.findById(id);
    }

    public Iterable<Filme> getFilmesByTipo(String tipo) {
        return rep.findByTipo(tipo);
    }

    public Iterable<Filme> getFilmesByAvalicao(String avaliacao) {
        return rep.findByAvaliacao(avaliacao);
    }

    public Filme save(Filme filme) {
        rep.save(filme);
        return filme;
    }


    public Filme update(Filme filme, Long id, Long avaliacao) {

        getFilmeById(id).map(db -> {
            db.setNome(filme.getNome());
            db.setTipo(filme.getTipo());
            db.setAvaliacao(filme.getAvaliacao());
            System.out.println("Filme id " + db.getId());
            rep.save(db);
            return db;
        }).orElseThrow(() ->
                new RuntimeException("Não foi possível atualizar o registro"));
        return filme;
    }

    public void delete(Long id) {
        Optional<Filme> filme = getFilmeById(id);
        if (filme.isPresent()){
            rep.deleteById(id);
        }
    }


}
