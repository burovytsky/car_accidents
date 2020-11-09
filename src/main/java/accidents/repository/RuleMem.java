package accidents.repository;

import accidents.model.Rule;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RuleMem implements Store<Rule> {
    private final Map<Integer, Rule> rules = new HashMap<>();

    public RuleMem() {
        rules.put(1, Rule.of(1, "Rule1"));
        rules.put(2, Rule.of(2, "Rule2"));
        rules.put(3, Rule.of(3, "Rule3"));
        rules.put(4, Rule.of(4, "Rule4"));
    }

    @Override
    public Collection<Rule> getAll() {
        return rules.values();
    }

    @Override
    public void create(Rule rule) {
        rules.put(rule.getId(), rule);
    }

    @Override
    public Rule findById(int id) {
        return rules.get(id);
    }
}
