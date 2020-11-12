package accidents.repository;

import accidents.model.AccidentType;
import accidents.model.Rule;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import accidents.model.Accident;

import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Accident> getAll() {
        return jdbc.query("select * from accidents order by id",
                (resultSet, row) -> {
                    Accident accident = new Accident();
                    accident.setId(resultSet.getInt("id"));
                    accident.setName(resultSet.getString("name"));
                    accident.setText(resultSet.getString("description"));
                    accident.setAddress(resultSet.getString("address"));
                    accident.setType(getAccidentType(resultSet.getInt("type_id")));
                    accident.setRuleSet(getSelectedRules(resultSet.getInt("id")));
                    return accident;
                });
    }

    public void create(Accident accident) {
        if ((findById(accident.getId()) == null)) {
            createNewAccident(accident);
        } else {
            updateAccident(accident);
        }
    }

    private void updateAccident(Accident accident) {
        String updateAccident = ("UPDATE accidents SET name = (?), description= (?), address = (?), type_id = (?) where id = (?)");
        jdbc.update(updateAccident,
                accident.getName(), accident.getText(), accident.getAddress(), accident.getType().getId(), accident.getId());
        jdbc.update("DELETE from accident_rules  where accident_id = (?)", accident.getId());
        String addAccidentRule = "INSERT INTO accident_rules (accident_id, rule_id) VALUES (?, ?)";
        for (Rule rule : accident.getRuleSet()) {
            jdbc.update(addAccidentRule, accident.getId(), rule.getId());
        }
    }

    private void createNewAccident(Accident accident) {
        String createAccident = "INSERT INTO accidents ( name, description, address, type_id) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(createAccident, new String[]{"id"});
                    ps.setString(1, accident.getName());
                    ps.setString(2, accident.getText());
                    ps.setString(3, accident.getAddress());
                    ps.setInt(4, accident.getType().getId());
                    return ps;
                },
                keyHolder);
        accident.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        String addAccidentRule = "INSERT INTO accident_rules (accident_id, rule_id) VALUES (?, ?)";
        for (Rule rule : accident.getRuleSet()) {
            jdbc.update(addAccidentRule, accident.getId(), rule.getId());
        }
    }

    public Accident findById(int id) {
        try {
            return jdbc.queryForObject(
                    "select * from accidents where id = ?",
                    (resultSet, row) -> {
                        Accident newAccident = new Accident();
                        newAccident.setId(resultSet.getInt("id"));
                        newAccident.setName(resultSet.getString("name"));
                        newAccident.setText(resultSet.getString("description"));
                        newAccident.setAddress(resultSet.getString("address"));
                        newAccident.setType(getAccidentType(resultSet.getInt("type_id")));
                        newAccident.setRuleSet(getSelectedRules(id));
                        return newAccident;
                    },
                    id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private AccidentType getAccidentType(int id) {
        return jdbc.queryForObject(
                "select * from types where id= ?",
                (resultSet, rowNum) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(resultSet.getInt("id"));
                    accidentType.setName(resultSet.getString("name"));
                    return accidentType;
                },
                id);
    }

    private Set<Rule> getSelectedRules(int id) {
        List<Rule> ruleList = jdbc.query(
                "select * from accident_rules where accident_id = ?",
                (resultSet, row) -> findRuleById(resultSet.getInt("rule_id")),
                id);
        return new HashSet<>(ruleList);
    }

    private Rule findRuleById(int id) {
        return jdbc.queryForObject(
                "select * from rules where id = ?",
                (resultSet, rowNum) -> {
                    Rule rule = new Rule();
                    rule.setId(resultSet.getInt("id"));
                    rule.setName(resultSet.getString("name"));
                    return rule;
                },
                id);
    }
}
