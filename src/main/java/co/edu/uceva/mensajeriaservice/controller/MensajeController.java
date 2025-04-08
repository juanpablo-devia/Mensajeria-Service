package co.edu.uceva.mensajeriaservice.controller;

import co.edu.uceva.mensajeriaservice.dto.*;
import co.edu.uceva.mensajeriaservice.model.Mensaje;
import co.edu.uceva.mensajeriaservice.service.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/mensajeria-service")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    @PostMapping("/mensajes")
    public ResponseEntity<MensajeResponse> crearMensaje(@RequestBody MensajeRequest request) {
        Mensaje nuevo = new Mensaje();
        nuevo.setIdRemitente(request.getIdRemitente());
        nuevo.setIdDestinatario(request.getIdDestinatario());
        nuevo.setAsunto(request.getAsunto());
        nuevo.setCuerpo(request.getCuerpo());

        Mensaje guardado = mensajeService.crearMensaje(nuevo);
        return ResponseEntity.ok(toResponse(guardado));
    }

    @GetMapping("/mensajes")
    public List<MensajeResponse> obtenerTodos() {
        return mensajeService.obtenerTodosLosMensajes().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/mensajes/{id}")
    public ResponseEntity<MensajeResponse> obtenerPorId(@PathVariable Long id) {
        Mensaje m = mensajeService.obtenerMensajePorId(id);
        return ResponseEntity.ok(toResponse(m));
    }

    @GetMapping("/mensajes/page/{pagina}")
    public ResponseEntity<MensajePageResponse> obtenerPaginado(@PathVariable int pagina) {
        Pageable pageable = PageRequest.of(pagina, 10);
        var page = mensajeService.obtenerMensajesPaginados(pageable);

        List<MensajeResponse> contenido = page.getContent().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new MensajePageResponse(
                contenido,
                page.getTotalPages(),
                page.getTotalElements(),
                page.getNumber()
        ));
    }

    @PutMapping("/mensajes/{id}")
    public ResponseEntity<MensajeResponse> actualizar(@PathVariable Long id, @RequestBody MensajeRequest request) {
        Mensaje m = new Mensaje();
        m.setIdRemitente(request.getIdRemitente());
        m.setIdDestinatario(request.getIdDestinatario());
        m.setAsunto(request.getAsunto());
        m.setCuerpo(request.getCuerpo());

        Mensaje actualizado = mensajeService.actualizarMensaje(id, m);
        return ResponseEntity.ok(toResponse(actualizado));
    }

    @DeleteMapping("/mensajes/{id}")
    public ResponseEntity<DeleteResponse> eliminar(@PathVariable Long id) {
        mensajeService.eliminarMensaje(id);
        return ResponseEntity.ok(new DeleteResponse("Mensaje con ID " + id + " eliminado correctamente"));
    }

    @PutMapping("/mensajes/{id}/leido")
    public ResponseEntity<MensajeResponse> marcarLeido(@PathVariable Long id) {
        Mensaje actualizado = mensajeService.marcarComoLeido(id);
        return ResponseEntity.ok(toResponse(actualizado));
    }

    private MensajeResponse toResponse(Mensaje m) {
        return new MensajeResponse(
                m.getId(),
                m.getIdRemitente(),
                m.getIdDestinatario(),
                m.getAsunto(),
                m.getCuerpo(),
                m.getFechaEnvio(),
                m.getLeido()
        );
    }
}