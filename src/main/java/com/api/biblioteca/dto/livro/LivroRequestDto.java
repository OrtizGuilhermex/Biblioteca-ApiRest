package com.api.biblioteca.dto.livro;

public record LivroRequestDto(
        String titulo,
        String autor,
        int ano_publicacao
) {
}
