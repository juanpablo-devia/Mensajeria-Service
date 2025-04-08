package co.edu.uceva.mensajeriaservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MensajeResponse {
    private Long id;
    private Long idRemitente;
    private Long idDestinatario;
    private String asunto;
    private String cuerpo;
    private LocalDateTime fechaEnvio;
    private Boolean leido;
}