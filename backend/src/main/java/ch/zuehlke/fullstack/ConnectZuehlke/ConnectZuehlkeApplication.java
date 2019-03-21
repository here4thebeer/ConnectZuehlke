package ch.zuehlke.fullstack.ConnectZuehlke;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.project.ProjectService;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@SpringBootApplication
@EnableSwagger2
@EnableCaching
public class ConnectZuehlkeApplication implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(ConnectZuehlkeApplication.class);

    private final ProjectService projectService;

    @Autowired
    public ConnectZuehlkeApplication(ProjectService projectService) {
        this.projectService = projectService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConnectZuehlkeApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        List<Project> projects = projectService.getProjects();
        log.info("Loaded {} projects", projects.size());
    }
}

