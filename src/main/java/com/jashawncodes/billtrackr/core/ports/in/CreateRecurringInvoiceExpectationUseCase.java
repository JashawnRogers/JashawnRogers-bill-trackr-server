package com.jashawncodes.billtrackr.core.ports.in;

import com.jashawncodes.billtrackr.core.model.recurringinvoiceexpectation.RecurrenceRule;
import com.jashawncodes.billtrackr.core.model.recurringinvoiceexpectation.RecurringInvoiceExpectation;
import com.jashawncodes.billtrackr.core.model.recurringinvoiceexpectation.TrackedInvoiceKey;

import java.util.UUID;

public interface CreateRecurringInvoiceExpectationUseCase {
    RecurringInvoiceExpectation createNewRecurringInvoiceExpectation(
            UUID vendorId,
            TrackedInvoiceKey trackedInvoiceKey,
            RecurrenceRule recurrenceRule
    );
}
