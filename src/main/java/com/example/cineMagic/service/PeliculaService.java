package com.example.cineMagic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cineMagic.model.Pelicula;
import com.example.cineMagic.repository.PeliculaRepository;

@Service
public class PeliculaService {
    @Autowired
    private PeliculaRepository peliculaRepository;

    public List<Pelicula> getAllPeliculas() {
        return peliculaRepository.findAll();
    }

    public Pelicula savePelicula(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    public Pelicula getPeliculaById(Long id) {
        return peliculaRepository.findById(id).orElse(null);
    }

    public Pelicula updatePelicula(Long id, Pelicula peliculaDetails) {
        Pelicula existingPelicula = getPeliculaById(id);
        if (existingPelicula != null) {
            existingPelicula.setTitulo(peliculaDetails.getTitulo());
            existingPelicula.setDescripcion(peliculaDetails.getDescripcion());
            existingPelicula.setDirector(peliculaDetails.getDirector());
            existingPelicula.setFechaEstreno(peliculaDetails.getFechaEstreno());
            return savePelicula(existingPelicula);
        }
        return null; 
    }

    public void deletePelicula(Long id) {
        peliculaRepository.deleteById(id);
    }
}
