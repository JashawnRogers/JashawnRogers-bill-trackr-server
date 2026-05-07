package com.jashawncodes.billtrackr.core.ports.in;

import com.jashawncodes.billtrackr.core.model.invoiceSchedule.RecurrenceRule;
import com.jashawncodes.billtrackr.core.model.invoiceSchedule.TrackedInvoiceKey;
import com.jashawncodes.billtrackr.core.model.vendor.PaymentTerms;
import com.jashawncodes.billtrackr.core.usecases.invoiceSchedule.CreateInvoiceScheduleResult;

import java.util.UUID;

public interface CreateInvoiceScheduleUseCase {
    CreateInvoiceScheduleResult createNewInvoiceSchedule(
            UUID vendorId,
            TrackedInvoiceKey trackedInvoiceKey,
            RecurrenceRule recurrenceRule,
            PaymentTerms paymentTerms
    );
}
