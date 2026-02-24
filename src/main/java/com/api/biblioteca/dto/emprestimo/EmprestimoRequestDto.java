package com.api.biblioteca.dto.emprestimo;

import java.time.LocalDate;

public record EmprestimoRequestDto(
            int livro_id,
            int usuario_id,
            LocalDate data_emprestimo,
            LocalDate data_devolucao
) {
}
