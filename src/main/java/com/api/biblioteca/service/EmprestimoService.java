package com.api.biblioteca.service;

import com.api.biblioteca.model.Emprestimo;
import com.api.biblioteca.repository.EmprestimoRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository) {
        this.emprestimoRepository = emprestimoRepository;
    }

    public Emprestimo salvarEmprestimo(Emprestimo emprestimo) throws SQLException{
        return emprestimoRepository.salvarEmprestimo(emprestimo);
    }

    public List<Emprestimo> obterTodosEmprestimos() throws SQLException{
        return emprestimoRepository.obterTodosEmprestimos();
    }

    public Emprestimo obterEmprestimoPorID(int id) throws SQLException{
        return emprestimoRepository.obterEmprestimoPorID(id);
    }

    public Emprestimo atualizarEmprestimo(Emprestimo emprestimo) throws SQLException{
        return emprestimoRepository.atualizarEmprestimo(emprestimo);
    }

    public void deletarEmprestimo(int id) throws SQLException{
        emprestimoRepository.deletarEmprestimo(id);
    }
}
