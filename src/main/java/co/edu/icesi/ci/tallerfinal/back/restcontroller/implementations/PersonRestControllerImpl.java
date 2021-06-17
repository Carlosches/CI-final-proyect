package co.edu.icesi.ci.tallerfinal.back.restcontroller.implementations;

import co.edu.icesi.ci.tallerfinal.back.model.Institution;
import co.edu.icesi.ci.tallerfinal.back.model.Person;
import co.edu.icesi.ci.tallerfinal.back.repositories.PersonRepository;
import co.edu.icesi.ci.tallerfinal.back.restcontroller.interfaces.PersonRestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/persons")
public class PersonRestControllerImpl implements PersonRestController {

    private PersonRepository personRepository;

    public PersonRestControllerImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @GetMapping("/")
    public Iterable<Person> getPersons(){
        return personRepository.findAll();
    }

}
