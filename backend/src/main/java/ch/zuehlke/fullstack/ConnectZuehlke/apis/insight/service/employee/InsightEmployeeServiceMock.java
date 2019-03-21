package ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.service.employee;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.EmployeeDto;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

@Service
@Profile({"ci", "default"})
public class InsightEmployeeServiceMock implements InsightEmployeeService {

    private static final Map<String, List<EmployeeDto>> EMPLOYEES = new HashMap<>();


    static {

        EMPLOYEES.put("C20098", asList(new EmployeeDto("Genc", "Mazlami", 1, "genm", "C20098")));
        EMPLOYEES.put("C22520", asList(new EmployeeDto("Yannick", "Streit", 1, "yast", "C22520")));
        EMPLOYEES.put("C22669", asList(new EmployeeDto("Don", "Kodiyan", 1, "doko", "C22669")));
        EMPLOYEES.put("C23719", asList(new EmployeeDto("Sebastian", "Müller", 1, "semu", "C23719")));
        EMPLOYEES.put("C20274", asList(new EmployeeDto("Fabian", "Keller", 1, "fabk", "C20274")));
    }

    @Override
    public List<EmployeeDto> getForProject(String projectCode) {
        List<EmployeeDto> employeeDtos = EMPLOYEES.get(projectCode);
        if (employeeDtos == null) {
            return Lists.newArrayList();
        }
        return employeeDtos;
    }
}
