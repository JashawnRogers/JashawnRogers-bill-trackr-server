package com.jashawncodes.billtrackr.core.useCases.generateExpectedInvoicesForMonth;

import java.time.LocalDate;
import java.util.UUID;

public record ExpectedInvoiceKey(
        UUID invoiceScheduleId,
        LocalDate expectedReceiveDate
) {
}
