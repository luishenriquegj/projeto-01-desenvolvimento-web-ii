package br.imd.ufrn.market.exceptions;

import org.springframework.http.HttpStatus;

public class EmptyException extends CustomException {
    private static final HttpStatus status = HttpStatus.NOT_FOUND;
    public EmptyException(String model) {
        super(model+" não encontrado", status);
    }
    public EmptyException() {

    }
}