package accidents.repository;

import accidents.model.AccidentType;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collection;

public class TypeJdbcTemplate {

    private final JdbcTemplate jdbc;

    public TypeJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Collection<AccidentType> getAll() {
        return jdbc.query("select id, name from types",
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                });
    }
}
