package io.github.thiagocaandrade;

import io.github.thiagocaandrade.domain.Filme;
import io.github.thiagocaandrade.domain.FilmeService;
import io.github.thiagocaandrade.domain.dto.FilmeDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;
import java.util.Optional;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmesApplicationsTests {

    @Autowired
    private FilmeService service;

    @Test
    public void test1(){
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

    }

    @Test
    public void test2(){

    }
}
