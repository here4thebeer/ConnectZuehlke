package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.skill;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.SkillDto;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

@Service
@Profile({"ci", "default"})
public class InsightSkillServiceMock implements InsightSkillService {

    private static final Map<String, List<SkillDto>> SKILLS = new HashMap<>();


    static {
        SKILLS.put("C20098", asList(new SkillDto("C20098", "Angular")));
        SKILLS.put("C22669", asList(new SkillDto("C22669", "Java")));
        SKILLS.put("C22520", asList(new SkillDto("C22520", "Angular")));
        SKILLS.put("C23719", asList(new SkillDto("C23719", ".NET")));
        SKILLS.put("C20274", asList(new SkillDto("C20274", "Python")));
    }

    @Override
    public List<SkillDto> getForProject(String projectCode) {
        List<SkillDto> skillDtos = SKILLS.get(projectCode);
        if (skillDtos == null) {
            return Lists.newArrayList();
        }
        return skillDtos;
    }
}
