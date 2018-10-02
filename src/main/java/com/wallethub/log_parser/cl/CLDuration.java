package com.wallethub.log_parser.cl;

import java.time.Duration;
import java.util.Arrays;
import java.util.Objects;

public enum CLDuration {

    HOURLY("hourly", Duration.ofHours(1)),
    DAILY("daily", Duration.ofDays(1));

    private String str;
    private java.time.Duration duration;

    CLDuration(String str, Duration duration) {
        this.str = str;
        this.duration = duration;
    }

    public static boolean contains(String value) {
        return Arrays.stream(values())
                .map(CLDuration::str)
                .anyMatch(str -> str.equals(value));
    }

    public String str() {
        return str;
    }

    public Duration duration() {
        return duration;
    }

    public static CLDuration of(String str) {
        for (CLDuration d : values()) {
            if (Objects.equals(d.str, str)) return d;
        }
        throw new IllegalArgumentException("There is no CLDuration for string value: " + str);
    }
}
