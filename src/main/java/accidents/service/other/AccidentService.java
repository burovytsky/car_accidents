package accidents.service.other;

import accidents.model.Accident;
import accidents.model.AccidentType;
import accidents.model.Rule;
import accidents.repository.other.AccidentMem;
import accidents.repository.other.RuleMem;
import accidents.repository.other.TypeMem;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

//@Service
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

    public void createAccident(Accident accident, String[] ids) {
        accident.setRuleSet(getSelectedRules(ids));
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

    public Set<Rule> getSelectedRules(String[] ids) {
        Set<Rule> rules = new HashSet<>();
        for (String id : ids) {
            rules.add(ruleMem.findById(Integer.parseInt(id)));
        }
        return rules;
    }
}
