package co.edu.icesi.ci.tallerfinal.back.restcontroller.interfaces;


import co.edu.icesi.ci.tallerfinal.back.model.Person;

public interface PersonRestController {
    public Iterable<Person> getPersons();
}
