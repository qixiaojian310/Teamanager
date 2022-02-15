package com.jian.service;

import com.jian.pojo.User;

public interface UserService {
    public boolean ifUserExist(String username);
    public String getUserPwd(String username);
    public boolean insertNewUser(User user);
}
