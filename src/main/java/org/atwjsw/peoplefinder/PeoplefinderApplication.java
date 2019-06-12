package org.atwjsw.peoplefinder;

import org.atwjsw.peoplefinder.domain.Person;
import org.atwjsw.peoplefinder.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class PeoplefinderApplication {

    public static void main(String[] args) {
        SpringApplication.run(PeoplefinderApplication.class, args);
    }

    @Bean
    CommandLineRunner init(PersonRepository personRepository) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("John", "Smith"));
        persons.add(new Person("Jane", "Doe"));
        persons.add(new Person("Jane", "Smith"));
        persons.add(new Person("John", "Doe"));

        return args -> {
            persons.forEach(p -> personRepository.save(p));
            personRepository.findAll().forEach(System.out::println);
        };
    }
}
