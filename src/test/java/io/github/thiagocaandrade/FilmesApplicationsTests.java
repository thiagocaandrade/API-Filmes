package io.github.thiagocaandrade;

import io.github.thiagocaandrade.domain.Filme;
import io.github.thiagocaandrade.domain.FilmeService;
import io.github.thiagocaandrade.domain.dto.FilmeDTO;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmesApplicationsTests {

    @Autowired
    private FilmeService service;

    @Test
    public void testSave(){
        Filme filme = new Filme();
        filme.setNome("Batman");
        filme.setTipo("acao");
        filme.setAvaliacao("medio");

        FilmeDTO f = service.insert(filme);

        assertNotNull(f);

        Long id = f.getId();
        assertNotNull(id);

        //Buscar o objeto
        Optional<FilmeDTO> op = Optional.ofNullable(service.getFilmeById(id));
        assertTrue(op.isPresent());

        f = op.get();
        assertEquals("Batman", f.getNome());
        assertEquals("acao", f.getTipo());
        assertEquals("medio", f.getAvaliacao());

        //Deletar o objeto
        service.delete(id);

    }

    @Test
    public void testLista(){

        List<FilmeDTO> filmes = service.getFilmes();
        assertEquals(3, filmes.size());

    }
}
