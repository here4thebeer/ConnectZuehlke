package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.employee.EmployeeService;
import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.employee.EmployeeServiceMocked;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("default")
public class DefaultEmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testDefaultProfileReturnsMockedInsightEmployeeService() {
        assertTrue(employeeService instanceof EmployeeServiceMocked);
    }
}