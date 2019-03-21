package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.projectdetails;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.ProjectDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.GET;

@Service
@Profile({"prod", "staging"})
public class InsightProjectDetailsServiceRemote implements InsightProjectDetailService {

    private final RestTemplate insightRestTemplate;


    @Autowired
    public InsightProjectDetailsServiceRemote(RestTemplate insightRestTemplate) {
        this.insightRestTemplate = insightRestTemplate;
    }


    @Override
    public ProjectDetailsDto getDetails(String projectCode) {
        ResponseEntity<ProjectDetailsDto> response;

        String url = "/projects/" + projectCode;
        try {
            response =
                    this.insightRestTemplate
                            .exchange(url, GET, null, new ParameterizedTypeReference<ProjectDetailsDto>() {
                            });
            return response.getBody();

        } catch (HttpServerErrorException e) {
            return null;
        }

    }
}
