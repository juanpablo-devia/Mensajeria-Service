package co.edu.uceva.mensajeriaservice.controller;

import co.edu.uceva.mensajeriaservice.model.Mensaje;
import co.edu.uceva.mensajeriaservice.service.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mensajes")
public class MensajeController {

    @Autowired
    private MensajeService service;
    @Autowired
    private MensajeService mensajeService;

    @PostMapping
    public Mensaje enviar(@RequestBody Mensaje mensaje) {
        return service.enviarMensaje(mensaje);
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<Mensaje> mensajesPorUsuario(@PathVariable Long idUsuario) {
        return service.getMensajesPorUsuario(idUsuario);
    }

    @PutMapping("/{id}/leido")
    public ResponseEntity<Mensaje> marcarComoLeido(@PathVariable ("id") long id){
        Mensaje mensajeActualizado = mensajeService.marcarComoLeido(id);
        return ResponseEntity.ok(mensajeActualizado);
    }

    @DeleteMapping("/{id}")
    public void eliminarMensaje(@PathVariable ("id") long id) {
        mensajeService.eliminarMensaje(id);
    }
}
