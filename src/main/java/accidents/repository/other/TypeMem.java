package accidents.repository.other;

import accidents.model.AccidentType;

import java.util.*;

public class TypeMem implements Store<AccidentType> {
    private final Map<Integer, AccidentType> accidentTypes = new HashMap<>();

    public TypeMem() {
        accidentTypes.put(1, AccidentType.of(1, "Two cars"));
        accidentTypes.put(2, AccidentType.of(2, "Car and Man"));
        accidentTypes.put(3, AccidentType.of(3, "Car and bike"));
    }

    @Override
    public Collection<AccidentType> getAll() {
        return accidentTypes.values();
    }

    @Override
    public void create(AccidentType accidentType) {
        accidentTypes.put(accidentType.getId(), accidentType);
    }

    @Override
    public AccidentType findById(int id) {
        return accidentTypes.get(id);
    }
}
