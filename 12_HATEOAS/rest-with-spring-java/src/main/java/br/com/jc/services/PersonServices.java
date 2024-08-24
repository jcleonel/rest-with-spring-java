package br.com.jc.services;

import br.com.jc.controllers.PersonController;
import br.com.jc.data.vo.v1.PersonVO;
import br.com.jc.data.vo.v2.PersonVOV2;
import br.com.jc.exceptions.RequiredObjectIsNullException;
import br.com.jc.exceptions.ResourceNotFoundException;
import br.com.jc.mapper.Mapper;
import br.com.jc.mapper.custom.PersonMapper;
import br.com.jc.model.Person;
import br.com.jc.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Service
public class PersonServices {
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    private PersonMapper personMapper;

    public PersonVO findById(Long id) {
        logger.info("Finding one person!");
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this Id!"));

        PersonVO vo = Mapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public List<PersonVO> findAll() {
        logger.info("Finding all persons!");
        var persons = Mapper.parseListObjects(repository.findAll(), PersonVO.class);
        persons.forEach(
                p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel())
        );
        return persons;
    }

    public PersonVO create(PersonVO person) {

        if (Objects.isNull(person)) {
            throw new RequiredObjectIsNullException();
        }

        logger.info("Create one person!");
        var entity = Mapper.parseObject(person, Person.class);
        PersonVO vo =  Mapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVOV2 createV2(PersonVOV2 person) {
        logger.info("Create one person V2!");
        var entity = personMapper.convertVoToEntity(person);
        return personMapper.convertEntityToVo(repository.save(entity));
    }

    public PersonVO update(PersonVO person) {

        if (Objects.isNull(person)) {
            throw new RequiredObjectIsNullException();
        }

        logger.info("Update one person!");

        var entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this Id!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        PersonVO vo = Mapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id) {
        logger.info("delete one person " + id);

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this Id!"));

        repository.delete(entity);

    }

}
