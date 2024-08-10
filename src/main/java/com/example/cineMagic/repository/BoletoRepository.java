package com.example.cineMagic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cineMagic.model.Boleto;
@Repository
public interface BoletoRepository extends JpaRepository <Boleto, Long> {
    
}
