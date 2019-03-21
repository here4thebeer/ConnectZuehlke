package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.skill;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.SkillDto;

import java.util.List;

public interface InsightSkillService {

    List<SkillDto> getForProject(String projectCode);
}
