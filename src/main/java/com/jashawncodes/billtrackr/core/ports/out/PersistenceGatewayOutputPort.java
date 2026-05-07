package com.jashawncodes.billtrackr.core.ports.out;

import com.jashawncodes.billtrackr.core.model.expectedInvoice.ExpectedInvoice;
import com.jashawncodes.billtrackr.core.model.invoiceSchedule.InvoiceSchedule;
import com.jashawncodes.billtrackr.core.model.vendor.Vendor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersistenceGatewayOutputPort {
//  Vendor
    boolean existsByVendorName(String vendorName);

    Vendor save(Vendor vendor);

    Optional<Vendor> findByVendorId(UUID vendorId);

    boolean existsByTrackedInvoiceKeyAndVendorId(String trackedInvoiceKey, UUID vendorId);


//  Recurring Invoice Expectations
    InvoiceSchedule save(InvoiceSchedule invoiceSchedule);

    List<InvoiceSchedule> findAllByIsActive();

//  Expected Invoices
    ExpectedInvoice save(ExpectedInvoice expectedInvoice);

    boolean existsByExpectedReceiveDateAndRecurringInvoiceExpectationId(LocalDate date, UUID recurringInvoiceExpectationId);

    Optional<ExpectedInvoice> findByExpectedReceiveDateAndRecurringInvoiceExpectationId(LocalDate date, UUID recurringInvoiceExpectationId);
}
