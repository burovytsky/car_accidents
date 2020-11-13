package accidents.service;

import accidents.model.Accident;
import accidents.model.AccidentType;
import accidents.model.Rule;
import accidents.repository.AccidentRepository;
import accidents.repository.RuleRepository;
import accidents.repository.TypeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class AccidentCrudService {
    private final AccidentRepository accidentRepository;
    private final TypeRepository typeRepository;
    private final RuleRepository ruleRepository;

    public AccidentCrudService(AccidentRepository accidentRepository, TypeRepository typeRepository, RuleRepository ruleRepository) {
        this.accidentRepository = accidentRepository;
        this.typeRepository = typeRepository;
        this.ruleRepository = ruleRepository;
    }

    public void createAccident(Accident accident, String[] ids) {
        Set<Rule> ruleSet = new HashSet<>();
        for (String id : ids) {
            ruleSet.add(ruleRepository.findById(Integer.parseInt(id)).get());
        }
        accident.setRuleSet(ruleSet);
        accidentRepository.save(accident);
    }

    public Accident findAccidentById(int id) {
        return accidentRepository.findById(id).get();
    }

    public Collection<Accident> getAccidents() {
        return accidentRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Collection<AccidentType> getTypes() {
        return (Collection<AccidentType>) typeRepository.findAll();
    }

    public Collection<Rule> getRules() {
        return (Collection<Rule>) ruleRepository.findAll();
    }

}
