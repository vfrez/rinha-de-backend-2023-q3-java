package com.rinha.cadPessoa.exception;

public class DuplicatedEntryException extends RuntimeException {

    public DuplicatedEntryException(String errorMessage) {
        super(errorMessage);
    }
}
