package co.edu.uceva.mensajeriaservice.service;

import co.edu.uceva.mensajeriaservice.exception.ResourceNotFoundException;
import co.edu.uceva.mensajeriaservice.model.Mensaje;
import co.edu.uceva.mensajeriaservice.repository.MensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MensajeServiceImpl implements MensajeService {

    @Autowired
    private MensajeRepository repo;

    @Override
    public Mensaje enviarMensaje(Mensaje mensaje) {
        mensaje.setLeido(false);
        mensaje.setFechaEnvio(LocalDateTime.now());
        return repo.save(mensaje);
    }

    @Override
    public List<Mensaje> getMensajesPorUsuario(Long idUsuario) {
        return repo.findAll().stream()
                .filter(m -> m.getIdDestinatario().equals(idUsuario))
                .collect(Collectors.toList());
    }

    @Override
    public Mensaje marcarComoLeido(Long id) {
        Mensaje mensaje = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mensaje con ID " + id + " no encontrado"));
        mensaje.setLeido(true);
        return repo.save(mensaje);
    }

    @Override
    public void eliminarMensaje(Long id) {
        repo.deleteById(id);
    }
}
