package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.customer;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.CustomerDto;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.ListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@Service
@Profile({"prod", "staging"})
public class InsightCustomerServiceRemote implements InsightCustomerService {
    private final RestTemplate insightRestTemplate;


    @Autowired
    public InsightCustomerServiceRemote(RestTemplate insightRestTemplate) {
        this.insightRestTemplate = insightRestTemplate;
    }

    @Override
    public List<CustomerDto> getCustomers() {
        ResponseEntity<ListDto<CustomerDto>> response = this.insightRestTemplate
                .exchange("/customers", GET, null, new ParameterizedTypeReference<ListDto<CustomerDto>>() {
                });

        return response.getBody().getItems();
    }
}
