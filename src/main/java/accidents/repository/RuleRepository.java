package accidents.repository;

import accidents.model.Rule;
import org.springframework.data.repository.CrudRepository;

public interface RuleRepository extends CrudRepository<Rule, Integer> {
}
