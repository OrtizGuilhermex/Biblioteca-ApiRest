package com.api.biblioteca.service;

import com.api.biblioteca.dto.emprestimo.EmprestimoRequestDto;
import com.api.biblioteca.dto.emprestimo.EmprestimoResponseDto;
import com.api.biblioteca.mapper.EmprestimoMapper;
import com.api.biblioteca.model.Emprestimo;
import com.api.biblioteca.model.Livro;
import com.api.biblioteca.repository.EmprestimoRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final EmprestimoMapper emprestimoMapper;

    public EmprestimoService(EmprestimoRepository emprestimoRepository, EmprestimoMapper emprestimoMapper) {
        this.emprestimoRepository = emprestimoRepository;
        this.emprestimoMapper = emprestimoMapper;
    }

    public EmprestimoResponseDto salvarEmprestimo(
            EmprestimoRequestDto emprestimoRequestDto) throws SQLException{

        Emprestimo emprestimo = emprestimoMapper.paraEntidade(emprestimoRequestDto);

        emprestimoRepository.salvarEmprestimo(emprestimo);

        return emprestimoMapper.paraResponseDto(emprestimo) ;
    }

    public List<EmprestimoResponseDto> obterTodosEmprestimos() throws SQLException{
        List<Emprestimo> emprestimoList = emprestimoRepository.obterTodosEmprestimos();

        return emprestimoList.stream()
                .map(emprestimoMapper::paraResponseDto)
                .toList();
    }

    public EmprestimoResponseDto obterEmprestimoPorID(int id) throws SQLException{

        Emprestimo emprestimo = emprestimoRepository.obterEmprestimoPorID(id);

        return emprestimoMapper.paraResponseDto(emprestimo);
    }

    public EmprestimoResponseDto atualizarEmprestimo(
            EmprestimoRequestDto emprestimoRequestDto,int id) throws SQLException{

            Emprestimo emprestimo = emprestimoMapper.paraEntidade(emprestimoRequestDto);

            emprestimoRepository.atualizarEmprestimo(emprestimo);

            return emprestimoMapper.paraResponseDto(emprestimo);
    }

    public List<EmprestimoResponseDto> listarEmprestimosPorUsuario(int id) throws SQLException{

        List<Emprestimo> emprestimoList = emprestimoRepository.buscarPorUsuario(id);

        return emprestimoList.stream()
                .map(emprestimoMapper::paraResponseDto)
                .toList();

    }

    public void deletarEmprestimo(int id) throws SQLException{
        emprestimoRepository.deletarEmprestimo(id);
    }
}
