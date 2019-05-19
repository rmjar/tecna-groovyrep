package rozanski.michal.tecnagroovyrep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rozanski.michal.tecnagroovyrep.entity.Groovyrep;

import java.util.Optional;

public interface GroovyrepRepository extends JpaRepository<Groovyrep, Long> {
    Optional<Groovyrep> findOneByName(String name);
}
