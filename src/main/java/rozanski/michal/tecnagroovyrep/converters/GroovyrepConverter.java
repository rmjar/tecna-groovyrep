package rozanski.michal.tecnagroovyrep.converters;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.stereotype.Component;
import rozanski.michal.tecnagroovyrep.dto.GroovyrepDto;
import rozanski.michal.tecnagroovyrep.dto.GroovyrepIdAndNameDto;
import rozanski.michal.tecnagroovyrep.entity.Groovyrep;
import rozanski.michal.tecnagroovyrep.entity.GroovyrepIdAndName;

@Component
public class GroovyrepConverter {

    public GroovyrepDto convertToGroovyrepDto(Groovyrep groovyrep) {
        String tmpScript = StringEscapeUtils
                .unescapeJson(groovyrep.getScript());
        return new GroovyrepDto(groovyrep.getId(), groovyrep.getName(), tmpScript);
    }

    public Groovyrep convertToGroovyrep(GroovyrepDto groovyrepDto) {
        String tmpScript = StringEscapeUtils
                .escapeJson(groovyrepDto.getScript());
        return new Groovyrep(groovyrepDto.getId(), groovyrepDto.getName(), tmpScript);
    }

    public GroovyrepIdAndNameDto convertToGroovyrepIdAndNameDto(GroovyrepIdAndName groovyrepIdAndName) {
        return new GroovyrepIdAndNameDto(groovyrepIdAndName.getId(), groovyrepIdAndName.getName());
    }

}
