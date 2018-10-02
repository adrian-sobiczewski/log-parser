package com.wallethub.log_parser.cl.validator;

import com.wallethub.log_parser.ex.CLValidationArgumentException;
import com.wallethub.log_parser.ex.CLValidationException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;

public class CLValidatorTest {

    private CLValidator validator;

    @Before
    public void setUp() throws Exception {
        validator = new CLValidator();
    }

    @Test(expected = CLValidationArgumentException.class)
    public void validate_allRequiredArgumentsAreProvidedWithValues_ArgumentExceptionThrown() {
        // given
        ApplicationArguments arguments = arguments(
                "--startDate=0",
                "--duration=0",
                "--threshold=0",
                "--accesslog=0"
        );

        // when
        validator.validate(arguments);

        // then
        // exception thrown due invalid argument '0'
    }

    @Test(expected = CLValidationException.class)
    public void validate_allRequiredArgumentsAreProvidedButWithoutValues_exceptionThrown() {
        // given
        ApplicationArguments arguments = arguments(
                "--startDate",
                "--duration",
                "--threshold"
        );

        // when
        validator.validate(arguments);

        // then
        // exception thrown
    }

    @Test(expected = CLValidationException.class)
    public void validate_oneArgumentMissing_exceptionThrown() {
        // given
        ApplicationArguments arguments = arguments(
                "--startDate",
                "--duration"
        );

        // when
        validator.validate(arguments);

        // then
        // exception thrown
    }

    @Test(expected = CLValidationException.class)
    public void validate_twoArgumentMissing_exceptionThrown() {
        // given
        ApplicationArguments arguments = arguments(
                "--startDate"
        );

        // when
        validator.validate(arguments);

        // then
        // exception thrown
    }

    @Test(expected = CLValidationException.class)
    public void validate_oneArgumentMisspelled_exceptionThrown() {
        // given
        ApplicationArguments arguments = arguments(
                "--MISSPELLED",
                "--duration",
                "--threshold"
        );

        // when
        validator.validate(arguments);

        // then
        // exception thrown
    }

    private ApplicationArguments arguments(String... args) {
        return new DefaultApplicationArguments(args);
    }

}