package rozanski.michal.tecnagroovyrep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rozanski.michal.tecnagroovyrep.dto.GroovyrepDto;
import rozanski.michal.tecnagroovyrep.dto.GroovyrepIdAndNameDto;
import rozanski.michal.tecnagroovyrep.exceptions.GroovyrepException;
import rozanski.michal.tecnagroovyrep.repository.GroovyrepRepository;
import rozanski.michal.tecnagroovyrep.service.GroovyrepService;

import java.util.List;
import java.util.Optional;

@RestController
public class GroovyrepController {

    @Autowired
    private GroovyrepRepository groovyrepRepository;

    @Autowired
    private GroovyrepService groovyrepService;

    @ResponseBody
    @RequestMapping(value = "groovylist", produces = "application/json")
    public List<GroovyrepIdAndNameDto> getGroovyList() {
        List<GroovyrepIdAndNameDto> groovyListingDto = null;
        try {
            groovyListingDto = groovyrepService.listResources();
        } catch (GroovyrepException e) {
            e.printStackTrace();
        }
        return groovyListingDto;
    }


    @ResponseBody
    @GetMapping(value = "/script/{id}", produces = "application/json")
    public GroovyrepDto getGroovyScriptById(@PathVariable Long id) {
        GroovyrepDto groovyrepDto = null;
        try {
            groovyrepDto = groovyrepService.findById(id);
        } catch (GroovyrepException e) {
            e.printStackTrace();
        }
        return groovyrepDto;
    }
}
