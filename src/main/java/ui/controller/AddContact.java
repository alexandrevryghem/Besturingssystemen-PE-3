package ui.controller;

import domain.model.Contact;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AddContact extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Contact contact = new Contact();

        List<String> errors = new ArrayList<>();
        getFirstName(contact, request, errors);
        getLastName(contact, request, errors);
        getDate(contact, request, errors);
        getHour(contact, request, errors);
        getGsm(contact, request, errors);
        getEmail(contact, request, errors);

        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
        } else {
            service.addContact(contact);
        }
        return "Controller?action=ContactOverview";
    }

    private void getFirstName(Contact contact, HttpServletRequest request, List<String> errors) {
        String firstName = request.getParameter("firstName");
        request.setAttribute("firstNamePreviousValue", firstName);
        try {
            contact.setFirstName(firstName);
            request.setAttribute("firstNameClass", "has-success");
        } catch (Exception exc) {
            request.setAttribute("firstNameClass", "has-error");
            errors.add(exc.getMessage());
        }
    }

    private void getLastName(Contact contact, HttpServletRequest request, List<String> errors) {
        String lastName = request.getParameter("lastName");
        request.setAttribute("lastNamePreviousValue", lastName);
        try {
            contact.setLastName(lastName);
            request.setAttribute("lastNameClass", "has-success");
        } catch (Exception exc) {
            request.setAttribute("lastNameClass", "has-error");
            errors.add(exc.getMessage());
        }
    }

    private void getDate(Contact contact, HttpServletRequest request, List<String> errors) {
        String date = request.getParameter("date");
        request.setAttribute("datePreviousValue", date);
        try {
            contact.setDate(date);
            request.setAttribute("dateClass", "has-success");
        } catch (Exception exc) {
            request.setAttribute("dateClass", "has-error");
            errors.add(exc.getMessage());
        }
    }

    private void getHour(Contact contact, HttpServletRequest request, List<String> errors) {
        String hour = request.getParameter("hour");
        request.setAttribute("hourPreviousValue", hour);
        try {
            contact.setHour(hour);
            request.setAttribute("hourClass", "has-success");
        } catch (Exception exc) {
            request.setAttribute("hourClass", "has-error");
            errors.add(exc.getMessage());
        }
    }
    
    private void getEmail(Contact contact, HttpServletRequest request, List<String> errors) {
        String email = request.getParameter("email");
        request.setAttribute("emailPreviousValue", email);
        try {
            contact.setEmail(email);
            request.setAttribute("emailClass", "has-success");
        } catch (Exception exc) {
            request.setAttribute("emailClass", "has-error");
            errors.add(exc.getMessage());
        }
    }

    private void getGsm(Contact contact, HttpServletRequest request, List<String> errors) {
        String gsm = request.getParameter("gsm");
        request.setAttribute("gsmPreviousValue", gsm);
        try {
            contact.setGsm(gsm);
            request.setAttribute("gsmClass", "has-success");
        } catch (Exception exc) {
            request.setAttribute("gsmClass", "has-error");
            errors.add(exc.getMessage());
        }
    }
}
