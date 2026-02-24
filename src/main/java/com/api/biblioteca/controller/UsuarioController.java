package com.api.biblioteca.controller;

import com.api.biblioteca.dto.usuario.UsuarioRequestDto;
import com.api.biblioteca.dto.usuario.UsuarioResponseDto;
import com.api.biblioteca.model.Livro;
import com.api.biblioteca.model.Usuario;
import com.api.biblioteca.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public UsuarioResponseDto salvarUsuario(
            @RequestBody UsuarioRequestDto usuarioRequestDto
    ){
        try{
            return usuarioService.salvarUsuario(usuarioRequestDto);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<UsuarioResponseDto> obterTodosUsuarios(){
        try {
            return usuarioService.obterTodosUsuarios();
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public UsuarioResponseDto obterUsuarioPorID(
            @PathVariable int id
    ){
        try {
            return usuarioService.obterUsuarioPorID(id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public UsuarioResponseDto atualizarUsuario(
            @PathVariable int id,
            @RequestBody UsuarioRequestDto usuarioRequestDto
    ){
        try{
            return usuarioService.atualizarUSuario(usuarioRequestDto,id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(
            @PathVariable int id
    ){
        try{
            usuarioService.deletarUsuario(id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
