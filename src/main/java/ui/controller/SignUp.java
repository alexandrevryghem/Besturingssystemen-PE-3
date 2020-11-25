package ui.controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class SignUp extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Person person = new Person();

        List<String> errors = new ArrayList<>();
        getUserid(person, request, errors);
        getFirstName(person, request, errors);
        getLastName(person, request, errors);
        getEmail(person, request, errors);
        getPassword(person, request, errors);

        String destination = "Controller?action=Register";
        if (errors.size() == 0) {
            try {
                service.addPerson(person);
                destination = "Controller?action=Overview";
            } catch (Exception exc) {
                errors.add(exc.getMessage());
            }
        }
        if (errors.size() != 0){
            request.setAttribute("errors", errors);
        }
        return destination;
    }

    private void getUserid(Person person, HttpServletRequest request, List<String> errors) {
        String firstName = request.getParameter("userid");
        request.setAttribute("useridPreviousValue", firstName);
        try {
            person.setUserid(firstName);
            request.setAttribute("useridClass", "has-success");
        } catch (Exception exc) {
            request.setAttribute("useridClass", "has-error");
            errors.add(exc.getMessage());
        }
    }

    private void getFirstName(Person person, HttpServletRequest request, List<String> errors) {
        String firstName = request.getParameter("firstName");
        request.setAttribute("firstNamePreviousValue", firstName);
        try {
            person.setFirstName(firstName);
            request.setAttribute("firstNameClass", "has-success");
        } catch (Exception exc) {
            request.setAttribute("firstNameClass", "has-error");
            errors.add(exc.getMessage());
        }
    }

    private void getLastName(Person person, HttpServletRequest request, List<String> errors) {
        String lastName = request.getParameter("lastName");
        request.setAttribute("lastNamePreviousValue", lastName);
        try {
            person.setLastName(lastName);
            request.setAttribute("lastNameClass", "has-success");
        } catch (Exception exc) {
            request.setAttribute("lastNameClass", "has-error");
            errors.add(exc.getMessage());
        }
    }

    private void getEmail(Person person, HttpServletRequest request, List<String> errors) {
        String email = request.getParameter("email");
        request.setAttribute("emailPreviousValue", email);
        try {
            person.setEmail(email);
            request.setAttribute("emailClass", "has-success");
        } catch (Exception exc) {
            request.setAttribute("emailClass", "has-error");
            errors.add(exc.getMessage());
        }
    }

    private void getPassword(Person person, HttpServletRequest request, List<String> errors) {
        String password = request.getParameter("password");
        request.setAttribute("passwordPreviousValue", password);
        try {
            person.setUnhashedPassword(password);
            request.setAttribute("passwordClass", "has-success");
        } catch (Exception exc) {
            request.setAttribute("passwordClass", "has-error");
            errors.add(exc.getMessage());
        }
    }
}
