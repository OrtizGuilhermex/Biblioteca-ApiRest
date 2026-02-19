package com.api.biblioteca.service;


import com.api.biblioteca.model.Livro;
import com.api.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
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
        livroRepository.atualizarUSuario(livro);
        return livro;
    }

    public void deletarLivro(int id) throws SQLException{
        livroRepository.deletarLivro(id);
    }

}
