package co.edu.uceva.mensajeriaservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "Mensajeria")
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long idRemitente;
    private Long idDestinatario;
    private String asunto;
    private String cuerpo;
    private LocalDateTime fechaEnvio;

    private Boolean leido;

    @PrePersist
    protected void onCreate() {
        fechaEnvio = LocalDateTime.now();
    }
}
