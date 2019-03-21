package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Map;

@JsonIgnoreProperties
@Entity
public class SkillDto {
    @Id
    @GeneratedValue
    private Integer id;

    private String projectCode;

    private String name;

    private Boolean isManagedSkill;

    public SkillDto() {
    }

    public SkillDto(String projectCode, String name) {
        this.projectCode = projectCode;
        this.name = name;
    }

    @JsonProperty("Skill")
    private void unpackSkillNameFromNestedObject(JsonNode skill) {
        name = skill.get("Name").asText();
        isManagedSkill = skill.get("IsManagedSkill").asBoolean();
    }

    public Integer getId() {
        return id;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getManagedSkill() {
        return isManagedSkill;
    }

    public void setManagedSkill(Boolean managedSkill) {
        isManagedSkill = managedSkill;
    }
}
