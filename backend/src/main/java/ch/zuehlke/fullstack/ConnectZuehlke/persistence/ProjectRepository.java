package ch.zuehlke.fullstack.ConnectZuehlke.persistence;

import ch.zuehlke.fullstack.ConnectZuehlke.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Project> getAll() {
        String query = "SELECT * FROM PROJECT_DTO p JOIN CUSTOMER_DTO c ON p.CUSTOMER_ID = c.ID";
        return jdbcTemplate.query(query, new ProjectMapper());
    }
}
