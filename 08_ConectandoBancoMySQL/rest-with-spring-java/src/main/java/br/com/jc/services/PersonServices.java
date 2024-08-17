package br.com.jc.services;

import br.com.jc.data.vo.v1.PersonVO;
import br.com.jc.exceptions.ResourceNotFoundException;
import br.com.jc.mapper.Mapper;
import br.com.jc.model.Person;
import br.com.jc.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public PersonVO findById(Long id) {
        logger.info("Finding one person!");
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this Id!"));

        return Mapper.parseObject(entity, PersonVO.class);
    }

    public List<PersonVO> findAll() {
        logger.info("Finding all persons!");
        return Mapper.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO create(Person person) {
        logger.info("Create one person!");
        var entity = Mapper.parseObject(person, Person.class);
        return Mapper.parseObject(repository.save(entity), PersonVO.class);
    }

    public PersonVO update(PersonVO person) {
        logger.info("Update one person!");

        var entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this Id!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return Mapper.parseObject(repository.save(entity), PersonVO.class);
    }

    public void delete(Long id) {
        logger.info("delete one person " + id);

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this Id!"));

        repository.delete(entity);

    }

}
