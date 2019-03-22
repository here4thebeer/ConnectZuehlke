package ch.zuehlke.fullstack.ConnectZuehlke.service;

import ch.zuehlke.fullstack.ConnectZuehlke.domain.Project;
import ch.zuehlke.fullstack.ConnectZuehlke.persistence.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@EnableScheduling
public class NotificationSimulator {
    private static final Logger log = LoggerFactory.getLogger(NotificationSimulator.class);
    private final SimpMessagingTemplate template;
    private final ProjectRepository projectRepository;

    @Autowired
    public NotificationSimulator(SimpMessagingTemplate template, ProjectRepository projectRepository) {
        this.template = template;
        this.projectRepository = projectRepository;
    }

    @Scheduled(fixedDelay = 10000)
    public void getNotification() {
        List<Project> all = projectRepository.getAll();
        Project project = all.get(new Random().nextInt(all.size()));
        template.convertAndSend("/topic/notification", project);

        log.info("New updated pushed for project: {}", project);
    }
}
