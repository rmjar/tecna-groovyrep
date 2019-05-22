package rozanski.michal.tecnagroovyrep.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rozanski.michal.tecnagroovyrep.dto.GroovyrepDto;
import rozanski.michal.tecnagroovyrep.dto.GroovyrepIdAndNameDto;
import rozanski.michal.tecnagroovyrep.exceptions.GroovyrepException;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GroovyrepServiceImplTest {

    @Autowired
    GroovyrepService groovyrepService;

    @Test
    public void shouldFindAllScripts() throws GroovyrepException {
        List<GroovyrepIdAndNameDto> result = groovyrepService.listResources();
        assertTrue("Number of scripts should be greater than 0", result.size() > 0);
    }


    @Test
    public void shouldFindScriptByID() throws GroovyrepException {
        Long id = Long.valueOf(1);

        GroovyrepDto result = groovyrepService.findById(id);
        assertTrue("Should find a script with Id eq 1", id.equals(result.getId()));
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test(expected = GroovyrepException.class)
    public void shouldThowErrorForANonExistingSciptId() throws GroovyrepException {
        Long id = Long.valueOf(20);
        GroovyrepDto result = groovyrepService.findById(id);
        thrown.expect(GroovyrepException.class);
        thrown.expectMessage("Script with id: 20 not found.");

    }

    @Test
    public void shouldFindScriptByName() throws GroovyrepException {
        String name = "Demo1.groovy";

        GroovyrepDto result = groovyrepService.findByName(name);
        assertTrue("Should find a script with name: " + name, name.equals(result.getName()));
    }

    @Test(expected = GroovyrepException.class)
    public void shouldThowErrorForANonExistingSciptName() throws GroovyrepException {
        String name = "TestNonExisting.groovy";
        GroovyrepDto result = groovyrepService.findByName(name);
        thrown.expect(GroovyrepException.class);
        thrown.expectMessage("Script with name: " + name + " not found.");
    }


    @Test
    public void shouldSaveANewScipt() throws GroovyrepException {
        GroovyrepDto newScipt = new GroovyrepDto("GroovyTestScript.groovy", "//Test script");
        GroovyrepDto result = groovyrepService.saveOrUpdate(newScipt);

        assertTrue("A script with a name: " + newScipt.getName() + " should be saved", result.getName().equals(newScipt.getName()));
    }

    @Test
    public void shouldUpdateScript() throws GroovyrepException {
        GroovyrepDto scriptToUpdate = groovyrepService.findById(1l);
        scriptToUpdate.setScript("//Updated script body");
        scriptToUpdate.setName("ANewUniqueName.groovy");
        GroovyrepDto result = groovyrepService.saveOrUpdate(scriptToUpdate);

        assertTrue("Script body should be updated to: " + scriptToUpdate.getScript(), result.getScript().equals(scriptToUpdate.getScript()));
        assertTrue("Script name should be updated to: " + scriptToUpdate.getName(), result.getName().equals(scriptToUpdate.getName()));
    }

    @Rule
    public ExpectedException thrownConstraint = ExpectedException.none();

    @Test
    public void shouldThrowUniqueConstraintViolationExceptionWhenUpdating() throws GroovyrepException {
        GroovyrepDto baseScript = groovyrepService.findById(3l);
        GroovyrepDto scriptToUpdate = groovyrepService.findById(2l);

        scriptToUpdate.setName(baseScript.getName());

        try {
            GroovyrepDto result = groovyrepService.saveOrUpdate(scriptToUpdate);
            thrownConstraint.expect(GroovyrepException.class);
            thrownConstraint.expectMessage("Error updating script with id: " + scriptToUpdate.getId());
        } catch (GroovyrepException e) {
        }
    }

    @Test(expected = GroovyrepException.class)
    public void shouldThrowUniqueConstraintViolationExceptionWhenSavingANewScript() throws GroovyrepException {
        GroovyrepDto baseScript = groovyrepService.findById(3l);
        GroovyrepDto newScript = new GroovyrepDto(baseScript.getName(), "//Test script");

        GroovyrepDto result = groovyrepService.saveOrUpdate(newScript);
        thrownConstraint.expect(GroovyrepException.class);
        thrownConstraint.expectMessage("Error saving new script");
    }

    @Rule
    public ExpectedException thrownDeleted = ExpectedException.none();

    @Test(expected = GroovyrepException.class)
    public void shouldDeleteAChosenScriptById() throws GroovyrepException {
        GroovyrepDto newScript = new GroovyrepDto("ScriptToBeDeleted.groovy", "//Test script to be deleted");
        GroovyrepDto result = groovyrepService.saveOrUpdate(newScript);
        assertTrue("A script for deletion should be added", result != null && result.getId() != null);

        groovyrepService.delete(result.getId());

        GroovyrepDto resultFinal = groovyrepService.findById(result.getId());
        thrownDeleted.expect(GroovyrepException.class);
        thrownDeleted.expectMessage("Script with id: " + result.getId() + " not found.");
    }

}