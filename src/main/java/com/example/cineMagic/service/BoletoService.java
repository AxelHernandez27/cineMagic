package com.example.cineMagic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cineMagic.model.Boleto;
import com.example.cineMagic.repository.BoletoRepository;

@Service
public class BoletoService {
    @Autowired

    private BoletoRepository boletoRepository;

    public List<Boleto> getAllBoletos() {
        return boletoRepository.findAll();
    }

    public Boleto saveBoleto(Boleto boleto) {
        return boletoRepository.save(boleto);
    }

    public Boleto getBoletoById(Long id) {
        return boletoRepository.findById(id).orElse(null);
    }

    public Boleto updateBoleto(Long id, Boleto boleto) {
        Boleto existingBoleto = getBoletoById(id);
        if (existingBoleto != null) {
            existingBoleto.setFuncion(boleto.getFuncion());
            existingBoleto.setUsuario(boleto.getUsuario());
            existingBoleto.setAsiento(boleto.getAsiento());
            existingBoleto.setFechaCompra(boleto.getFechaCompra());
            existingBoleto.setPrecio(boleto.getPrecio());
            return boletoRepository.save(existingBoleto);
        }
        return null; 
    }

    public void deleteBoleto(Long id) {
        boletoRepository.deleteById(id);
    }
}
