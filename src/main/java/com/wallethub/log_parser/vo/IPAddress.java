package com.wallethub.log_parser.vo;

import com.google.common.base.MoreObjects;

/**
 * Value Object
 */
public class IPAddress {

    private final String value;

    public IPAddress(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("value", value)
                .toString();
    }
}
