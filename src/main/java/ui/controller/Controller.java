package ui.controller;

import domain.service.ContactTracingService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ContactTracingService service = new ContactTracingService();

    private HandlerFactory handlerFactory = new HandlerFactory();

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("action") == null ? null : request.getParameter("action").substring(0, 1).toUpperCase() + request.getParameter("action").substring(1);
        String destination = "index.jsp";
        if (command != null) {
            try {
                RequestHandler handler = handlerFactory.getHandler(command, service);
                destination = handler.handleRequest(request, response);
            } catch (Exception exc) {
                request.setAttribute("error", exc.getMessage());
            }
        }
        request.getRequestDispatcher(destination).forward(request, response);
    }
}
