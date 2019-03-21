package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.employee;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.EmployeeDto;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.ProjectEmployeeDto;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpMethod.GET;

@Service
@Profile({"prod", "staging"})
public class InsightEmployeeServiceRemote implements InsightEmployeeService {

    private final RestTemplate restTemplate;

    @Autowired
    public InsightEmployeeServiceRemote(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public List<EmployeeDto> getForProject(String projectCode) {

        ResponseEntity<List<ProjectEmployeeDto>> response;

        String url = "/projects/" + projectCode + "/team/current";
        try {
            response =
                    this.restTemplate
                            .exchange(url, GET, null, new ParameterizedTypeReference<List<ProjectEmployeeDto>>() {
                            });
            return response.getBody().stream().map(ProjectEmployeeDto::getEmployeeDto).collect(Collectors.toList());

        } catch (HttpServerErrorException e) {
            System.out.println(url);
            return Lists.newArrayList();
        }

    }
}
