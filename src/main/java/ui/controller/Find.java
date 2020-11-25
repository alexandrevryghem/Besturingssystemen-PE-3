package ui.controller;

import domain.model.Contact;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class Find extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String searchString = request.getParameter("searchString").trim();
        request.setAttribute("searchStringPreviousValue", searchString);
        try {
            List<Contact> contacts = service.findContacts(searchString);
            request.setAttribute("contacts", contacts);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "search.jsp";
    }
}
