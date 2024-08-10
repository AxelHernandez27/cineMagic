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

import com.example.cineMagic.model.Boleto;
import com.example.cineMagic.service.BoletoService;

@RestController
@RequestMapping("/api/boleto")
public class BoletoController {
    @Autowired
    private BoletoService boletoService;

    //Obtiene la lista de todos los boletos
    @GetMapping
    public List<Boleto> getAllBoletos(){
        return boletoService.getAllBoletos();
    }

    //Crea un nuevo boleto
    @PostMapping
    public Boleto createBoleto(@RequestBody Boleto boleto){
        return boletoService.saveBoleto(boleto);
    }

    //Busca un boleto por id en especifico
    @GetMapping("/{id}")
    public Boleto getBoletoById(@PathVariable Long id){
        return boletoService.getBoletoById(id);
    }

    //Actualiza el boleto
    @PutMapping("/{id}")
    public Boleto updateBoleto(@PathVariable Long id, @RequestBody Boleto boleto) {
        Boleto existingBoleto = boletoService.getBoletoById(id);
        if (existingBoleto != null) {
            boleto.setId(id);  // Asegúrate de que el ID del boleto esté establecido
            return boletoService.saveBoleto(boleto);
        }
        return null; 
    }

    //Elimina el boleto
    @DeleteMapping("/{id}")
    public void deleteBoleto(@PathVariable Long id){
        boletoService.deleteBoleto(id);
    }

}
