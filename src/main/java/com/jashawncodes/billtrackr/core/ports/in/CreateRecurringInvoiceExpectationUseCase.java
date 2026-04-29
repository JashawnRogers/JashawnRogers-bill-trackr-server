package com.jashawncodes.billtrackr.core.ports.in;

import com.jashawncodes.billtrackr.core.model.recurringinvoiceexpectation.RecurrenceRule;
import com.jashawncodes.billtrackr.core.model.recurringinvoiceexpectation.TrackedInvoiceKey;
import com.jashawncodes.billtrackr.core.usecases.recurringInvoiceExpectation.CreateRecurringInvoiceExpectationResult;

import java.util.UUID;

public interface CreateRecurringInvoiceExpectationUseCase {
    CreateRecurringInvoiceExpectationResult createNewRecurringInvoiceExpectation(
            UUID vendorId,
            TrackedInvoiceKey trackedInvoiceKey,
            RecurrenceRule recurrenceRule
    );
}
