package io.github.thiagocaandrade.api;

import io.github.thiagocaandrade.domain.Filme;
import io.github.thiagocaandrade.domain.FilmeService;
import io.github.thiagocaandrade.domain.dto.FilmeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/filmes")
public class FilmesController {

    @Autowired
    private FilmeService service;

    @GetMapping()
    public ResponseEntity get() {
        List<FilmeDTO> filmes = service.getFilmes();
        return ResponseEntity.ok(filmes);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        FilmeDTO filme = service.getFilmeById(id);

        return ResponseEntity.ok(filme);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<FilmeDTO>> getFilmesByTipo(@PathVariable("tipo") String tipo) {
        List<FilmeDTO> filmes = service.getFilmesByTipo(tipo);

        return filmes.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(filmes);
    }

    @GetMapping("/avaliacao/{avaliacao}")
    public ResponseEntity<List<FilmeDTO>> getFilmesByAvalicao(@PathVariable("avaliacao") String avaliacao){
        List<FilmeDTO> filmes = service.getFilmesByAvalicao(avaliacao);

        return filmes.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(filmes);
    }

    @PostMapping
    public ResponseEntity post(@RequestBody Filme filme) {
    try{
        FilmeDTO f = service.insert(filme);
        URI location = getUri(f.getId());
        return ResponseEntity.created(location).build();
    } catch (Exception ex) {
        return ResponseEntity.badRequest().build();
    }

    }

    private URI getUri(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Filme filme) {

        filme.setId(id);

        FilmeDTO c = service.update(filme, id);

        return c != null ?
                ResponseEntity.ok(c) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable ("id") Long id){
        service.delete(id);

        return ResponseEntity.ok().build();

    }

}
