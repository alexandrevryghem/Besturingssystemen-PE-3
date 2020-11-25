package domain.service;

import domain.model.Contact;
import domain.model.Person;

import java.util.List;

public class ContactTracingService {
    private PersonService personDb = new PersonService();
    private ContactService contactDb = new ContactService();

    public ContactTracingService() {}

    public Person getPerson(String personId) {
        return getPersonDb().get(personId);
    }

    public List<Person> getPersons() {
        return getPersonDb().getAll();
    }

    public void addPerson(Person person) {
        getPersonDb().add(person);
    }

    public void updatePersons(Person person) {
        getPersonDb().update(person);
    }

    public void deletePerson(String id) {
        getPersonDb().delete(id);
    }

    private PersonService getPersonDb() {
        return personDb;
    }

//----------------------------------------------------------------------------------------------------------------------

    public List<Contact> getContacts() {
        return getContactDb().getAll();
    }

    public void addContact(Contact contact) {
        getContactDb().add(contact);
    }

    public List<Contact> findContacts(String substring) {
        return getContactDb().search(substring);
    }

    private ContactService getContactDb() {
        return contactDb;
    }
}
