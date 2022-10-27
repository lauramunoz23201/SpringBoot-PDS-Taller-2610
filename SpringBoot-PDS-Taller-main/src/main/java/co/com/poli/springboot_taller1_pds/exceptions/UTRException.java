package co.com.poli.springboot_taller1_pds.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class UTRException extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;

    public UTRException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
