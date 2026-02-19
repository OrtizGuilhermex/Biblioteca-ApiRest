package com.api.biblioteca.service;

import com.api.biblioteca.model.Usuario;
import com.api.biblioteca.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvarUsuario(Usuario usuario)throws SQLException{
        return usuarioRepository.salvarUsuario(usuario);
    }

    public List<Usuario> obterTodosUsuarios()throws SQLException{
        return usuarioRepository.obterTodosUsuarios();
    }

    public Usuario obterUsuarioPorID(int id)throws SQLException{
        return usuarioRepository.obterUsuarioPorID(id);
    }

    public Usuario atualizarUSuario (Usuario usuario, int id) throws SQLException {
        usuario.setId(id);
        usuarioRepository.atualizarUSuario(usuario);
        return usuario;
    }

    public void deletarUsuario(int id) throws SQLException{
        usuarioRepository.deletarUsuario(id);
    }
}
