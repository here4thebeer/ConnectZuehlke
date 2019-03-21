package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.skill;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.SkillDto;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.http.HttpMethod.GET;

@Service
@Profile({"prod", "staging"})
public class InsightSkillServiceRemote implements InsightSkillService {

    private final RestTemplate restTemplate;

    @Autowired
    public InsightSkillServiceRemote(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public List<SkillDto> getForProject(String projectCode) {

        ResponseEntity<List<SkillDto>> response;

        String url = "/projects/" + projectCode + "/skills";
        try {
            response =
                    this.restTemplate
                            .exchange(url, GET, null, new ParameterizedTypeReference<List<SkillDto>>() {
                            });
            return Objects.requireNonNull(response.getBody()).stream()
                    .peek(skillDto -> skillDto.setProjectCode(projectCode))
                    .collect(Collectors.toList());
        } catch (HttpServerErrorException e) {
            System.out.println(url);
            return Lists.newArrayList();
        }
    }
}
