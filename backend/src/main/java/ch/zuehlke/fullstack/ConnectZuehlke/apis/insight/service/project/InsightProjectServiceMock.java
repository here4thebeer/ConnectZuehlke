package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.project;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.ProjectDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.asList;


@Service
@Profile({"ci", "default"})
public class InsightProjectServiceMock implements InsightProjectService {

    private static final List<ProjectDto> PROJECTS = asList(
            new ProjectDto("C18180", "CHF", 1, "Offering", "SAM TO", 1, 18447),
            new ProjectDto("C20098", "CHF", 1, "Offering", "Starlight", 2, 18448),
            new ProjectDto("C22669", "CHF", 1, "Offering", "Sanierung Finanzen", 3, 18449)
    );

    @Override
    public List<ProjectDto> getProjectDtos() {
        return PROJECTS;
    }

}
