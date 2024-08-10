package com.example.cineMagic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cineMagic.model.Funcion;
@Repository
public interface FuncionRepository extends JpaRepository <Funcion, Long> {

}
