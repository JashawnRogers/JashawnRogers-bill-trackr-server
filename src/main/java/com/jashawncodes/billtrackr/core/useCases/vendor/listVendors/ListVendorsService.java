package com.jashawncodes.billtrackr.core.useCases.vendor.listVendors;

import com.jashawncodes.billtrackr.core.ports.in.vendor.ListVendorsUseCase;
import com.jashawncodes.billtrackr.core.ports.out.gateways.VendorGatewayOutputPort;
import com.jashawncodes.billtrackr.core.useCases.PageRequestValidator;
import com.jashawncodes.billtrackr.core.useCases.PageResponse;

public class ListVendorsService implements ListVendorsUseCase {
    private final VendorGatewayOutputPort vendorGateway;

    public ListVendorsService(VendorGatewayOutputPort vendorGateway) {
        this.vendorGateway = vendorGateway;
    }

    /**
     * Returns a paginated list of vendors for dashboard and reporting use cases.
     *
     * <p>This use case delegates querying responsibilities to the persistence layer,
     * including filtering, searching, sorting, and pagination. Query normalization
     * and default value handling are performed by {@link ListVendorsQuery}.</p>
     *
     * <p>The returned data is optimized for read/display operations and should not
     * be treated as a mutable domain aggregate.</p>
     *
     * @param query contains pagination, filtering, sorting, and search
     *              used to retrieve vendors
     * @return a paginated response containing vendor read models matching
     *         the supplied query criteria
     * @throws IllegalArgumentException if the page request contains invalid pagination values
     */
    @Override
    public PageResponse<ListVendorsReadModel> listVendors(ListVendorsQuery query) {
        PageRequestValidator.validate(query.pageRequest());

        return vendorGateway.findAllPaginated(query);
    }
}
