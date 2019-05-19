package rozanski.michal.tecnagroovyrep.service;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rozanski.michal.tecnagroovyrep.converters.GroovyrepConverter;
import rozanski.michal.tecnagroovyrep.dto.GroovyResultDto;
import rozanski.michal.tecnagroovyrep.dto.GroovyrepDto;
import rozanski.michal.tecnagroovyrep.dto.GroovyrepIdAndNameDto;
import rozanski.michal.tecnagroovyrep.dto.GroovyrepRunParamsDto;
import rozanski.michal.tecnagroovyrep.entity.Groovyrep;
import rozanski.michal.tecnagroovyrep.entity.GroovyrepIdAndName;
import rozanski.michal.tecnagroovyrep.exceptions.GroovyrepException;
import rozanski.michal.tecnagroovyrep.repository.GroovyrepListingRepository;
import rozanski.michal.tecnagroovyrep.repository.GroovyrepRepository;

import javax.validation.ConstraintViolationException;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GroovyrepServiceImpl implements GroovyrepService {
    @Autowired
    private GroovyrepRepository groovyrepRepository;

    @Autowired
    GroovyrepListingRepository groovyrepListingRepository;

    @Autowired
    GroovyrepConverter groovyrepConverter;

    @Override
    public GroovyrepDto findById(Long id) throws GroovyrepException {
        Optional<Groovyrep> source = groovyrepRepository.findById(id);

        GroovyrepDto target = new GroovyrepDto();
        if (source.isPresent()) {
            target = groovyrepConverter.convertToGroovyrepDto(source.get());
        }
        return target;
    }

    @Override
    public GroovyrepDto findByName(String name) throws GroovyrepException {
        Optional<Groovyrep> source = groovyrepRepository.findOneByName(name);

        GroovyrepDto target = new GroovyrepDto();
        if (source.isPresent()) {
            target = groovyrepConverter.convertToGroovyrepDto(source.get());
        }
        return target;
    }

    @Override
    public GroovyrepDto saveOrUpdate(GroovyrepDto groovyrepDto) throws GroovyrepException {
        Groovyrep groovyrep = groovyrepConverter.convertToGroovyrep(groovyrepDto);
            groovyrep = groovyrepRepository.save(groovyrep);
        return groovyrepConverter.convertToGroovyrepDto(groovyrep);
    }

    @Override
    public void delete(Long id) {
        Optional<Groovyrep> groovyrep = groovyrepRepository.findById(id);
        if (groovyrep.isPresent()) {
            groovyrepRepository.delete(groovyrep.get());
        }
    }


    @Override
    public GroovyResultDto runScript(GroovyrepDto groovyrepDto, GroovyrepRunParamsDto params) {
        GroovyResultDto groovyResultDto = new GroovyResultDto();
        Binding binding;
        GroovyShell groovyShell;
        binding = new Binding();

        if (params != null && params.getParams().length > 0) {
            Arrays.stream(params.getParams()).forEach(param -> binding.setVariable(param.getName(), param.getValue()));
            groovyShell = new GroovyShell(binding);
        } else {
            groovyShell = new GroovyShell();
        }

        Object result = groovyShell.evaluate(groovyrepDto.getScript(), groovyrepDto.getName());
        if (result != null) {
            groovyResultDto.setOut(result.toString());
        }
        return groovyResultDto;

    }

    @Override
    public List<GroovyrepIdAndNameDto> listResources() throws GroovyrepException {

        List<GroovyrepIdAndName> groovyListing = groovyrepListingRepository.getGroovyrepIdAndName();

        List<GroovyrepIdAndNameDto> groovyListingDto = StreamSupport.stream(groovyListing.spliterator(), false)
                .map(groovyListingItem -> groovyrepConverter.convertToGroovyrepIdAndNameDto(groovyListingItem))
                .collect(Collectors.toList());
        return groovyListingDto;
    }
}
