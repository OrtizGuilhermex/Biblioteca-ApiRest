package com.api.biblioteca.dto.livro;

public record LivroResponseDto (
        int id,
        String titulo,
        String autor,
        int ano_publicacao
) {
}
