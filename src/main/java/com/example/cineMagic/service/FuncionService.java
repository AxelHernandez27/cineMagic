package com.example.cineMagic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cineMagic.model.Funcion;
import com.example.cineMagic.repository.FuncionRepository;

@Service
public class FuncionService {
    @Autowired

    private FuncionRepository funcionRepository;

    public List<Funcion> getAllFunciones() {
        return funcionRepository.findAll();
    }

    public Funcion saveFuncion(Funcion funcion) {
        return funcionRepository.save(funcion);
    }

    public Funcion getFuncionById(Long id) {
        return funcionRepository.findById(id).orElse(null);
    }
    
    public Funcion updateFuncion(Long id, Funcion funcionDetails) {
        Funcion funcion = funcionRepository.findById(id).orElse(null);
        if (funcion != null) {
            funcion.setPelicula(funcionDetails.getPelicula());
            funcion.setHorario(funcionDetails.getHorario());
            funcion.setSala(funcionDetails.getSala());
            return funcionRepository.save(funcion);
        }
        return null;
    }

    public void deleteFuncion(Long id) {
        funcionRepository.deleteById(id);
    }
}
