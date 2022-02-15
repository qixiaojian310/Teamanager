package com.jian.service;

public interface UserService {
    public boolean ifUserExist(String username);
    public String getUserPwd(String username);
}
