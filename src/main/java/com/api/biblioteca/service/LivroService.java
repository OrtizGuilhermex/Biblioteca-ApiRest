package com.api.biblioteca.service;


import com.api.biblioteca.dto.livro.LivroRequestDto;
import com.api.biblioteca.dto.livro.LivroResponseDto;
import com.api.biblioteca.mapper.LivroMapper;
import com.api.biblioteca.model.Emprestimo;
import com.api.biblioteca.model.Livro;
import com.api.biblioteca.repository.EmprestimoRepository;
import com.api.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final EmprestimoRepository emprestimoRepository;
    private final LivroMapper livroMapper;

    public LivroService(LivroRepository livroRepository, EmprestimoRepository emprestimoRepository, LivroMapper livroMapper) {
        this.livroRepository = livroRepository;
        this.emprestimoRepository = emprestimoRepository;
        this.livroMapper = livroMapper;
    }

    public LivroResponseDto salvarLivro(
            LivroRequestDto livroRequestDto) throws SQLException{

        Livro livro = livroMapper.paraEntidade(livroRequestDto);

        livroRepository.salvarLivro(livro);

        return livroMapper.paraResponseDto(livro);

    }

    public List<LivroResponseDto> obterTodosLivros() throws SQLException{
        List<Livro> livroList = livroRepository.obterTodoslivros();

        return livroList.stream()
                .map(livroMapper::paraResponseDto)
                .toList();

    }

    public LivroResponseDto obterLivroPorID(int id) throws SQLException{

        Livro livro = livroRepository.obterLivroPorID(id);

        return livroMapper.paraResponseDto(livro);

    }

    public LivroResponseDto atualizarLivro(
            LivroRequestDto livroRequestDto,int id) throws SQLException{

        Livro livro = livroMapper.paraEntidade(livroRequestDto);

        livroRepository.atualizarLivro(livro);
        livro.setId(id);

        return livroMapper.paraResponseDto(livro);

    }

    public void deletarLivro(int id) throws SQLException{

        if(emprestimoRepository.emprestimoAtivo(id)){
            throw new RuntimeException("Operação negada: O livro possui empréstimos pendentes de devolução.");
        }

        try {
            livroRepository.deletarLivro(id);
        } catch (SQLException e) {
            if (e.getErrorCode() == 1451) {
                throw new RuntimeException("Não é possível apagar o livro pois ele possui histórico de empréstimos antigos.");
            }
            throw e;
        }
    }

}
