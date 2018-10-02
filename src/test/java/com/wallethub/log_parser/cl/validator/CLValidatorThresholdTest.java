package com.wallethub.log_parser.cl.validator;

import com.wallethub.log_parser.ex.CLValidationArgumentException;
import org.junit.Test;

public class CLValidatorThresholdTest {

    @Test
    public void validate_number_noExceptionThrow() {
        // given
        String value = "30";

        // when
        CLValidatorThreshold.validate(value);

        // then
        // no exception thrown
    }

    @Test(expected = CLValidationArgumentException.class)
    public void validate_NotANumber_exceptionThrown() {
        // given
        String value = "3x";

        // when
        CLValidatorThreshold.validate(value);

        // then
        // exception thrown
    }

}