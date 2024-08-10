package com.example.cineMagic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cineMagic.model.Usuario;
import com.example.cineMagic.service.UsuarioService;
import com.example.cineMagic.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Registra un nuevo usuario. La contraseña se encripta antes de guardar el usuario.
    @PostMapping("/register")
    public String registerUser(@RequestBody Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword())); // Encriptar la contraseña
        usuarioService.saveUsuario(usuario);
        return "User registered successfully";
    }

    //Inicia sesión de un usuario. Verifica las credenciales y devuelve un token JWT si son correctas.
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        Optional<Usuario> existingUser = usuarioService.getUsuarioByEmail(usuario.getEmail());
        if (existingUser.isPresent() && passwordEncoder.matches(usuario.getPassword(), existingUser.get().getPassword())) {
            String token = jwtUtil.generateToken(existingUser.get().getEmail()); // Uso correcto del método
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Unauthorized");
    }
}
