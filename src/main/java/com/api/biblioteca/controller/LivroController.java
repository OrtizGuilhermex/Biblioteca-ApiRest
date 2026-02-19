package com.api.biblioteca.controller;

import com.api.biblioteca.model.Emprestimo;
import com.api.biblioteca.model.Livro;
import com.api.biblioteca.service.LivroService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }


    @PostMapping
    public Livro salvarLivro(
            @RequestBody Livro livro
    ){
        try{
            return livroService.salvarLivro(livro);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<Livro> obterTodosLivros(){
        try {
            return livroService.obterTodosLivros();
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Livro obterLivroPorID(
            @PathVariable int id
    ){
        try {
            return livroService.obterLivroPorID(id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Livro atualizarLivro(
            @PathVariable int id,
            @RequestBody Livro livro
    ){
        try{
            return livroService.atualizarLivro(livro,id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deletarLivro(
            @PathVariable int id
    ){
        try{
            livroService.deletarLivro(id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
