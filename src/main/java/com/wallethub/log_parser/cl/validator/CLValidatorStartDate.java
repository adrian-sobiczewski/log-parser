package com.wallethub.log_parser.cl.validator;

import com.wallethub.log_parser.cl.CLArgument;
import com.wallethub.log_parser.ex.CLValidationArgumentException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static com.wallethub.log_parser.parameter.Parameter.DATE_FORMATTER;
import static com.wallethub.log_parser.parameter.Parameter.DATE_PATTERN;

public class CLValidatorStartDate {

    public static void validate(String value) {
        try {
            LocalDateTime.parse(value, DATE_FORMATTER);
        } catch (DateTimeParseException exception) {
            throw new CLValidationArgumentException(CLArgument.START_DATE, value, "Please specify date using format " + DATE_PATTERN);
        }

    }

}
