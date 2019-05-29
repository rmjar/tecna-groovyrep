package rozanski.michal.tecnagroovyrep.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import rozanski.michal.tecnagroovyrep.entity.Groovyrep;
import rozanski.michal.tecnagroovyrep.entity.GroovyrepIdAndName;

import java.util.List;
import java.util.Optional;

public interface GroovyrepRepository extends Repository<Groovyrep, Long> {
    Optional<Groovyrep> findOneByName(String name);

    @Query("select g.id as id, g.name as name from Groovyrep g")
    List<GroovyrepIdAndName> getGroovyrepIdAndName();

    Optional<Groovyrep> findById(Long id);

    Groovyrep save(Groovyrep groovyrep);

    void delete(Groovyrep groovyrep);
}
