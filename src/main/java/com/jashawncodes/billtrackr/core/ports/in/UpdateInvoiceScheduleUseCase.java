package com.jashawncodes.billtrackr.core.ports.in;

import com.jashawncodes.billtrackr.core.useCases.invoiceSchedule.updateInvoiceSchedule.UpdateInvoiceScheduleCommand;
import com.jashawncodes.billtrackr.core.useCases.invoiceSchedule.updateInvoiceSchedule.UpdateInvoiceScheduleResult;

public interface UpdateInvoiceScheduleUseCase {
    UpdateInvoiceScheduleResult updateInvoiceSchedule(UpdateInvoiceScheduleCommand command);
}
