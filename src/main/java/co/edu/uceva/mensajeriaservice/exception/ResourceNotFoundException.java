package co.edu.uceva.mensajeriaservice.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException (String mensaje) {
        super (mensaje);
    }
}
