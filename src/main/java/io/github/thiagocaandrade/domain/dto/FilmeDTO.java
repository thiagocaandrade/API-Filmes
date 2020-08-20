package io.github.thiagocaandrade.domain.dto;

import io.github.thiagocaandrade.domain.Filme;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class FilmeDTO extends Filme {

    private Long id;
    private String nome;
    private String tipo;
    private String avaliacao;

//    public FilmeDTO(Filme f){
//        this.id = f.getId();
//        this.nome = f.getNome();
//        this.tipo = f.getTipo();
//        this.avaliacao = f.getAvaliacao();
//    }

    public static FilmeDTO create(Filme f){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(f, FilmeDTO.class);

    }


}
