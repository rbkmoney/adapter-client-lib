package com.rbkmoney.damsel;


import com.rbkmoney.damsel.base.Timer;

public class BaseWrapper {

    public static Timer makeTimerTimeout(Integer timeout) {
        return Timer.timeout(timeout);
    }

}
