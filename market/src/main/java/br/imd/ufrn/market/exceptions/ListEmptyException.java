package br.imd.ufrn.market.exceptions;

import org.springframework.http.HttpStatus;

public class ListEmptyException extends CustomException {
    private static final HttpStatus status = HttpStatus.NOT_FOUND;
    public ListEmptyException(String model) {
        super("Nenhum(a) "+ model +" foi encontrado(a)", status);
    }
    public ListEmptyException() {

    }
}