package com.api.biblioteca.controller;

import com.api.biblioteca.dto.livro.LivroRequestDto;
import com.api.biblioteca.dto.livro.LivroResponseDto;
import com.api.biblioteca.model.Livro;
import com.api.biblioteca.service.LivroService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }


    @PostMapping
    public LivroResponseDto salvarLivro(
            @RequestBody LivroRequestDto livroRequestDto
    ){
        try{
            return livroService.salvarLivro(livroRequestDto);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<LivroResponseDto> obterTodosLivros(){
        try {
            return livroService.obterTodosLivros();
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public LivroResponseDto obterLivroPorID(
            @PathVariable int id
    ){
        try {
            return livroService.obterLivroPorID(id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public LivroResponseDto atualizarLivro(
            @PathVariable int id,
            @RequestBody LivroRequestDto livroRequestDto
    ){
        try{
            return livroService.atualizarLivro(livroRequestDto,id);
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
