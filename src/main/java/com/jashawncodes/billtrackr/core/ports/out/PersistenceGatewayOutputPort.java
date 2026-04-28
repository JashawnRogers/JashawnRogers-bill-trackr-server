package com.jashawncodes.billtrackr.core.ports.out;

import com.jashawncodes.billtrackr.core.model.vendor.Vendor;

import java.util.Optional;
import java.util.UUID;

public interface PersistenceGatewayOutputPort {

    boolean existsByVendorName(String vendorName);

    Vendor save(Vendor vendor);

    Optional<Vendor> findByVendorId(UUID vendorId);
}
