package com.wallethub.log_parser.cl.validator;

import com.wallethub.log_parser.cl.CLArgument;
import com.wallethub.log_parser.cl.CLDuration;
import com.wallethub.log_parser.ex.CLValidationArgumentException;

public class CLValidatorDuration {

    public static void validate(String value) {
        if (!CLDuration.contains(value))
            throw new CLValidationArgumentException(CLArgument.DURATION, value, "Please specify one of values: hourly | daily");

    }

}
