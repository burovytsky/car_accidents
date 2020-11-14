package accidents.model;


import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "accidents")
public class Accident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String address;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private AccidentType type;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "accident_rules", joinColumns = {
    @JoinColumn(name = "accident_id")},
            inverseJoinColumns = {@JoinColumn(name = "rule_id")})
    private Set<Rule> ruleSet;

    public Accident() {
    }

    public Accident(int id, String name, String description, String address, AccidentType type, Set<Rule> ruleSet) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.type = type;
        this.ruleSet = ruleSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AccidentType getType() {
        return type;
    }

    public void setType(AccidentType type) {
        this.type = type;
    }

    public Set<Rule> getRuleSet() {
        return ruleSet;
    }

    public void setRuleSet(Set<Rule> ruleSet) {
        this.ruleSet = ruleSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accident accident = (Accident) o;
        return id == accident.id
                && Objects.equals(name, accident.name)
                && Objects.equals(description, accident.description)
                && Objects.equals(address, accident.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, address);
    }
}
