package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.customer;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.google.GeocodeService;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.CustomerDto;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.ListDto;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpMethod.GET;

@Service
@Profile({"prod", "staging"})
public class InsightCustomerServiceRemote implements InsightCustomerService {
    private static final Logger log = LoggerFactory.getLogger(InsightCustomerServiceRemote.class);

    private final RestTemplate insightRestTemplate;
    private final GeocodeService geocodeService;

    @Autowired
    public InsightCustomerServiceRemote(RestTemplate insightRestTemplate, GeocodeService geocodeService) {
        this.insightRestTemplate = insightRestTemplate;
        this.geocodeService = geocodeService;
    }

    @Override
    public List<CustomerDto> getCustomers() {
        ResponseEntity<ListDto<CustomerDto>> response = this.insightRestTemplate
                .exchange("/customers", GET, null, new ParameterizedTypeReference<ListDto<CustomerDto>>() {
                });

        return response.getBody().getItems();
    }

    @Override
    public CustomerDto getForId(Integer customerId) {

        ResponseEntity<CustomerDto> response;

        String url = "/customers/" + customerId;
        try {
            response = this.insightRestTemplate
                    .exchange(url, GET, null, new ParameterizedTypeReference<CustomerDto>() {
                    });
            fixLocation(response.getBody());
            return response.getBody();

        } catch (HttpServerErrorException e) {
            return null;
        }

    }

    private void fixLocation(CustomerDto customer) {
        if (customer.getId().equals(14337)) {
            customer.setLatitude(51.354231);
            customer.setLongitude(-0.460845);
        } else if (customer.getId().equals(13291)) {
            customer.setLatitude(51.505784);
            customer.setLongitude(-0.018477);
        } else if (customer.getId().equals(12945)) {
            customer.setLatitude(22.318358);
            customer.setLongitude(114.161001);
        } else if (customer.getId().equals(12660)) {
            customer.setLatitude(51.536726);
            customer.setLongitude(-0.079696);
        } else if (customer.getId().equals(13269)) {
            customer.setLatitude(53.480453);
            customer.setLongitude(-2.238986);
        } else {
            ArrayList<String> possibleAddresses = Lists.newArrayList(
                    customer.getName() + ", " + customer.getZip() + " " + customer.getCity() + ", " + customer.getCountry(),
                    customer.getStreet() + ", " + customer.getZip() + " " + customer.getCity() + ", " + customer.getCountry(),
                    customer.getName() + ", " + customer.getCountry()
            );
            if (customer.getStreet() == null ||
                    customer.getStreet().toLowerCase().contains("postfach") ||
                    customer.getLatitude() == null ||
                    customer.getLongitude() == null) {
                possibleAddresses.stream()
                        .map(geocodeService::getGeocodeOfAddress)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .findFirst().ifPresent(latLng -> {
                    log.info("Updating customer {} with {}", customer, latLng);
                    customer.setLatitude(latLng.lat);
                    customer.setLongitude(latLng.lng);
                });
            }
        }
    }
}
