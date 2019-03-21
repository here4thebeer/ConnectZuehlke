package ch.zuehlke.fullstack.ConnectZuehlke.service;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.CustomerDto;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.EmployeeDto;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.ProjectDto;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.customer.InsightCustomerService;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.employee.InsightEmployeeService;
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

    private final InsightEmployeeService insightEmployeeService;

    public CrawlerService(ProjectRepository projectRepository, InsightProjectService insightProjectService, CustomerRepository customerRepository, InsightCustomerService insightCustomerService, InsightEmployeeService insightEmployeeService) {
        this.projectRepository = projectRepository;
        this.insightProjectService = insightProjectService;
        this.customerRepository = customerRepository;
        this.insightCustomerService = insightCustomerService;
        this.insightEmployeeService = insightEmployeeService;
    }

    public void crawlInsight() {
        List<ProjectDto> projects = insightProjectService.getProjectDtos();
        log.info("Loaded {} projects", projects.size());
        projectRepository.saveAll(projects);

        List<CustomerDto> customers = insightCustomerService.getCustomers();
        log.info("Loaded {} customers", customers.size());
        customerRepository.saveAll(customers);

        for (ProjectDto project : projects) {
            List<EmployeeDto> employees = insightEmployeeService.getForProject(project.getCode());
            if (employees.isEmpty()) {
                continue;
            }
            //TODO: persist employees with foreign key to project
            System.out.println(employees);

        }




    }
}
