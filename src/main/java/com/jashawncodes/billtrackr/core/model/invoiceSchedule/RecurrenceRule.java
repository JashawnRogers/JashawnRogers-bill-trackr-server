package com.jashawncodes.billtrackr.core.model.invoiceSchedule;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static com.jashawncodes.billtrackr.core.model.DomainValidator.notNull;


/**
 * <p>
 *  Recurrence rule defines when the invoice should be generated based on its recurrenceType.
 *  - For example: Monthly = Jan 31 -> Feb 28 -> Mar 28... Each date represents when an invoice would be generated given
 *    an anchorDate that is at the last day of a month
 * </p>
 *
 * @param recurrenceType please see the recurrenceType Enum
 * @param anchorDate the date at which recurrence should begin from
 */
public record RecurrenceRule(RecurrenceType recurrenceType, LocalDate anchorDate) {
    public RecurrenceRule {
        notNull(anchorDate);
        notNull(recurrenceType);
    }

    /**
     * <p>
     *     Returns empty list if anchorDate is after end of month. Otherwise, fast-forward current date to the first
     *     date that appears in the given month
     * </p>
     * @param yearMonth the timeframe in which the function should search for invoices
     * @return List of dates a generated invoice would be generated based on its recurrenceType and anchorDates
     */
    public List<LocalDate> expectedReceiveDatesBasedOnDatesWithin(YearMonth yearMonth) {
//      Initialize list of expected receive dates to be later populated and returned
        List<LocalDate> expectedReceiveDates = new ArrayList<>();

        LocalDate startOfMonth = yearMonth.atDay(1);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();

        if (anchorDate.isAfter(endOfMonth)) {
            return List.of();
        }

        LocalDate current = anchorDate;

//      Fast-forward current date to the first date that appears in the given month
        while (current.isBefore(startOfMonth)) {
            current = nextExpectedReceiveDateAfter(current);
        }

        while (!current.isAfter(endOfMonth)) {
            expectedReceiveDates.add(current);
            current = nextExpectedReceiveDateAfter(current);
        }

        return expectedReceiveDates;
    }

    /**
     * <p>
     *     Finds next expectedReceivedDate based on given LocalDate parameter
     * </p>
     * @param date Date in which the method should start from
     * @return LocalDate based on recurrenceType of InvoiceSchedule
     */
    private LocalDate nextExpectedReceiveDateAfter(LocalDate date) {
        return switch (recurrenceType) {
            case WEEKLY, BI_WEEKLY -> date.plusWeeks(recurrenceType().convertedToInt);
            case MONTHLY, QUARTERLY -> date.plusMonths(recurrenceType().convertedToInt);
            case ANNUALLY -> date.plusYears(recurrenceType.convertedToInt);
        };
    }
}
