package com.jashawncodes.billtrackr.core.useCases.expectedInvoice.markExpectedInvoiceAsReceived;

import com.jashawncodes.billtrackr.core.NotFoundException;
import com.jashawncodes.billtrackr.core.NullParameterException;
import com.jashawncodes.billtrackr.core.model.expectedInvoice.ExpectedInvoice;
import com.jashawncodes.billtrackr.core.ports.in.expectedInvoice.MarkExpectedInvoiceAsReceivedUseCase;
import com.jashawncodes.billtrackr.core.ports.out.gateways.ExpectedInvoiceGatewayOutputPort;

import java.time.LocalDate;
import java.util.UUID;

public class MarkExpectedInvoiceAsReceivedService implements MarkExpectedInvoiceAsReceivedUseCase {
    private final ExpectedInvoiceGatewayOutputPort expectedInvoiceGateway;

    public MarkExpectedInvoiceAsReceivedService(ExpectedInvoiceGatewayOutputPort expectedInvoiceGateway) {
        this.expectedInvoiceGateway = expectedInvoiceGateway;
    }

    @Override
    public MarkExpectedInvoiceAsReceivedResult markExpectedInvoiceAsReceived(LocalDate receivedDate, UUID expectedInvoiceId) {
        if (receivedDate == null) {
            throw new NullParameterException("A receive date is required to mark as received");
        }

        if (expectedInvoiceId == null) {
            throw new NullParameterException("Invoice ID is required");
        }

        ExpectedInvoice expectedInvoice = expectedInvoiceGateway.findById(expectedInvoiceId)
                .orElseThrow(() -> new NotFoundException("Invoice not found"));

        expectedInvoice.markAsReceived(receivedDate);

        ExpectedInvoice saved = expectedInvoiceGateway.save(expectedInvoice);

        return MarkExpectedInvoiceAsReceivedResult.of(
                saved.getId(),
                saved.getExpectedReceiveDate(),
                saved.getReceivedDate(),
                saved.getInvoiceStatus(),
                saved.getNote()
        );
    }
}
