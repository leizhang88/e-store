package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        String username = user.getUsername();
        // Check if the username is registerd
        User result = userMapper.findByUsername(username);
        if(result != null) {
            throw new UsernameDuplicatedException("Username already taken");
        }

        // Password encryption: md5
        String oldPassword = user.getPassword();
        String salt = UUID.randomUUID().toString().toUpperCase();
        // Save salt for authentication
        user.setSalt(salt);
        String md5Password = getMD5Password(oldPassword, salt);
        user.setPassword(md5Password);

        // Set values for new user records
        user.setIsDelete(0);
        user.setCreatedUser(username);
        user.setModifiedUser(username);
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        Integer rows = userMapper.insert(user);
        if(rows != 1) {
            throw new InsertException("Unknown error occurred during registration");
        }
    }

    @Override
    public User login(String username, String password) {
        User result = userMapper.findByUsername(username);

        // Check if user exists
        if(result == null) {
            throw new UserNotFoundException("User not found");
        }

        // Check if password match
        String salt = result.getSalt();
        String newMd5Password = getMD5Password(password, salt);
        if(!newMd5Password.equals(result.getPassword())) {
            throw new PasswordNotMatchException("Username/password not match");
        }

        // Check if username is deleted
        if(result.getIsDelete() == 1) {
            throw new UserNotFoundException("User not found");
        }

        // Return the user with needed properties only to accelerate data transmission
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());

        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("User not found");
        }

        // Authentication
        String oldMd5Password = getMD5Password(oldPassword, result.getSalt());
        if(!result.getPassword().equals(oldMd5Password)) {
            throw new PasswordNotMatchException("Wrong password");
        }

        // Update password
        String newMd5Password = getMD5Password(newPassword, result.getSalt());
        Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password, username, new Date());
        if(rows != 1) {
            throw new UpdateException("Error occurred when updating password");
        }
    }

    @Override
    public User getByUid(Integer uid) {
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("User not found");
        }

        // extract properties needed by the front-end and pass them
        // as a new user object
        User user = new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());

        return result;
    }

    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("User not found");
        }
        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());

        Integer rows = userMapper.updateInfoByUid(user);
        if(rows != 1) {
            throw new UpdateException("Error occurred while updating user info");
        }
    }

    @Override
    public void changeAvatar(Integer uid, String avatar, String username) {
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("User not found");
        }

        Integer rows = userMapper.updateAvatarByUid(uid, avatar, username, new Date());
        if(rows != 1) {
            throw new UpdateException("Error occurred while updating user avatar");
        }
    }

    // Encrypt password using md5
    private String getMD5Password(String password, String salt) {
        for(int i = 0; i < 3; i++)
            password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        return password;
    }
}
