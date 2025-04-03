package co.edu.uceva.mensajeriaservice.dto;

import java.time.LocalDateTime;

public class MensajeResponse {
    public Long id;
    public String asunto;
    public Boolean leido;
    public LocalDateTime fechaEnvio;
}
