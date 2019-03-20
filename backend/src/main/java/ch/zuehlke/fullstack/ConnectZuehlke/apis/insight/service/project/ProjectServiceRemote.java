package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.project;


import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.ListDto;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.ProjectDto;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpMethod.GET;

@Service
@Profile({"prod", "staging"})
public class ProjectServiceRemote implements ProjectService {
    private final RestTemplate insightRestTemplate;

    @Autowired
    public ProjectServiceRemote(RestTemplate insightRestTemplate) {
        this.insightRestTemplate = insightRestTemplate;
    }

    public List<Project> getProjects() {
        ResponseEntity<ListDto<ProjectDto>> response =
                this.insightRestTemplate
                        .exchange("/projects", GET, null, new ParameterizedTypeReference<ListDto<ProjectDto>>() {});

        // TODO yast: get customer for each project and aggregate location

        return response.getBody().getItems().stream()
                .map(ProjectDto::toProject)
                .collect(toList());
    }
}