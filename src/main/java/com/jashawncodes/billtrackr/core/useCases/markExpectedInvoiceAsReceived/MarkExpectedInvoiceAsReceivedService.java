package com.jashawncodes.billtrackr.core.useCases.markExpectedInvoiceAsReceived;

import com.jashawncodes.billtrackr.core.NotFoundException;
import com.jashawncodes.billtrackr.core.NullParameterException;
import com.jashawncodes.billtrackr.core.model.expectedInvoice.ExpectedInvoice;
import com.jashawncodes.billtrackr.core.ports.in.MarkExpectedInvoiceAsReceivedUseCase;
import com.jashawncodes.billtrackr.core.ports.out.PersistenceGatewayOutputPort;

import java.time.LocalDate;
import java.util.UUID;

public class MarkExpectedInvoiceAsReceivedService implements MarkExpectedInvoiceAsReceivedUseCase {
    private final PersistenceGatewayOutputPort persistenceGatewayOutputPort;

    public MarkExpectedInvoiceAsReceivedService(PersistenceGatewayOutputPort persistenceGatewayOutputPort) {
        this.persistenceGatewayOutputPort = persistenceGatewayOutputPort;
    }

    @Override
    public MarkExpectedInvoiceAsReceivedResult markExpectedInvoiceAsReceived(LocalDate receivedDate, UUID expectedInvoiceId) {
        if (receivedDate == null) {
            throw new NullParameterException("A receive date is required to mark as received");
        }

        if (expectedInvoiceId == null) {
            throw new NullParameterException("Invoice ID is required");
        }

        ExpectedInvoice expectedInvoice = persistenceGatewayOutputPort.findById(expectedInvoiceId)
                .orElseThrow(() -> new NotFoundException("Invoice not found"));

        expectedInvoice.markAsReceived(receivedDate);

        ExpectedInvoice saved = persistenceGatewayOutputPort.save(expectedInvoice);

        return MarkExpectedInvoiceAsReceivedResult.of(
                saved.getId(),
                saved.getExpectedReceiveDate(),
                saved.getReceivedDate(),
                saved.getInvoiceStatus(),
                saved.getNote()
        );
    }
}
