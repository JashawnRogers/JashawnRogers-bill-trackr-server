package com.jashawncodes.billtrackr.core.ports.out;

import com.jashawncodes.billtrackr.core.model.expectedInvoice.ExpectedInvoice;
import com.jashawncodes.billtrackr.core.model.expectedInvoice.InvoiceStatus;
import com.jashawncodes.billtrackr.core.model.invoiceSchedule.InvoiceSchedule;
import com.jashawncodes.billtrackr.core.model.vendor.Vendor;
import com.jashawncodes.billtrackr.core.useCases.listExpectedMissingInvoicesForMonth.MissingExpectedInvoiceReadModel;

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


//  Invoice Schedule
    InvoiceSchedule save(InvoiceSchedule invoiceSchedule);

    List<InvoiceSchedule> findAllByIsActive();

//  Expected Invoice
    ExpectedInvoice save(ExpectedInvoice expectedInvoice);

    Optional<ExpectedInvoice> findById(UUID id);

    Optional<ExpectedInvoice> findByExpectedReceiveDateAndRecurringInvoiceExpectationId(LocalDate date, UUID recurringInvoiceExpectationId);

    List<MissingExpectedInvoiceReadModel> findByInvoiceStatusAndExpectedReceiveDateBetween(InvoiceStatus invoiceStatus, LocalDate start, LocalDate end);
}
