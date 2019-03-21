package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectEmployeeDto {

    @JsonProperty("Employee")
    private EmployeeDto employeeDto;

    public EmployeeDto getEmployeeDto() {
        return employeeDto;
    }

    public void setEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
    }

    @Override
    public String toString() {
        return "ProjectEmployeeDto{" +
                "employeeDto=" + employeeDto +
                '}';
    }
}
