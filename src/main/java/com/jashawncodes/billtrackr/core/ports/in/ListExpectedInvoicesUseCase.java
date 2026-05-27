package com.jashawncodes.billtrackr.core.ports.in;

import com.jashawncodes.billtrackr.core.useCases.PageRequest;
import com.jashawncodes.billtrackr.core.useCases.PageResponse;
import com.jashawncodes.billtrackr.core.useCases.expectedInvoice.ListExpectedInvoicesQuery;
import com.jashawncodes.billtrackr.core.useCases.expectedInvoice.listExpectedInvoices.ExpectedInvoiceReadModel;

public interface ListExpectedInvoicesUseCase {
    PageResponse<ExpectedInvoiceReadModel> listExpectedInvoices(ListExpectedInvoicesQuery query);
}
