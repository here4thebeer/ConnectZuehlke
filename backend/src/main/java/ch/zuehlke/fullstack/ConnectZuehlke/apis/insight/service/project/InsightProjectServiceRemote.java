package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.project;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.ProjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@Service
public class InsightProjectServiceRemote implements InsightProjectService {

    private final RestTemplate insightRestTemplate;

    @Autowired
    public InsightProjectServiceRemote(RestTemplate insightRestTemplate) {
        this.insightRestTemplate = insightRestTemplate;
    }

    @Override
    public List<ProjectDto> getProjectDtos() {
        ResponseEntity<List<ProjectDto>> response =
                this.insightRestTemplate
                        .exchange("/projects", GET, null, new ParameterizedTypeReference<List<ProjectDto>>() {
                        });

        return response.getBody();
    }

}
