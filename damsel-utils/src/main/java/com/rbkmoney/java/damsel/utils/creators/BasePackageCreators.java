package com.rbkmoney.java.damsel.utils.creators;


import com.rbkmoney.damsel.base.Timer;

public class BasePackageCreators {

    public static Timer createTimerTimeout(Integer timeout) {
        return Timer.timeout(timeout);
    }

}
