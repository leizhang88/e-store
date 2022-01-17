package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.service.ex.*;
import com.cy.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;


public class BaseController {
    public static final int success = 200;

    @ExceptionHandler({ServiceException.class, FileUploadException.class}) // Handles exceptions for current class
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>();

        if (e instanceof UsernameDuplicatedException) {
            result.setState(4000);
            result.setMessage("Username already taken");
        } else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("Error occurred during insertion");
        } else if (e instanceof UserNotFoundException) {
            result.setState(5001);
            result.setMessage("User not found");
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(5002);
            result.setMessage("Wrong password");
        } else if (e instanceof UpdateException) {
            result.setState(5003);
            result.setMessage("Error occurred during password update");
        } else if (e instanceof AddressCountLimitException) {
            result.setState(5004);
            result.setMessage("Address number exceeds limit");
        } else if (e instanceof AddressNotFoundException) {
            result.setState(5005);
            result.setMessage("Address does not exist");
        } else if (e instanceof AccessDeniedException) {
            result.setState(5006);
            result.setMessage("Access denied");
        } else if (e instanceof DeleteException) {
            result.setState(5007);
            result.setMessage("Eerror occurred while deleting address");
        } else if (e instanceof FileEmptyException) {
            result.setState(6000);
            result.setMessage("Empty file");
        } else if (e instanceof FileSizeException) {
            result.setState(6001);
            result.setMessage("File size exceeds limit");
        } else if (e instanceof FileTypeException) {
            result.setState(6002);
            result.setMessage("File type not supported");
        } else if (e instanceof FileStateException) {
            result.setState(6003);
            result.setMessage("File state error");
        } else if (e instanceof FileUploadIOException) {
            result.setState(6004);
            result.setMessage("Error occurred while uploading file");
        }
        return result;
    }

    /**
     * Get uid from session
     * @param session session object
     * @return uid of the login user
     */
    protected final Integer getUidFromSession (HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * Get username from session
     * @param session session object
     * @return username of the login user
     */
    protected final String getUsernameFromSession (HttpSession session){
        return session.getAttribute("username").toString();
    }
}
