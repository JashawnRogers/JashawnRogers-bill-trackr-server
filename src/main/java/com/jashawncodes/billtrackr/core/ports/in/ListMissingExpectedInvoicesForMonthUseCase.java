package com.jashawncodes.billtrackr.core.ports.in;

import com.jashawncodes.billtrackr.core.useCases.listExpectedMissingInvoicesForMonth.MissingExpectedInvoiceReadModel;

import java.time.YearMonth;
import java.util.List;

public interface ListMissingExpectedInvoicesForMonthUseCase {
    List<MissingExpectedInvoiceReadModel> listMissingExpectedInvoicesForMonth(YearMonth yearMonth);
}
