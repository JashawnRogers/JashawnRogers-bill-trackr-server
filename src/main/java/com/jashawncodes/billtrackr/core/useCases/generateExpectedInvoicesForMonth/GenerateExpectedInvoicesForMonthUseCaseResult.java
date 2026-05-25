package com.jashawncodes.billtrackr.core.useCases.generateExpectedInvoicesForMonth;

import java.time.YearMonth;

public record GenerateExpectedInvoicesForMonthUseCaseResult(
        YearMonth yearMonth,
        int createdCount,
        int alreadyExistedCount

) {
    public static GenerateExpectedInvoicesForMonthUseCaseResult of(
            YearMonth yearMonth,
            int createdCount,
            int alreadyExistedCount
    ) {
        return new GenerateExpectedInvoicesForMonthUseCaseResult(
                yearMonth,
                createdCount,
                alreadyExistedCount
        );
    }
}
