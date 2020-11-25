package domain.model;

import java.sql.Date;
import java.sql.Time;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
    private String firstName;
    private String lastName;
    private Date date;
    private Time hour;
    private String email;
    private String gsm;

    public Contact(String firstName, String lastName, Date date, Time hour, String email, String gsm) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = date;
        this.hour = hour;
        this.email = email;
        this.gsm = gsm;
    }

    public Contact() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.isEmpty()) {
            throw new ModelException("No firstname given");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.isEmpty()) {
            throw new ModelException("No last name given");
        }
        this.lastName = lastName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        if (date == null) {
            throw new ModelException("Date is empty");
        }
        this.date = date;
    }

    public void setDate(String date) {
        if (date.isEmpty()) {
            throw new ModelException("No date given");
        }
        this.date = Date.valueOf(date);
    }

    public Time getHour() {
        return hour;
    }

    public void setHour(Time hour) {
        if (hour == null) {
            throw new ModelException("Hour is empty");
        }
        this.hour = hour;
    }

    public void setHour(String hour) {
        if (hour.isEmpty()) {
            throw new ModelException("No hour given");
        }
        this.hour = Time.valueOf(hour+":00");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email.isEmpty()) {
            throw new ModelException("No email given");
        }
        String USERID_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(USERID_PATTERN);
        Matcher m = p.matcher(email);
        if (!m.matches()) {
            throw new ModelException("Email is not valid");
        }
        this.email = email;
    }

    public String getGsm() {
        return gsm;
    }

    public void setGsm(String gsm) {
        Pattern pattern = Pattern.compile("^[+]?[0-9]{10,13}");
        Matcher m = pattern.matcher(gsm);
        if (!m.matches()) {
            throw new ModelException("Phone number is not valid");
        }
        this.gsm = gsm;
    }

    @Override
    public String toString() {
        return "Contact: firstName='" + firstName + "', lastName='" + lastName + "', date=" + date + "', hour='" + hour + "', email='" + email + "', gsm='" + gsm + "'";
    }
}
