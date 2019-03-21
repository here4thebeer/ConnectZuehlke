package ch.zuehlke.fullstack.ConnectZuehlke.service;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.CustomerDto;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.ProjectDto;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.customer.InsightCustomerService;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.project.InsightProjectService;
import ch.zuehlke.fullstack.ConnectZuehlke.persistence.InsightCustomerRepository;
import ch.zuehlke.fullstack.ConnectZuehlke.persistence.InsightProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrawlerService {
    private static final Logger log = LoggerFactory.getLogger(CrawlerService.class);

    private final InsightProjectRepository insightProjectRepository;
    private final InsightProjectService insightProjectService;
    private final InsightCustomerRepository insightCustomerRepository;
    private final InsightCustomerService insightCustomerService;

    public CrawlerService(InsightProjectRepository insightProjectRepository, InsightProjectService insightProjectService, InsightCustomerRepository insightCustomerRepository, InsightCustomerService insightCustomerService) {
        this.insightProjectRepository = insightProjectRepository;
        this.insightProjectService = insightProjectService;
        this.insightCustomerRepository = insightCustomerRepository;
        this.insightCustomerService = insightCustomerService;
    }

    public void crawlInsight() {
        List<ProjectDto> projects = insightProjectService.getProjectDtos();
        log.info("Loaded {} projects", projects.size());
        insightProjectRepository.saveAll(projects);

        List<CustomerDto> customers = insightCustomerService.getCustomers();
        log.info("Loaded {} customers", customers.size());
        insightCustomerRepository.saveAll(customers);


    }
}
