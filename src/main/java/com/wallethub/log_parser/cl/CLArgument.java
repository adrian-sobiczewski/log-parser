package com.wallethub.log_parser.cl;

import com.wallethub.log_parser.cl.validator.CLValidatorAccessLog;
import com.wallethub.log_parser.cl.validator.CLValidatorDuration;
import com.wallethub.log_parser.cl.validator.CLValidatorStartDate;
import com.wallethub.log_parser.cl.validator.CLValidatorThreshold;
import org.springframework.boot.ApplicationArguments;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Stream;

public enum CLArgument {

    START_DATE("startDate") {
        @Override
        public void validate(String value) {
            CLValidatorStartDate.validate(value);
        }

        @Override
        public boolean required() {
            return true;
        }
    },

    DURATION("duration") {
        @Override
        public void validate(String value) {
            CLValidatorDuration.validate(value);
        }

        @Override
        public boolean required() {
            return true;
        }

    },

    THRESHOLD("threshold") {
        @Override
        public void validate(String value) {
            CLValidatorThreshold.validate(value);
        }

        @Override
        public boolean required() {
            return true;
        }
    },

    ACCESS_LOG("accesslog") {
        @Override
        public void validate(String value) {
            CLValidatorAccessLog.validate(value);
        }

        @Override
        public boolean required() {
            return false;
        }
    };


    private String str;


    CLArgument(String str) {
        this.str = str;
    }

    public static String strs() {
        StringJoiner joiner = new StringJoiner(",");
        Stream.of(values())
                .map(CLArgument::str)
                .forEach(joiner::add);
        return joiner.toString();

    }

    public abstract void validate(String value);

    public abstract boolean required();

    public String extract(ApplicationArguments arguments) {
        List<String> values = arguments.getOptionValues(str);
        return values == null ? null : values.get(0); // ASSUMPTION: Argument has only one value
    }

    public String str() {
        return str;
    }

    public static CLArgument of(String str) {
        for (CLArgument p : values()) {
            if (Objects.equals(p.str, str)) return p;
        }
        throw new IllegalArgumentException("There is no CLArgument for string value: " + str);
    }

}
