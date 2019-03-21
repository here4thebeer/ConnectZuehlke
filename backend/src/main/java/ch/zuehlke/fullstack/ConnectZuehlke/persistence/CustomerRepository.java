package ch.zuehlke.fullstack.ConnectZuehlke.persistence;

import ch.zuehlke.fullstack.ConnectZuehlke.apis.insight.dto.CustomerDto;
import ch.zuehlke.fullstack.ConnectZuehlke.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerDto, Integer> {

}
