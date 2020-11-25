package domain.service;

import domain.db.ContactDB;
import domain.db.ContactDBSQL;
import domain.model.Contact;

import java.util.List;

public class ContactService {
    private final ContactDB contacts = new ContactDBSQL();

    public List<Contact> getAll() {
        return contacts.getAll();
    }

    public void add(Contact contact) {
        contacts.add(contact);
    }

    public List<Contact> search(String searchString) {
        return contacts.search(searchString);
    }

    public int getNumberOfContacts() {
        return contacts.getAll().size();
    }
}
