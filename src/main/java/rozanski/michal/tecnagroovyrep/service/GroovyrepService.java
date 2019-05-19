package rozanski.michal.tecnagroovyrep.service;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import rozanski.michal.tecnagroovyrep.aux.Param;
import rozanski.michal.tecnagroovyrep.dto.GroovyResultDto;
import rozanski.michal.tecnagroovyrep.dto.GroovyrepDto;
import rozanski.michal.tecnagroovyrep.dto.GroovyrepIdAndNameDto;
import rozanski.michal.tecnagroovyrep.dto.GroovyrepRunParamsDto;
import rozanski.michal.tecnagroovyrep.exceptions.GroovyrepException;

import java.util.Arrays;
import java.util.List;

public interface GroovyrepService {
    public GroovyrepDto findById(Long id) throws GroovyrepException;

    public GroovyrepDto findByName(String name) throws GroovyrepException;

    public GroovyrepDto saveOrUpdate(GroovyrepDto groovyrepDto) throws GroovyrepException;

    public List<GroovyrepIdAndNameDto> listResources() throws GroovyrepException;

    public void delete(Long id) throws Exception;

    public GroovyResultDto runScript(GroovyrepDto groovyrepDto, GroovyrepRunParamsDto params);
}
