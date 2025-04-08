package co.edu.uceva.mensajeriaservice.service;

import co.edu.uceva.mensajeriaservice.exception.ResourceNotFoundException;
import co.edu.uceva.mensajeriaservice.model.Mensaje;
import co.edu.uceva.mensajeriaservice.repository.MensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MensajeServiceImpl implements MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    @Override
    public Mensaje crearMensaje(Mensaje mensaje) {
        mensaje.setLeido(false);
        return mensajeRepository.save(mensaje);
    }

    @Override
    public Mensaje obtenerMensajePorId(Long id) {
        return mensajeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mensaje con ID " + id + " no encontrado"));
    }

    @Override
    public List<Mensaje> obtenerTodosLosMensajes() {
        return mensajeRepository.findAll();
    }

    @Override
    public Page<Mensaje> obtenerMensajesPaginados(Pageable pageable) {
        return mensajeRepository.findAll(pageable);
    }

    @Override
    public Mensaje actualizarMensaje(Long id, Mensaje nuevo) {
        Mensaje actual = obtenerMensajePorId(id);

        actual.setIdRemitente(nuevo.getIdRemitente());
        actual.setIdDestinatario(nuevo.getIdDestinatario());
        actual.setAsunto(nuevo.getAsunto());
        actual.setCuerpo(nuevo.getCuerpo());

        return mensajeRepository.save(actual);
    }

    @Override
    public void eliminarMensaje(Long id) {
        obtenerMensajePorId(id); // lanza excepción si no existe
        mensajeRepository.deleteById(id);
    }

    @Override
    public Mensaje marcarComoLeido(Long id) {
        Mensaje mensaje = obtenerMensajePorId(id);
        mensaje.setLeido(true);
        return mensajeRepository.save(mensaje);
    }
}