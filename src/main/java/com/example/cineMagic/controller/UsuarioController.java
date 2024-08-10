package com.example.cineMagic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cineMagic.model.Usuario;
import com.example.cineMagic.service.UsuarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
@Autowired
private UsuarioService usuarioService;

 @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

  @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario){
        return usuarioService.saveUsuario(usuario);
    }
  
  @GetMapping("/{id}")
    public Usuario getUsuarioById(@PathVariable long id){
        return usuarioService.getUsuarioById(id);
    } 
    
    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetails) {
        Usuario existingUsuario = usuarioService.getUsuarioById(id);
        if (existingUsuario != null) {
            existingUsuario.setNombre(usuarioDetails.getNombre());
            existingUsuario.setEmail(usuarioDetails.getEmail());
            existingUsuario.setPassword(usuarioDetails.getPassword()); // Cambiado a password
            existingUsuario.setRol(usuarioDetails.getRol());
            return usuarioService.saveUsuario(existingUsuario);
        }
        return null;
    }
    
    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable long id){
        usuarioService.deleteUsuario(id);
    }


}
