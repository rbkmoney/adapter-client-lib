package com.rbkmoney.cds.client.storage.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardDataProxyModel {

    private String pan;
    private String cardholderName;

    private byte expMonth;
    private short expYear;

}
