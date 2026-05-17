package com.jashawncodes.billtrackr.core.useCases.generateExpectedInvoicesForMonth;

import com.jashawncodes.billtrackr.core.model.expectedInvoice.ExpectedInvoice;
import com.jashawncodes.billtrackr.core.model.expectedInvoice.InvalidExpectedReceiveDateException;
import com.jashawncodes.billtrackr.core.model.expectedInvoice.InvalidPaymentTermsException;
import com.jashawncodes.billtrackr.core.model.invoiceSchedule.InvoiceSchedule;
import com.jashawncodes.billtrackr.core.model.vendor.PaymentTerms;
import com.jashawncodes.billtrackr.core.ports.in.GenerateExpectedInvoicesForMonthUseCase;
import com.jashawncodes.billtrackr.core.ports.out.IdGeneratorOutputPort;
import com.jashawncodes.billtrackr.core.ports.out.gateways.ExpectedInvoiceGatewayOutputPort;
import com.jashawncodes.billtrackr.core.ports.out.gateways.InvoiceScheduleGatewayOutputPort;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

public class GenerateExpectedInvoicesForMonthService implements GenerateExpectedInvoicesForMonthUseCase {
    private final ExpectedInvoiceGatewayOutputPort expectedInvoiceGateway;
    private final InvoiceScheduleGatewayOutputPort invoiceScheduleGateway;
    private final IdGeneratorOutputPort idGeneratorOutputPort;

    public GenerateExpectedInvoicesForMonthService(ExpectedInvoiceGatewayOutputPort expectedInvoiceGateway,
                                                   InvoiceScheduleGatewayOutputPort invoiceScheduleGateway,
                                                   IdGeneratorOutputPort idGeneratorOutputPort
    ) {
        this.expectedInvoiceGateway = expectedInvoiceGateway;
        this.invoiceScheduleGateway = invoiceScheduleGateway;
        this.idGeneratorOutputPort = idGeneratorOutputPort;
    }

    @Override
    public List<GenerateExpectedInvoicesForMonthUseCaseResult> generateExpectedInvoiceForMonthUseCase(
            YearMonth yearMonth
    ) {

//      Load all active invoice schedules
        List<InvoiceSchedule> activeInvoiceSchedules =
                invoiceScheduleGateway.findAllByIsActive();

//      Instantiate empty list to hold expected invoices
        List<GenerateExpectedInvoicesForMonthUseCaseResult> expectedInvoices = new ArrayList<>();

//      Loop through active invoice schedules to produce all invoices within parameter's date range
        for (InvoiceSchedule invoiceSchedule : activeInvoiceSchedules) {

            List<LocalDate> expectedReceiveDates = invoiceSchedule.getRecurrenceRule().datesWithin(yearMonth);

            for (LocalDate expectedReceiveDate : expectedReceiveDates) {

                Optional<ExpectedInvoice> existingExpectedInvoice = expectedInvoiceGateway
                        .findByExpectedReceiveDateAndRecurringInvoiceExpectationId(
                                expectedReceiveDate, invoiceSchedule.getId()
                        );

//              If expected invoice exists by expectedReceiveDate and invoiceSchedule ID, retrieve it, map it...
//              and add to List of expected invoices
                if (existingExpectedInvoice.isPresent()) {

                    GenerateExpectedInvoicesForMonthUseCaseResult expectedInvoiceResult =
                            new GenerateExpectedInvoicesForMonthUseCaseResult(
                            existingExpectedInvoice.get().getId(),
                            invoiceSchedule.getTrackedInvoiceKey().trackedInvoiceKey(),
                            existingExpectedInvoice.get().getExpectedReceiveDate(),
                            calculateDueDate(existingExpectedInvoice.get().getExpectedReceiveDate(), invoiceSchedule.getPaymentTerms()),
                            invoiceSchedule.getPaymentTerms(),
                            null,
                            existingExpectedInvoice.get().getInvoiceStatus(),
                            existingExpectedInvoice.get().getNote()
                    );

                    expectedInvoices.add(expectedInvoiceResult);
                } else {
//                  If expected invoice does not exist by expectedReceiveDate and invoiceSchedule ID, create new, map it...
//                  and add it to List of expected invoices

                    UUID id = idGeneratorOutputPort.generateNewUUID();

                    ExpectedInvoice expectedInvoice = ExpectedInvoice.createNew(
                            id,
                            invoiceSchedule.getId(),
                            expectedReceiveDate,
                            calculateDueDate(expectedReceiveDate, invoiceSchedule.getPaymentTerms()),
                            null
                    );

                    ExpectedInvoice saved = expectedInvoiceGateway.save(expectedInvoice);

                    GenerateExpectedInvoicesForMonthUseCaseResult expectedInvoiceResult =
                            new GenerateExpectedInvoicesForMonthUseCaseResult(
                            saved.getId(),
                            invoiceSchedule.getTrackedInvoiceKey().trackedInvoiceKey(),
                            expectedInvoice.getExpectedReceiveDate(),
                            saved.getDueDate(),
                            invoiceSchedule.getPaymentTerms(),
                            null,
                            saved.getInvoiceStatus(),
                            expectedInvoice.getNote()
                    );

                    expectedInvoices.add(expectedInvoiceResult);
                }

            }
        }

        return expectedInvoices;

    }

    private LocalDate calculateDueDate(LocalDate expectedReceiveDate, PaymentTerms paymentTerms) {
        if (paymentTerms == null) {
            throw new InvalidPaymentTermsException("Payment term is missing");
        }

        if (expectedReceiveDate == null) {
            throw new InvalidExpectedReceiveDateException("Expected receive date is missing");
        }

        return expectedReceiveDate.plusDays(paymentTerms.days());
    }
}
