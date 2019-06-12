package org.atwjsw.peoplefinder.controller;

import org.atwjsw.peoplefinder.domain.Person;
import org.atwjsw.peoplefinder.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://people-finder-app.herokuapp.com")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons")
    public Iterable<Person> getPersons(@RequestParam(required = false) String firstName,
                                       @RequestParam(required = false) String lastName) {

        if (lastName != null) {
            return personService.getAllPersonsByLastName(lastName);
        }

        if (firstName != null) {
            return personService.getAllPersonsByFirstName(firstName);
        }

        return personService.getAllPersons();
    }
}
