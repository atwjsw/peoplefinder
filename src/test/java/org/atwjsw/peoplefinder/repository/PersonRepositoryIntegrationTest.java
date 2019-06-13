package org.atwjsw.peoplefinder.repository;

import org.atwjsw.peoplefinder.domain.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryIntegrationTest {

    @Autowired
    private PersonRepository personRepository;

    @Before()
    public void setup() {

        // given
        Person johnSimth = new Person("testFirstName", "testLastName");
        personRepository.save(johnSimth);
        Person johnDoe = new Person("testFirstName", "testLastName");
        personRepository.save(johnDoe);
    }

    @Test
    public void whenFindByFirstNameHasMatched_thenReturnFoundPersons() {
        // when
        List<Person> persons = personRepository.findByFirstName("testFirstName");

        // then
        assertEquals(2, persons.size());
    }

    @Test
    public void whenFindByFirstNameNotMatched_thenReturnEmptyList() {
        // when
        List<Person> persons = personRepository.findByFirstName("testFisrtNameNotExists");

        // then
        assertTrue(persons.isEmpty());
    }

    @Test
    public void whenFindByLastNameHasMatched_thenReturnFoundPersons() {
        // when
        List<Person> persons = personRepository.findByLastName("testLastName");

        // then
        assertEquals(2, persons.size());
    }

    @Test
    public void whenFindByLastNameNotMatched_thenReturnEmptyList() {
        // when
        List<Person> persons = personRepository.findByLastName("testLastNameNotExists");

        // then
        assertTrue(persons.isEmpty());
    }
}
