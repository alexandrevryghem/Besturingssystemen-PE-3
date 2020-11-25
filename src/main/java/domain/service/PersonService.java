package domain.service;

import domain.db.PersonDB;
import domain.db.PersonDBSQL;
import domain.model.Person;

import java.util.List;

public class PersonService {
    private final PersonDB persons = new PersonDBSQL();

    public Person get(String personId) {
        if (personId == null || personId.trim().isEmpty()) {
            throw new ServiceException("No id given");
        }
        for (Person person : persons.getAll()) {
            if (person.getUserid().equals(personId))
                return person;
        }
        return null;
    }

    public List<Person> getAll() {
        return persons.getAll();
    }

    public void add(Person person) {
        for (Person p : persons.getAll()) {
            if (p.getUserid().equals(person.getUserid()))
                throw new ServiceException("User already exists");
        }
        persons.add(person);
    }

    public void update(Person person) {
        if (person == null) {
            throw new ServiceException("No person given");
        }
        if (!persons.contains(person.getUserid())) {
            throw new ServiceException("No person found");
        }
        persons.remove(person.getUserid());
        persons.add(person);
    }

    public void delete(String personId) {
        if (personId == null) {
            throw new ServiceException("No id given");
        }
        persons.remove(personId);
    }

    public int getNumberOfPersons() {
        return persons.getAll().size();
    }
}
