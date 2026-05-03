package com.jashawncodes.billtrackr.core.ports.in;

import com.jashawncodes.billtrackr.core.usecases.expectedInvoice.GenerateExpectedInvoicesForMonthUseCaseResult;

import java.time.LocalDate;
import java.time.YearMonth;

public interface GenerateExpectedInvoicesForMonthUseCase {
    GenerateExpectedInvoicesForMonthUseCaseResult generateExpectedInvoiceForMonthUseCase(YearMonth yearMonth);
}
