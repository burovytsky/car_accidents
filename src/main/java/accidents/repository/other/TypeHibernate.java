package accidents.repository.other;

import accidents.model.AccidentType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class TypeHibernate {

    private final SessionFactory sf;

    public TypeHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public AccidentType create(AccidentType accidentType) {
        try (Session session = sf.openSession()) {
            session.getTransaction().begin();
            session.save(accidentType);
            session.getTransaction().commit();
            return accidentType;
        }
    }

    public List<AccidentType> getAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from AccidentType", AccidentType.class)
                    .list();
        }
    }
}
