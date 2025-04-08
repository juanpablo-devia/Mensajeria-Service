package co.edu.uceva.mensajeriaservice.service;

import co.edu.uceva.mensajeriaservice.model.Mensaje;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MensajeService {
    Mensaje crearMensaje(Mensaje mensaje);
    Mensaje obtenerMensajePorId(Long id);
    List<Mensaje> obtenerTodosLosMensajes();
    Page<Mensaje> obtenerMensajesPaginados(Pageable pageable);
    Mensaje actualizarMensaje(Long id, Mensaje mensaje);
    void eliminarMensaje(Long id);
    Mensaje marcarComoLeido(Long id);
}