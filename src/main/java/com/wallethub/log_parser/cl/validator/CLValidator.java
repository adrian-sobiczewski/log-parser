package com.wallethub.log_parser.cl.validator;

import com.wallethub.log_parser.cl.CLArgument;
import com.wallethub.log_parser.ex.CLValidationException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CLValidator {

    /**
     * ASSUMPTION: All arguments have only one value
     */
    private static final int ARGUMENT_VALUE_INDEX = 0;
    private static final String ERROR_ARGUMENT_MISSING_OR_MISSPELLED = "Given arguments missing or misspelled: %s\nPlease provide all required arguments:%s";
    private static final String ERROR_VALUE_MISSING_OR_MISSPELLED = "Please specify values for given arguments. ex. --threshold=200";

    public void validate(ApplicationArguments arguments) {
        if (!requiredArgumentsProvided(arguments))
            throw new CLValidationException(String.format(ERROR_ARGUMENT_MISSING_OR_MISSPELLED, arguments.getOptionNames(), CLArgument.strs()));
        if (!argumentsHasValues(arguments))
            throw new CLValidationException(ERROR_VALUE_MISSING_OR_MISSPELLED);
        validateArgumentsValues(arguments);
    }

    private boolean argumentsHasValues(ApplicationArguments arguments) {
        return arguments.getOptionNames().stream()
                .noneMatch(name -> arguments.getOptionValues(name).isEmpty());
    }

    private boolean requiredArgumentsProvided(ApplicationArguments arguments) {
        return Arrays.stream(CLArgument.values())
                .filter(CLArgument::required)
                .map(CLArgument::str)
                .allMatch(arguments::containsOption);
    }

    private void validateArgumentsValues(ApplicationArguments arguments) {
        Arrays.stream(CLArgument.values())
                .filter(CLArgument::required)
                .forEach(arg -> arg.validate(arguments.getOptionValues(arg.str()).get(ARGUMENT_VALUE_INDEX)));
    }

}
