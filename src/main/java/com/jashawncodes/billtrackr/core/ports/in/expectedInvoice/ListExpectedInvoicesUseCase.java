package com.jashawncodes.billtrackr.core.ports.in.expectedInvoice;

import com.jashawncodes.billtrackr.core.useCases.PageResponse;
import com.jashawncodes.billtrackr.core.useCases.expectedInvoice.ListExpectedInvoicesQuery;
import com.jashawncodes.billtrackr.core.useCases.expectedInvoice.listExpectedInvoices.ExpectedInvoiceReadModel;

public interface ListExpectedInvoicesUseCase {
    PageResponse<ExpectedInvoiceReadModel> listExpectedInvoices(ListExpectedInvoicesQuery query);
}
