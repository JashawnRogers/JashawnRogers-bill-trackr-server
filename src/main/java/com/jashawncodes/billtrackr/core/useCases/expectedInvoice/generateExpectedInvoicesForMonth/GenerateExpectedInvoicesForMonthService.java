package com.jashawncodes.billtrackr.core.useCases.expectedInvoice.generateExpectedInvoicesForMonth;

import com.jashawncodes.billtrackr.core.model.expectedInvoice.ExpectedInvoice;
import com.jashawncodes.billtrackr.core.model.expectedInvoice.InvalidExpectedReceiveDateException;
import com.jashawncodes.billtrackr.core.model.expectedInvoice.InvalidPaymentTermsException;
import com.jashawncodes.billtrackr.core.model.vendor.PaymentTerms;
import com.jashawncodes.billtrackr.core.ports.in.GenerateExpectedInvoicesForMonthUseCase;
import com.jashawncodes.billtrackr.core.ports.out.IdGeneratorOutputPort;
import com.jashawncodes.billtrackr.core.ports.out.gateways.ExpectedInvoiceGatewayOutputPort;
import com.jashawncodes.billtrackr.core.ports.out.gateways.InvoiceScheduleGatewayOutputPort;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

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

    /**
     * Generates any missing expected invoices for the given month.
     *
     * <p>This use case loads all active invoice schedules with the payment terms needed
     * to calculate due dates, then checks which expected invoices already exist for
     * those schedules within the requested month. Existing invoices are skipped so
     * the operation can be safely run multiple times for the same month.</p>
     *
     * <p>The method returns generation metadata only, not the generated invoices
     * themselves. Listing expected invoices for the UI should be handled by a
     * separate query/list use case.</p>
     *
     * @param yearMonth the month to generate expected invoices for
     * @return a summary containing the month, number of newly created invoices,
     *         and number of invoices that already existed
     * @throws InvalidPaymentTermsException if a schedule is missing payment terms
     * @throws InvalidExpectedReceiveDateException if an expected receive date is missing
     */
    @Override
    public GenerateExpectedInvoicesForMonthUseCaseResult generateExpectedInvoicesForMonthUseCase(
            YearMonth yearMonth
    ) {
        List<InvoiceScheduleForGeneration> invoiceSchedules =
                  invoiceScheduleGateway.loadActiveSchedulesWithPaymentTerms();

        List<UUID> invoiceScheduleIds = invoiceSchedules.stream()
                  .map(InvoiceScheduleForGeneration::Id)
                  .toList();

        LocalDate monthStart = yearMonth.atDay(1);
        LocalDate monthEnd = yearMonth.atEndOfMonth();

        List<ExpectedInvoice> existingInvoices =
                  expectedInvoiceGateway.findExistingForSchedulesBetween(
                          invoiceScheduleIds,
                          monthStart,
                          monthEnd
                  );

        Set<ExpectedInvoiceKey> existingInvoiceKeys = existingInvoices.stream()
                .map(invoice -> new ExpectedInvoiceKey(
                        invoice.getInvoiceScheduleId(),
                        invoice.getExpectedReceiveDate()
                ))
                .collect(Collectors.toSet());

        int createdCount = 0;
        int alreadyExistsCount = 0;

        for (InvoiceScheduleForGeneration schedule : invoiceSchedules) {
            if (schedule.paymentTerms() == null) {
                throw new InvalidPaymentTermsException
                        ("Invoice schedule is missing payment terms. ID: " + schedule.Id());
            }

            if (schedule.expectedReceiveDate() == null) {
                throw new InvalidExpectedReceiveDateException
                        ("Invoice schedule is missing expected receive date. ID: " + schedule.Id());
            }

            List<LocalDate> expectedReceiveDates =
                    schedule.recurrenceRule().expectedReceiveDatesBasedOnDatesWithin(yearMonth);

            for (LocalDate expectedReceiveDate : expectedReceiveDates) {
                ExpectedInvoiceKey key = new ExpectedInvoiceKey(
                        schedule.Id(),
                        expectedReceiveDate
                );

                if (existingInvoiceKeys.contains(key)) {
                    alreadyExistsCount++;
                    continue;
                }

                LocalDate dueDate = calculateDueDate(expectedReceiveDate, schedule.paymentTerms());

                ExpectedInvoice expectedInvoice = ExpectedInvoice.createNew(
                        idGeneratorOutputPort.generateNewUUID(),
                        schedule.Id(),
                        expectedReceiveDate,
                        dueDate,
                        null
                );

                expectedInvoiceGateway.save(expectedInvoice);
                existingInvoiceKeys.add(key);
                createdCount++;
            }
        }

        return new GenerateExpectedInvoicesForMonthUseCaseResult(
                yearMonth,
                createdCount,
                alreadyExistsCount
        );
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
