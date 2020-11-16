package accidents.repository.other;

import accidents.model.Rule;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.HashSet;

import java.util.Set;

public class RuleHibernate {
    private final SessionFactory sf;

    public RuleHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Rule create(Rule rule) {
        try (Session session = sf.openSession()) {
            session.getTransaction().begin();
            session.save(rule);
            session.getTransaction().commit();
            return rule;
        }
    }

    public Set<Rule> getAll() {
        try (Session session = sf.openSession()) {
            return new HashSet<>(session
                    .createQuery("from Rule", Rule.class)
                    .list());
        }
    }

    public Rule findById(int id) {
        try (Session session = sf.openSession()) {
            return session.get(Rule.class, id);
        }
    }

}
