package com.rbkmoney.java.damsel.converter;

import com.rbkmoney.damsel.cds.ExpDate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Calendar;

/**
 * @deprecated because removed
 * use {@link BankCardExpDateConverter}.
 */
@Deprecated
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExpDateConverter {

    /**
     * Return date format should be YYММ
     */
    public String getYearMonth(ExpDate expDate) {
        return String.format("%1$02d%2$02d", expDate.getYear() % 100, expDate.getMonth());
    }

    /**
     * Return last 2 digits year (e.g. YY)
     */
    public static String getYear(ExpDate expDate) {
        return String.format("%1$02d", expDate.getYear() % 100);
    }

    /**
     * Return full date YYYYMMDD
     */
    public static String getFullDate(ExpDate expDate) {
        return String.format("%1$04d%2$02d%3$02d", expDate.getYear(), expDate.getMonth(), getDayOfMonth(expDate));
    }

    /**
     * Return full date MM
     */
    public static String getMonth(ExpDate expDate) {
        return String.format("%1$02d", expDate.getMonth());
    }

    /**
     * Return max days of month (DD)
     */
    public static Integer getDayOfMonth(ExpDate expDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(expDate.getYear(), expDate.getMonth(), -1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}
