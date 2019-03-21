package ch.zuehlke.fullstack.ConnectZuehlke.persistence;

import ch.zuehlke.fullstack.ConnectZuehlke.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Project> getAll() {
        String query = "SELECT * FROM PROJECT_DTO p JOIN CUSTOMER_DTO c ON p.CUSTOMER_ID = c.ID";
        List<Project> projects = jdbcTemplate.query(query, new ProjectMapper());

        projects.forEach(project -> {
            Long employeeCount = getEmployeeCount(project.getProjectCode());
            List<String> skills = getSkillsForProject(project.getProjectCode());
            project.setAmountOfEmployees(employeeCount.intValue());
            project.setSkills(skills);
        });
        return projects;
    }

    public boolean hasRows() {
        String query = "SELECT count(*) FROM PROJECT_DTO";
        Long count = jdbcTemplate.query(query, new SingleColumnRowMapper<Long>()).get(0);
        return count > 0;
    }

    private List<String> getSkillsForProject(String projectCode) {
        String query = "SELECT name FROM SKILL_DTO WHERE project_code = '" + projectCode + "'" ;
        return jdbcTemplate.query(query, new SingleColumnRowMapper<>());
    }

    public long getEmployeeCount(String projectCode) {
        String query = "SELECT count(*) FROM EMPLOYEE_DTO e WHERE e.project_code = '" + projectCode +"'";
        return jdbcTemplate.query(query, new SingleColumnRowMapper<Long>()).get(0);
    }
}
