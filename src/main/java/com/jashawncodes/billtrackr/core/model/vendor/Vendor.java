package com.jashawncodes.billtrackr.core.model.vendor;


import java.util.UUID;

import static com.jashawncodes.billtrackr.core.model.DomainValidator.notNull;

public class Vendor {
    private final UUID id;
    private VendorName vendorName;
    private PaymentTerms paymentTerms;
    private boolean active;

    public Vendor(
            UUID id,
            VendorName vendorName,
            PaymentTerms paymentTerms,
            boolean active
    ) {
        this.id = notNull(id);
        this.vendorName = notNull(vendorName);
        this.paymentTerms = notNull(paymentTerms);
        this.active = active;
    }

    public static Vendor createNew(
            UUID id,
            VendorName vendorName,
            PaymentTerms paymentTerms
    ) {
        return new Vendor(
                id,
                vendorName,
                paymentTerms,
                true
        );
    }

    public void updateVendorName(VendorName updatedVendorName) {
        this.vendorName = notNull(updatedVendorName);
    }

    public void updatePaymentTerms(PaymentTerms updatedPaymentTerms) {
        this.paymentTerms = notNull(updatedPaymentTerms);
    }

    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    public UUID getId() {
        return id;
    }

    public VendorName getVendorName() {
        return vendorName;
    }

    public PaymentTerms getPaymentTerms() {
        return paymentTerms;
    }

    public boolean isActive() {
        return active;
    }
}
