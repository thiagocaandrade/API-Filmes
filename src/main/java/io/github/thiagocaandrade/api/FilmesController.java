package io.github.thiagocaandrade.api;

import io.github.thiagocaandrade.domain.Filme;
import io.github.thiagocaandrade.domain.FilmeService;
import net.bytebuddy.build.Plugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/filmes")
public class FilmesController {

    @Autowired
    private FilmeService service;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Filme> get(){
        return service.getFilmes();
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id){

        Optional<Filme> filme = service.getFilmeById(id);

        return filme
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tipo/{tipo}")
    public Iterable<Filme> getFilmesByTipo(@PathVariable("tipo") String tipo){
        return service.getFilmesByTipo(tipo);
    }

    @GetMapping("/avaliacao/{avaliacao}")
    public Iterable<Filme> getFilmesByAvalicao(@PathVariable("avaliacao") String avaliacao){
        return service.getFilmesByAvalicao(avaliacao);
    }

    @PostMapping
    public String post(@RequestBody Filme filme){
        Filme f = service.save(filme);

        return "Filme salvo com sucesso!";
    }

    @PutMapping("/{id}")
    public String put(@PathVariable("id") Long id, @RequestBody Filme filme, @RequestBody Long avaliacao){
        Filme f = service.update(filme, id, avaliacao);
        return "Filme salvo com sucesso!";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable ("id") Long id){
        service.delete(id);

        return "Filme deletado com sucesso!";
    }


}
