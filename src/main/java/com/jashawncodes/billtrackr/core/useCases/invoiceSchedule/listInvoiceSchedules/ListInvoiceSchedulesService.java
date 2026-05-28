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

    /**
     * Returns a paginated list of invoice schedules for dashboard and reporting use cases.
     *
     * <p>This use case delegates querying responsibilities to the persistence layer,
     * including filtering, searching, sorting, and pagination. Query normalization
     * and default value handling are performed by {@link ListInvoiceScheduleQuery}.</p>
     *
     * <p>The returned data is optimized for read/display operations and should not
     * be treated as a mutable domain aggregate.</p>
     *
     * @param query contains pagination, filtering, sorting, and search
     *              used to retrieve invoice schedules
     * @return a paginated response containing vendor read models matching
     *         the supplied query criteria
     * @throws IllegalArgumentException if the page request contains invalid pagination values
     */
    @Override
    public PageResponse<ListInvoiceScheduleReadModel> listInvoiceSchedules(ListInvoiceScheduleQuery query) {
        PageRequestValidator.validate(query.pageRequest());

        return invoiceScheduleGateway.findAllPaginated(query);
    }
}
