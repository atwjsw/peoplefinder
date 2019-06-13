package org.atwjsw.peoplefinder.service;

import org.atwjsw.peoplefinder.domain.Person;
import org.atwjsw.peoplefinder.repository.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

    private PersonService personService;
    @Mock
    private PersonRepository personRepository;
    private List<Person> persons;

    @Before
    public void setUp() {
        personService = new PersonService(personRepository);
        persons = new ArrayList<>();
        persons.add(new Person("John", "Doe"));
        persons.add(new Person("Jane", "Smith"));
        persons.add(new Person("Jane", "Doe"));
        persons.add(new Person("John", "Smith"));
    }

    @Test
    public void getAllPersons() {
        when(personRepository.findAll()).thenReturn(persons);

        List<Person> persons = personService.getAllPersons();

        assertEquals(persons.size(), persons.size());
        verify(personRepository).findAll();
    }

    @Test
    public void getAllPersonsByFirstName() {
        String firstName = "John";
        when(personRepository.findByFirstName(firstName)).thenReturn(
            persons.stream().filter(p -> firstName.equals(p.getFirstName())).collect(Collectors.toList()));

        List<Person> persons = personService.getAllPersonsByFirstName(firstName);

        assertEquals(2, persons.size());
        verify(personRepository).findByFirstName(firstName);
    }

    @Test
    public void getAllPersonsByLastName() {
        String lastName = "Smith";
        when(personRepository.findByLastName(lastName)).thenReturn(
            persons.stream().filter(p -> lastName.equals(p.getLastName())).collect(Collectors.toList()));

        List<Person> persons = personService.getAllPersonsByLastName(lastName);

        assertEquals(2, persons.size());
        verify(personRepository).findByLastName(lastName);
    }
}
