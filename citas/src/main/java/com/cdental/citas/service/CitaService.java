package com.cdental.citas.service;

import com.cdental.citas.client.OdontologoClient;
import com.cdental.citas.client.PacienteClient;
import com.cdental.citas.dto.OdontologoDTO;
import com.cdental.citas.dto.PacienteDTO;
import com.cdental.citas.dto.RegistrarCitaDTO;
import com.cdental.citas.model.Cita;
import com.cdental.citas.model.DetalleCita;
import com.cdental.citas.repository.CitaRepository;
import com.cdental.citas.repository.DetalleCitaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private DetalleCitaRepository detalleCitaRepository;

    @Autowired
    private PacienteClient pacienteClient;

    @Autowired
    private OdontologoClient odontologoClient;

    @Transactional
    public Cita registrarCitaCompleta(RegistrarCitaDTO dto) {
        Cita cita = dto.getCita();
        List<DetalleCita> detalles = dto.getDetalles();

        PacienteDTO paciente = pacienteClient.obtenerPorId(cita.getIdPaciente());
        if (paciente == null) {
            throw new RuntimeException("Error: El paciente con ID " + cita.getIdPaciente() + " no existe.");
        }

        OdontologoDTO odontologo = odontologoClient.obtenerPorId(cita.getIdOdontologo());
        if (odontologo == null) {
            throw new RuntimeException("Error: El odontólogo con ID " + cita.getIdOdontologo() + " no existe.");
        }

        BigDecimal acumuladorTotal = BigDecimal.ZERO;

        for (DetalleCita det : detalles) {
            BigDecimal cantidadBD = new BigDecimal(det.getCantidad());
            BigDecimal subtotal = det.getPrecioUnitario().multiply(cantidadBD);
            det.setSubtotalLinea(subtotal);

            acumuladorTotal = acumuladorTotal.add(subtotal);
        }

        cita.setTotalGeneral(acumuladorTotal);
        cita.setEstado("PENDING");

        Cita citaGuardada = citaRepository.save(cita);

        for (DetalleCita det : detalles) {
            det.setIdCita(citaGuardada.getIdCita());
            detalleCitaRepository.save(det);
        }

        return citaGuardada;
    }

    public Cita buscarPorId(Long id) {
        return citaRepository.findById(id).orElse(null);
    }

    public Cita actualizarCita(Long id, Cita datosNuevos) {
        Cita citaExistente = citaRepository.findById(id).orElse(null);

        if (citaExistente != null) {
            citaExistente.setMotivo(datosNuevos.getMotivo());
            citaExistente.setEstado(datosNuevos.getEstado());
            citaExistente.setFechaHora(datosNuevos.getFechaHora());

            return citaRepository.save(citaExistente);
        }
        return null;
    }

    @Transactional
    public boolean eliminarCitaCompleta(Long id) {
        Cita cita = citaRepository.findById(id).orElse(null);

        if (cita != null) {
            List<DetalleCita> detalles = detalleCitaRepository.findByIdCita(id);
            for (DetalleCita det : detalles) {
                detalleCitaRepository.deleteById(det.getIdDetalle());
            }

            citaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Long contarCitasDePaciente(Long idPaciente) {
        return citaRepository.countByIdPaciente(idPaciente);
    }

    public List<Cita> buscarCitasPorEstado(String estado) {
        return citaRepository.findByEstado(estado);
    }
}
