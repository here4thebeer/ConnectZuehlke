package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.project;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.ProjectDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
@Profile({"ci", "default"})
public class InsightProjectServiceMock implements InsightProjectService {

    @Override
    public List<ProjectDto> getProjectDtos() {
        return Collections.emptyList();
    }

}
