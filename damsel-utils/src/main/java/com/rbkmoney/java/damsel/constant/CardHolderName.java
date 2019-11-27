package com.rbkmoney.java.damsel.constant;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CardHolderName {

    NONAME("NONAME"),
    UNKNOWN("UNKNOWN");

    private final String value;

}
