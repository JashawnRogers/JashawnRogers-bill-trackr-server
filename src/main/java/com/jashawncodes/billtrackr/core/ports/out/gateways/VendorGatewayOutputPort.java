package com.jashawncodes.billtrackr.core.ports.out.gateways;

import com.jashawncodes.billtrackr.core.model.vendor.Vendor;
import com.jashawncodes.billtrackr.core.useCases.PageResponse;
import com.jashawncodes.billtrackr.core.useCases.vendor.listVendors.ListVendorsQuery;
import com.jashawncodes.billtrackr.core.useCases.vendor.listVendors.ListVendorsReadModel;

import java.util.Optional;
import java.util.UUID;

public interface VendorGatewayOutputPort {
    boolean existsByVendorName(String vendorName);

    Vendor save(Vendor vendor);

    Optional<Vendor> findById(UUID vendorId);

    boolean existsByTrackedInvoiceKeyAndVendorId(String trackedInvoiceKey, UUID vendorId);

    boolean existsByTrackedInvoiceKeyAndVendorIdAndNot(String trackedInvoiceKey, UUID vendorId, UUID invoiceScheduleId);

    boolean existsByVendorNameAndNot(String vendorName, UUID vendorId);

    PageResponse<ListVendorsReadModel> findAllPaginated(ListVendorsQuery query);
}
