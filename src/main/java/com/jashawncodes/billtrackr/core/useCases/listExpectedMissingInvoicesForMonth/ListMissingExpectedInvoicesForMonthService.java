package com.jashawncodes.billtrackr.core.useCases.listExpectedMissingInvoicesForMonth;

import com.jashawncodes.billtrackr.core.model.expectedInvoice.InvoiceStatus;
import com.jashawncodes.billtrackr.core.ports.in.ListMissingExpectedInvoicesForMonthUseCase;
import com.jashawncodes.billtrackr.core.ports.out.PersistenceGatewayOutputPort;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public class ListMissingExpectedInvoicesForMonthService implements ListMissingExpectedInvoicesForMonthUseCase {
    private final PersistenceGatewayOutputPort persistenceGatewayOutputPort;

    public ListMissingExpectedInvoicesForMonthService(PersistenceGatewayOutputPort persistenceGatewayOutputPort) {
        this.persistenceGatewayOutputPort = persistenceGatewayOutputPort;
    }


//    This method should possibly return a list of result objects rather than read model objects
    @Override
    public List<MissingExpectedInvoiceReadModel> listMissingExpectedInvoicesForMonth(YearMonth yearMonth) {
//        If yearMonth is ever null, yearMonth will = today's date
        if (yearMonth == null) {
            yearMonth = YearMonth.now();
        }

//      Parse start and end dates
        LocalDate start = yearMonth.atDay(1);
        LocalDate end = yearMonth.atEndOfMonth();

//      Return list from query
        return persistenceGatewayOutputPort
                .findByInvoiceStatusAndExpectedReceiveDateBetween(InvoiceStatus.MISSING, start, end);
    }
}
