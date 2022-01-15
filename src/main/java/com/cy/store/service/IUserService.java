package com.cy.store.service;

import com.cy.store.entity.User;

public interface IUserService {
    void reg(User user);

    User login(String username, String password);

    void changePassword(Integer uid, String username,
                        String oldPassword, String newPassword);

    User getByUid(Integer uid);

    /**
     * Change info of user of given uid according to the user object
     * by username
     * @param uid The user whose info will be changed
     * @param username The user who makes the change
     * @param user A user object where new values are stored
     */
    void changeInfo(Integer uid, String username, User user);

    /**
     * Change avatar of user of given uid by username to the given avatar
     * @param uid
     * @param avatar
     * @param username
     */
    void changeAvatar(Integer uid, String avatar, String username);
}
