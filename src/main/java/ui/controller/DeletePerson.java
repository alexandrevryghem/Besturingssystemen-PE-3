package ui.controller;

import domain.model.Person;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeletePerson extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Person person = (Person) request.getSession().getAttribute("person");

        service.deletePerson(person.getUserid());
        request.getSession().invalidate();

        return "index.jsp";
    }
}
