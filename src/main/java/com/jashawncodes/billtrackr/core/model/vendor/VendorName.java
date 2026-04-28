package com.jashawncodes.billtrackr.core.model.vendor;

import com.jashawncodes.billtrackr.core.model.DomainValidator;

public record VendorName(String name) {
    public VendorName {
        name = DomainValidator.normalizeText(name);
    }

    public static VendorName of(String name) {
        return new VendorName(name);
    }
}
