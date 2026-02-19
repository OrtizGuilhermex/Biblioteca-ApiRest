package com.api.biblioteca.repository;

import com.api.biblioteca.model.Emprestimo;
import com.api.biblioteca.utils.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmprestimoRepository {

    public Emprestimo salvarEmprestimo(Emprestimo emprestimo) throws SQLException {
        String query = """
                INSERT INTO emprestimo (
                livro_id,
                usuario_id,
                data_emprestimo,
                data_devolucao
                )
                VALUES
                (?,?,?,?)
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS)){

            stmt.setInt(1, emprestimo.getLivro_id());
            stmt.setInt(2, emprestimo.getUsuario_id());
            stmt.setDate(3,Date.valueOf(emprestimo.getData_emprestimo().toString()));
            stmt.setDate(4,Date.valueOf(emprestimo.getData_emprestimo().toString()));
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                emprestimo.setId(rs.getInt(1));
                return emprestimo;
            }
        }
        return null;
    }

    public List<Emprestimo> obterTodosEmprestimos() throws SQLException{
        List<Emprestimo> emprestimos = new ArrayList<>();

        String query = """
                SELECT livro_id,
                usuario_id,
                data_emprestimo,
                data_devolucao
                FROM emprestimo
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                emprestimos.add(new Emprestimo(
                        rs.getInt("livro_id"),
                        rs.getInt("usuario_id"),
                        rs.getDate("data_emprestimo"),
                        rs.getDate("data_devolucao")
                ));
            }
        }
        return emprestimos;
    }

    public Emprestimo obterEmprestimoPorID(int id) throws SQLException{
        String query = """
                SELECT livro_id,
                usuario_id,
                data_emprestimo,
                data_devolucao
                FROM emprestimo
                WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return new Emprestimo(
                        rs.getInt("livro_id"),
                        rs.getInt("usuario_id"),
                        rs.getDate("data_emprestimo"),
                        rs.getDate("data_devolucao")
                );
            }
        }
        throw new RuntimeException("Livro não encontrado");
    }

    public Emprestimo atualizarEmprestimo(Emprestimo emprestimo) throws SQLException{
        String query = """
                UPDATE emprestimo
                set livro_id = ?,
                usuario_id = ?,
                data_emprestimo = ?,
                data_devolucao = ?
                WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, emprestimo.getLivro_id());
            stmt.setInt(2, emprestimo.getUsuario_id());
            stmt.setDate(3,Date.valueOf(emprestimo.getData_emprestimo().toString()));
            stmt.setDate(4,Date.valueOf(emprestimo.getData_devolucao().toString()));
            stmt.setInt(5,emprestimo.getId());
            stmt.executeUpdate();
        }
        return emprestimo;
    }

    public void deletarEmprestimo(int id)throws SQLException{
        String query = """
                DELETE FROM livro
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

}
