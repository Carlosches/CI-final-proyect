package co.edu.icesi.ci.tallerfinal.back.restcontroller.implementations;

import co.edu.icesi.ci.tallerfinal.back.model.Institution;
import co.edu.icesi.ci.tallerfinal.back.model.Person;
import co.edu.icesi.ci.tallerfinal.back.repositories.PersonRepository;
import co.edu.icesi.ci.tallerfinal.back.restcontroller.interfaces.PersonRestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PersonRestControllerImpl implements PersonRestController {

    private PersonRepository personRepository;

    public PersonRestControllerImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @GetMapping("/persons/")
    public Iterable<Person> getPersons(){
        return personRepository.findAll();
    }

    @Override
    @GetMapping("/persons/{persId}")
    public Person getPerson(@PathVariable("persId") long persId) {
        return personRepository.findById(persId).get();
    }
}
