package com.example.cineMagic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cineMagic.model.Pelicula;
@Repository
public interface PeliculaRepository extends JpaRepository <Pelicula, Long>{

}
