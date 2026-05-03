package com.jashawncodes.billtrackr.core.usecases.expectedInvoice;

import com.jashawncodes.billtrackr.core.model.recurringinvoiceexpectation.RecurringInvoiceExpectation;
import com.jashawncodes.billtrackr.core.ports.in.GenerateExpectedInvoicesForMonthUseCase;
import com.jashawncodes.billtrackr.core.ports.out.IdGeneratorOutputPort;
import com.jashawncodes.billtrackr.core.ports.out.PersistenceGatewayOutputPort;

import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class GenerateExpectedInvoicesForMonthService implements GenerateExpectedInvoicesForMonthUseCase {
    private final PersistenceGatewayOutputPort persistenceGatewayOutputPort;
    private final IdGeneratorOutputPort idGeneratorOutputPort;

    public GenerateExpectedInvoicesForMonthService(PersistenceGatewayOutputPort persistenceGatewayOutputPort,
                                                   IdGeneratorOutputPort idGeneratorOutputPort
    ) {
        this.persistenceGatewayOutputPort = persistenceGatewayOutputPort;
        this.idGeneratorOutputPort = idGeneratorOutputPort;
    }

    @Override
    public GenerateExpectedInvoicesForMonthUseCaseResult generateExpectedInvoiceForMonthUseCase(YearMonth yearMonth) {

//      Load all active recurring invoice expectations
        Set<RecurringInvoiceExpectation> activeRecurringInvoiceExpectations = new HashSet<>(persistenceGatewayOutputPort.findAllByIsActive());

//      Filter by isActive and date range params
        Set<RecurringInvoiceExpectation> filteredRecurringInvoiceExpectations = activeRecurringInvoiceExpectations.stream()
                .filter(RecurringInvoiceExpectation::isActive)
                .collect(Collectors.toSet());

    }
}
