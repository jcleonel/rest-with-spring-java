package br.com.jc.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.jc.model.Person;

@Service
public class PersonServices {
	
	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	public Person findById(String id) {		
		logger.info("Finding one person!");
		return new Person(counter.incrementAndGet(), "Jean", "Leonel", "Rua 1", "Male");
	}
	
	public List<Person> findAll() {		
		logger.info("Finding all persons!");
		
		List<Person> persons = new ArrayList<>();
		for (int i = 0; i <= 8; i++) {
			Person person = mockPerson(i);
			persons.add(person);
		}
		
		return persons;
		
	}
	
	public Person create(Person person) {
		logger.info("Create one person!");
		return person;
	}

	public Person update(Person person) {
		logger.info("Update one person!");
		return person;
	}
	
	public void delete(String id) {
		logger.info("delete one person " + id);
	}
	
	private Person mockPerson(int i) {
		return new Person(counter.incrementAndGet(), "Jean " + i, "Leonel "  + i, "Rua "  + i, "Male "  + i);
	}
	
	
}
