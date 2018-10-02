package com.wallethub.log_parser.cl.validator;

import com.wallethub.log_parser.cl.CLArgument;
import com.wallethub.log_parser.ex.CLValidationArgumentException;

public class CLValidatorAccessLog {

    public static void validate(String value) {
        if (value == null || value.isEmpty())
            throw new CLValidationArgumentException(CLArgument.ACCESS_LOG, value, "Please specify correct access log path");
    }
}
