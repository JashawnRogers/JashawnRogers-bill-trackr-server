package com.jashawncodes.billtrackr.core.model.recurringinvoiceexpectation;

import static com.jashawncodes.billtrackr.core.model.DomainValidator.*;

import java.util.UUID;

public class RecurringInvoiceExpectation {
    private final UUID id;
    private UUID vendorId;
    private TrackedInvoiceKey trackedInvoiceKey;
    private RecurrenceRule recurrenceRule;
    private boolean active;


    public RecurringInvoiceExpectation(UUID id,
                                       UUID vendorId,
                                       TrackedInvoiceKey trackedInvoiceKey,
                                       RecurrenceRule recurrenceRule,
                                       boolean active
    ) {
        this.id = notNull(id);
        this.vendorId = notNull(vendorId);
        this.trackedInvoiceKey = notNull(trackedInvoiceKey);
        this.recurrenceRule = notNull(recurrenceRule);
        this.active = active;
    }

//    Static factory method to enforce domain rules
    public static RecurringInvoiceExpectation createNew(
            UUID id,
            UUID vendorId,
            TrackedInvoiceKey trackedInvoiceKey,
            RecurrenceRule recurrenceRule
    ) {
        return new RecurringInvoiceExpectation(
                notNull(id),
                notNull(vendorId),
                notNull(trackedInvoiceKey),
                notNull(recurrenceRule),
                true
        );
    }

    public void activate() {
        active = true;
    }

    public void deactivate() {
        active = false;
    }

    public boolean belongsToVendor(UUID vendorId) {
        return this.vendorId.equals(notNull(vendorId));
    }

    public void updateTrackedInvoiceKey(TrackedInvoiceKey newTrackedInvoiceKey) {
        this.trackedInvoiceKey = notNull(newTrackedInvoiceKey);
    }

    public void updateVendorId(UUID updatedVendorId) {
        this.vendorId = notNull(updatedVendorId);
    }

    public void updateRecurrenceRule(RecurrenceRule newRecurrenceRule) {
        this.recurrenceRule = notNull(newRecurrenceRule);
    }

    public UUID getId() {
        return id;
    }

    public UUID getVendorId() {
        return vendorId;
    }

    public TrackedInvoiceKey getTrackedInvoiceKey() {
        return trackedInvoiceKey;
    }

    public RecurrenceRule getRecurrenceRule() {
        return recurrenceRule;
    }

    public boolean isActive() {
        return active;
    }
}
