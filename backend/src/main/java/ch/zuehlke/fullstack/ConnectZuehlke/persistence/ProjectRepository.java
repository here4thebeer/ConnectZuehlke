package ch.zuehlke.fullstack.ConnectZuehlke.persistence;

import ch.zuehlke.fullstack.ConnectZuehlke.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProjectRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Project> getAll() {
        String query = "SELECT * FROM PROJECT_DTO p JOIN CUSTOMER_DTO c ON p.CUSTOMER_ID = c.ID";
        return jdbcTemplate.query(query, new ProjectMapper())
                .stream()
                .peek(project -> project.setAmountOfEmployees(getEmployeeCount(project.getProjectCode()).intValue()))
                .filter(project -> project.getAmountOfEmployees() > 0)
                .peek(project -> project.setSkills(getSkillsForProject(project.getProjectCode())))
                .collect(Collectors.toList());
    }

    public boolean hasRows() {
        String query = "SELECT count(*) FROM PROJECT_DTO";
        Long count = jdbcTemplate.query(query, new SingleColumnRowMapper<Long>()).get(0);
        return count > 0;
    }

    private List<String> getSkillsForProject(String projectCode) {
        String query = "SELECT name FROM SKILL_DTO WHERE project_code = '" + projectCode + "'";
        return jdbcTemplate.query(query, new SingleColumnRowMapper<>());
    }

    public Long getEmployeeCount(String projectCode) {
        String query = "SELECT count(*) FROM EMPLOYEE_DTO e WHERE e.project_code = '" + projectCode + "'";
        return jdbcTemplate.query(query, new SingleColumnRowMapper<Long>()).get(0);
    }
}
