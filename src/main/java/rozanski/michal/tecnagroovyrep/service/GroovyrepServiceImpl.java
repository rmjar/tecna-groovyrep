package rozanski.michal.tecnagroovyrep.service;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rozanski.michal.tecnagroovyrep.converters.GroovyrepConverter;
import rozanski.michal.tecnagroovyrep.dto.GroovyrepDto;
import rozanski.michal.tecnagroovyrep.dto.GroovyrepIdAndNameDto;
import rozanski.michal.tecnagroovyrep.entity.Groovyrep;
import rozanski.michal.tecnagroovyrep.entity.GroovyrepIdAndName;
import rozanski.michal.tecnagroovyrep.exceptions.GroovyrepException;
import rozanski.michal.tecnagroovyrep.repository.GroovyrepListingRepository;
import rozanski.michal.tecnagroovyrep.repository.GroovyrepRepository;

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
        Optional<Groovyrep> source;
        GroovyrepDto target = new GroovyrepDto();

        source = groovyrepRepository.findById(id);
        if (source.isPresent()) {
            target = groovyrepConverter.convertToGroovyrepDto(source.get());
            return target;
        }
        return null;
    }

    @Override
    public GroovyrepDto saveOrUpdate(GroovyrepDto groovyrepDto) throws GroovyrepException {

        return null;
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
