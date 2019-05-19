package rozanski.michal.tecnagroovyrep.controller;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rozanski.michal.tecnagroovyrep.dto.GroovyResultDto;
import rozanski.michal.tecnagroovyrep.dto.GroovyrepDto;
import rozanski.michal.tecnagroovyrep.dto.GroovyrepIdAndNameDto;
import rozanski.michal.tecnagroovyrep.dto.GroovyrepRunParamsDto;
import rozanski.michal.tecnagroovyrep.exceptions.GroovyrepException;
import rozanski.michal.tecnagroovyrep.service.GroovyrepService;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

@RestController
public class GroovyrepController {

    @Autowired
    private GroovyrepService groovyrepService;

    @ResponseBody
    @RequestMapping(value = "scripts", produces = "application/json")
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


    @PostMapping(value = "/script", produces = "application/json")
    public GroovyrepDto newScript(@RequestBody GroovyrepDto newGroovyrep) {
        try {
            return groovyrepService.saveOrUpdate(newGroovyrep);
        } catch (GroovyrepException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping(value = "/script/{id}", produces = "application/json")
    public GroovyrepDto updateScript(@RequestBody GroovyrepDto groovyrep, @PathVariable Long id) {
        GroovyrepDto toSaveDto = new GroovyrepDto(id, groovyrep.getName(), groovyrep.getScript());
        try {
            return groovyrepService.saveOrUpdate(toSaveDto);
        } catch (GroovyrepException e) {
            e.printStackTrace();
        }
        return null;
    }


    @DeleteMapping("/script/{id}")
    public void deleteScript(@PathVariable Long id) {
        try {
            groovyrepService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping(value = "run/{id}")
    @ResponseBody
    public GroovyResultDto runGroovyScriptById(@PathVariable Long id, @RequestBody(required = false) GroovyrepRunParamsDto params) {
        GroovyrepDto groovyrepDto = null;

        try {
            groovyrepDto = groovyrepService.findById(id);
        } catch (GroovyrepException e) {
            e.printStackTrace();
        }

        GroovyResultDto groovyResultDto = groovyrepService.runScript(groovyrepDto, params);
        return groovyResultDto;
    }

    @PostMapping(value = "run/byname/{name}")
    @ResponseBody
    public GroovyResultDto runGroovyScriptByName(@PathVariable String name, @RequestBody(required = false) GroovyrepRunParamsDto params) {
        GroovyrepDto groovyrepDto = null;

        try {
            groovyrepDto = groovyrepService.findByName(name);
        } catch (GroovyrepException e) {
            e.printStackTrace();
        }

        GroovyResultDto groovyResultDto = groovyrepService.runScript(groovyrepDto, params);
        return groovyResultDto;
    }
}
