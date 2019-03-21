package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.customer;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.CustomerDto;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Customer;

import java.util.List;

public interface InsightCustomerService {
    List<CustomerDto> getCustomers();

    CustomerDto getForId(Integer customerId);
}
