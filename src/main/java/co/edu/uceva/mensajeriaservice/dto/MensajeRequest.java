package co.edu.uceva.mensajeriaservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MensajeRequest {
    public Long idRemitente;
    public Long idDestinatario;
    public String asunto;
    public String cuerpo;
}