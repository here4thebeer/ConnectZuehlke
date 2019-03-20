package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.project;

import ch.zuehlke.fullstack.ConnectZuehlke.domain.Location;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Project;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Profile({"ci", "default"})
public class ProjectServiceMocked implements ProjectService {

    @Override
    public List<Project> getProjects() {
        Location swisscomLocation = new Location("Förrlibruckstrasse 181", "Zürich", 8005, "Schweiz", 47.391702f, 8.511068f);
        Project swisscom = new Project("SAM TO", swisscomLocation);

        Location sixLocation = new Location("Hardturmstrasse 201", "Zürich", 8005, "Schweiz", 47.393659f, 8.509338f);
        Project six = new Project("Starlight", sixLocation);

        Location sbbLocation = new Location("Lindenhofstrasse 1", "Worblaufen", 3048, "Schweiz", 46.980497f, 7.459547f);
        Project sbb = new Project("Sanierung Finanzen", sbbLocation);

        return Arrays.asList(swisscom, six, sbb);
    }

}