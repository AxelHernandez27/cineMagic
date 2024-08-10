package com.example.cineMagic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cineMagic.model.Funcion;
import com.example.cineMagic.service.FuncionService;

@RestController
@RequestMapping("/api/funciones")
public class FuncionController {

    @Autowired
    private FuncionService funcionService;

    @GetMapping
    public List<Funcion> getAllFunciones(){
        return funcionService.getAllFunciones();
    }

    @PostMapping
    public Funcion createFuncion(@RequestBody Funcion funcion){
        return funcionService.saveFuncion(funcion);
    }

    @GetMapping("/{id}")
    public Funcion getFuncionById(@PathVariable Long id){
        return funcionService.getFuncionById(id);
    }

    @PutMapping("/{id}")
    public Funcion updateFuncion(@PathVariable Long id, @RequestBody Funcion funcion) {
        Funcion existingFuncion = funcionService.getFuncionById(id);
        if (existingFuncion != null) {
            // Actualiza los campos de la función existente con los datos de la nueva función
            existingFuncion.setPelicula(funcion.getPelicula());
            existingFuncion.setHorario(funcion.getHorario());
            existingFuncion.setSala(funcion.getSala());
            return funcionService.saveFuncion(existingFuncion);
        } else {
            return null; 
        }
    }

    @DeleteMapping("/{id}")
    public void deleteFuncion(@PathVariable Long id){
     funcionService.deleteFuncion(id);
    }




}
