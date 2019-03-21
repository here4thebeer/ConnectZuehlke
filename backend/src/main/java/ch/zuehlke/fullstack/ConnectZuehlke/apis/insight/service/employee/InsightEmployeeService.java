package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.employee;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.EmployeeDto;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Employee;

import java.util.List;

public interface InsightEmployeeService {

    List<EmployeeDto> getForProject(String projectCode);
}
