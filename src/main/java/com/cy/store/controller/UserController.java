package com.cy.store.controller;

import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UsernameDuplicatedException;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // equals @Controller + @ResponseBody
@RequestMapping("users")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    @RequestMapping("reg")
    public JsonResult<Void> reg(User user) {
        userService.reg(user);
        return new JsonResult<>(success);
    }
    /*
    @RequestMapping("reg")
    public JsonResult<Void> reg(User user) {
        JsonResult<Void> result = new JsonResult<>();
        try {
            userService.reg(user);
            result.setState(200);
            result.setMessage("Success");
        } catch (UsernameDuplicatedException e) {
            result.setState(4000);
            result.setMessage("Username already taken");
        } catch (InsertException e) {
            result.setState(5000);
            result.setMessage("Error occurred during registration");
        }
        return result;
    }*/

    @RequestMapping("login")
    public JsonResult<User> login(String username, String password) {
        User user = userService.login(username, password);
        return new JsonResult<User>(success, user);
    }
}
