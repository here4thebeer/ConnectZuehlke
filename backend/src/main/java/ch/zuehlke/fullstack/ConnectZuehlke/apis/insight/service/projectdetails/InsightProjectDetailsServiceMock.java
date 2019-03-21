package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.projectdetails;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.ProjectDetailsDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"ci", "default"})
public class InsightProjectDetailsServiceMock implements InsightProjectDetailService {
    @Override
    public ProjectDetailsDto getDetails(String projectCode) {
        return null;
    }
}
