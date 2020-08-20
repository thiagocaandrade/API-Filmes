package io.github.thiagocaandrade.domain;

import io.github.thiagocaandrade.api.exception.ObjectNotFoundException;
import io.github.thiagocaandrade.domain.dto.FilmeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository rep;

    public List<FilmeDTO> getFilmes() {
        List<FilmeDTO> list = rep.findAll().stream().map(FilmeDTO::create).collect(Collectors.toList());
        return list;
    }

    public FilmeDTO getFilmeById(Long id) {
        Optional<Filme> carro = rep.findById(id);
        return carro.map(FilmeDTO::create)
                .orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado"));
    }

    public List<FilmeDTO> getFilmesByTipo(String tipo) {
        return rep.findByTipo(tipo)
                .stream().map(FilmeDTO::create)
                .collect(Collectors.toList());
    }

    public List<FilmeDTO> getFilmesByAvalicao(String avaliacao) {
        return rep.findByAvaliacao(avaliacao)
                .stream().map(FilmeDTO::create)
                .collect(Collectors.toList());
    }

    public FilmeDTO insert(Filme filme) {
        Assert.isNull(filme.getId(),"Não foi possível inserir o registro");

        return FilmeDTO.create(rep.save(filme));
    }


    public FilmeDTO update(Filme filme, Long id) {

        Assert.notNull(id, "Não foi possível atualizar o registro");

        Optional<Filme> optional = rep.findById(id);
        if (optional.isPresent()) {
            Filme db = optional.get();
            db.setNome(filme.getNome());
            db.setTipo(filme.getTipo());
            db.setAvaliacao(filme.getAvaliacao());
            System.out.println("Carro id " + db.getId());
            rep.save(db);

            return FilmeDTO.create(db);
        } else {
            return null;
        }

    }

    public void delete(Long id) {
        rep.deleteById(id);

    }

}
