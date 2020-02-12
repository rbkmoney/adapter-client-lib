package com.rbkmoney.java.damsel.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Error {
    public static final String DEFAULT_ERROR_CODE = "error";
    public static final String SLEEP_TIMEOUT = "sleep timeout";
    public static final String UNKNOWN = "unknown";
}
