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
                rs.getString("CODE"),
                null,
                Lists.newArrayList(),
                null,
                null,
                null,
                0,
                false,
                null
        );
    }
}
