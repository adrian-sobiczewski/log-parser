package com.wallethub.log_parser.ex;

import com.wallethub.log_parser.cl.CLArgument;

public class CLValidationArgumentException extends RuntimeException {

    private final CLArgument param;
    private final String value;
    private final String message;

    public CLValidationArgumentException(CLArgument param, String value, String message) {
        this.param = param;
        this.value = value;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return String.format("ARG: --%s=%s -> %s", param.str(), value, message);
    }

}
