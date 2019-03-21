package ch.zuehlke.fullstack.ConnectZuehlke.service.employee;

import ch.zuehlke.fullstack.ConnectZuehlke.domain.Employee;

import java.io.IOException;
import java.util.List;

public interface EmployeeService {

  List<Employee> getEmployees();

  byte[] getEmployeePicture(int id) throws IOException;

  Employee getEmployee(String code);
}
