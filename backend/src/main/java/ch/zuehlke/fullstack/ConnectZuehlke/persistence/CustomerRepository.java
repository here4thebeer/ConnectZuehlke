package ch.zuehlke.fullstack.ConnectZuehlke.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Service;

@Service
public class CustomerRepository {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean hasRows() {
        String query = "SELECT count(*) FROM CUSTOMER_DTO";
        Long count = jdbcTemplate.query(query, new SingleColumnRowMapper<Long>()).get(0);
        return count > 0;
    }
}
