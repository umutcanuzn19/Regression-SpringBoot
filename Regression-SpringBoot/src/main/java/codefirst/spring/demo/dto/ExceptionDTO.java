package codefirst.spring.demo.dto;

import lombok.Data;

@Data
public class ExceptionDTO {
    private String message;
    private String httpStatus;
    private String localDateTime;
}
