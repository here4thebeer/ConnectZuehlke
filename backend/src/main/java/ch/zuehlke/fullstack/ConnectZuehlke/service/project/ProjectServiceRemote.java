package ch.zuehlke.fullstack.ConnectZuehlke.service.project;


import ch.zuehlke.fullstack.ConnectZuehlke.domain.Project;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile({"prod", "staging"})
public class ProjectServiceRemote implements ProjectService {
    @Override
    public List<Project> getProjects() {
        return null;
    }

//    @Autowired
//    public ProjectServiceRemote(RestTemplate insightRestTemplate) {
//        this.insightRestTemplate = insightRestTemplate;
//    }
//
//    @Override
//    public List<Project> getProjects() {
//        return null;
//    }

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