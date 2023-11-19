package proyecto.daw_1.proyecto.daw_1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class ErrorMessage {
    private Integer statusCode;
    private Date dateError;
    private String message;
    private String description;
}
