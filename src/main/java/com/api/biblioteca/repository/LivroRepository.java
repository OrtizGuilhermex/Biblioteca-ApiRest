package com.api.biblioteca.repository;
import com.api.biblioteca.model.Livro;
import com.api.biblioteca.utils.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LivroRepository {

    public Livro salvarLivro(Livro livro) throws SQLException {
        String query = """
                INSERT INTO livro (
                titulo,
                autor,
                ano_publicacao
                )
                VALUES
                (?,?,?)
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3,livro.getAno_publicacao());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                livro.setId(rs.getInt(1));
                return livro;
            }
        }
        return null;
    }

    public List<Livro> obterTodoslivros() throws SQLException{
        List<Livro> livros = new ArrayList<>();

        String query = """
                SELECT titulo
                ,autor
                ,ano_publicacao
                FROM livro
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                livros.add(new Livro(
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("ano_publicacao")
                ));
            }
        }
        return livros;
    }

    public Livro obterLivroPorID(int id) throws SQLException{
        String query = """
                SELECT titulo
                ,autor
                ,ano_publicacao
                FROM livro
                WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("ano_publicacao")
                );
            }
        }
        throw new RuntimeException("Livro não encontrado");
    }

    public Livro atualizarUSuario(Livro livro) throws SQLException{
        String query = """
                UPDATE livro
                set titulo = ?,
                autor = ?,
                ano_publicacao = ?
                WHERE id = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3,livro.getAno_publicacao());
            stmt.setInt(4,livro.getId());
            stmt.executeUpdate();
        }
        return livro;
    }

    public void deletarLivro(int id)throws SQLException{
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
