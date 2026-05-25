package com.jashawncodes.billtrackr.core.model.invoiceSchedule;

/**
 * Recurrence
 */
public enum RecurrenceType {
    WEEKLY(1),
    BI_WEEKLY(2),
    MONTHLY(1),
    QUARTERLY(3),
    ANNUALLY(1);

    final int convertedToInt;

    RecurrenceType(int convertedToInt) {
        this.convertedToInt = convertedToInt;
    }

};
