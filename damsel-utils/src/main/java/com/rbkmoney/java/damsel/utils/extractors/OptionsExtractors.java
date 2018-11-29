package com.rbkmoney.java.damsel.utils.extractors;

import java.util.Map;

import static java.lang.Integer.parseInt;

public class OptionsExtractors {

    public static final String TIMER_TIMEOUT = "timer_timeout";
    public static final String TIMER_ADD_TIME = "timer_add_time";

    public static Integer extractAddTime(Map<String, String> options, int timerAddTime) {
        return parseInt(options.getOrDefault(TIMER_ADD_TIME, String.valueOf(timerAddTime)));
    }

    public static Integer extractTimeout(Map<String, String> options, int timerTimeout) {
        return parseInt(options.getOrDefault(TIMER_TIMEOUT, String.valueOf(timerTimeout)));
    }

}
