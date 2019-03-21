package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto;

import ch.zuehlke.fullstack.ConnectZuehlke.domain.Employee;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

@JsonIgnoreProperties
@Entity
public class EmployeeDto {

    @JsonProperty("FirstName")
    private String firstName;

    @JsonProperty("LastName")
    private String lastName;

    @JsonProperty("Id")
    private int id;

    @Id
    @JsonProperty("Code")
    private String code;

    private String projectCode;

    public EmployeeDto() {
        // JPA constructor
    }

    public EmployeeDto(String firstName, String lastName, int id, String code, String projectCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.code = code;
        this.projectCode = projectCode;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getId() {
        return id;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public Employee toEmployee() {
        return new Employee(getFirstName(), getLastName(), getId(), getCode());
    }

    private String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
