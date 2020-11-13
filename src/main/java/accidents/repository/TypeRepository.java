package accidents.repository;

import accidents.model.AccidentType;
import org.springframework.data.repository.CrudRepository;

public interface TypeRepository extends CrudRepository<AccidentType, Integer> {
}
