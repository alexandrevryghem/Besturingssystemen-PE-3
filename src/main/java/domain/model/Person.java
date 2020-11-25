package domain.model;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
    private String userid;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    public Person(String userid, String email, String hashedPassword, String firstName, String lastName) {
        setUserid(userid);
        setEmail(email);
        setAlreadyHashedPassword(hashedPassword);
        setFirstName(firstName);
        setLastName(lastName);
    }

    public Person() { }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        if (userid.isEmpty()) {
            throw new ModelException("No userid given");
        }
        this.userid = userid;
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
            throw new ModelException("Email not valid");
        }
        this.email = email;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isCorrectPassword(String password) {
        if (password.isEmpty()) {
            throw new ModelException("No password given");
        }
        try {
            return getPassword().equals(hashPassword(password));
        } catch (Exception e) {
            throw new ModelException("The given password cannot be hashed");
        }
    }

    public void setAlreadyHashedPassword(String password) {
        if (password.isEmpty()) {
            throw new ModelException("No password given");
        }
        this.password = password;
    }

    public void setUnhashedPassword(String password) {
        if (password.isEmpty()) {
            throw new ModelException("No password given");
        }
        try {
            this.password = hashPassword(password);
        } catch (Exception e) {
            throw new ModelException("The given password cannot be hashed");
        }
    }

    private String hashPassword(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //create MessageDigest
        MessageDigest crypt = MessageDigest.getInstance("SHA-512");
        //reset
        crypt.reset();
        //update
        byte[] passwordBytes = password.getBytes("UTF-8");
        crypt.update(passwordBytes);
        //digest
        byte[] digest = crypt.digest();
        //convert to String
        BigInteger digestAsBigInteger = new BigInteger(1, digest);
        //return hashed password
        return digestAsBigInteger.toString(16);
    }

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

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + ": " + getUserid() + ", " + getEmail();
    }
}
