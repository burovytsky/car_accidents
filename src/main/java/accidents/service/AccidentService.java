package accidents.service;

import accidents.model.Accident;
import accidents.model.AccidentType;
import accidents.model.Rule;
import accidents.repository.AccidentMem;
import accidents.repository.RuleMem;
import accidents.repository.TypeMem;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AccidentService {
    private final AccidentMem accidentMem;
    private final TypeMem typeMem;
    private final RuleMem ruleMem;

    public AccidentService() {
        accidentMem = new AccidentMem();
        typeMem = new TypeMem();
        ruleMem = new RuleMem();
    }

    public Collection<Accident> getAccidents() {
        return accidentMem.getAll();
    }

    public void createAccident(Accident accident) {
        accidentMem.create(accident);
    }

    public Accident findAccidentById(int id) {
        return accidentMem.findById(id);
    }

    public Collection<AccidentType> getAccidentTypes() {
        return typeMem.getAll();
    }

    public AccidentType findTypeById(int id) {
        return typeMem.findById(id);
    }

    public void createAccidentType(AccidentType accidentType) {
        typeMem.create(accidentType);
    }

    public Collection<Rule> getRules() {
        return ruleMem.getAll();
    }

    public void createRule(Rule rule) {
        ruleMem.create(rule);
    }

    public Rule findRuleById(int id) {
        return ruleMem.findById(id);
    }
}
