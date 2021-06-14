package co.edu.icesi.ci.tallerfinal.front.model.wrapper;

import co.edu.icesi.ci.tallerfinal.front.model.classes.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonList {

    List<Person> personList;

    public PersonList() {
        this.personList = new ArrayList<>();
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }


}
