package ui.controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogIn extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Person person = null;
        for (Person p : service.getPersons()) {
            if (p.getUserid().toLowerCase().equals(request.getParameter("userid").toLowerCase())) {
                person = p;
                break;
            }
        }

        if (person != null && person.isCorrectPassword(request.getParameter("password")))
            request.getSession().setAttribute("person", person);
        else
            request.setAttribute("error", "No valid userid/password");
        return "index.jsp";
    }
}
