package org.atwjsw.peoplefinder.service;

import org.atwjsw.peoplefinder.domain.Person;
import org.atwjsw.peoplefinder.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Iterable<Person> getAllPersons() {
        return this.personRepository.findAll();
    }

    public Iterable<Person> getAllPersonsByFirstName(String firstName) {
        return this.personRepository.findByFirstName(firstName);
    }

    public Iterable<Person> getAllPersonsByLastName(String lastName) {
        return this.personRepository.findByLastName(lastName);
    }

}
