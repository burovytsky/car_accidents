package accidents.repository;

import accidents.model.Accident;
import accidents.model.AccidentType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem implements Store<Accident> {
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private static final AtomicInteger ACCIDENT_ID = new AtomicInteger(3);


    public AccidentMem() {
        Accident accident1 = new Accident(1, "Accident1", "Description1",
                "some street", AccidentType.of(1, "Two cars"));
        Accident accident2 = new Accident(2, "Accident2", "Description2",
                "some street", AccidentType.of(2, "Car and Man"));
        Accident accident3 = new Accident(3, "Accident3", "Description3",
                "some street", AccidentType.of(2, "Car and Man"));
        accidents.put(accident1.getId(), accident1);
        accidents.put(accident2.getId(), accident2);
        accidents.put(accident3.getId(), accident3);
    }

    @Override
    public Collection<Accident> getAll() {
        return accidents.values();
    }

    @Override
    public void create(Accident accident) {
        if (accidents.containsKey(accident.getId())) {
            accidents.replace(accident.getId(), accident);
        } else {
            accident.setId(ACCIDENT_ID.incrementAndGet());
            accidents.put(accident.getId(), accident);
        }
    }

    @Override
    public Accident findById(int id) {
        return accidents.get(id);
    }
}