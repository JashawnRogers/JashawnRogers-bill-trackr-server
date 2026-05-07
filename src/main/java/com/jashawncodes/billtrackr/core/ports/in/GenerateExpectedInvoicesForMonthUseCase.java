package com.jashawncodes.billtrackr.core.ports.in;

import com.jashawncodes.billtrackr.core.useCases.generateExpectedInvoicesForMonth.GenerateExpectedInvoicesForMonthUseCaseResult;

import java.time.YearMonth;
import java.util.List;

public interface GenerateExpectedInvoicesForMonthUseCase {
    List<GenerateExpectedInvoicesForMonthUseCaseResult> generateExpectedInvoiceForMonthUseCase(YearMonth yearMonth);
}
