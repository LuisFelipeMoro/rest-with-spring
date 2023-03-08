package com.rest.demo.services;

import com.rest.demo.controllers.PersonController;
import com.rest.demo.dto.PersonDto;
import com.rest.demo.exceptions.RequiredObjectIsNullException;
import com.rest.demo.exceptions.ResourceNotFoundException;
import com.rest.demo.mapper.GenericMapper;
import com.rest.demo.model.Person;
import com.rest.demo.repositories.PersonRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {
    private final Logger logger = Logger.getLogger(PersonService.class.getName());
    @Autowired
    PersonRepository personRepository;

    public List<PersonDto> findAll(){

        logger.info("Finding all people");

        var persons = GenericMapper.parseListObjects(personRepository.findAll(), PersonDto.class);
        persons
                .stream()
                .forEach(p -> {
                    try {
                        p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return persons;
    }
    public PersonDto findById(Long id){

        if(id == null) throw new RequiredObjectIsNullException();
        logger.info("Finding a person");

        var entity = personRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));
        PersonDto dto = GenericMapper.parseObject(entity, PersonDto.class);
        try {
            dto.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dto;
    }

    public PersonDto create(PersonDto person) throws Exception {

        if(person == null) throw new RequiredObjectIsNullException();
        logger.info("Creating a person");
        var entity = GenericMapper.parseObject(person, Person.class);
        PersonDto dto = GenericMapper.parseObject(personRepository.save(entity), PersonDto.class);
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());
        return dto;
    }

    public PersonDto update(PersonDto person) throws Exception {

        if(person == null) throw new RequiredObjectIsNullException();
        logger.info("Updating a person");

        Person entity = personRepository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        PersonDto dto = GenericMapper.parseObject(personRepository.save(entity), PersonDto.class);
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());

        return dto;
    }

    public void delete(Long id){
        logger.info("Deleting a person");

        var entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));

        personRepository.delete(entity);

    }
}
