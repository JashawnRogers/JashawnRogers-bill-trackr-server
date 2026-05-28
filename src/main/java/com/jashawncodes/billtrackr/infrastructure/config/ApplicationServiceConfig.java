package com.jashawncodes.billtrackr.infrastructure.config;

import com.jashawncodes.billtrackr.core.ports.out.IdGeneratorOutputPort;
import com.jashawncodes.billtrackr.core.ports.out.gateways.ExpectedInvoiceGatewayOutputPort;
import com.jashawncodes.billtrackr.core.ports.out.gateways.InvoiceScheduleGatewayOutputPort;
import com.jashawncodes.billtrackr.core.ports.out.gateways.VendorGatewayOutputPort;
import com.jashawncodes.billtrackr.core.useCases.expectedInvoice.generateExpectedInvoicesForMonth.GenerateExpectedInvoicesForMonthService;
import com.jashawncodes.billtrackr.core.useCases.expectedInvoice.listExpectedInvoices.ListExpectedInvoicesService;
import com.jashawncodes.billtrackr.core.useCases.expectedInvoice.listExpectedMissingInvoicesForMonth.ListMissingExpectedInvoicesForMonthService;
import com.jashawncodes.billtrackr.core.useCases.expectedInvoice.markExpectedInvoiceAsReceived.MarkExpectedInvoiceAsReceivedService;
import com.jashawncodes.billtrackr.core.useCases.invoiceSchedule.createInvoiceSchedule.CreateInvoiceScheduleService;
import com.jashawncodes.billtrackr.core.useCases.invoiceSchedule.listInvoiceSchedules.ListInvoiceSchedulesService;
import com.jashawncodes.billtrackr.core.useCases.invoiceSchedule.updateInvoiceSchedule.UpdateInvoiceScheduleService;
import com.jashawncodes.billtrackr.core.useCases.vendor.createVendor.CreateVendorService;
import com.jashawncodes.billtrackr.core.useCases.vendor.listVendors.ListVendorsService;
import com.jashawncodes.billtrackr.core.useCases.vendor.updateVendor.UpdateVendorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationServiceConfig {
    @Bean
    CreateVendorService createVendorService(VendorGatewayOutputPort vendorGatewayOutputPort,
                                            IdGeneratorOutputPort idGeneratorOutputPort) {
        return new CreateVendorService(vendorGatewayOutputPort, idGeneratorOutputPort);
    }

    @Bean
    ListVendorsService listVendorsService(VendorGatewayOutputPort vendorGatewayOutputPort) {
        return new ListVendorsService(vendorGatewayOutputPort);
    }

    @Bean
    UpdateVendorService updateVendorService(VendorGatewayOutputPort vendorGatewayOutputPort) {
        return new UpdateVendorService(vendorGatewayOutputPort);
    }

    @Bean
    CreateInvoiceScheduleService createInvoiceScheduleService(
            InvoiceScheduleGatewayOutputPort invoiceScheduleGatewayOutputPort,
            VendorGatewayOutputPort vendorGatewayOutputPort,
            IdGeneratorOutputPort idGeneratorOutputPort
    ) {
        return new CreateInvoiceScheduleService(
                invoiceScheduleGatewayOutputPort,
                vendorGatewayOutputPort,
                idGeneratorOutputPort
        );
    }

    @Bean
    ListInvoiceSchedulesService listInvoiceSchedulesService(
            InvoiceScheduleGatewayOutputPort invoiceScheduleGatewayOutputPort
    ) {
        return new ListInvoiceSchedulesService(invoiceScheduleGatewayOutputPort);
    }

    @Bean
    UpdateInvoiceScheduleService updateInvoiceScheduleService(
            InvoiceScheduleGatewayOutputPort invoiceScheduleGatewayOutputPort,
            VendorGatewayOutputPort vendorGatewayOutputPort
    ) {
        return new UpdateInvoiceScheduleService(invoiceScheduleGatewayOutputPort, vendorGatewayOutputPort);
    }

    @Bean
    GenerateExpectedInvoicesForMonthService generateExpectedInvoicesForMonthService(
            ExpectedInvoiceGatewayOutputPort expectedInvoiceGatewayOutputPort,
            InvoiceScheduleGatewayOutputPort invoiceScheduleGatewayOutputPort,
            IdGeneratorOutputPort idGeneratorOutputPort
    ) {
        return new GenerateExpectedInvoicesForMonthService(
                expectedInvoiceGatewayOutputPort,
                invoiceScheduleGatewayOutputPort,
                idGeneratorOutputPort
        );
    }

    @Bean
    ListExpectedInvoicesService listExpectedInvoicesService(
            ExpectedInvoiceGatewayOutputPort expectedInvoiceGatewayOutputPort
    ) {
        return new ListExpectedInvoicesService(expectedInvoiceGatewayOutputPort);
    }

    @Bean
    ListMissingExpectedInvoicesForMonthService listMissingExpectedInvoicesForMonthService(
            ExpectedInvoiceGatewayOutputPort expectedInvoiceGatewayOutputPort
    ) {
        return new ListMissingExpectedInvoicesForMonthService(expectedInvoiceGatewayOutputPort);
    }

    @Bean
    MarkExpectedInvoiceAsReceivedService markExpectedInvoiceAsReceivedService(
            ExpectedInvoiceGatewayOutputPort expectedInvoiceGatewayOutputPort
    ) {
        return new MarkExpectedInvoiceAsReceivedService(expectedInvoiceGatewayOutputPort);
    }
}
