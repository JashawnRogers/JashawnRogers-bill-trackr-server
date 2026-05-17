package com.jashawncodes.billtrackr.core.ports.in;

import com.jashawncodes.billtrackr.core.useCases.updateInvoiceSchedule.UpdateInvoiceScheduleResult;

import java.util.UUID;

public interface UpdateInvoiceScheduleUseCase {
    UpdateInvoiceScheduleResult updateInvoiceSchedule(UUID invoiceScheduleId);
}
