package accidents.repository;

import accidents.model.Accident;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private static final AtomicInteger ACCIDENT_ID = new AtomicInteger(3);


    public AccidentMem() {
        Accident accident1 = new Accident(0, "Accident1", "Description1", "some street");
        Accident accident2 = new Accident(1, "Accident2", "Description2", "some street");
        Accident accident3 = new Accident(2, "Accident3", "Description3", "some street");
        accidents.put(accident1.getId(), accident1);
        accidents.put(accident2.getId(), accident2);
        accidents.put(accident3.getId(), accident3);
    }

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public void createAccident(Accident accident) {
        accident.setId(ACCIDENT_ID.incrementAndGet());
        accidents.put(accident.getId(), accident);
    }
}