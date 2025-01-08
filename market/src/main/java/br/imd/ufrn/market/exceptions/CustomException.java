package br.imd.ufrn.market.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
public class CustomException extends Exception {
    private String message;
    private HttpStatus status;
    public CustomException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }

    public CustomException() {

    }
}