package ch.zuehlke.fullstack.ConnectZuehlke.persistence;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.ProjectDetailsDto;
import org.springframework.data.repository.CrudRepository;

public interface InsightProjectDetailsRepository extends CrudRepository<ProjectDetailsDto, String> {

}
