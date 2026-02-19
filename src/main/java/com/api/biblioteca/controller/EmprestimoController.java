package com.api.biblioteca.controller;


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
    public Emprestimo salvarEmprestimo(
            @RequestBody Emprestimo emprestimo
    ){
        try{
            return emprestimoService.salvarEmprestimo(emprestimo);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<Emprestimo> obterTodosEmprestimos(){
        try {
            return emprestimoService.obterTodosEmprestimos();
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Emprestimo obterEmprestimoPorID(
            @PathVariable int id
    ){
        try {
            return emprestimoService.obterEmprestimoPorID(id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/usuario/{id}")
    public List<Emprestimo> listarEmprestimosPorUsuario(
        @PathVariable   int id
    ){
        try {
        List<Emprestimo> lista = emprestimoService.listarEmprestimosPorUsuario(id);
        return lista;
    } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar empréstimos: " + e.getMessage());    }
    }

    @PutMapping("/{id}")
    public Emprestimo atualizarEmprestimo(
            @PathVariable int id,
            @RequestBody Emprestimo emprestimo
    ){
        try{
            return emprestimoService.atualizarEmprestimo(emprestimo,id);
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
