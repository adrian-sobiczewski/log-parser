package com.wallethub.log_parser.cl.validator;

import com.wallethub.log_parser.ex.CLValidationArgumentException;
import org.junit.Test;

public class CLValidatorCLDurationTest {

    @Test
    public void validate_hourly_noExceptionThrown() {
        // given
        String value = "hourly";

        // when
        CLValidatorDuration.validate(value);

        // then
        // no exception thrown
    }

    @Test
    public void validate_daily_noExceptionThrown() {
        // given
        String value = "daily";

        // when
        CLValidatorDuration.validate(value);

        // then
        // no exception thrown
    }

    @Test(expected = CLValidationArgumentException.class)
    public void validate_misspelledOption_exceptionThrown() {
        // given
        String value = "dily";

        // when
        CLValidatorDuration.validate(value);

        // then
        // exception thrown
    }
}