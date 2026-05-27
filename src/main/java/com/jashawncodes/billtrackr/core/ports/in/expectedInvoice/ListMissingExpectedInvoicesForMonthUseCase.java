package com.jashawncodes.billtrackr.core.ports.in.expectedInvoice;

import com.jashawncodes.billtrackr.core.useCases.expectedInvoice.listExpectedMissingInvoicesForMonth.MissingExpectedInvoiceReadModel;

import java.time.YearMonth;
import java.util.List;

public interface ListMissingExpectedInvoicesForMonthUseCase {
    List<MissingExpectedInvoiceReadModel> listMissingExpectedInvoicesForMonth(YearMonth yearMonth);
}
