package com.wallethub.log_parser.cl.validator;

import com.wallethub.log_parser.cl.CLArgument;
import com.wallethub.log_parser.ex.CLValidationArgumentException;

public class CLValidatorThreshold {

    public static void validate(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new CLValidationArgumentException(CLArgument.THRESHOLD, value, "Please specify integer number");
        }
    }
}
