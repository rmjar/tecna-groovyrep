package rozanski.michal.tecnagroovyrep.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rozanski.michal.tecnagroovyrep.entity.GroovyrepIdAndName;


import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroovyrepListingRepositoryTest {

    @Autowired
    private GroovyrepRepository groovyrepListingRepository;

    @Test
    public void shouldRetrieveListOfScripts() {
        List<GroovyrepIdAndName> groovyrepIdAndNames = groovyrepListingRepository.getGroovyrepIdAndName();

        assertTrue("Number of scripts should be greater than 0",  groovyrepIdAndNames.size() > 0);


    }
}