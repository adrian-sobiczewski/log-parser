package com.wallethub.log_parser.ex;

public class CLValidationException extends RuntimeException {

    private final String message;

    public CLValidationException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
