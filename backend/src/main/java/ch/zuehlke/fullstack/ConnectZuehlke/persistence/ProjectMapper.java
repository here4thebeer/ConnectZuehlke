package ch.zuehlke.fullstack.ConnectZuehlke.persistence;

import ch.zuehlke.fullstack.ConnectZuehlke.domain.Location;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Project;
import com.google.common.collect.Lists;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ProjectMapper implements RowMapper<Project> {
    @Override
    public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
        String projectCode = rs.getString("CODE");
        Integer customerId = rs.getInt("CUSTOMER_ID");
        return new Project(
                rs.getString("TITLE"),
                new Location(
                        rs.getString("STREET"),
                        rs.getString("CITY"),
                        rs.getInt("ZIP"),
                        rs.getString("COUNTRY"),
                        rs.getFloat("LONGITUDE"),
                        rs.getFloat("LATITUDE")
                ),
                null,
                projectCode,
                null,
                Lists.newArrayList(),
                createPictureUrl(projectCode),
                createLogoUrl(customerId),
                createProjectUrl(projectCode),
                0,
                false,
                null
        );
    }

    private String createLogoUrl(int customerId) {
        return "/api/v1/customers/"+customerId+"/logo";
    }

    private String createPictureUrl(String projectCode) {
        return "/api/v1/projects/" + projectCode + "/picture";
    }

    private String createProjectUrl(String projectCode) {
        return "/projects/" + projectCode;
    }
}
