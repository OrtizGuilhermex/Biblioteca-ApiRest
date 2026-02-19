package com.api.biblioteca.service;


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

    public LivroService(LivroRepository livroRepository, EmprestimoRepository emprestimoRepository) {
        this.livroRepository = livroRepository;
        this.emprestimoRepository = emprestimoRepository;
    }

    public Livro salvarLivro(Livro livro) throws SQLException{
        return livroRepository.salvarLivro(livro);
    }

    public List<Livro> obterTodosLivros() throws SQLException{
        return livroRepository.obterTodoslivros();
    }

    public Livro obterLivroPorID(int id) throws SQLException{
        return livroRepository.obterLivroPorID(id);
    }

    public Livro atualizarLivro(Livro livro,int id) throws SQLException{
        livro.setId(id);
        livroRepository.atualizarLivro(livro);
        return livro;
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
