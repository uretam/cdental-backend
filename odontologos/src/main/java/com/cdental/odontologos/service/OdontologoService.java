package com.cdental.odontologos.service;

import com.cdental.odontologos.model.Odontologo;
import com.cdental.odontologos.repository.OdontologoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService {

    @Autowired
    private OdontologoRepository odontologoRepository;

    public List<Odontologo> listarTodos() {
        return odontologoRepository.findAll();
    }

    public Odontologo buscarPorId(Long id) {
        return odontologoRepository.findById(id).orElse(null);
    }

    public Odontologo guardar(Odontologo odontologo) {
        odontologo.setActivo(true);
        return odontologoRepository.save(odontologo);
    }

    public Odontologo actualizar(Long id, Odontologo odontologo) {
        Odontologo odontologoExistente = odontologoRepository.findById(id).orElse(null);
        if (odontologoExistente != null) {
            odontologoExistente.setMatricula(odontologo.getMatricula());
            odontologoExistente.setNombres(odontologo.getNombres());
            odontologoExistente.setApellidos(odontologo.getApellidos());
            odontologoExistente.setEspecialidad(odontologo.getEspecialidad());
            odontologoExistente.setTelefono(odontologo.getTelefono());
            odontologoExistente.setActivo(odontologo.getActivo());

            return odontologoRepository.save(odontologoExistente);
        }
        return null;
    }

    public void eliminar(Long id) {
        odontologoRepository.deleteById(id);
    }
}
