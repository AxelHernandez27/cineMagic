package com.example.cineMagic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cineMagic.model.Pelicula;
import com.example.cineMagic.service.PeliculaService;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculaController {
    @Autowired

    private PeliculaService peliculaService;

    @GetMapping
    public List<Pelicula> getAllPeliculas() {
        return peliculaService.getAllPeliculas();
    }

    @PostMapping
    public Pelicula createPelicula(@RequestBody Pelicula pelicula){
        return peliculaService.savePelicula(pelicula);
    }

    @GetMapping("/{id}")
    public Pelicula getPeliculaById(@PathVariable Long id){
        return peliculaService.getPeliculaById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> updatePelicula(@PathVariable Long id, @RequestBody Pelicula peliculaDetails) {
        Pelicula existingPelicula = peliculaService.getPeliculaById(id);
        if (existingPelicula != null) {
            existingPelicula.setTitulo(peliculaDetails.getTitulo());
            existingPelicula.setDescripcion(peliculaDetails.getDescripcion());
            existingPelicula.setDirector(peliculaDetails.getDirector());
            existingPelicula.setFechaEstreno(peliculaDetails.getFechaEstreno());
            Pelicula updatedPelicula = peliculaService.savePelicula(existingPelicula);
            return ResponseEntity.ok(updatedPelicula);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deletePelicula(@PathVariable Long id){
        peliculaService.deletePelicula(id);
    }

}
