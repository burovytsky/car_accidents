package accidents.repository;

import accidents.model.Accident;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {
    Collection<Accident> findAll(Sort id);
}
