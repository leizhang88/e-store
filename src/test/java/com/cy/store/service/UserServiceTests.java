package com.cy.store.service;

import com.cy.store.entity.User;
import com.cy.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    private IUserService userService;

    @Test
    public void reg() {
        User user = new User();
        try {
            user.setUsername("fin");
            user.setPassword("123");
            userService.reg(user);
            System.out.println("OK");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void login() {
        User user = userService.login("louis", "123");
        System.out.println(user);
    }

    @Test
    public void changePassword() {
        userService.changePassword(10, "admin", "123", "321");
    }

    @Test
    public void getByUid() {
        System.out.println(userService.getByUid(10));
    }

    @Test
    public void updateInfoByUid() {
        User user = new User();
        user.setPhone("90091900000");
        user.setEmail("xxx@gmail.com");
        userService.changeInfo(10, "admin", user);
    }

    @Test
    public void changeAvatar() {
        userService.changeAvatar(11, "/upload/test.png", "admin");
    }
}
