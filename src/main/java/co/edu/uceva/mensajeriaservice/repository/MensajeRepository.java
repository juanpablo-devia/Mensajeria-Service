package co.edu.uceva.mensajeriaservice.repository;

import co.edu.uceva.mensajeriaservice.model.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
}
