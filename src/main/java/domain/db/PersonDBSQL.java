package domain.db;

import domain.model.Person;
import util.DbConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDBSQL implements PersonDB {
    private final Connection connection;
    private final String schema;

    public PersonDBSQL() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
    }

    /**
     * Stores the given person in the database
     *
     * @param person The person to be added
     * @throws DbException if the given person is null
     * @throws DbException if the given person can not be added
     */
    @Override
    public void add(Person person) {
        if (person == null) {
            throw new DbException("Nothing to add.");
        }
        String sql = String.format("INSERT INTO %s.person (userid, email, password, firstname, lastname) VALUES (?, ?, ?, ?, ?)", this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, person.getUserid());
            statementSQL.setString(2, person.getEmail());
            statementSQL.setString(3, person.getPassword());
            statementSQL.setString(4, person.getFirstName());
            statementSQL.setString(5, person.getLastName());
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    /**
     * Removes the given person
     *
     * @param personId The personId of the person to be removed
     * @throws DbException if the given person is null
     * @throws DbException if the given person can not be removed
     */
    @Override
    public void remove(String personId) {
        if (personId == null) {
            throw new DbException("No id given");
        }
        String sql = String.format("DELETE FROM %s.person WHERE userid=?", this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, personId);
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    /**
     * Check if the database contains the given person
     *
     * @param personId The personId to be checked
     * @throws DbException if the given person doesn't exist
     * @throws DbException if the given person can not be removed
     * @return returns true if the person is already inside the database
     */
    @Override
    public boolean contains(String personId) {
        if (personId == null) {
            throw new DbException("No id given");
        }
        String sql = String.format("SELECT EXISTS(SELECT userid FROM %s.person WHERE userid = ?)", this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, personId);
            return statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    /**
     * Returns a list with all people stored in the database
     * @return An arraylist with all people stored in the database
     * @throws DbException when there are problems with the connection to the database
     */
    @Override
    public List<Person> getAll() {
        List<Person> people = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.person", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                String userid = result.getString("userid");
                String email = result.getString("email");
                String password = result.getString("password");
                String firstName = result.getString("firstname");
                String lastName = result.getString("lastname");
                Person person = new Person(userid, email, password, firstName, lastName);
                people.add(person);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return people;
    }
}
