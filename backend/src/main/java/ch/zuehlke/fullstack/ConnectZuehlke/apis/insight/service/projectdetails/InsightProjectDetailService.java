package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.projectdetails;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.ProjectDetailsDto;

public interface InsightProjectDetailService {

    ProjectDetailsDto getDetails(String projectCode);
}
