package domain.db;

import domain.model.Person;

import java.util.List;

public interface PersonDB {
    /**
     * Stores the given person
     *
     * @param person The person to be added
     * @throws DbException if the given person is null
     * @throws DbException if the given person can not be added
     */
    void add(Person person);

    /**
     * Removes the given person
     *
     * @param personId The personId of the person to be removed
     * @throws DbException if the given personId is null
     * @throws DbException if the given person can not be removed
     */
    void remove(String personId);

    /**
     * Check if the database contains the given person
     *
     * @param personId The personId to be checked
     * @throws DbException if the given person doesn't exist
     * @throws DbException if the given person can not be removed
     * @return returns true if the person is already inside the database
     */
    boolean contains(String personId);

    /**
     * Returns a list with all people stored in the database
     *
     * @return An arraylist with all people stored in the database
     * @throws DbException if something went wrong
     */
    List<Person> getAll();
}
