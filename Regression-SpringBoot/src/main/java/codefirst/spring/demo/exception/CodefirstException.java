package codefirst.spring.demo.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class CodefirstException extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;
    private LocalDateTime localDateTime;

    public CodefirstException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.localDateTime = LocalDateTime.now();
    }
}
