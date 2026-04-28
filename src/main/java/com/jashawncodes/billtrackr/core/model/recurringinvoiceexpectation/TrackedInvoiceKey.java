package com.jashawncodes.billtrackr.core.model.recurringinvoiceexpectation;

import com.jashawncodes.billtrackr.core.model.DomainValidator;

public record TrackedInvoiceKey(String trackedInvoiceKey) {
    public TrackedInvoiceKey {
        trackedInvoiceKey = DomainValidator.normalizeText(trackedInvoiceKey);
    }

    public static TrackedInvoiceKey of (String trackedInvoiceKey) {
        return new TrackedInvoiceKey(trackedInvoiceKey);
    }
}
