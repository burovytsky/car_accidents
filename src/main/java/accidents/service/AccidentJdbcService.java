package accidents.service;

import accidents.model.Accident;
import accidents.model.AccidentType;
import accidents.model.Rule;
import accidents.repository.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class AccidentJdbcService {
    private final AccidentJdbcTemplate accidentJdbcTemplate;
    private final TypeJdbcTemplate typeJdbcTemplate;
    private final RuleJdbcTemplate ruleJdbcTemplate;

    public AccidentJdbcService(JdbcTemplate jdbc) {
        accidentJdbcTemplate = new AccidentJdbcTemplate(jdbc);
        typeJdbcTemplate = new TypeJdbcTemplate(jdbc);
        ruleJdbcTemplate = new RuleJdbcTemplate(jdbc);
    }

    public Collection<Accident> getAccidents() {
        return accidentJdbcTemplate.getAll();
    }

    public void createAccident(Accident accident, String[] ids) {
        Set<Rule> ruleSet = new HashSet<>();
        for (String id : ids) {
            ruleSet.add(ruleJdbcTemplate.findById(Integer.parseInt(id)));
        }
        accident.setRuleSet(ruleSet);
        accidentJdbcTemplate.create(accident);
    }

    public Accident findAccidentById(int id) {
        return accidentJdbcTemplate.findById(id);
    }

    public Collection<AccidentType> getAccidentTypes() {
        return typeJdbcTemplate.getAll();
    }

    public Collection<Rule> getRules() {
        return ruleJdbcTemplate.getAll();
    }

}
