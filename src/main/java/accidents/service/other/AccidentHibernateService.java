package accidents.service.other;

import accidents.model.Accident;
import accidents.model.AccidentType;
import accidents.model.Rule;
import accidents.repository.other.AccidentHibernate;
import accidents.repository.other.RuleHibernate;
import accidents.repository.other.TypeHibernate;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class AccidentHibernateService {
    private final AccidentHibernate accidentHibernate;
    private final TypeHibernate typeHibernate;
    private final RuleHibernate ruleHibernate;

    public AccidentHibernateService(SessionFactory sf) {
        this.accidentHibernate = new AccidentHibernate(sf);
        this.typeHibernate = new TypeHibernate(sf);
        this.ruleHibernate = new RuleHibernate(sf);
    }

    public Collection<Accident> getAccidents() {
        return accidentHibernate.getAll();
    }

    public void createAccident(Accident accident, String[] ids) {
        Set<Rule> ruleSet = new HashSet<>();
        for (String id : ids) {
            ruleSet.add(ruleHibernate.findById(Integer.parseInt(id)));
        }
        accident.setRuleSet(ruleSet);
        accidentHibernate.create(accident);
    }

    public Accident findAccidentById(int id) {
        return accidentHibernate.findById(id);
    }

    public Collection<AccidentType> getAccidentTypes() {
        return typeHibernate.getAll();
    }

    public Collection<Rule> getRules() {
        return ruleHibernate.getAll();
    }

}
