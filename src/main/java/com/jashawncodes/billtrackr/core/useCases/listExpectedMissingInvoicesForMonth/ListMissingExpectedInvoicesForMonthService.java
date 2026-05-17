package com.jashawncodes.billtrackr.core.useCases.listExpectedMissingInvoicesForMonth;

import com.jashawncodes.billtrackr.core.model.expectedInvoice.InvoiceStatus;
import com.jashawncodes.billtrackr.core.ports.in.ListMissingExpectedInvoicesForMonthUseCase;
import com.jashawncodes.billtrackr.core.ports.out.gateways.ExpectedInvoiceGatewayOutputPort;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public class ListMissingExpectedInvoicesForMonthService implements ListMissingExpectedInvoicesForMonthUseCase {
    private final ExpectedInvoiceGatewayOutputPort expectedInvoiceGateway;

    public ListMissingExpectedInvoicesForMonthService(ExpectedInvoiceGatewayOutputPort expectedInvoiceGateway) {
        this.expectedInvoiceGateway = expectedInvoiceGateway;
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
        return expectedInvoiceGateway
                .findByInvoiceStatusAndExpectedReceiveDateBetween(InvoiceStatus.MISSING, start, end);
    }
}
