package com.jashawncodes.billtrackr.core.model.recurringinvoiceexpectation;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.jashawncodes.billtrackr.core.model.DomainValidator.notNull;

public record RecurrenceRule(RecurrenceType recurrenceType, LocalDate anchorDate) {
    public RecurrenceRule {
        notNull(anchorDate);
        notNull(recurrenceType);
    }

    public List<LocalDate> datesWithin(YearMonth yearMonth) {
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

    private LocalDate nextExpectedReceiveDateAfter(LocalDate date) {
        return switch (recurrenceType) {
            case WEEKLY, BI_WEEKLY -> date.plusWeeks(recurrenceType().convertedToInt);
            case MONTHLY, QUARTERLY -> date.plusMonths(recurrenceType().convertedToInt);
            case ANNUALLY -> date.plusYears(recurrenceType.convertedToInt);
        };
    }
}
