package com.cy.store.controller;

import com.cy.store.service.ex.*;
import com.cy.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;


public class BaseController {
    public static final int success = 200;

    @ExceptionHandler(ServiceException.class) // Handles exceptions for current class
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>();

        if(e instanceof UsernameDuplicatedException) {
            result.setState(4000);
            result.setMessage("Exception: username already taken");
        } else if(e instanceof UserNotFoundException) {
            result.setState(5001);
            result.setMessage("Exception: user not found");
        } else if(e instanceof PasswordNotMatchException) {
            result.setState(5002);
            result.setMessage("Exception: username/password not match");
        } else if(e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("Exception: error occurred during insertion");
        }
        return result;
    }

    /**
     * Get uid from session
     * @param session session object
     * @return uid of the login user
     */
    protected final Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * Get username from session
     * @param session session object
     * @return username of the login user
     */
    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }
}
