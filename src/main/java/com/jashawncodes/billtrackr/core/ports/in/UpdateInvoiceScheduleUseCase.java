package com.jashawncodes.billtrackr.core.ports.in;

import com.jashawncodes.billtrackr.core.useCases.updateInvoiceSchedule.UpdateInvoiceScheduleCommand;
import com.jashawncodes.billtrackr.core.useCases.updateInvoiceSchedule.UpdateInvoiceScheduleResult;

public interface UpdateInvoiceScheduleUseCase {
    UpdateInvoiceScheduleResult updateInvoiceSchedule(UpdateInvoiceScheduleCommand command);
}
