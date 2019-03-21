package ch.zuehlke.fullstack.ConnectZuehlke.rest;


import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.CustomerDto;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.customer.InsightCustomerService;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CustomerRestController {
    private final InsightCustomerService insightCustomerService;

    public CustomerRestController(InsightCustomerService insightCustomerService) {
        this.insightCustomerService = insightCustomerService;
    }

    @GetMapping("/api/customers")
    public List<Customer> customerList() {
        return insightCustomerService.getCustomers()
                .stream()
                .map(CustomerDto::toCustomer)
                .collect(Collectors.toList());
    }


}