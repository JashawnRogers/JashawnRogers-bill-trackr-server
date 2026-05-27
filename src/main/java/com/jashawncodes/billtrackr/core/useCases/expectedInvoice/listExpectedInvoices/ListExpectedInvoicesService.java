package com.jashawncodes.billtrackr.core.useCases.expectedInvoice.listExpectedInvoices;

import com.jashawncodes.billtrackr.core.ports.in.ListExpectedInvoicesUseCase;
import com.jashawncodes.billtrackr.core.ports.out.gateways.ExpectedInvoiceGatewayOutputPort;
import com.jashawncodes.billtrackr.core.useCases.PageRequestValidator;
import com.jashawncodes.billtrackr.core.useCases.PageResponse;
import com.jashawncodes.billtrackr.core.useCases.expectedInvoice.ListExpectedInvoicesQuery;


public class ListExpectedInvoicesService implements ListExpectedInvoicesUseCase {
    private final ExpectedInvoiceGatewayOutputPort expectedInvoiceGateway;

    public ListExpectedInvoicesService(ExpectedInvoiceGatewayOutputPort expectedInvoiceGateway) {
        this.expectedInvoiceGateway = expectedInvoiceGateway;
    }

    /**
     * Returns a paginated list of expected invoices for dashboard and reporting use cases.
     *
     * <p>This use case delegates querying responsibilities to the persistence layer,
     * including filtering, searching, sorting, and pagination. Query normalization
     * and default value handling are performed by {@link ListExpectedInvoicesQuery}.</p>
     *
     * <p>The returned data is optimized for read/display operations and should not
     * be treated as a mutable domain aggregate.</p>
     *
     * @param query contains pagination, filtering, sorting, search, and month criteria
     *              used to retrieve expected invoices
     * @return a paginated response containing expected invoice read models matching
     *         the supplied query criteria
     * @throws IllegalArgumentException if the page request contains invalid pagination values
     */
    @Override
    public PageResponse<ExpectedInvoiceReadModel> listExpectedInvoices(ListExpectedInvoicesQuery query) {
        PageRequestValidator.validate(query.pageRequest());

        return expectedInvoiceGateway.findAllPaginated(query);
    }
}
