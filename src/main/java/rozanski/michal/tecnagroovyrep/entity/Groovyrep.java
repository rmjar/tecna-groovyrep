package rozanski.michal.tecnagroovyrep.entity;

import javax.persistence.*;

@Entity
@Table(name = "groovyrep")
public class Groovyrep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String desc;

    private String script;

    public Groovyrep() {
    }

    public Groovyrep(Long id, String desc, String script) {
        this.id = id;
        this.desc = desc;
        this.script = script;
    }

    public Groovyrep(String desc, String script) {
        this.desc = desc;
        this.script = script;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }
}
