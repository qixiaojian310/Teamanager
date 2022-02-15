package com.jian.service;

import com.jian.pojo.User;
import org.springframework.stereotype.Service;

@Service("signinUserDaoImpl")
public class SigninUserServiceImpl implements UserService {
    @Override
    public boolean ifUserExist(String username) {
        return false;
    }

    @Override
    public String getUserPwd(String username) {
        return null;
    }

    @Override
    public boolean insertNewUser(User user){return true;}
}
