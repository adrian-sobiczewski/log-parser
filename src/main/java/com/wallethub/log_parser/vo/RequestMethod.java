package com.wallethub.log_parser.vo;

public enum RequestMethod {

    GET;

    public static RequestMethod of(String value) {
        for (RequestMethod rm : values()) {
            if (value.contains(rm.name()))
                return rm;
        }
        throw new IllegalArgumentException("There is no Request Method for value: " + value);
    }
}
