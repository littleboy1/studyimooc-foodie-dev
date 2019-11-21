package com.lzq.service;

import com.lzq.bo.UserBo;
import com.lzq.pojo.Users;

public interface UserService {

    boolean queryUserNameIsExist(String username);

    public Users createUser(UserBo userBo);
}
