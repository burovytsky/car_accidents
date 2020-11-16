package accidents.repository.other;

import accidents.model.Rule;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collection;

public class RuleJdbcTemplate {
    private final JdbcTemplate jdbc;

    public RuleJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Collection<Rule> getAll() {
        return jdbc.query("select id, name from rules",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    public Rule findById(int id) {
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
