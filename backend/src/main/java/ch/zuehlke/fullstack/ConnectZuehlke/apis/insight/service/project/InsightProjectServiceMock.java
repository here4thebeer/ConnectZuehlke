package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.project;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.ProjectDto;

import java.util.Collections;
import java.util.List;

public class InsightProjectServiceMock implements InsightProjectService {

    @Override
    public List<ProjectDto> getProjectDtos() {
        return Collections.emptyList();
    }

}
