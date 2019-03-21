package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.customer;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.CustomerDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.asList;

@Service
@Profile({"ci", "default"})
public class InsightCustomerServiceMocked implements InsightCustomerService {

    private static final List<CustomerDto> CUSTOMERS = asList(
            new CustomerDto(18444, "Test AG, Wikon", "Bahnhofstrasse 33", "Wikon", "Schweiz", 4806, 7.9790377484264, 47.2676948098177),
            new CustomerDto(18445, "Zühlke Engineering AG", "Wiesenstrasse 10a", "Schlieren", "Schweiz", 8956, 8.4415769, 47.4000486),
            new CustomerDto(18446, "Sebastian AG, Zürich", "Seefeldstrasse 147", "Zürich", "Schweiz", 8008, 8.5537504, 47.3565633),
            new CustomerDto(18447, "Swisscom", "Förrlibruckstrasse 181", "Zürich", "Schweiz", 8005, 8.511068, 47.391702),
            new CustomerDto(18448, "SIX", "Hardturmstrasse 201", "Zürich", "Schweiz", 8005, 8.509338, 47.393659),
            new CustomerDto(18449, "SBB", "Lindenhofstrasse 1", "Worblaufen", "Schweiz", 3048, 7.459547, 46.980497)
    );


    @Override
    public List<CustomerDto> getCustomers() {
        return CUSTOMERS;
    }

    @Override
    public CustomerDto getForId(Integer customerId) {
        return null;
    }
}
