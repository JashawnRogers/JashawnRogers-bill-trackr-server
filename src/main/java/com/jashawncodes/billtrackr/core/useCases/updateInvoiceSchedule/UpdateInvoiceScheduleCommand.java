package com.jashawncodes.billtrackr.core.useCases.updateInvoiceSchedule;

import com.jashawncodes.billtrackr.core.model.invoiceSchedule.RecurrenceRule;
import com.jashawncodes.billtrackr.core.model.invoiceSchedule.TrackedInvoiceKey;

import java.util.Optional;
import java.util.UUID;

public record UpdateInvoiceScheduleCommand(
        UUID invoiceScheduleId,
        Optional<TrackedInvoiceKey> trackedInvoiceKey,
        Optional<RecurrenceRule> recurrenceRule,
        Optional<Boolean> active

) {}
