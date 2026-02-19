package com.api.biblioteca.controller;

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
    public Usuario salvarUsuario(
            @RequestBody Usuario usuario
    ){
        try{
            return usuarioService.salvarUsuario(usuario);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<Usuario> obterTodosUsuarios(){
        try {
            return usuarioService.obterTodosUsuarios();
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Usuario obterUsuarioPorID(
            @PathVariable int id
    ){
        try {
            return usuarioService.obterUsuarioPorID(id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Usuario atualizarUsuario(
            @PathVariable int id,
            @RequestBody Usuario usuario
    ){
        try{
            return usuarioService.atualizarUSuario(usuario,id);
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
