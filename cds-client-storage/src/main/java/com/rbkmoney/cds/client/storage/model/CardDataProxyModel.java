package com.rbkmoney.cds.client.storage.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardDataProxyModel {

    public String pan;
    public String cardholderName;

    public byte expMonth;
    public short expYear;

}
