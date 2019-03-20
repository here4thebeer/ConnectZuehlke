package ch.zuehlke.fullstack.ConnectZuehlke.rest;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.project.ProjectService;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Location;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("default")
@WebMvcTest(value = ProjectRestController.class)
public class ProjectRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    @Test
    public void getProjects() throws Exception {
        when(projectService.getProjects()).thenReturn(getTestProjects());

        mockMvc.perform(get("/api/projects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("SAM TO"))
                .andExpect(jsonPath("$[0].location.street").value("Förrlibruckstrasse 181"))
                .andExpect(jsonPath("$[1].title").value("Starlight"))
                .andExpect(jsonPath("$[1].location.street").value("Hardturmstrasse 201"));
    }

    @Test
    public void getEmptyProjects() throws Exception {
        when(projectService.getProjects()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/projects"))
                .andExpect(content().json("[]"));

    }

    private List<Project> getTestProjects() {
        return new ArrayList<>(Arrays.asList(
                new Project("SAM TO", new Location("Förrlibruckstrasse 181", "Zürich", 8005, "Schweiz", 8.511068f, 47.391702f)),
                new Project("Starlight", new Location("Hardturmstrasse 201", "Zürich", 8005, "Schweiz", 8.509338f, 47.393659f))
        ));
    }
}

