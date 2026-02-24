package com.api.biblioteca.mapper;

import com.api.biblioteca.dto.usuario.UsuarioRequestDto;
import com.api.biblioteca.dto.usuario.UsuarioResponseDto;
import com.api.biblioteca.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario paraEntidade (
            UsuarioRequestDto usuarioRequestDto
    ){
        return new Usuario(
                usuarioRequestDto.nome(),
                usuarioRequestDto.email()
        );
    }

    public UsuarioResponseDto paraResponseDto (
            Usuario usuario
    ){
        return new UsuarioResponseDto(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }
}
