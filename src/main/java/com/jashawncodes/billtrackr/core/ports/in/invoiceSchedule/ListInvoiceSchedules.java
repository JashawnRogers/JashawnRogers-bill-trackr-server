package com.jashawncodes.billtrackr.core.ports.in.invoiceSchedule;

import com.jashawncodes.billtrackr.core.useCases.PageResponse;
import com.jashawncodes.billtrackr.core.useCases.invoiceSchedule.listInvoiceSchedules.ListInvoiceScheduleQuery;
import com.jashawncodes.billtrackr.core.useCases.invoiceSchedule.listInvoiceSchedules.ListInvoiceScheduleReadModel;

public interface ListInvoiceSchedules {
    PageResponse<ListInvoiceScheduleReadModel> listInvoiceSchedules(ListInvoiceScheduleQuery query);
}
