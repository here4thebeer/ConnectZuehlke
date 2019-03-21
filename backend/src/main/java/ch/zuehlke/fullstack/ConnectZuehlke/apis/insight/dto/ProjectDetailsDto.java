package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class ProjectDetailsDto {

    @JsonProperty("Code")
    @Id
    private String code;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Industry")
    private String industry;

    @JsonProperty("Company")
    private String company;

    @JsonProperty("PublicDescription")
    @Lob
    private String description;

    public ProjectDetailsDto() {
        // JPA constructor
    }

    public ProjectDetailsDto(String code, String name, String industry, String company, String description) {
        this.code = code;
        this.name = name;
        this.industry = industry;
        this.company = company;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProjectDetailsDto{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", industry='" + industry + '\'' +
                ", company='" + company + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
