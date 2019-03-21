package ch.zuehlke.fullstack.ConnectZuehlke.service.project;


import ch.zuehlke.fullstack.ConnectZuehlke.domain.Project;
import ch.zuehlke.fullstack.ConnectZuehlke.persistence.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile({"prod", "staging"})
public class ProjectServiceRemote implements ProjectService {


    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceRemote(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> getProjects() {
        return projectRepository.getAll();
    }


}