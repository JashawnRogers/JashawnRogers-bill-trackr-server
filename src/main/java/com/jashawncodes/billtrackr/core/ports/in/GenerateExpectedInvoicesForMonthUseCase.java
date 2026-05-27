package com.jashawncodes.billtrackr.core.ports.in;

import com.jashawncodes.billtrackr.core.useCases.expectedInvoice.generateExpectedInvoicesForMonth.GenerateExpectedInvoicesForMonthUseCaseResult;

import java.time.YearMonth;

public interface GenerateExpectedInvoicesForMonthUseCase {
    GenerateExpectedInvoicesForMonthUseCaseResult generateExpectedInvoicesForMonthUseCase(YearMonth yearMonth);
}
