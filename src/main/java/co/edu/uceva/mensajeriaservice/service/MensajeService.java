package co.edu.uceva.mensajeriaservice.service;

import co.edu.uceva.mensajeriaservice.model.Mensaje;

import java.util.List;

public interface MensajeService {
    Mensaje enviarMensaje(Mensaje mensaje);
    List<Mensaje> getMensajesPorUsuario(Long idUsuario);
    Mensaje marcarComoLeido(Long id);
    void eliminarMensaje(Long id);
}
