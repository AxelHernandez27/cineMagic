package com.example.cineMagic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cineMagic.model.Usuario;
import com.example.cineMagic.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private final UsuarioRepository usuarioRepository;
   

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Optional<Usuario> getUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email); // Asegúrate de tener este método en el repositorio
    }

    // Método para actualizar un usuario existente
    public Usuario updateUsuario(Long id, Usuario usuarioDetails) {
        Usuario existingUsuario = getUsuarioById(id);
        if (existingUsuario != null) {
            existingUsuario.setNombre(usuarioDetails.getNombre());
            existingUsuario.setEmail(usuarioDetails.getEmail());
            existingUsuario.setPassword(usuarioDetails.getPassword());
            existingUsuario.setRol(usuarioDetails.getRol());
            return saveUsuario(existingUsuario);
        }
        return null; 
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}