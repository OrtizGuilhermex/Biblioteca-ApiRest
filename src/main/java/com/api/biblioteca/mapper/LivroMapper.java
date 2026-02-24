package com.api.biblioteca.mapper;


import com.api.biblioteca.dto.livro.LivroRequestDto;
import com.api.biblioteca.dto.livro.LivroResponseDto;
import com.api.biblioteca.model.Livro;
import org.springframework.stereotype.Component;

@Component
public class LivroMapper {

    public Livro paraEntidade (
            LivroRequestDto livroRequestDto
    ){
        return new Livro(
                livroRequestDto.titulo(),
                livroRequestDto.autor(),
                livroRequestDto.ano_publicacao()
        );
    }

    public LivroResponseDto paraResponseDto (
            Livro livro
    ){
        return new LivroResponseDto(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getAno_publicacao()
        );
    }

}
