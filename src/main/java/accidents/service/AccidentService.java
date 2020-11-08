package accidents.service;

import accidents.model.Accident;
import accidents.repository.AccidentMem;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AccidentService {
    private final AccidentMem accidentMem;

    public AccidentService() {
        accidentMem = new AccidentMem();
    }

    public Collection<Accident> getAccidents() {
        return accidentMem.getAccidents();
    }

    public void createAccident(Accident accident) {
        accidentMem.createAccident(accident);
    }
}
