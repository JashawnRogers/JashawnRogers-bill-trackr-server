package com.jashawncodes.billtrackr.core.useCases.invoiceSchedule.listInvoiceSchedules;

import com.jashawncodes.billtrackr.core.ports.in.invoiceSchedule.ListInvoiceSchedules;
import com.jashawncodes.billtrackr.core.ports.out.gateways.InvoiceScheduleGatewayOutputPort;
import com.jashawncodes.billtrackr.core.useCases.PageRequestValidator;
import com.jashawncodes.billtrackr.core.useCases.PageResponse;

public class ListInvoiceSchedulesService implements ListInvoiceSchedules {
    private final InvoiceScheduleGatewayOutputPort invoiceScheduleGateway;

    public ListInvoiceSchedulesService(InvoiceScheduleGatewayOutputPort invoiceScheduleGateway) {
        this.invoiceScheduleGateway = invoiceScheduleGateway;
    }

    @Override
    public PageResponse<ListInvoiceScheduleReadModel> listInvoiceSchedules(ListInvoiceScheduleQuery query) {
        PageRequestValidator.validate(query.pageRequest());

        return invoiceScheduleGateway.findAllPaginated(query);
    }
}
