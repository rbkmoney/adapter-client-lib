package com.rbkmoney.java.damsel.utils.creators;


import com.rbkmoney.damsel.cds.*;
import com.rbkmoney.damsel.domain.BankCard;

public class CdsPackageCreators {

    public static final String CARDHOLDER_NAME_NONAME = "NONAME";
    public static final String CARDHOLDER_NAME_UNKNOWN = "UNKNOWN";


    public static ExpDate createExpDate(byte month, short year) {
        return new ExpDate(month, year);
    }

    public static ExpDate createExpDate(String month, String year) {
        return createExpDate(Byte.parseByte(month), Short.parseShort(year));
    }

    public static CardData createCardData(String cardholderName, String cvv, String pan, ExpDate expDate) {
        return new CardData(pan, expDate).setCardholderName(cardholderName).setCvv(cvv);
    }

    public static CardData createCardDataWithExpDate(String cardholderName, String cvv, String pan, byte month, short year) {
        return createCardData(cardholderName, cvv, pan, createExpDate(month, year));
    }

    public static CardData createCardDataWithExpDate(String cardholderName, String cvv, String pan, String month, String year) {
        return createCardData(cardholderName, cvv, pan, createExpDate(month, year));
    }

    public static SessionData createSessionData(AuthData authData) {
        return new SessionData(authData);
    }

    public static AuthData createAuthData(CardSecurityCode cardSecurityCode) {
        return AuthData.card_security_code(cardSecurityCode);
    }

    public static AuthData createAuthDataWithAuth3DS(Auth3DS auth3DS) {
        return AuthData.auth_3ds(auth3DS);
    }


    public static Auth3DS createAuth3DS(String cryptogram, String eci) {
        return new Auth3DS(cryptogram).setEci(eci);
    }

    public static AuthData createAuthDataWithCryptogramAndEci(String cryptogram, String eci) {
        return AuthData.auth_3ds(createAuth3DS(cryptogram, eci));
    }

    public static AuthData createAuthDataWithCardSecurityCode(String cvv) {
        return AuthData.card_security_code(new CardSecurityCode(cvv));
    }

    public static Auth3DS createAuth3DS(String cryptogram) {
        return createAuth3DS(cryptogram, null);
    }

    public static PutCardDataResult createPutCardDataResult(BankCard bankCard, String session) {
        return new PutCardDataResult(bankCard, session);
    }

    public static UnlockStatus createUnlockStatusUnlocked() {
        return UnlockStatus.unlocked(new Unlocked());
    }

    public static UnlockStatus createUnlockStatusMoreKeysNeeded(short value) {
        return UnlockStatus.more_keys_needed(value);
    }
}
