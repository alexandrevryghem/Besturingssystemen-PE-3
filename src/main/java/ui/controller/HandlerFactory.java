package ui.controller;

import domain.service.ContactTracingService;

public class HandlerFactory {

    public RequestHandler getHandler(String handlerName, ContactTracingService service) {
        RequestHandler handler;
        try {
            Class handlerClass = Class.forName("ui.controller." + handlerName);
            Object handlerObject = handlerClass.getConstructor().newInstance();
            handler = (RequestHandler) handlerObject;
            handler.setModel(service);
        } catch (Exception e) {
            throw new RuntimeException("The requested page could not be found.");
        }

        return handler;
    }

}
