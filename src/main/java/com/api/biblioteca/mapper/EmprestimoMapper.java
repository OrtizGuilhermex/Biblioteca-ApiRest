package com.api.biblioteca.mapper;

import com.api.biblioteca.dto.emprestimo.EmprestimoRequestDto;
import com.api.biblioteca.dto.emprestimo.EmprestimoResponseDto;
import com.api.biblioteca.model.Emprestimo;
import org.springframework.stereotype.Component;

@Component
public class EmprestimoMapper {

    public Emprestimo paraEntidade(
            EmprestimoRequestDto emprestimoRequestDto
    ){
        return new Emprestimo(
                emprestimoRequestDto.livro_id(),
                emprestimoRequestDto.usuario_id(),
                emprestimoRequestDto.data_emprestimo(),
                emprestimoRequestDto.data_devolucao()
        );
    }

    public EmprestimoResponseDto paraResponseDto (
            Emprestimo emprestimo
    ){
        return new EmprestimoResponseDto(
                emprestimo.getId(),
                emprestimo.getLivro_id(),
                emprestimo.getUsuario_id(),
                emprestimo.getData_emprestimo(),
                emprestimo.getData_devolucao()
        );
    }
}
