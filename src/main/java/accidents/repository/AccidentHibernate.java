package accidents.repository;

import accidents.model.Rule;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import accidents.model.Accident;

import java.util.List;

@Repository
public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }


    public Accident create(Accident accident) {
        if (findById(accident.getId()) == null) {
            return createNew(accident);
        } else {
            return update(accident);
        }
    }

    private Accident createNew(Accident accident) {
        try (Session session = sf.openSession()) {
            session.getTransaction().begin();
            session.save(accident);
            session.getTransaction().commit();
            return accident;
        }
    }

    public Accident update(Accident accident) {
        try (Session session = sf.openSession()) {
            session.getTransaction().begin();
            session.update(accident);
            session.getTransaction().commit();
            return accident;
        }
    }

    public List<Accident> getAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Accident s  order by s.id", Accident.class)
                    .list();
        }
    }

    public Accident findById(int id) {
        try (Session session = sf.openSession()) {
            return session.get(Accident.class, id);
        }
    }
}
