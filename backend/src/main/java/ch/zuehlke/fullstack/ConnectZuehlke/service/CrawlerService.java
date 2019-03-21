package ch.zuehlke.fullstack.ConnectZuehlke.service;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.CustomerDto;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.ProjectDto;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.customer.InsightCustomerService;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.employee.InsightEmployeeService;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.project.InsightProjectService;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.skill.InsightSkillService;
import ch.zuehlke.fullstack.ConnectZuehlke.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.Objects;

@Service
public class CrawlerService {
    private static final Logger log = LoggerFactory.getLogger(CrawlerService.class);

    private final InsightProjectRepository insightProjectRepository;
    private final InsightProjectService insightProjectService;
    private final InsightCustomerRepository insightCustomerRepository;
    private final InsightCustomerService insightCustomerService;
    private final InsightEmployeeRepository insightEmployeeRepository;
    private final InsightEmployeeService insightEmployeeService;
    private final InsightSkillService insightSkillService;
    private final InsightSkillRepository insightSkillRepository;

    private final ProjectRepository projectRepository;
    private final CustomerRepository customerRepository;

    public CrawlerService(InsightProjectRepository insightProjectRepository, InsightProjectService insightProjectService, InsightCustomerRepository insightCustomerRepository, InsightCustomerService insightCustomerService, InsightEmployeeRepository insightEmployeeRepository, InsightEmployeeService insightEmployeeService, InsightSkillService insightSkillService, InsightSkillRepository insightSkillRepository, ProjectRepository projectRepository, CustomerRepository customerRepository) {
        this.insightProjectRepository = insightProjectRepository;
        this.insightProjectService = insightProjectService;
        this.insightCustomerRepository = insightCustomerRepository;
        this.insightCustomerService = insightCustomerService;
        this.insightEmployeeRepository = insightEmployeeRepository;
        this.insightEmployeeService = insightEmployeeService;
        this.insightSkillService = insightSkillService;
        this.insightSkillRepository = insightSkillRepository;
        this.projectRepository = projectRepository;
        this.customerRepository = customerRepository;
    }

    public void crawlInsight() {

        if (!projectRepository.hasRows()) {
            List<ProjectDto> projects = insightProjectService.getProjectDtos();
            log.info("Loaded {} projects", projects.size());
            insightProjectRepository.saveAll(projects);

            Flux.fromStream(projects.stream())
                    .parallel()
                    .runOn(Schedulers.parallel())
                    .map(ProjectDto::getCode)
                    .map(insightEmployeeService::getForProject)
                    .filter(Objects::nonNull)
                    .filter(employeeDtos -> !employeeDtos.isEmpty())
                    .subscribe(insightEmployeeRepository::saveAll);

            Flux.fromStream(projects.stream())
                    .parallel()
                    .runOn(Schedulers.parallel())
                    .map(ProjectDto::getCode)
                    .map(insightSkillService::getForProject)
                    .filter(Objects::nonNull)
                    .filter(skillDtos -> !skillDtos.isEmpty())
                    .subscribe(insightSkillRepository::saveAll);

            Flux.fromStream(projects.stream())
                    .parallel()
                    .runOn(Schedulers.parallel())
                    .map(ProjectDto::getCustomerId)
                    .map(insightCustomerService::getForId)
                    .filter(Objects::nonNull)
                    .subscribe(insightCustomerRepository::save);

        }

    }
}
