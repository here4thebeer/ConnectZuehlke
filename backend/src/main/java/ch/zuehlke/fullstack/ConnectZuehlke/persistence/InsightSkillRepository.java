package ch.zuehlke.fullstack.ConnectZuehlke.persistence;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.SkillDto;
import org.springframework.data.repository.CrudRepository;

public interface InsightSkillRepository extends CrudRepository<SkillDto, Integer> {

}
