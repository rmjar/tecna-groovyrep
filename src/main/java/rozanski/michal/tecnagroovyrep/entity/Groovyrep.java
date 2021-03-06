package rozanski.michal.tecnagroovyrep.entity;

import org.springframework.data.repository.Repository;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "groovyrep")
public class Groovyrep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String script;

    public Groovyrep() {
    }

    public Groovyrep(Long id, String name, String script) {
        this.id = id;
        this.name = name;
        this.script = script;
    }

    public Groovyrep(String name, String script) {
        this.name = name;
        this.script = script;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }


}

