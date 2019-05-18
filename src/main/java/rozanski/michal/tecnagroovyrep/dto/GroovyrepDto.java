package rozanski.michal.tecnagroovyrep.dto;

public class GroovyrepDto {

    private Long id;
    private String name;
    private String script;

    public GroovyrepDto(){
    }

    public GroovyrepDto(Long id, String name, String script){
        this.id = id;
        this.name = name;
        this.script = script;
    }

    public GroovyrepDto(String name, String script){
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
