package domain.db;

import domain.model.Contact;

import java.util.List;

public interface ContactDB {
    /**
     * Stores the given contact
     *
     * @param contact The contact to be added
     * @throws DbException if the given contact is null
     * @throws DbException if the given contact can not be added
     */
    void add(Contact contact);

    /**
     * Searches for all the contacts with that substring in their name
     *
     * @param substring The substring to be searched
     * @return An arraylist with all contacts with {@code substring}
     * @throws DbException if the given substring is empty
     * @throws DbException if the given contacts can not be retrieved
     */
    List<Contact> search(String substring);

    /**
     * Returns a list with all contacts stored in the database
     *
     * @return An arraylist with all contacts stored in the database
     * @throws DbException if something went wrong
     */
    List<Contact> getAll();
}
