package org.atwjsw.peoplefinder.controller;

import org.atwjsw.peoplefinder.domain.Person;
import org.atwjsw.peoplefinder.repository.PersonRepository;
import org.atwjsw.peoplefinder.service.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    private List<Person> persons;

    @MockBean
    private PersonService peronService;

    @MockBean
    private PersonRepository personRepository;

    @Before
    public void setup() {
        persons = Arrays.asList(
            new Person("John", "Doe"),
            new Person("Jane", "Smith"),
            new Person("John", "Smith"),
            new Person("Jane", "Doe")
        );
    }

    @Test
    public void whenListPersons_thenReturnJsonArray() throws Exception {

        when(peronService.getAllPersons()).thenReturn(persons);

        mvc.perform(get("/persons")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(4)))
            .andExpect(jsonPath("$[0].firstName", is("John")))
            .andExpect(jsonPath("$[0].lastName", is("Doe")));
    }

    @Test
    public void searchPersonsFirstName_whenHasMatched_thenReturnFoundPersons() throws Exception {

        String firstName = "John";

        when(peronService.getAllPersonsByFirstName(firstName))
            .thenReturn(persons.stream().filter(p -> firstName.equals(p.getFirstName())).collect(Collectors.toList()));

        mvc.perform(get("/persons?firstName=" + firstName)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].firstName", is(firstName)))
            .andExpect(jsonPath("$[1].firstName", is(firstName)));
    }

    @Test
    public void searchPersonsFirstName_whenNoMatched_thenReturnEmptyJsonArray() throws Exception {

        String firstName = "firstNameNotExists";

        when(peronService.getAllPersonsByFirstName(firstName))
            .thenReturn(persons.stream().filter(p -> firstName.equals(p.getFirstName())).collect(Collectors.toList()));

        mvc.perform(get("/persons?firstName=" + firstName)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void searchPersonsByLastName_whenHasMatched_thenReturnFoundPersons() throws Exception {

        String lastName = "Smith";

        when(peronService.getAllPersonsByLastName(lastName))
            .thenReturn(persons.stream().filter(p -> lastName.equals(p.getLastName())).collect(Collectors.toList()));

        mvc.perform(get("/persons?lastName=" + lastName)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].lastName", is(lastName)))
            .andExpect(jsonPath("$[1].lastName", is(lastName)));
    }

    @Test
    public void searchPersonsLastName_whenNoMatched_thenReturnEmptyJsonArray() throws Exception {

        String lastName = "lastNameNotExists";

        when(peronService.getAllPersonsByLastName(lastName))
            .thenReturn(persons.stream().filter(p -> lastName.equals(p.getLastName())).collect(Collectors.toList()));

        mvc.perform(get("/persons?lastName=" + lastName)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(0)));
    }
}
