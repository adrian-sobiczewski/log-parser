package com.wallethub.log_parser.parameter;

import com.wallethub.log_parser.cl.CLDuration;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Immutable Object
 */
public class Parameter {

    public static final String DATE_PATTERN = "yyyy-MM-dd.HH:mm:ss";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    private LocalDateTime start;
    private Duration duration;
    private Integer threshold;

    private Parameter() {
        // prevent constructor instantiation
    }

    public static Parameter of(String startDate, String duration, String threshold) {
        Parameter p = new Parameter();
        p.start = LocalDateTime.parse(startDate, DATE_FORMATTER);
        p.duration = CLDuration.of(duration).duration();
        p.threshold = Integer.valueOf(threshold);
        return p;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public Duration getDuration() {
        return duration;
    }

    public Integer getThreshold() {
        return threshold;
    }

}
