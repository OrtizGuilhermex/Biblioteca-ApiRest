package com.api.biblioteca.service;

import com.api.biblioteca.dto.usuario.UsuarioRequestDto;
import com.api.biblioteca.dto.usuario.UsuarioResponseDto;
import com.api.biblioteca.mapper.UsuarioMapper;
import com.api.biblioteca.model.Usuario;
import com.api.biblioteca.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public UsuarioResponseDto salvarUsuario(
            UsuarioRequestDto usuarioRequestDto)throws SQLException{

        Usuario usuario = usuarioMapper.paraEntidade(usuarioRequestDto);

        usuarioRepository.salvarUsuario(usuario);

        return usuarioMapper.paraResponseDto(usuario);
    }

    public List<UsuarioResponseDto> obterTodosUsuarios()throws SQLException{
        List<Usuario> usuarioList = usuarioRepository.obterTodosUsuarios();

        return usuarioList.stream()
                .map(usuarioMapper::paraResponseDto)
                .toList();

    }

    public UsuarioResponseDto obterUsuarioPorID(int id)throws SQLException{

        Usuario usuario = usuarioRepository.obterUsuarioPorID(id);

        return usuarioMapper.paraResponseDto(usuario);
    }

    public UsuarioResponseDto atualizarUSuario (
            UsuarioRequestDto usuarioRequestDto, int id) throws SQLException {

        Usuario usuario = usuarioMapper.paraEntidade(usuarioRequestDto);

        usuarioRepository.atualizarUSuario(usuario);
        usuario.setId(id);

        return usuarioMapper.paraResponseDto(usuario);
    }

    public void deletarUsuario(int id) throws SQLException{
        usuarioRepository.deletarUsuario(id);
    }
}
