package ch.zuehlke.fullstack.ConnectZuehlke.persistence;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.EmployeeDto;
import org.springframework.data.repository.CrudRepository;

public interface InsightEmployeeRepository extends CrudRepository<EmployeeDto, Integer> {

}
