package co.edu.uceva.mensajeriaservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MensajePageResponse {
    private List<MensajeResponse> contenido;
    private int totalPaginas;
    private long totalElementos;
    private int paginaActual;

    public MensajePageResponse(List<MensajeResponse> contenido, int totalPaginas, long totalElementos, int paginaActual) {
        this.contenido = contenido;
        this.totalPaginas = totalPaginas;
        this.totalElementos = totalElementos;
        this.paginaActual = paginaActual;
    }
}