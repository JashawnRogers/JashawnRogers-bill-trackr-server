package com.jashawncodes.billtrackr.core.ports.in;

import com.jashawncodes.billtrackr.core.model.invoiceSchedule.RecurrenceRule;
import com.jashawncodes.billtrackr.core.model.invoiceSchedule.TrackedInvoiceKey;
import com.jashawncodes.billtrackr.core.useCases.invoiceSchedule.createInvoiceSchedule.CreateInvoiceScheduleResult;

import java.util.UUID;

public interface CreateInvoiceScheduleUseCase {
    CreateInvoiceScheduleResult createNewInvoiceSchedule(
            UUID vendorId,
            TrackedInvoiceKey trackedInvoiceKey,
            RecurrenceRule recurrenceRule
    );
}
