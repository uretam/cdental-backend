package com.cdental.tratamientos.service;

import com.cdental.tratamientos.model.Tratamiento;
import com.cdental.tratamientos.repository.TratamientoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TratamientoService {

    @Autowired
    private TratamientoRepository tratamientoRepository;

    public List<Tratamiento> listarTodos() {
        return tratamientoRepository.findAll();
    }

    public Tratamiento buscarPorId(Long id) {
        return tratamientoRepository.findById(id).orElse(null);
    }

    public Tratamiento guardar(Tratamiento tratamiento) {
        tratamiento.setActivo(true);
        return tratamientoRepository.save(tratamiento);
    }

    public Tratamiento actualizar(Long id, Tratamiento datosNuevos) {
        Tratamiento tratamientoExistente = tratamientoRepository.findById(id).orElse(null);
        
        if (tratamientoExistente != null) {
            tratamientoExistente.setNombre(datosNuevos.getNombre());
            tratamientoExistente.setDescripcion(datosNuevos.getDescripcion());
            tratamientoExistente.setPrecio(datosNuevos.getPrecio());
            tratamientoExistente.setActivo(datosNuevos.getActivo());

            return tratamientoRepository.save(tratamientoExistente);
        }
        return null;
    }

    public void eliminar(Long id) {
        tratamientoRepository.deleteById(id);
    }
}
