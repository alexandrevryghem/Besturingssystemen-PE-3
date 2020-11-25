package domain.db;

import domain.model.Contact;
import util.DbConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDBSQL implements ContactDB {
    private final Connection connection;
    private final String schema;

    public ContactDBSQL() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
    }

    /**
     * Stores the given contact in the database
     *
     * @param contact The contact to be added
     * @throws DbException if the given contact is null
     * @throws DbException if the given contact can not be added
     */
    @Override
    public void add(Contact contact) {
        if (contact == null) {
            throw new DbException("Nothing to add.");
        }
        String sql = String.format("INSERT INTO %s.contact (date, hour, email, gsm, firstname, lastname) VALUES (?, ?, ?, ?, ?, ?)", this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setDate(1, contact.getDate());
            statementSQL.setTime(2, contact.getHour());
            statementSQL.setString(3, contact.getEmail());
            statementSQL.setString(4, contact.getGsm());
            statementSQL.setString(5, contact.getFirstName());
            statementSQL.setString(6, contact.getLastName());
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    /**
     * Searches for all the contacts with that substring in their name
     *
     * @param substring The substring to be searched
     * @return An arraylist with all contacts with {@code substring}
     * @throws DbException if the given substring is empty
     * @throws DbException if the given contacts can not be retrieved
     */
    @Override
    public List<Contact> search(String substring) {
        if (substring.isEmpty()) {
            throw new DbException("Nothing to search.");
        }
        List<Contact> contacts = new ArrayList<>();
        String sql = String.format("SELECT DISTINCT firstname, lastname, email, gsm FROM %s.contact WHERE LOWER(firstname) LIKE LOWER(?) OR LOWER(lastname) LIKE LOWER(?)", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, "%" + substring + "%");
            statementSQL.setString(2, "%" + substring + "%");
            ResultSet result = statementSQL.executeQuery();
            while (result.next()) {
                Contact contact = new Contact();
                contact.setFirstName(result.getString("firstname"));
                contact.setLastName(result.getString("lastname"));
                contact.setEmail(result.getString("email"));
                contact.setGsm(result.getString("gsm"));
                contacts.add(contact);
            }
        } catch (SQLException e) {
            throw new DbException(e);
        }
        return contacts;
    }

    /**
     * Returns a list with all contacts stored in the database
     * @return An arraylist with all contacts stored in the database
     * @throws DbException when there are problems with the connection to the database
     */
    @Override
    public List<Contact> getAll() {
        List<Contact> contacts = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.contact", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                String firstName = result.getString("firstname");
                String lastName = result.getString("lastname");
                Date date = result.getDate("date");
                Time hour = result.getTime("hour");
                String email = result.getString("email");
                String gsm = result.getString("gsm");
                Contact contact = new Contact(firstName, lastName, date, hour, email, gsm);
                contacts.add(contact);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return contacts;
    }
}
