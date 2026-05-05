package com.jashawncodes.billtrackr.core.ports.in;

import com.jashawncodes.billtrackr.core.usecases.expectedInvoice.GenerateExpectedInvoicesForMonthUseCaseResult;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public interface GenerateExpectedInvoicesForMonthUseCase {
    List<GenerateExpectedInvoicesForMonthUseCaseResult> generateExpectedInvoiceForMonthUseCase(YearMonth yearMonth);
}
