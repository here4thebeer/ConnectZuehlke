package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto;

import ch.zuehlke.fullstack.ConnectZuehlke.domain.Project;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class ProjectDto {

    public Project toProject(ProjectDto projectDto) {
        return new Project();
    }
}
