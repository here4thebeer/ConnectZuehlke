package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.project;


import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.CustomerDto;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.ProjectCustomerDetailDto;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.ProjectDto;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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

    @Override
    @Cacheable("projects")
    public List<Project> getProjects() {
        ResponseEntity<List<ProjectDto>> response =
                this.insightRestTemplate
                        .exchange("/projects", GET, null, new ParameterizedTypeReference<List<ProjectDto>>() {
                        });

        List<ProjectDto> projectDtos = new ArrayList<>(response.getBody());
//        queryProjectDetails(projectDtos);
        return projectDtos.stream().map(ProjectDto::toProject).collect(toList());
    }

//    private void queryProjectDetails(List<ProjectDto> projectDtos) {
//        ParameterizedTypeReference<ProjectCustomerDetailDto> typeReference = new ParameterizedTypeReference<ProjectCustomerDetailDto>() {
//        };
//        projectDtos.forEach(projectDto -> {
//            String uri = "/projects/" + projectDto.getCode();
//            ResponseEntity<ProjectCustomerDetailDto> response = this.insightRestTemplate.exchange(uri, GET, null, typeReference);
//            CustomerDto customerDto = response.getBody().getCustomer();
//            projectDto.setCustomerDto(customerDto);
//            System.out.println(projectDto);
//        });
//    }

}