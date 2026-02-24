package com.api.biblioteca.controller;


import com.api.biblioteca.dto.emprestimo.EmprestimoRequestDto;
import com.api.biblioteca.dto.emprestimo.EmprestimoResponseDto;
import com.api.biblioteca.model.Emprestimo;
import com.api.biblioteca.service.EmprestimoService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @PostMapping
    public EmprestimoResponseDto salvarEmprestimo(
            @RequestBody EmprestimoRequestDto emprestimoRequestDto
            ){
        try{
            return emprestimoService.salvarEmprestimo(emprestimoRequestDto);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<EmprestimoResponseDto> obterTodosEmprestimos(){
        try {
            return emprestimoService.obterTodosEmprestimos();
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public EmprestimoResponseDto obterEmprestimoPorID(
            @PathVariable int id
    ){
        try {
            return emprestimoService.obterEmprestimoPorID(id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/usuario/{id}")
    public List<EmprestimoResponseDto> listarEmprestimosPorUsuario(
        @PathVariable   int id
    ){
        try {
        List<EmprestimoResponseDto> lista = emprestimoService.listarEmprestimosPorUsuario(id);
        return lista;
    } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar empréstimos: " + e.getMessage());    }
    }

    @PutMapping("/{id}")
    public EmprestimoResponseDto atualizarEmprestimo(
            @PathVariable int id,
            @RequestBody EmprestimoRequestDto emprestimoRequestDto
    ){
        try{
            return emprestimoService.atualizarEmprestimo(emprestimoRequestDto,id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deletarEmprestimo(
            @PathVariable int id
    ){
        try{
            emprestimoService.deletarEmprestimo(id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
