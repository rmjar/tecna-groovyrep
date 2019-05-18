package rozanski.michal.tecnagroovyrep.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import rozanski.michal.tecnagroovyrep.entity.Groovyrep;
import rozanski.michal.tecnagroovyrep.entity.GroovyrepIdAndName;

import java.util.List;

public interface GroovyrepListingRepository extends Repository<Groovyrep, Long> {

    @Query("select g.id as id, g.name as name from Groovyrep g")
    List<GroovyrepIdAndName> getGroovyrepIdAndName();
}
