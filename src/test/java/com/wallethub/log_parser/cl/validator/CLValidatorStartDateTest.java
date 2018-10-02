package com.wallethub.log_parser.cl.validator;

import com.wallethub.log_parser.ex.CLValidationArgumentException;
import org.junit.Test;

public class CLValidatorStartDateTest {

    @Test
    public void validate_correctFormat_noExceptionThrown() {
        // given
        String date = "2018-09-28.09:30:30";

        // when
        CLValidatorStartDate.validate(date);

        // then
        // no exception thrown
    }

    @Test(expected = CLValidationArgumentException.class)
    public void validate_wrongFormat_exceptionThrown() {
        // given
        String date = "201812-09-28.09:30:30";

        // when
        CLValidatorStartDate.validate(date);

        // then
        // exception thrown
    }

}