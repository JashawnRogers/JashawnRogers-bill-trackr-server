package com.jashawncodes.billtrackr.core.model.invoiceSchedule;

import com.jashawncodes.billtrackr.core.model.vendor.PaymentTerms;

import static com.jashawncodes.billtrackr.core.model.DomainValidator.*;

import java.util.Optional;
import java.util.UUID;

public class InvoiceSchedule {
    private final UUID id;
    private UUID vendorId;
    private TrackedInvoiceKey trackedInvoiceKey;
    private RecurrenceRule recurrenceRule;
    private boolean active;
    private final PaymentTerms paymentTerms;


    public InvoiceSchedule(UUID id,
                           UUID vendorId,
                           TrackedInvoiceKey trackedInvoiceKey,
                           RecurrenceRule recurrenceRule,
                           boolean active,
                           PaymentTerms paymentTerms
    ) {
        this.id = notNull(id);
        this.vendorId = notNull(vendorId);
        this.trackedInvoiceKey = notNull(trackedInvoiceKey);
        this.recurrenceRule = notNull(recurrenceRule);
        this.active = active;
        this.paymentTerms = paymentTerms;
    }

//    Static factory method to enforce domain rules
    public static InvoiceSchedule createNew(
            UUID id,
            UUID vendorId,
            TrackedInvoiceKey trackedInvoiceKey,
            RecurrenceRule recurrenceRule,
            PaymentTerms paymentTerms
    ) {
        return new InvoiceSchedule(
                notNull(id),
                notNull(vendorId),
                notNull(trackedInvoiceKey),
                notNull(recurrenceRule),
                true,
                notNull(paymentTerms)
        );
    }

    public void update(Optional<TrackedInvoiceKey> trackedInvoiceKey,
                       Optional<RecurrenceRule> recurrenceRule,
                       Optional<Boolean> active) {
        trackedInvoiceKey.ifPresent(this::updateTrackedInvoiceKey);
        recurrenceRule.ifPresent(this::updateRecurrenceRule);
        active.ifPresent(value -> {
            if (value) {
                this.activate();
            } else {
                this.deactivate();
            }
        });
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

    public void updateTrackedInvoiceKey(TrackedInvoiceKey trackedInvoiceKey) {
        this.trackedInvoiceKey = notNull(trackedInvoiceKey);
    }

    public void updateVendorId(UUID updatedVendorId) {
        this.vendorId = notNull(updatedVendorId);
    }

    public void updateRecurrenceRule(RecurrenceRule recurrenceRule) {
        this.recurrenceRule = notNull(recurrenceRule);
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

    public PaymentTerms getPaymentTerms() {
        return paymentTerms;
    }
}
