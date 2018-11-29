package com.rbkmoney.java.damsel.converter;

import java.math.BigDecimal;

public class AmountConverter {

    public static String getFormattedAmount(long amount) {
        return new BigDecimal(amount).movePointLeft(2).toString();
    }

}
