package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.project;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.ProjectDto;

import java.util.List;

public interface InsightProjectService {

    List<ProjectDto> getProjectDtos();

}
