package ch.zuehlke.fullstack.ConnectZuehlke.service;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.CustomerDto;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.ProjectDto;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.customer.InsightCustomerService;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.project.InsightProjectService;
import ch.zuehlke.fullstack.ConnectZuehlke.persistence.CustomerRepository;
import ch.zuehlke.fullstack.ConnectZuehlke.persistence.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrawlerService {
    private static final Logger log = LoggerFactory.getLogger(CrawlerService.class);

    private final ProjectRepository projectRepository;
    private final InsightProjectService insightProjectService;
    private final CustomerRepository customerRepository;
    private final InsightCustomerService insightCustomerService;

    public CrawlerService(ProjectRepository projectRepository, InsightProjectService insightProjectService, CustomerRepository customerRepository, InsightCustomerService insightCustomerService) {
        this.projectRepository = projectRepository;
        this.insightProjectService = insightProjectService;
        this.customerRepository = customerRepository;
        this.insightCustomerService = insightCustomerService;
    }

    public void crawlInsight() {
        List<ProjectDto> projects = insightProjectService.getProjectDtos();
        log.info("Loaded {} projects", projects.size());
        projectRepository.saveAll(projects);

        List<CustomerDto> customers = insightCustomerService.getCustomers();
        log.info("Loaded {} customers", customers.size());
        customerRepository.saveAll(customers);


    }
}
